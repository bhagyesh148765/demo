package com.mortgage.businesslayer.demo.restservice.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.generated.GetMaxVersionByMortgageIDResponse;
import com.mortgage.businesslayer.demo.dto.GetAllMortgagesBkndRestResponse;
import com.mortgage.businesslayer.demo.dto.MortgageDto;

@Component
public class MortgageRestServiceClient {

	@Autowired
	RestTemplate restTemplate;

	public Integer getmaxVersionByMorgageIDRestCall(final String mortgageID) {

		final String uri = "https://localhost:8080/MortgageDataLayer/Mortgages/getMaxVersion?mortgageID=" + mortgageID;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<GetMaxVersionByMortgageIDResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
				entity, GetMaxVersionByMortgageIDResponse.class);
		System.out.println("responseEntity " + responseEntity);
		GetMaxVersionByMortgageIDResponse maxVersionDataLayerReponseDto = responseEntity.getBody();
		System.out.println("maxVersion" + maxVersionDataLayerReponseDto.getMaxVersion());
		return maxVersionDataLayerReponseDto.getMaxVersion();

	}

	public GetAllMortgagesBkndRestResponse getAllMortgagesRestCall(final String sortOrder) {
		try {
			final String uri = "https://localhost:8080/MortgageDataLayer/Mortgages?orderBy=" + sortOrder;
			Map<String, String> params = new HashMap<String, String>();
			params.put("orderBy", "createdDate");
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<GetAllMortgagesBkndRestResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
					entity, GetAllMortgagesBkndRestResponse.class);
			GetAllMortgagesBkndRestResponse mortgageResponseDto = responseEntity.getBody();
			return mortgageResponseDto;
		} catch (HttpStatusCodeException e) {
			System.out.println("issue with backend ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("issue with backend ");
			e.printStackTrace();
			
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			
		}
		
		return null;
	}

	/**
	 * Rest servicecall to create new mortgage in database
	 * 
	 * @param reqEntity
	 * @return String status of operation
	 */
	public String createMortgageRestCall(final MortgageDto reqEntity) {
		final String posturi = "https://localhost:8080/MortgageDataLayer/Mortgages/createMortgage";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> request = new HttpEntity<>(reqEntity, headers);
		ResponseEntity<String> status = restTemplate.postForEntity(posturi, request, String.class);
		System.out.println("responseEntityPost " + status.getBody());
		return status.getBody();
	}

}
