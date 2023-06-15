package com.gft.app.workflow.request;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeName("conformityCheckRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ConformityCheckRequest extends BaseMessageRequest {

    private String routingKey;
}
