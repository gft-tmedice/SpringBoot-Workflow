package com.gft.app.workflow.request;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeName("documentConfirmationRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DocumentConfirmationRequest extends BaseMessageRequest {

    private String routingKey;

    private String documentImageScore;
}
