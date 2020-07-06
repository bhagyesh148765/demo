package com.mortgage.datalayer.demo;

import static com.mortgage.datalayer.demo.constant.MortgageConstant.Date_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.generated.CreateMortgageRequest;
import com.generated.CreateMortgageResponse;
import com.generated.GetMaxVersionByMortgageIDRequest;
import com.generated.GetMaxVersionByMortgageIDResponse;
import com.generated.GetMortgagesRequest;
import com.generated.GetMortgagesResponse;
import com.generated.MortgageDtoType;
import com.mortgage.datalayer.demo.dto.MortgageDto;
import com.mortgage.datalayer.demo.model.MortgageEntity;
import com.mortgage.datalayer.demo.repository.MortgageRepositoryDao;
import com.mortgage.datalayer.demo.soapservice.endpoint.MortgagesSoapServiceEndpoint;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataLayerDemoApplicationMain.class)
public class SOAPServiceEndpointTest {

	@Autowired
	private MortgagesSoapServiceEndpoint mortgagesSoapServiceEndpoint;

	@Mock
	private MortgageRepositoryDao mortgageRepositoryDaoMock;

	@Value(value = "classpath:SoapRequests/getAllMortgagesByCreatedDate.xml")
	private Resource getAllMortgagesByCreatedDateXml;
	@Value(value = "classpath:SoapRequests/getAllMortgagesByOfferDate.xml")
	private Resource getAllMortgagesByOfferDate;
	@Value(value = "classpath:SoapRequests/getAllMortgagesBySomeRandom.xml")
	private Resource getAllMortgagesBySomeRandom;

	@Value(value = "classpath:SoapRequests/createMorgage.xml")
	private Resource createMorgage;
	@Value(value = "classpath:SoapRequests/getMaxVersionNumber.xml")
	private Resource getMaxVersionNumber;
	@Value(value = "classpath:SoapRequests/getMaxVersionNumberforNonAvailalble.xml")
	private Resource getMaxVersionNumberforNonAvailalble;

