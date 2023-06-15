package com.gft.app.service;

import com.gft.app.constants.AppProcess;
import com.gft.app.elw.IProcessManager;
import com.gft.app.elw.MessageRequest;
import com.gft.app.elw.exceptions.*;
import com.gft.app.elw.model.EventResponse;
import com.gft.app.elw.model.ProcessInstance;
import com.gft.app.elw.model.UserContext;
import com.gft.app.elw.model.workflow.Step;
import com.gft.app.elw.utils.DateUtils;
import com.gft.app.server.model.Process;
import com.gft.app.workflow.request.BaseMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class WorkflowService {

    @Autowired
    IProcessManager iProcessManager;

    public Process startProcess() throws ProcessNotFound, ProcessCreationError {
        ProcessInstance processInstance = start(AppProcess.RECOGNITION_PROCESS.getProcess(), AppProcess.RECOGNITION_PROCESS.getVersion());

        Process process = convertModel(processInstance);
        return process;
    }

    public Process statusProcess(String uuid) throws ProcessNotFound {
        ProcessInstance processInstance = load(UUID.fromString(uuid));

        Process process = convertModel(processInstance);
        return process;
    }

    public com.gft.app.server.model.Step step(String uuid, String stepName) throws ProcessNotFound, EventNotFound {
        ProcessInstance processInstance = load(UUID.fromString(uuid));
        com.gft.app.elw.model.workflow.Step stepElw = step(processInstance, stepName);
        com.gft.app.server.model.Step step = new com.gft.app.server.model.Step();
        BeanUtils.copyProperties(stepElw, step);
        return step;
    }

    public EventResponse run(String process_uuid, String eventName, Map<String, BaseMessageRequest> payload) throws ProcessNotFound, ActionError, EventNotPermitted {
        Map<String, MessageRequest> messageRequest = new HashMap<>();

        for (Map.Entry<String, BaseMessageRequest> source : payload.entrySet())
            messageRequest.put(source.getKey(), source.getValue());

        EventResponse eventResponse = processEvent(UUID.fromString(process_uuid), eventName, messageRequest);
        return eventResponse;
    }

    public ProcessInstance start(String processName, String processVersion) throws ProcessNotFound, ProcessCreationError {
        UserContext userContext = new UserContext();
        return iProcessManager.start(processName, processVersion, userContext);
    }

    public EventResponse processEvent(UUID process_uuid, String eventName, Map<String, MessageRequest> messageRequest) throws ProcessNotFound, EventNotPermitted, ActionError {
        UserContext userContext = new UserContext();
        return iProcessManager.processEvent(process_uuid, eventName, messageRequest, userContext);
    }

    public com.gft.app.elw.model.workflow.Step step(ProcessInstance processInstance, String eventName) throws ProcessNotFound, EventNotFound {
        UserContext userContext = new UserContext();
        Step step = iProcessManager.getStep(processInstance, eventName, userContext);
        return step;
    }

    public ProcessInstance cancel(UUID process_uuid) throws ProcessNotFound, EventNotPermitted, ActionError {
        return iProcessManager.cancel(process_uuid);
    }

    public ProcessInstance end(UUID process_uuid) throws ProcessNotFound, EventNotPermitted, ActionError {
        return iProcessManager.end(process_uuid);
    }

    public ProcessInstance load(UUID process_uuid) throws ProcessNotFound {
        return iProcessManager.load(process_uuid);
    }

    public MessageRequest requestModel(String actionClass) {
        return iProcessManager.requestType(actionClass);
    }

    private Process convertModel(ProcessInstance processInstance) {
        Process process = new Process();
        process.setProcessId(processInstance.getProcessId());
        process.setProcessName(processInstance.getProcessName());
        process.setProcessVersion(processInstance.getProcessVersion());
        process.setProcessStatus(processInstance.getProcessStatus());
        process.setCurrentStep(processInstance.getCurrentStep());
        process.setParentProcessId(processInstance.getParentProcessId());
        process.setCreationTime(DateUtils.convert(processInstance.getCreationTime()));
        process.setCurrentStepEntryTime(DateUtils.convert(processInstance.getCurrentStepEntryTime()));
        process.setClosingTime(DateUtils.convert(processInstance.getClosingTime()));
        process.setUpdateTime(DateUtils.convert(processInstance.getUpdateTime()));
        return process;
    }

}
