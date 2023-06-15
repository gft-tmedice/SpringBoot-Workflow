package com.gft.app.workflow.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessRequest implements Serializable {

    private Map<String, BaseMessageRequest> payload;

}
