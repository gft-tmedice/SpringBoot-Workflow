package com.gft.app.workflow.request;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeName("documentMetadataRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DocumentMetadataRequest extends BaseMessageRequest {

    private String documentType;
    private String documentNumber;

}
