package com.gft.app.workflow.worker;

import com.gft.app.elw.IAction;
import com.gft.app.elw.utils.DateUtils;
import com.gft.app.workflow.request.DocumentMetadataRequest;
import com.gft.app.workflow.response.DocumentMetadataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentMetadataExecution implements IAction<DocumentMetadataRequest, DocumentMetadataResponse> {

    @Override
    public DocumentMetadataRequest requestModel() {
        return new DocumentMetadataRequest();
    }

    @Override
    public DocumentMetadataResponse run(DocumentMetadataRequest processParams) {
        log.info(processParams.toString());

        DocumentMetadataResponse response = new DocumentMetadataResponse();
        response.setStatus("");
        response.setUpdatedDate(DateUtils.datenow());
        return response;
    }
}
