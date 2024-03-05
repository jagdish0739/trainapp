package com.local24.trainapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.local24.trainapp.dao.TrainRepository;
import com.local24.trainapp.exception.ResourceNotFoundException;
import com.local24.trainapp.model.Train;
/*
 * Test class for TrainServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class TestTrainServiceImpl {

	@InjectMocks
	private TrainServiceImpl trainService;
	
	@Mock
	private TrainRepository trainRepository;
	
	@Test
	public void testAddTrain() {
		Train train = getTrain();
		when(trainRepository.save(any(Train.class))).thenReturn(train);
		Train tr = trainService.addTrain(train);
		assertEquals(12345, tr.getNumber());
		assertNotEquals(123, tr.getNumber());
	}

	@Test
	public void testUpdateTrain() {
		Optional<Train> train = Optional.of(new Train());
		train.get().setNumber(1345);
		train.get().setName("DURONTO EXPRESS");
		when(trainRepository.findById(any(Long.class))).thenReturn(train);
		when(trainRepository.save(any(Train.class))).thenReturn(train.get());
		Train tr = trainService.updateTrain(train.get());
		assertEquals(1345, tr.getNumber());
		assertNotEquals(123, tr.getNumber());

	}

	@Test
	public void testFindTrain() {
		List<String> trains = new ArrayList<>();
		trains.add("DURONTO");
		when(trainRepository.findAllTrainsBySourceAndDestination(any(String.class), any(String.class))).thenReturn(trains);
		List<String> trns = trainService.getTrain("HWH", "SBC");
		assertEquals(1, trns.size());
	}

	@Test
	public void testDeleteTrain() {
		Train train = getTrain();
		try {
	    trainService.deleteTrain(12345);
	    verify(trainRepository, times(1)).delete(train);
		}catch (ResourceNotFoundException e) {
			assertTrue(e.getLocalizedMessage().contains("Record not found with train number"));
		}
	}

	private Train getTrain() {
		Train train = new Train();
		train.setNumber(12345);
		train.setName("DURONTO EXPRESS");
		return train;
	}

}
