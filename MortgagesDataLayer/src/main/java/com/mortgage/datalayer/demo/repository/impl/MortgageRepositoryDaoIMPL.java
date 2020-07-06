package com.mortgage.datalayer.demo.repository.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mortgage.datalayer.demo.dataresource.MortgageDatabase;
import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.model.MortgageEntity;
import com.mortgage.datalayer.demo.repository.MortgageRepositoryDao;

@Repository
public class MortgageRepositoryDaoIMPL implements MortgageRepositoryDao {

	/**
	 * find all entities with sort order if provided provided in request
	 *
	 * @param entity
	 * @return the entities
	 */
	public List<MortgageDto> findAll() {
		ConcurrentLinkedQueue<MortgageEntity> list = MortgageDatabase.getDataBaseRecords();
		return list.stream().map(dto -> {
			final MortgageDto respnseDTO = new MortgageDto();
			respnseDTO.setMortgageID(dto.getMortgageID());
			respnseDTO.setVersion(dto.getVersion());
			respnseDTO.setOfferID(dto.getOfferID());
			respnseDTO.setProductID(dto.getProductID());
			respnseDTO.setIsOfferExpired(dto.getIsOfferExpired());
			respnseDTO.setCreatedDate(dto.getCreatedDate());
			respnseDTO.setOfferDate(dto.getOfferDate());
			return respnseDTO;
		}).collect(Collectors.toList());
	}

	/**
	 * Saves an MortgageEntity
	 *
	 * @param entity
	 * @return the saved entity
	 */
	public void saveOrUpdate(MortgageEntity entity) {
		ConcurrentLinkedQueue<MortgageEntity> list = MortgageDatabase.getDataBaseRecords();
		List<MortgageEntity> filteredList = list.stream().filter(
				p -> p.getMortgageID().equals(entity.getMortgageID()) && p.getVersion().equals(entity.getVersion()))
				.collect(Collectors.toList());
		if (filteredList != null && filteredList.size() > 0) {
			final MortgageEntity updateEntity = filteredList.get(0);
			if (updateEntity != null && updateEntity.getVersion() == entity.getVersion()) {
				updateEntity.setOfferID(entity.getOfferID());
				updateEntity.setOfferDate(entity.getOfferDate());
				updateEntity.setProductID(entity.getProductID());
				updateEntity.setIsOfferExpired("N");
			}
		} else {
			list.add(entity);
		}

	}

	/**
	 * retrieve Max version for given MortgageId
	 */
	public Integer getMaxVersionByMorgageId(final String mortgageID) {
		ConcurrentLinkedQueue<MortgageEntity> mortgageList = MortgageDatabase.getDataBaseRecords();
		final Optional<MortgageEntity> maxVersionMortgage = mortgageList.stream()
				.filter(p -> p.getMortgageID().equals(mortgageID))
				.max(Comparator.comparingInt(MortgageEntity::getVersion));
		if (maxVersionMortgage.isPresent()) {
			return maxVersionMortgage.get().getVersion();
		} else {
			return 0;
		}
	}

	@Override
	/**
	 * updates Expiry flag in database if offer date is passed
	 */
	public void updateExpiryFlag() {
		ConcurrentLinkedQueue<MortgageEntity> mortgageList = MortgageDatabase.getDataBaseRecords();
		mortgageList.stream().filter(mortgageEntity -> mortgageEntity.getOfferDate().before(new Date()))
				.forEach(mortgageEntity -> mortgageEntity.setIsOfferExpired("Y"));
	}

}
