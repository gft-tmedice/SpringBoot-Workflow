package com.gft.app.elw;

import com.gft.app.elw.constants.ApplicationConstants;
import com.gft.app.elw.constants.ProcessStatus;
import com.gft.app.elw.exceptions.*;
import com.gft.app.elw.model.EventHistory;
import com.gft.app.elw.model.EventResponse;
import com.gft.app.elw.model.ProcessInstance;
import com.gft.app.elw.model.UserContext;
import com.gft.app.elw.model.workflow.Action;
import com.gft.app.elw.model.workflow.Event;
import com.gft.app.elw.model.workflow.Step;
import com.gft.app.elw.model.workflow.Workflowschema;
import com.gft.app.elw.service.ProcessService;
import com.gft.app.elw.utils.DateUtils;
import com.gft.app.elw.utils.FileUtils;
import com.gft.app.elw.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ProcessManager implements IProcessManager {

    @Autowired
    ProcessService processService;

    @Autowired
    Map<String, IAction> actions;

    @Override
    public ProcessInstance start(String processFileName, String processVersion, UserContext userContext) throws ProcessNotFound, ProcessCreationError {

        Workflowschema schema = getSchema(processFileName);

        String processUuid = UUID.randomUUID().toString();

        EventHistory eventHistory = new EventHistory();
        eventHistory.setDestinationStep(schema.getStartActivity());
        eventHistory.setEventName(schema.getProcessName());
        eventHistory.setEventTime(DateUtils.datenow());
        eventHistory.setEventUser(userContext.getUserName());
        eventHistory.setProcessId(processUuid);
        eventHistory = processService.save(eventHistory);
        log.info(eventHistory.toString());

        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setClosingTime(DateUtils.datenow());
        processInstance.setCreationTime(DateUtils.datenow());
        processInstance.setCurrentStep(schema.getStartActivity());
        processInstance.setProcessStatus(ProcessStatus.OPEN.toString());
        processInstance.setCurrentStepEntryTime(DateUtils.datenow());
        processInstance.setProcessId(processUuid);
        processInstance.setProcessName(schema.getProcessName());
        processInstance.setProcessFileName(processFileName);
        processInstance.setProcessVersion(schema.getVersion());
        processInstance.setUpdateTime(DateUtils.datenow());
        processInstance = processService.save(processInstance);
        log.info(processInstance.toString());

        return processInstance;
    }

    @Override
    public Step getStep(ProcessInstance processInstance, String stepName, UserContext userContext) throws EventNotFound {
        Workflowschema schema = getSchema(processInstance.getProcessFileName());
        Optional<Step> step = schema.getWorkflow().getSteps().stream().filter(source -> source.getName().equalsIgnoreCase(stepName)).findFirst();

        if (!step.isPresent())
            throw new EventNotFound(MessageFormat.format(ApplicationConstants.ENTITY_NOT_FOUND_BY_PROPERTY, stepName));

        return step.get();
    }

    @Override
    public EventResponse processEvent(UUID processUuid, String eventName, Map<String, MessageRequest> request, UserContext userContext) throws ProcessNotFound, EventNotPermitted, ActionError {

        Optional<ProcessInstance> processInstance = processService.findProcessInstanceById(processUuid);
        if (processInstance.get() == null)
            throw new ProcessNotFound(MessageFormat.format(ApplicationConstants.ENTITY_NOT_FOUND, processUuid.toString()));

        log.info(processInstance.get().toString());

        if (!processInstance.get().getProcessStatus().equalsIgnoreCase(ProcessStatus.OPEN.toString()))
            throw new EventNotPermitted(MessageFormat.format(ApplicationConstants.EVENT_NOT_PERMITED, processInstance.get().getProcessStatus()));

        Workflowschema schema = getSchema(processInstance.get().getProcessFileName());

        Optional<Step> step = schema.getWorkflow().getSteps().stream().filter(source -> source.getName().equalsIgnoreCase(processInstance.get().getCurrentStep())).findFirst();
        Step stepModel = step.get();
        log.info("Step-model : {}", stepModel.toString());
        log.info("EventName : {} ", eventName);

        Optional<Event> event = stepModel.getEvents().stream().filter(source -> source.getName().equalsIgnoreCase(eventName)).findFirst();
        if (!event.isPresent())
            throw new EventNotPermitted(MessageFormat.format(ApplicationConstants.EVENT_NOT_PERMITED, processInstance.get().getProcessStatus()));

        log.info("Event : {} ", event.get().toString());

        EventResponse eventResponse = new EventResponse();
        eventResponse.setCurrStep(event.get().getDestination());


        if (event.get().getActions() != null && !CollectionUtils.isEmpty(event.get().getActions()))
            for (Action action : event.get().getActions()) {
                log.info(MessageFormat.format(ApplicationConstants.RUNNING_METHOD_BY_FLOW, action.getActionClass(), action.getActionName(), request));

                IAction triggerAction = actions.get(action.getActionClass());
                MessageResponse messageResponse = (MessageResponse) triggerAction.run(request.get(action.getActionName()));
                eventResponse.getActionsResponse().put(action.getActionName(), messageResponse);
            }

        ProcessInstance processInstanceEntity = processInstance.get();
        processInstanceEntity.setCurrentStep(event.get().getDestination());
        processInstanceEntity.setUpdateTime(DateUtils.datenow());
        processService.save(processInstanceEntity);

        EventHistory eventHistoryEntity = new EventHistory();
        eventHistoryEntity.setProcessId(processUuid.toString());
        eventHistoryEntity.setEventName(eventName);
        eventHistoryEntity.setEventTime(DateUtils.datenow());
        eventHistoryEntity.setDestinationStep(event.get().getDestination());
        processService.save(eventHistoryEntity);

        return eventResponse;
    }

    @Override
    public ProcessInstance end(UUID processUuid) throws ProcessNotFound, EventNotPermitted, ActionError {

        Optional<ProcessInstance> processInstance = processService.findProcessInstanceById(processUuid);
        if (processInstance.get() == null)
            throw new ProcessNotFound(MessageFormat.format(ApplicationConstants.ENTITY_NOT_FOUND, processUuid.toString()));

        if (!processInstance.get().getProcessStatus().equalsIgnoreCase(ProcessStatus.OPEN.toString()))
            throw new EventNotPermitted(MessageFormat.format(ApplicationConstants.EVENT_NOT_PERMITED, processInstance.get().getProcessStatus()));

        ProcessInstance processInstanceEntity = processInstance.get();
        processInstanceEntity.setClosingTime(DateUtils.datenow());
        processInstanceEntity.setUpdateTime(DateUtils.datenow());
        processInstanceEntity.setProcessStatus(ProcessStatus.CLOSED.toString());
        processService.save(processInstanceEntity);

        EventHistory eventHistoryEntity = new EventHistory();
        eventHistoryEntity.setProcessId(processUuid.toString());
        eventHistoryEntity.setEventName(ProcessStatus.CLOSED.toString());
        eventHistoryEntity.setEventTime(DateUtils.datenow());
        processService.save(eventHistoryEntity);

        return processInstance.get();
    }

    @Override
    public ProcessInstance cancel(UUID processUuid) throws ProcessNotFound, EventNotPermitted, ActionError {

        Optional<ProcessInstance> processInstance = processService.findProcessInstanceById(processUuid);
        if (processInstance.get() == null)
            throw new ProcessNotFound(MessageFormat.format(ApplicationConstants.ENTITY_NOT_FOUND, processUuid.toString()));

        if (!processInstance.get().getProcessStatus().equalsIgnoreCase(ProcessStatus.OPEN.toString()))
            throw new EventNotPermitted(MessageFormat.format(ApplicationConstants.EVENT_NOT_PERMITED, processInstance.get().getProcessStatus()));

        ProcessInstance processInstanceEntity = processInstance.get();
        processInstanceEntity.setClosingTime(DateUtils.datenow());
        processInstanceEntity.setUpdateTime(DateUtils.datenow());
        processInstanceEntity.setProcessStatus(ProcessStatus.CANCELED.toString());
        processService.save(processInstanceEntity);

        EventHistory eventHistoryEntity = new EventHistory();
        eventHistoryEntity.setProcessId(processUuid.toString());
        eventHistoryEntity.setEventName(ProcessStatus.CANCELED.toString());
        eventHistoryEntity.setEventTime(DateUtils.datenow());
        eventHistoryEntity.setDestinationStep(null);
        processService.save(eventHistoryEntity);

        return processInstance.get();
    }

    @Override
    public ProcessInstance load(UUID processUuid) throws ProcessNotFound {
        Optional<ProcessInstance> processInstance = processService.findProcessInstanceById(processUuid);
        if (processInstance.get() == null)
            throw new ProcessNotFound(MessageFormat.format(ApplicationConstants.ENTITY_NOT_FOUND, processUuid.toString()));

        return processInstance.get();
    }

    @Override
    public MessageRequest requestType(String actionClass) {
        IAction triggerAction = actions.get(actionClass);
        return (MessageRequest) triggerAction.requestModel();
    }

    private Workflowschema getSchema(String processFileName) {
        InputStream schemaStream = FileUtils.getFileAsIOStream(processFileName);
        Workflowschema Schema = JsonUtils.jsonStringToObject(schemaStream, Workflowschema.class);
        return Schema;
    }

}
