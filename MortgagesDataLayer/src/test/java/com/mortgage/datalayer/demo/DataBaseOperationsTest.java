package com.mortgage.datalayer.demo;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.mortgage.datalayer.demo.repository.impl.MortgageRepositoryDaoIMPL;

public class MortgageRepositoryDaoIMPLTest extends CustomTest {

	@InjectMocks
	MortgageRepositoryDaoIMPL dortgageRepositoryDaoIMPL;

	@Test(expected = Test.None.class /* no exception expected */)
	public void updateExpiryFlag() throws Exception {
		dortgageRepositoryDaoIMPL.updateExpiryFlag();
	}

}
