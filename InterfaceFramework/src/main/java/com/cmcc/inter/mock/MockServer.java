package com.cmcc.inter.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author iversoncl
 * @Date 2015年8月3日
 * @Project InterfaceFramework
 */
public class MockServer {
	 private WireMockServer wireMockServer;
	    
	    public void setUp(String ip,int port,String url,String responseContent,String method,boolean def) {
	    	
	        wireMockServer = new WireMockServer(wireMockConfig().port(port));
	        WireMock.configureFor(ip, port);
	        wireMockServer.start();  
	        if(method.equals("get")){
	    		if(def){
	    			stubGetRequests();
	    		}else{
	    			stubGetRequests(url, responseContent);
	    		}
	    	}else if(method.equals("post")){
	    		stubPostRequests();
	    	}
	    }
	    
	    public void tearDown() {    
	        wireMockServer.stop();
	        wireMockServer.shutdown();
	     }
	    
	    
	    private void stubGetRequests() {
	      stubFor(get(urlEqualTo("/cars/Chevy"))
	              .withHeader("Accept", equalTo("application/json"))
	              .withHeader("User-Agent", equalTo("Jakarta Commons-HttpClient/3.1"))
	                      .willReturn(aResponse()
	                                  .withHeader("content-type", "application/json")
	                                  .withStatus(200)
	                                  .withBody("{\"message\":\"Chevy car response body\"}")
	                                 )
	             );
	      
	    }
	    
	    private void stubGetRequests(String url,String responseContent) {
		      stubFor(get(urlEqualTo(url))
		              .withHeader("content-type", equalTo("application/json"))
		              .withHeader("User-Agent", equalTo("HttpClient4"))
		                      .willReturn(aResponse()
		                                  .withHeader("content-type", "application/json")
		                                  .withStatus(200)
		                                  .withBody(responseContent)
		                                 )
		             );
		      
		    }
	    
	    private void stubPostRequests() {
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
}
