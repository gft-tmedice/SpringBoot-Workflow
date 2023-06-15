package com.gft.app.workflow.response;

import com.gft.app.elw.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcrResponse implements MessageResponse {

    private String status;
}