	@Test
	public void getAllMortgagesByCreatedDateTest() throws Exception {
		SOAPMessage message = MessageFactory.newInstance().createMessage(null,
				getAllMortgagesByCreatedDateXml.getInputStream());
		JAXBContext jaxbContext = JAXBContext.newInstance(GetMortgagesRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GetMortgagesRequest getMortgagesRequest = (GetMortgagesRequest) jaxbUnmarshaller
				.unmarshal(message.getSOAPBody().extractContentAsDocument());

		GetMortgagesResponse getMortgagesResponse = mortgagesSoapServiceEndpoint.getAllMortgages(getMortgagesRequest);
		assertNotNull(getMortgagesResponse);

		MortgageDtoType mortgageDto = getMortgagesResponse.getMortgageDto().get(0);
		assertEquals(mortgageDto.getMortgageID(), "M1");
		assertEquals(mortgageDto.getVersion(), 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(xmlGregorianCalendartoDate(mortgageDto.getCreatedDate())), "20/04/2015");
		assertEquals(sdf.format(xmlGregorianCalendartoDate(mortgageDto.getOfferDate())), "20/07/2017");

	}

	@Test
	public void getAllMortgagesByOfferDateTest() throws Exception {
		SOAPMessage message = MessageFactory.newInstance().createMessage(null,
				getAllMortgagesByOfferDate.getInputStream());
		JAXBContext jaxbContext = JAXBContext.newInstance(GetMortgagesRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GetMortgagesRequest getMortgagesRequest = (GetMortgagesRequest) jaxbUnmarshaller
				.unmarshal(message.getSOAPBody().extractContentAsDocument());
		GetMortgagesResponse getMortgagesResponse = mortgagesSoapServiceEndpoint.getAllMortgages(getMortgagesRequest);
		assertNotNull(getMortgagesResponse);
		MortgageDtoType mortgageDto = getMortgagesResponse.getMortgageDto().get(0);
		assertEquals(mortgageDto.getMortgageID(), "M2");
		assertEquals(mortgageDto.getVersion(), 2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(sdf.format(xmlGregorianCalendartoDate(mortgageDto.getCreatedDate())), "20/05/2015");
		assertEquals(sdf.format(xmlGregorianCalendartoDate(mortgageDto.getOfferDate())), "20/06/2017");

	}

	@Test
	public void getAllMortgagesByRandomTest() throws Exception {
		SOAPMessage message = MessageFactory.newInstance().createMessage(null,
				getAllMortgagesByOfferDate.getInputStream());
		JAXBContext jaxbContext = JAXBContext.newInstance(GetMortgagesRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GetMortgagesRequest getMortgagesRequest = (GetMortgagesRequest) jaxbUnmarshaller
				.unmarshal(message.getSOAPBody().extractContentAsDocument());
		GetMortgagesResponse getMortgagesResponse = mortgagesSoapServiceEndpoint.getAllMortgages(getMortgagesRequest);
		assertNotNull(getMortgagesResponse);

	}

	@Test
	public void getMaxNumberVersionByMortgaGeIDTest() throws Exception {
		SOAPMessage message = MessageFactory.newInstance().createMessage(null, getMaxVersionNumber.getInputStream());
		JAXBContext jaxbContext = JAXBContext.newInstance(GetMaxVersionByMortgageIDRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GetMaxVersionByMortgageIDRequest getMaxVersionByMortgageIDRequest = (GetMaxVersionByMortgageIDRequest) jaxbUnmarshaller
				.unmarshal(message.getSOAPBody().extractContentAsDocument());
		GetMaxVersionByMortgageIDResponse getMaxVersionByMortgageIDResponse = mortgagesSoapServiceEndpoint
				.getMaxVersionByMortgageID(getMaxVersionByMortgageIDRequest);
		assertNotNull(getMaxVersionByMortgageIDResponse);
		assertEquals(getMaxVersionByMortgageIDResponse.getMaxVersion(), 3);

	}

	@Test
	public void getMaxNumberVersionForNonAvailableTest() throws Exception {
		SOAPMessage message = MessageFactory.newInstance().createMessage(null,
				getMaxVersionNumberforNonAvailalble.getInputStream());
		JAXBContext jaxbContext = JAXBContext.newInstance(GetMaxVersionByMortgageIDRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GetMaxVersionByMortgageIDRequest getMaxVersionByMortgageIDRequest = (GetMaxVersionByMortgageIDRequest) jaxbUnmarshaller
				.unmarshal(message.getSOAPBody().extractContentAsDocument());
		GetMaxVersionByMortgageIDResponse getMaxVersionByMortgageIDResponse = mortgagesSoapServiceEndpoint
				.getMaxVersionByMortgageID(getMaxVersionByMortgageIDRequest);
		assertNotNull(getMaxVersionByMortgageIDResponse);
		assertEquals(getMaxVersionByMortgageIDResponse.getMaxVersion(), 0);

	}

	@Test
	public void createMortgageTest() throws Exception {
		SOAPMessage message = MessageFactory.newInstance().createMessage(null, createMorgage.getInputStream());
		JAXBContext jaxbContext = JAXBContext.newInstance(CreateMortgageRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		CreateMortgageRequest createMortgageRequest = (CreateMortgageRequest) jaxbUnmarshaller
				.unmarshal(message.getSOAPBody().extractContentAsDocument());
		CreateMortgageResponse createMortgageResponse = mortgagesSoapServiceEndpoint
				.createMorgage(createMortgageRequest);
		assertNotNull(createMortgageResponse);
		assertEquals(createMortgageResponse.getStatus(), "Success");
		
	}

	/**
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 * 
	 * @param XMLGregorianCalendar calendar
	 * @return Date
	 */
	public static Date xmlGregorianCalendartoDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}
}