package com.local24.trainapp.service;

import java.util.List;

import com.local24.trainapp.model.Train;

public interface TrainService {

	Train addTrain(Train train);

	Train updateTrain(Train train);

	List<String> getTrain(String sourceStation, String destinationStation);

	void deleteTrain(long number);

}
