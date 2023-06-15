package com.gft.app.workflow.worker;


import com.gft.app.elw.IAction;
import com.gft.app.workflow.request.FaceMatchRequest;
import com.gft.app.workflow.response.FaceMatchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FaceMatchExecution implements IAction<FaceMatchRequest, FaceMatchResponse> {

    @Override
    public FaceMatchRequest requestModel() {
        return new FaceMatchRequest();
    }

    @Override
    public FaceMatchResponse run(FaceMatchRequest processParams) {
        log.info(processParams.toString());

        FaceMatchResponse response = new FaceMatchResponse();
        response.setStatus("");
        response.setThresholdScore("");
        return response;
    }
}
