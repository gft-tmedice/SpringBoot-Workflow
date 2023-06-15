package com.gft.app.resource;

import com.gft.app.elw.MessageRequest;
import com.gft.app.elw.exceptions.*;
import com.gft.app.elw.model.EventResponse;
import com.gft.app.server.api.WorkflowApi;
import com.gft.app.server.model.Error;
import com.gft.app.server.model.Process;
import com.gft.app.server.model.Step;
import com.gft.app.service.WorkflowService;
import com.gft.app.workflow.request.ProcessRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RestController
public class WorkflowController implements WorkflowApi {

    @Autowired
    WorkflowService workflowService;

    @Override
    public ResponseEntity<Process> startProcessUsingGET() {
        try {
            Process process = workflowService.startProcess();
            return new ResponseEntity(process, HttpStatus.OK);

        } catch (ProcessNotFound | ProcessCreationError ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Step> stepByUuidAndEventNameUsingGET(String uuid, String stepName) {
        try {
            Step step = workflowService.step(uuid, stepName);
            return new ResponseEntity(step, HttpStatus.OK);

        } catch (ProcessNotFound | EventNotFound ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @ApiOperation(value = "", nickname = "runProcess", notes = "", tags = {"Workflow",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Process.", response = Process.class),
            @ApiResponse(code = 400, message = "Process couldn't have been runned."),
            @ApiResponse(code = 500, message = "An unexpected error occured.", response = Error.class)})
    @RequestMapping(value = "/runProcess/{uuid}/{eventName}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<EventResponse> runProcess(@ApiParam(value = "UUID", required = true) @PathVariable("uuid") String process_uuid,
                                                    @ApiParam(value = "the event name", required = true) @PathVariable("eventName") String eventName,
                                                    @ApiParam(value = "", required = true) @Valid @RequestBody ProcessRequest processRequest) {
        try {
            EventResponse response = workflowService.run(process_uuid, eventName, processRequest.getPayload());
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (ProcessNotFound | ActionError | EventNotPermitted ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @ApiOperation(value = "", nickname = "requestModel", notes = "", tags = {"Workflow",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Process.", response = MessageRequest.class),
            @ApiResponse(code = 500, message = "An unexpected error occured.", response = Error.class)})
    @RequestMapping(value = "/model/{actionClass}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<MessageRequest> requestModel(@ApiParam(value = "actionClass", required = true) @PathVariable("actionClass") String actionClass) {
        MessageRequest model = workflowService.requestModel(actionClass);
        return new ResponseEntity(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Process> statusProcessByUuidUsingGET(String uuid) {

        try {
            Process process = workflowService.statusProcess(uuid);
            return new ResponseEntity(process, HttpStatus.OK);
        } catch (ProcessNotFound ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> cancelProcessByUuidUsingGET(String uuid) {
        return null;
    }

    @Override
    public ResponseEntity<Void> endProcessByUuidUsingGET(String uuid) {
        return null;
    }
}
