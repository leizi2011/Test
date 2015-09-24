package com.cmcc.inter.mock;

/**
 * @author iversoncl
 * @Date 2015年8月3日
 * @Project InterfaceFramework
 */

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.testng.ITest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cmcc.inter.http.util.HttpRequest;
import com.cmcc.inter.http.util.HttpResponseDao;
import com.cmcc.inter.http.util.RequestDataUtil;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jayway.restassured.response.Response;

public class  MockTest implements ITest{
    
    private WireMockServer wireMockServer;
    
    @Override
    public String getTestName() {
      return "Mock Test";
    }
    
    public MockTest() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8090));
        WireMock.configureFor("localhost", 8090);
        wireMockServer.start();  
    }
    
    @BeforeTest
    public void stubRequests() {
      stubFor(get(urlEqualTo("/cars/Chevy"))
              .withHeader("Accept", equalTo("application/json"))
              .withHeader("User-Agent", equalTo("Jakarta Commons-HttpClient/3.1"))
                      .willReturn(aResponse()
                                  .withHeader("content-type", "application/json")
                                  .withStatus(200)
                                  .withBody("{\"message\":\"Chevy car response body\"}")
                                 )
             );
      
      stubFor(post(urlEqualTo("/cars/Mini"))
              .withHeader("Authorization", equalTo("Basic d8d74jf82o929d"))
              .withHeader("Accept", equalTo("application/json"))
              .withHeader("User-Agent", equalTo("Jakarta Commons-HttpClient/3.1"))
              .withRequestBody(equalTo("Mini Cooper"))
                      .willReturn(aResponse()
                                  .withHeader("content-type", "application/json")
                                  .withStatus(200)
                                  .withBody("{\"message\":\"Mini Cooper car response body\", \"success\":true}")
                                 )
             );
    }
    
    @Test
    public void test_Get_Method() {
        
        String url = "http://localhost:8090/cars/Chevy";
        String method = "GET";
        String body = "";
        
        ArrayList<Header> headers = new ArrayList<Header>();
        headers.add(RequestDataUtil.getHeader("Accept", "application/json"));
        headers.add(RequestDataUtil.getHeader("User-Agent", "Jakarta Commons-HttpClient/3.1"));
        HttpResponseDao response = HttpRequest.get(url, headers, null);
        response.getEntity();
        response.getHeader();
    }
    
//    @Test
//    public void test_Post_Method() {
//        
//        String url = "http://localhost:8090/cars/Mini";
//        String method = "POST";
//        String body = "Mini Cooper";
//        
//        HashMap<String, String> headers = new HashMap<String, String>();
//        headers.put("Authorization", "Basic d8d74jf82o929d");
//        headers.put("Accept", "application/json");
//        headers.put("User-Agent", "Jakarta Commons-HttpClient/3.1");
//        
//        HTTPRequestor httpRequestor = new HTTPRequestor();
//        Response response = null;
//        
//        try {
//                response = httpRequestor.perform_request(url, method, headers, body);
//        } catch (Exception e) {
//                fail("Problem using HTTPRequestor to generate response: " + e.getMessage());
//        }
//        
//        assertEquals(200, response.getStatusCode());
//        assertEquals("Mini Cooper car response body", response.jsonPath().get("message"));
//        assertEquals(true, response.jsonPath().get("success"));
//        
//    }
    
    @AfterTest(alwaysRun=true)
    public void tearDown() {    
      wireMockServer.stop();
      wireMockServer.shutdown();
    }

}

