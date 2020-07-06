package com.mortgage.datalayer.demo;

import static com.mortgage.datalayer.demo.constant.MortgageConstant.Date_FORMAT;
import com.mortgage.datalayer.demo.CustomTest;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.model.MortgageEntity;
import com.mortgage.datalayer.demo.repository.impl.MortgageRepositoryDaoIMPL;

public class DataBaseOperationsTest extends CustomTest {

	@InjectMocks
	MortgageRepositoryDaoIMPL dortgageRepositoryDaoIMPL;

	@Test(expected = Test.None.class /* no exception expected */)
	public void updateExpiryFlagTest() throws Exception {
		dortgageRepositoryDaoIMPL.updateExpiryFlag();
	}

	public void MaxVersionByMorgageIdTest() throws Exception {
		Integer max = dortgageRepositoryDaoIMPL.getMaxVersionByMorgageId("M3");
		assertTrue(max == 3);
	}

	public void saveTest() throws Exception {
		List<MortgageDto> oldDTOS = dortgageRepositoryDaoIMPL.findAll();
		int initialSize = oldDTOS.size();
		MortgageEntity entity = new MortgageEntity("M8", 3, "OI-1", "B1", Date_FORMAT.parse("20/04/2020"),
				Date_FORMAT.parse("14/03/2019"), "N");
		dortgageRepositoryDaoIMPL.saveOrUpdate(entity);
		List<MortgageDto> newDTOS = dortgageRepositoryDaoIMPL.findAll();
		int newSize = newDTOS.size();
		assertTrue(initialSize != newSize);
	}

	@Test(expected = Test.None.class /* no exception expected */)
	public void UpdateTest() throws Exception {

		List<MortgageDto> oldDTOS = dortgageRepositoryDaoIMPL.findAll();
		int initialSize = oldDTOS.size();
		MortgageEntity entity = new MortgageEntity("M8", 6, "OI-1", "B1", Date_FORMAT.parse("20/04/2021"),
				Date_FORMAT.parse("14/03/2019"), "N");
		dortgageRepositoryDaoIMPL.saveOrUpdate(entity);
		List<MortgageDto> newDTOS = dortgageRepositoryDaoIMPL.findAll();
		int newSize = newDTOS.size();
		assertTrue(initialSize != newSize);
		entity = new MortgageEntity("M8", 3, "OI-1", "B12", Date_FORMAT.parse("20/04/2021"),
				Date_FORMAT.parse("14/03/2019"), "N");
		dortgageRepositoryDaoIMPL.saveOrUpdate(entity);
		int newestSize = newDTOS.size();
		assertTrue(newestSize == newSize);
	}

	public void getTest() throws Exception {
		List<MortgageDto> oldDTOS = dortgageRepositoryDaoIMPL.findAll();
		int initialSize = oldDTOS.size();
		assertTrue(initialSize != 0);
	}
}
