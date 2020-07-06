/*
 * package com.demo.test;
 * 
 * import javax.xml.bind.JAXBElement; import javax.xml.transform.Source;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.Mock; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.context.ApplicationContext; import
 * org.springframework.core.io.Resource; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * org.springframework.test.web.client.RequestMatcher; import
 * org.springframework.test.web.client.ResponseCreator; import
 * org.springframework.ws.client.core.WebServiceTemplate; import
 * org.springframework.ws.test.client.MockWebServiceServer; import
 * org.springframework.ws.test.client.RequestMatchers; import
 * org.springframework.ws.test.client.ResponseCreators; import
 * org.springframework.xml.transform.StringSource;
 * 
 * import com.generated.GetMaxVersionByMortgageIDRequest; import
 * com.generated.GetMaxVersionByMortgageIDResponse; import
 * com.mortgage.businesslayer.demo.BusinessLayerDemoApplicationMain; import
 * com.mortgage.businesslayer.demo.exception.MortgageBusinessException; import
 * com.mortgage.businesslayer.demo.soapservice.soapclient.
 * MortgageSoapServiceClient;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @SpringBootTest(classes = BusinessLayerDemoApplicationMain.class) public
 * class MortgageSoapServiceClientTest {
 * 
 * @Autowired private MortgageSoapServiceClient client;
 * 
 * @Autowired ApplicationContext applicationContext;
 * 
 * @Mock WebServiceTemplate webServiceTemplate;
 * 
 * @Mock JAXBElement jaxBElement; private MockWebServiceServer mockServer;
 * 
 * @Before public void setUp() { //MockitoAnnotations.initMocks(this);
 * GetMaxVersionByMortgageIDResponse response = new
 * GetMaxVersionByMortgageIDResponse(); response.setMaxVersion(5);
 * //when(webServiceTemplate.marshalSendAndReceive(any(JAXBElement.class))).
 * thenReturn(jaxBElement); //MockitoAnnotations.initMocks(this); mockServer =
 * MockWebServiceServer.createServer(applicationContext); }
 * 
 * 
 * 
 * @Test public void testGetResponse() throws MortgageBusinessException {
 * 
 * 
 * Source expectedRequestPayload = new StringSource(
 * "<ns3:getMaxVersionByMortgageIDRequest xmlns:ns2='http://www.barclays.org/mortgage/'>"
 * + "<ns2:mortgageIDReq>m3</ns3:mortgageIDReq>" +
 * "</ns3:getMaxVersionByMortgageIDRequest>"); Source responsePayload = new
 * StringSource(
 * "<getMaxVersionByMortgageIDResponse  xmlns='http://www.barclays.org/mortgage/'>"
 * + "<maxVersion>10</maxVersion>" + "</getMaxVersionByMortgageIDResponse>");
 * mockServer.expect(RequestMatchers.payload(expectedRequestPayload)).andRespond
 * (ResponseCreators.withPayload(responsePayload));
 * 
 * 
 * GetMaxVersionByMortgageIDRequest maxVersionRequest = new
 * GetMaxVersionByMortgageIDRequest(); maxVersionRequest.setMortgageIDReq("m3");
 * GetMaxVersionByMortgageIDResponse response1 =
 * client.getmaxVersionByMorgageIDSoapCall(maxVersionRequest);
 * System.out.println(response1);
 * 
 * }
 * 
 * }
 */