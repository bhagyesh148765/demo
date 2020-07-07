package com.mortgage.businesslayer.demo.restservice.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.generated.GetMaxVersionByMortgageIDResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;
import com.mortgage.businesslayer.demo.exception.MortgageBusinessException;

@Component
public class MortgageRestServiceClient {

	private static final Logger log = LoggerFactory.getLogger(MortgageRestServiceClient.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${restURLGetMaxVersion}")
	private String restURLGetMaxVersion;
	@Value("${restURLGetAllMorgages}")
	private String restURLGetAllMorgages;
	@Value("${restURLCreateMorgages}")
	private String restURLCreateMorgages;

	/**
	 * Rest client call to fetch max version number
	 * 
	 * @param mortgageID
	 * @return
	 * @throws MortgageBusinessException
	 */
	public Integer getmaxVersionByMorgageIDRestCall(final String mortgageID) throws MortgageBusinessException {
		try {
			log.info("entering into getmaxVersionByMorgageIDRestCall method ");
			final String uri = restURLGetMaxVersion + mortgageID;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<GetMaxVersionByMortgageIDResponse> responseEntity = restTemplate.exchange(uri,
					HttpMethod.GET, entity, GetMaxVersionByMortgageIDResponse.class);
			GetMaxVersionByMortgageIDResponse maxVersionDataLayerReponseDto = responseEntity.getBody();
			log.info("exiting from getmaxVersionByMorgageIDRestCall method ");
			return maxVersionDataLayerReponseDto.getMaxVersion();
		} catch (Exception e) {
			log.error("error occured during backend communication" + e.getMessage() + e.getCause());
			throw new MortgageBusinessException("Communication Error", e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Rest cleint call to get all mortgsges details
	 * 
	 * @param sortOrder
	 * @return
	 * @throws MortgageBusinessException
	 */
	public GetAllMortgagesBkndRestResponse getAllMortgagesRestCall(final String sortOrder)
			throws MortgageBusinessException {
		log.info("entering into getAllMortgagesRestCall method ");
		try {
			final String uri = restURLGetAllMorgages + sortOrder;
			Map<String, String> params = new HashMap<String, String>();
			params.put("orderBy", "createdDate");
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<GetAllMortgagesBkndRestResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
					entity, GetAllMortgagesBkndRestResponse.class);
			GetAllMortgagesBkndRestResponse mortgageResponseDto = responseEntity.getBody();
			log.info("exiting from getmaxVersionByMorgageIDRestCall method ");
			return mortgageResponseDto;
		} catch (Exception e) {
			log.error("error occured during backend communication in getAllMortgagesRestCall method" + e.getMessage()
					+ e.getCause());
			throw new MortgageBusinessException("Communication Error", e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Rest servicecall to create new mortgage in database
	 * 
	 * @param reqEntity
	 * @return String status of operation
	 * @throws MortgageBusinessException
	 */
	public String createMortgageRestCall(final MortgageDto reqEntity) throws MortgageBusinessException {
		try {
			final String posturi = restURLCreateMorgages;
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<?> request = new HttpEntity<>(reqEntity, headers);
			ResponseEntity<String> status = restTemplate.postForEntity(posturi, request, String.class);
			System.out.println("responseEntityPost " + status.getBody());
			return status.getBody();
		} catch (Exception e) {
			log.error("error occured during backend communication in createMortgageRestCall method" + e.getMessage()
					+ e.getCause());
			throw new MortgageBusinessException("Communication Error", e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
