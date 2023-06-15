package com.gft.app.elw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "process_instance")
public class ProcessInstance {

	@Id
	@Column(name = "process_id", nullable = false)
	private String processId;

	@Column(name = "process_name", nullable = false)
	private String processName;

	@Column(name = "process_File_Name", nullable = false)
	private String processFileName;

	@Column(name = "process_version", nullable = false)
	private String processVersion;

	@Column(name = "process_status", nullable = false)
	private String processStatus;

	@Column(name = "current_step", nullable = false)
	private String currentStep;

	@Column(name = "parent_process_id")
	private String parentProcessId;

	@Column(name = "creation_time", nullable = false)
	private Date creationTime;

	@Column(name = "current_step_entry_time", nullable = false)
	private Date currentStepEntryTime;

	@Column(name = "closing_time", nullable = false)
	private Date closingTime;

	@Column(name = "update_time", nullable = false)
	private Date updateTime;

}
