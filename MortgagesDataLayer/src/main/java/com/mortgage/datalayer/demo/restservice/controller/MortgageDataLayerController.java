package com.mortgage.datalayer.demo.restservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generated.GetMaxVersionByMortgageIDResponse;
import com.mortgage.datalayer.demo.dto.CreateMortgageRestRequest;
import com.mortgage.datalayer.demo.dto.GetMortgagesRestResponse;
import com.mortgage.datalayer.demo.restservice.resquesthandler.MortgageRestRequestHandler;

@RestController
@RequestMapping("/Mortgages")
public class MortgageDataLayerController {
	@Autowired
	MortgageRestRequestHandler restRequestHandler;

	@GetMapping
	public ResponseEntity<GetMortgagesRestResponse> getAllMortgages(@RequestParam("orderBy") String orderBy) {
		GetMortgagesRestResponse mortgageResponseDto = restRequestHandler.getAllMortgages(orderBy);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<GetMortgagesRestResponse>(mortgageResponseDto, httpHeaders, HttpStatus.OK);
	}

	@PostMapping("createMortgage")
	public ResponseEntity<String> createMortgage(@Valid @RequestBody CreateMortgageRestRequest createMortgageInputRequest) {
		String status = restRequestHandler.createMortgage(createMortgageInputRequest);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("getMaxVersion")
	public ResponseEntity<GetMaxVersionByMortgageIDResponse> getAllMaxVersionByMortgageID(
			@RequestParam("mortgageID") String mortgageID) {
		GetMaxVersionByMortgageIDResponse maxVersionDto = restRequestHandler.getMaxVersionByMortgageID(mortgageID);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<GetMaxVersionByMortgageIDResponse>(maxVersionDto, httpHeaders, HttpStatus.OK);
	}

}