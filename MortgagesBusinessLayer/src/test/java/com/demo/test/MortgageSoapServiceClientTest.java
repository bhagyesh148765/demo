/*
 * package com.demo.test;
 * 
 * import static org.hamcrest.CoreMatchers.any; import static
 * org.junit.Assert.assertEquals; import static org.mockito.Mockito.when;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.MockitoAnnotations; import
 * org.springframework.ws.client.core.WebServiceTemplate;
 * 
 * import com.demo.CustomTest; import
 * com.generated.GetMaxVersionByMortgageIDRequest; import
 * com.generated.GetMaxVersionByMortgageIDResponse; import
 * com.mortgage.businesslayer.demo.soapservice.soapclient.
 * MortgageSoapServiceClient;
 * 
 * public class MortgageSoapServiceClientTest extends CustomTest {
 * 
 * private MortgageSoapServiceClient client;
 * 
 * @Mock WebServiceTemplate webServiceTemplate;
 * 
 * 
 * public void setUp() { MockitoAnnotations.initMocks(this);
 * client.setWebServiceTemplate(webServiceTemplate);
 * GetMaxVersionByMortgageIDResponse response = new
 * GetMaxVersionByMortgageIDResponse(); response.setMaxVersionID(5);
 * when(webServiceTemplate.marshalSendAndReceive(any(
 * GetMaxVersionByMortgageIDRequest.class))) .thenReturn(response); }
 * 
 * 
 * public void testGetResponse() {
 * client.setWebServiceTemplate(webServiceTemplate);
 * GetMaxVersionByMortgageIDRequest maxVersionRequest = new
 * GetMaxVersionByMortgageIDRequest(); maxVersionRequest.setMortgageIDReq("M1");
 * GetMaxVersionByMortgageIDResponse response =
 * client.getmaxVersionByMorgageIDSoapCall(maxVersionRequest);
 * assertEquals(response.getMaxVersionID(), 5); }
 * 
 * }
 */