package com.mortgage.businesslayer.demo.controller;

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

import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.service.MortgageService;

@RestController
@RequestMapping("/MortgagesBusiness")
public class MortgageBusinessLayerController {
	@Autowired
	MortgageService service;

	/**
	 * This controller method is used to receive getmortgages request from consumer
	 * and pass it on to service components and send details back to consumer post request processing 
	 * 
	 * @param orderBy
	 * @return GetAllMortgagesResponseDto List of mortgages
	 */
	@GetMapping
	public ResponseEntity<GetAllMortgagesConsumerResponse> getAllMortgages(@RequestParam("orderBy") String orderBy) {
		GetAllMortgagesConsumerResponse mortgageResponseDto = service.getAllMortgages(orderBy);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<GetAllMortgagesConsumerResponse>(mortgageResponseDto, httpHeaders, HttpStatus.OK);
	}

	/**
	 * This controller method is used to receive create mortgage request from
	 * consumer and pass it on to service components
	 * 
	 * @param mortgageDto
	 * @return
	 */
	@PostMapping("createMortgage")
	public ResponseEntity<String> createMortgage(@Valid @RequestBody MortgageDto mortgageDto) {

		String status = service.createMortgage(mortgageDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.CREATED);
	}

}