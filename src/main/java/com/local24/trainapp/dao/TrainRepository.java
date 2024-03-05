package com.local24.trainapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.local24.trainapp.model.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long>{
	
	@Query("SELECT t.name FROM Train t WHERE :source IN elements(t.stations) AND :destination IN elements(t.stations)")
    List<String> findAllTrainsBySourceAndDestination(String source, String destination);

}
