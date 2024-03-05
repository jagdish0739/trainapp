package com.local24.trainapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.local24.trainapp.dao.TrainRepository;
import com.local24.trainapp.exception.ResourceNotFoundException;
import com.local24.trainapp.model.Train;
import com.local24.trainapp.service.TrainService;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Override
	public Train addTrain(Train train) {
		return trainRepository.save(train);
	}

	@Override
	public Train updateTrain(Train train) {

		Optional<Train> trainDb = this.trainRepository.findById(train.getNumber());

		if (trainDb.isPresent()) {
			Train trainUpdate = trainDb.get();
			trainUpdate.setNumber(train.getNumber());
			trainUpdate.setName(train.getName());
			trainUpdate.setStations(train.getStations());
			trainRepository.save(trainUpdate);
			return trainUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with train number : " + train.getNumber());
		}
	}

	@Override
	public List<String> getTrain(String sourceStation, String destinationStation) {
		
		return this.trainRepository.findAllTrainsBySourceAndDestination(sourceStation, destinationStation);
		
	}

	@Override
	public void deleteTrain(long number) {

		Optional<Train> trainDb = this.trainRepository.findById(number);

		if (trainDb.isPresent()) {
			  this.trainRepository.delete(trainDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with train number : " + number);
        }
	}

}
