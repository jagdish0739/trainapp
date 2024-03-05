package com.local24.trainapp.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local24.trainapp.model.Train;
import com.local24.trainapp.service.TrainService;

/*
 * Controller class to handle all the REST calls
 */
@RestController
public class TrainResource {

	@Autowired
	private TrainService trainService;
	
	@PostMapping("/train")
    public ResponseEntity < Train > createTrain(@RequestBody Train train) {
        return ResponseEntity.ok().body(this.trainService.addTrain(train));
    }
	
	@PutMapping("/train")
    public ResponseEntity < Train > updateTrain(@RequestBody Train train) {
        return ResponseEntity.ok().body(this.trainService.updateTrain(train));
    }
	
	@GetMapping("/train")
    public ResponseEntity < List<String> > getTrain(@RequestParam("sourceStation") String sourceStation, @RequestParam("destinationStation") String destinationStation) {
        return ResponseEntity.ok().body(this.trainService.getTrain(sourceStation, destinationStation));
    }
	
	@DeleteMapping("/train/{number}")
    public ResponseEntity < String > deleteTrain(@PathVariable long number ) {
		this.trainService.deleteTrain(number);
        return ResponseEntity.ok().body("Train data deleted successfully");
    }
}
