/*
 * package com.demo.test;
 * 
 * import static org.hamcrest.CoreMatchers.any; import static
 * org.junit.Assert.assertEquals; import static
 * org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.when;
 * 
 * import org.junit.Test; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.Mockito; import
 * org.mockito.MockitoAnnotations; import org.springframework.http.HttpEntity;
 * import org.springframework.http.HttpMethod; import
 * org.springframework.ws.client.core.WebServiceTemplate; import static
 * org.mockito.ArgumentMatchers.any; import com.demo.CustomTest; import
 * com.generated.GetMaxVersionByMortgageIDRequest; import
 * com.generated.GetMaxVersionByMortgageIDResponse; import
 * com.mortgage.businesslayer.demo.exception.MortgageBusinessException; import
 * com.mortgage.businesslayer.demo.soapservice.soapclient.
 * MortgageSoapServiceClient;
 * 
 * public class MortgageSoapServiceClientTest extends CustomTest {
 * 
 * @InjectMocks private MortgageSoapServiceClient client;
 * 
 * @Mock WebServiceTemplate webServiceTemplate;
 * 
 * public void setUp() { MockitoAnnotations.initMocks(this);
 * client.setWebServiceTemplate(webServiceTemplate);
 * GetMaxVersionByMortgageIDResponse response = new
 * GetMaxVersionByMortgageIDResponse();
 * 
 * 
 * } Mockito.when(service.getAllMortgages(any(String.class))).thenReturn(
 * getAllMortgagesResponseDto);
 * 
 * @Test public void testGetResponse() throws MortgageBusinessException {
 * client.setWebServiceTemplate(webServiceTemplate);
 * GetMaxVersionByMortgageIDResponse response = new
 * GetMaxVersionByMortgageIDResponse(); response.setMaxVersion(5);
 * when(webServiceTemplate.marshalSendAndReceive(any(
 * GetMaxVersionByMortgageIDRequest.class))) .thenReturn(response);
 * GetMaxVersionByMortgageIDRequest maxVersionRequest = new
 * GetMaxVersionByMortgageIDRequest(); maxVersionRequest.setMortgageIDReq("M1");
 * GetMaxVersionByMortgageIDResponse response1 =
 * client.getmaxVersionByMorgageIDSoapCall(maxVersionRequest);
 * System.out.println(response1);
 * 
 * }
 * 
 * }
 */