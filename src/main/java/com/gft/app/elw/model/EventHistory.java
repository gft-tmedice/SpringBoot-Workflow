package com.gft.app.elw.model;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_history")
@IdClass(EventHistoryId.class)
public class EventHistory {

	@Id
	@Column(name = "process_id", nullable = false)
	private String processId;

	@Column(name = "event_name", nullable = false)
	private String eventName;

	@Column(name = "event_time", nullable = false)
	private Date eventTime;

	@Column(name = "destination_step")
	private String destinationStep;

	@Column(name = "event_user")
	private String eventUser;

	@Column(name = "event_note")
	private String eventNote;

}
