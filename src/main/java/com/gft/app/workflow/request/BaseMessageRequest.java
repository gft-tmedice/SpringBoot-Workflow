package com.gft.app.workflow.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gft.app.elw.MessageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OcrRequest.class, name = "ocrRequest"),
        @JsonSubTypes.Type(value = ConformityCheckRequest.class, name = "conformityCheckRequest"),
        @JsonSubTypes.Type(value = DocumentConfirmationRequest.class, name = "documentConfirmationRequest"),
        @JsonSubTypes.Type(value = DocumentMetadataRequest.class, name = "documentMetadataRequest"),
        @JsonSubTypes.Type(value = FaceMatchRequest.class, name = "faceMatchRequest")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseMessageRequest implements MessageRequest {

    public String processId;

}
