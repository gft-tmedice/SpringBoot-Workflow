package com.gft.app.workflow.response;


import com.gft.app.elw.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceMatchResponse implements MessageResponse {

    private String status;
    private String thresholdScore;

}
