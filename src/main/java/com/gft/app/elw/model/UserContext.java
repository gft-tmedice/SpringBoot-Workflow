package com.gft.app.elw.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContext {

	private String userName;

	private Map<String, UserContextAttributes> additionalProperties;
}