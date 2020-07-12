package com.mortgage.businesslayer.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generated.CreateMortgageResponse;
import com.mortgage.businesslayer.demo.aop.TrackTime;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesConsumerResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;
import com.mortgage.businesslayer.demo.service.MortgageService;
import static com.mortgage.businesslayer.demo.constant.MortgageConstant.*;

@RestController
@RequestMapping("/MortgagesBusiness")
@CrossOrigin(origins = "*", maxAge = 3600)
/**
 * This class represents rest controller class for mortgage business layer
 * 
 * @author bhagyesh
 *
 */
public class MortgageBusinessLayerController {
	private static final String CREATE_MORTGAGE = "createMortgage";
	@Autowired
	MortgageService service;

	/**
	 * This Rest controller method is used to receive get Mortgage request from
	 * consumer and pass it on to service components and send details back to
	 * consumer post request processing
	 * 
	 * @param orderBy -String
	 * @return GetAllMortgagesResponseDto List of mortgages
	 * @throws MortgageBusinessException
	 */
	@GetMapping
	@TrackTime
	public ResponseEntity<GetAllMortgagesConsumerResponse> getAllMortgages(final @RequestParam(ORDER_BY) String orderBy)
			throws MortgageBusinessException {
		final GetAllMortgagesConsumerResponse mortgageResponseDto = service.getAllMortgages(orderBy);
		return new ResponseEntity<GetAllMortgagesConsumerResponse>(mortgageResponseDto, new HttpHeaders(),
				HttpStatus.OK);
	}

	/**
	 * This Rest controller method is used to receive create mortgage request from
	 * consumer and pass it on to service components ,performs request validation 
	 * 
	 * @param mortgageDto
	 * @return
	 * @throws MortgageBusinessException
	 */
	@PostMapping(CREATE_MORTGAGE)
	@TrackTime
	public ResponseEntity<CreateMortgageResponse> createMortgage(final @Valid @RequestBody MortgageDto mortgageDto)
			throws MortgageBusinessException {
		final CreateMortgageResponse status = service.createMortgage(mortgageDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<CreateMortgageResponse>(status, new HttpHeaders(), HttpStatus.CREATED);
	}

}