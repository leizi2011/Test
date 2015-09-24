package com.cmcc.inter.test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cmcc.inter.data.util.TransferData;
import com.cmcc.inter.http.util.HttpRequest;
import com.cmcc.inter.http.util.HttpResponseDao;
import com.cmcc.inter.http.util.RequestDataUtil;
import com.cmcc.inter.msg.Message;
import com.cmcc.inter.msg.MessageFactory;
import com.cmcc.inter.msg.MessageType;
import com.cmcc.inter.tools.PropertiesHandle;

/**
 * @author iversoncl
 * @Date 2015年7月22日
 * @Project InterfaceFramework
 */
public class HttpTest {

	private static String target_url = null;
	private static String action = null;

	@DataProvider
	public static Object[][] myCaseData() throws IOException {
		return TransferData.getObjectData("0");   //excel中标识的caseid对应的用例都将存入
	}

	@BeforeClass
	public void setUp() {
		
		//获取配置文件中的host和action
		target_url = PropertiesHandle.readValue("target_url");
		action = PropertiesHandle.readValue("logout");
	}
    
	@Test(dataProvider = "myCaseData", dataProviderClass = HttpTest.class)
	public void loginTest(Object caseId, Object deviceid, Object preKey,
			Object preValue) throws ClassNotFoundException {

		/*
		 * get请求用例
		 */
		 String url = RequestDataUtil.getReqUrl(target_url + action,    //get请求组装url
		 deviceid.toString());
		 HttpResponseDao hrd = HttpRequest.get(url);				//返回由header、entity、cookie组成的对象，方便自由获取
		 Message msg = MessageFactory.toMessage(hrd.getEntity(),  //对最常用的json对象处理
		 MessageType.JSON);
		 Assert.assertEquals(msg.getString(preKey.toString()),    //断言预期值和实际值
		 preValue.toString());

		/*
		 * post请求用例
		 */
		HttpResponseDao hrd1 = HttpRequest.post(target_url + action,
				RequestDataUtil.postNameValue(deviceid.toString()));
		Message msg1 = MessageFactory.toMessage(hrd1.getEntity(),
				MessageType.JSON);
		Assert.assertEquals(msg1.getString(preKey.toString()),
				preValue.toString());
		
		/*
		 * 数据库比对，参加嗨健康框架
		 */
//		MyBatisUtil.getSessionFactory().openSession();
		
		
		/*
		 * TCP/UDP长连接，使用TCPClient、UDPClient进行处理
		 */
//		TCPClient tp = new TCPClient();
//		tp.createClient();
	}
}
