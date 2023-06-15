package com.gft.app.elw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventHistoryId implements Serializable {

	private String processId;
	private String eventName;
	private Date eventTime;
}
