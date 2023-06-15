package com.gft.app.workflow.worker;

import com.gft.app.elw.IAction;
import com.gft.app.workflow.request.OcrRequest;
import com.gft.app.workflow.response.OcrResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OcrExecution implements IAction<OcrRequest, OcrResponse> {

    @Override
    public OcrRequest requestModel() {
        return new OcrRequest();
    }

    @Override
    public OcrResponse run(OcrRequest processParams) {
        log.info(processParams.toString());

        OcrResponse response = new OcrResponse();
        response.setStatus("");
        return response ;
    }
}
