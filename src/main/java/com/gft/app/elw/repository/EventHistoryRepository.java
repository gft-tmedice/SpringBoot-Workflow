package com.gft.app.elw.repository;

import com.gft.app.elw.model.EventHistoryId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gft.app.elw.model.EventHistory;

@Repository
public interface EventHistoryRepository extends PagingAndSortingRepository<EventHistory, EventHistoryId> {

}
