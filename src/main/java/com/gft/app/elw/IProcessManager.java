package com.gft.app.elw;

import java.util.Map;
import java.util.UUID;

import com.gft.app.elw.exceptions.*;
import com.gft.app.elw.model.EventResponse;
import com.gft.app.elw.model.ProcessInstance;
import com.gft.app.elw.model.UserContext;
import com.gft.app.elw.model.workflow.Step;

public interface IProcessManager {

    public ProcessInstance start(String processName, String processVersion, UserContext userContext) throws ProcessNotFound, ProcessCreationError;

    public Step getStep(ProcessInstance processInstance, String stepName, UserContext userContext) throws ProcessNotFound, EventNotFound;

    public EventResponse processEvent(UUID process_uuid, String eventName, Map<String, MessageRequest> request, UserContext userContext) throws ProcessNotFound, EventNotPermitted, ActionError;

    public ProcessInstance end(UUID process_uuid) throws ProcessNotFound, EventNotPermitted, ActionError;

    public ProcessInstance cancel(UUID process_uuid) throws ProcessNotFound, EventNotPermitted, ActionError;

    public ProcessInstance load(UUID process_uuid) throws ProcessNotFound;

    public MessageRequest requestType(String actionClass)  ;

}
