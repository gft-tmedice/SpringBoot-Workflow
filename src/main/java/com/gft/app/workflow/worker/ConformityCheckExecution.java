package com.gft.app.workflow.worker;

import com.gft.app.elw.IAction;
import com.gft.app.elw.utils.DateUtils;
import com.gft.app.workflow.request.ConformityCheckRequest;
import com.gft.app.workflow.response.ConformityCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConformityCheckExecution implements IAction<ConformityCheckRequest, ConformityCheckResponse> {

    @Override
    public ConformityCheckRequest requestModel() {
        return new ConformityCheckRequest();
    }

    @Override
    public ConformityCheckResponse run(ConformityCheckRequest processParams) {
        log.info(processParams.toString());

        ConformityCheckResponse response = new ConformityCheckResponse();
        response.setStatus("");
        response.setCreationDate(DateUtils.datenow());
        response.setCategory("");
        return response;
    }
}
