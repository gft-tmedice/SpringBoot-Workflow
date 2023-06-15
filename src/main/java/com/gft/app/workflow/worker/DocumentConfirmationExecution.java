package com.gft.app.workflow.worker;

import com.gft.app.elw.IAction;
import com.gft.app.elw.utils.DateUtils;
import com.gft.app.workflow.request.DocumentConfirmationRequest;
import com.gft.app.workflow.response.DocumentConfirmationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentConfirmationExecution implements IAction<DocumentConfirmationRequest, DocumentConfirmationResponse> {

    @Override
    public DocumentConfirmationRequest requestModel() {
        return new DocumentConfirmationRequest();
    }

    @Override
    public DocumentConfirmationResponse run(DocumentConfirmationRequest processParams) {
        log.info(processParams.toString());

        DocumentConfirmationResponse response = new DocumentConfirmationResponse();
        response.setStatus("");
        response.setCreationDate(DateUtils.datenow());
        response.setScore("");
        return response;
    }
}
