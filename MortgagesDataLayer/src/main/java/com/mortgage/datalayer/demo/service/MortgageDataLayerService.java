package com.mortgage.datalayer.demo.service;

import static com.mortgage.datalayer.demo.constant.MortgageConstant.CREATED_DATE;
import static com.mortgage.datalayer.demo.constant.MortgageConstant.OFFER_DATE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.datalayer.demo.dataresource.MortgageDatabase;
import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.model.MortgageEntity;
import com.mortgage.datalayer.demo.repository.MortgageRepositoryDao;

/**
 * 
 * @author bhagyesh
 *
 */
@Service
//S@Validated
public class MortgageDataLayerService {

	private static final Logger log = LoggerFactory.getLogger(MortgageDataLayerService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	MortgageRepositoryDao repository;

	/**
	 * 
	 * @param sortOrder
	 * @return
	 */
	public List<MortgageDto> getAllMortgages(String sortOrder) {
		log.info("getAllMortgages method execution  START  at ", dateFormat.format(new Date()));
		List<MortgageDto> mortgageList = repository.findAll(sortOrder);
		Comparator<MortgageDto> comparator = null;
		switch (sortOrder) {
		case CREATED_DATE:
			comparator = Comparator.comparing(MortgageDto::getCreatedDate);
			break;
		case OFFER_DATE:
			comparator = Comparator.comparing(MortgageDto::getOfferDate);
			break;
		}
		if (comparator != null) {
			return mortgageList.stream().sorted(comparator).collect(Collectors.toList());
		}
		System.out.println(mortgageList);
		log.info("getAllMortgages method execution  END  at ", dateFormat.format(new Date()));
		return mortgageList;

	}

	/**
	 * 
	 * @param reqEntity
	 * @return
	 */
	public String createMortgage(final MortgageDto reqEntity) {
		MortgageEntity newEntity = new MortgageEntity();
		newEntity.setMortgageID(reqEntity.getMortgageID());
		newEntity.setProductID(reqEntity.getProductID());
		newEntity.setVersion(reqEntity.getVersion());
		newEntity.setCreatedDate(new Date());
		newEntity.setOfferID(reqEntity.getOfferID());
		newEntity.setOfferDate(reqEntity.getOfferDate());
		newEntity.setIsOfferExpired("N");
		repository.saveOrUpdate(newEntity);
		return "Mortgage creation is Successfull";
	}

	/**
	 * This service layer method interacts with Repository to get max version for
	 * mortgageID
	 * 
	 * @param mortgageId
	 * @return Integer max version
	 */
	public Integer getMaxVersionByMortgageID(String mortgageId) {
		return repository.getMaxVersionByMorgageId(mortgageId);

	}

}