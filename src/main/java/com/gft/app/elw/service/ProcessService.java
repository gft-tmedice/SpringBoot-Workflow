package com.gft.app.elw.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.app.elw.model.EventHistory;
import com.gft.app.elw.model.ProcessInstance;
import com.gft.app.elw.repository.EventHistoryRepository;
import com.gft.app.elw.repository.ProcessInstanceRepository;

@Service
public class ProcessService {

	@Autowired
	ProcessInstanceRepository processInstanceRepository;

	@Autowired
	EventHistoryRepository eventHistoryRepository;

	public ProcessInstance save(ProcessInstance entity) {
		return processInstanceRepository.save(entity);
	}

	public EventHistory save(EventHistory entity) {
		return eventHistoryRepository.save(entity);
	}

	public Optional<ProcessInstance> findProcessInstanceById(UUID processUuid) {
		Optional<ProcessInstance> entity = processInstanceRepository.findById(processUuid.toString());
		return entity;
	}

}