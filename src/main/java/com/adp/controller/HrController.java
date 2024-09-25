package com.adp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.model.HRDetails;
import com.adp.service.HrService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/hr")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class HrController {
	private HrService hrService;

	public HrController(HrService hrService) {
		super();
		this.hrService = hrService;
	}
	
	@GetMapping
	public ResponseEntity<List<HRDetails>> getAll(){
		List<HRDetails> hrDetails= hrService.getAllHrs();
		return ResponseEntity.ok(hrDetails);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HRDetails> getById(@PathVariable int id){
		HRDetails hrDetail = hrService.getByHrId(id);
		return ResponseEntity.ok(hrDetail);
	}
	
	@PostMapping
	public ResponseEntity<String> addHrDetails(@Valid @RequestBody HRDetails hrDetails){
		String result = hrService.addHrDetail(hrDetails);
		return ResponseEntity.ok(result);
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateHrDetail(@PathVariable int id, @RequestBody HRDetails hrDetails){
		String result = hrService.updateHrDetail(id, hrDetails);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteHrDetail(@PathVariable int id){
		String result = hrService.deleteHrDetail(id);
		return ResponseEntity.ok(result);
	}
	
	
}
