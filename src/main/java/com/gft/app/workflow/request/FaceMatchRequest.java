package com.gft.app.workflow.request;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeName("faceMatchRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FaceMatchRequest extends BaseMessageRequest {

    private String serialNumber;

}
