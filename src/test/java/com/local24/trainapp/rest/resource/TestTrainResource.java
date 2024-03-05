package com.local24.trainapp.rest.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.local24.trainapp.model.Train;
import com.local24.trainapp.service.TrainService;

/*
 * Test class for controller
 */
@ExtendWith(MockitoExtension.class)
public class TestTrainResource {

	@InjectMocks
	private TrainResource trainResource;

	@Mock
	private TrainService trainService;

	@Test
	public void testAddTrain() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Train train = new Train();
		train.setNumber(12345);
		train.setName("DURONTO EXPRESS");
		when(trainService.addTrain(any(Train.class))).thenReturn(train);
		ResponseEntity<Train> responseEntity = trainResource.createTrain(train);
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	public void testUpdateTrain() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Train train = new Train();
		train.setNumber(12345);
		train.setName("DURONTO EXPRESS");
		when(trainService.updateTrain(any(Train.class))).thenReturn(train);
		ResponseEntity<Train> responseEntity = trainResource.updateTrain(train);
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	/**
	 * Test method for negative scenario where train does not exist
	 */
	@Test
	public void testUpdateTrain_1() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Train train = new Train();
		train.setNumber(12345);
		train.setName("DURONTO EXPRESS");
		when(trainService.updateTrain(any(Train.class))).thenReturn(train);
		ResponseEntity<Train> responseEntity = null;
		try {
			responseEntity = trainResource.updateTrain(train);
		} catch (Exception e) {
			assertEquals(500, responseEntity.getStatusCode().value());
		}
	}

	@Test
	public void testFindTrain() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<String> trains = new ArrayList<>();
		trains.add("DURONTO");
		when(trainService.getTrain(any(String.class), any(String.class))).thenReturn(trains);
		ResponseEntity<List<String>> responseEntity = trainResource.getTrain("HWH", "SBC");
		assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	public void testDeleteTrain() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		doNothing().when(trainService).deleteTrain(any(Long.class));
		ResponseEntity<String> responseEntity = trainResource.deleteTrain(12345);
		assertEquals(200, responseEntity.getStatusCode().value());
	}

}
