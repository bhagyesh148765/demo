package com.mortgage.datalayer.demo.dataresource;

import static com.mortgage.datalayer.demo.constant.MortgageConstant.Date_FORMAT;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.mortgage.datalayer.demo.model.MortgageEntity;

public class MortgageDatabase {

	private static ConcurrentLinkedQueue<MortgageEntity> mortgageDataBaseRecords = new ConcurrentLinkedQueue<MortgageEntity>();

	static {
		initializeDataBase();
	}

	private static void initializeDataBase() {
		try {
			mortgageDataBaseRecords.add(new MortgageEntity("M2", 3, "OI-1", "B1", Date_FORMAT.parse("20/04/2020"),
					Date_FORMAT.parse("14/03/2017"), "N"));
			mortgageDataBaseRecords.add(new MortgageEntity("M1", 1, "OI-1", "B1", Date_FORMAT.parse("20/07/2017"),
					Date_FORMAT.parse("20/04/2015"), "N"));
			mortgageDataBaseRecords.add(new MortgageEntity("M2", 2, "OI-2", "B1", Date_FORMAT.parse("20/06/2017"),
					Date_FORMAT.parse("20/05/2015"), "N"));
			mortgageDataBaseRecords
					.add(new MortgageEntity("M3", 3, "OI-3", "B2", Date_FORMAT.parse("21/05/2021"), new Date(), "N"));
		} catch (Exception e) {
		}
	}

	/**
	 * @return the dataBaseRecords
	 */
	public static ConcurrentLinkedQueue<MortgageEntity> getDataBaseRecords() {
		return mortgageDataBaseRecords;
	}

	synchronized public static void addNewMortgage(MortgageEntity entity) {
		mortgageDataBaseRecords.add(entity);
	}
}
