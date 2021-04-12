package com.buy.watches.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy.watches.model.Watch;

@Repository
public interface WatchRepo extends JpaRepository<Watch, Integer> {

	Watch findByWatchid(String watchid);

}
