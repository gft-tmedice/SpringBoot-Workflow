package com.gft.app.elw.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gft.app.elw.model.ProcessInstance;

@Repository
public interface ProcessInstanceRepository extends PagingAndSortingRepository<ProcessInstance, String> {

}
