package com.mortgage.datalayer.demo.repository;

import java.util.List;

import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.model.MortgageEntity;

public interface MortgageRepositoryDao {

	/**
	 * find all entities with sort order
	 *
	 * @param entity
	 * @return the entities
	 */
	List<MortgageDto> findAll(String sort);

	/**
	 * Saves an entity
	 *
	 * @param entity
	 * @return the saved entity
	 */
	void saveOrUpdate(MortgageEntity entity);

	/**
	 * gets max version of mortgage
	 *
	 * @param entity
	 * @return the Integer entity
	 */

	Integer getMaxVersionByMorgageId(String mortgageID);

	/**
	 * updates expiry flag to Y where offered date is expired
	 *
	 * @param entity
	 * @return the Integer entity
	 */
	void updateExpiryFlag();

}
