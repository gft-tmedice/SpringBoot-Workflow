package com.gft.app.elw.model;

import com.gft.app.elw.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private String currStep;

    private Map<String, MessageResponse> actionsResponse = new HashMap<>();

}