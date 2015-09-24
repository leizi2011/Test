package com.cmcc.inter.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author iversoncl
 * @Date 2015年4月11日
 * @Project InterfaceFramework
 */
@SuppressWarnings("unchecked")
public class JsonUtil {


	/**
	 * @Description:Json转化Map类型
	 * @param json
	 * @return Map
	 * @author: iversoncl
	 * @time:2015年4月12日 下午8:18:05
	 */
	
	public static Map<String, Object> Json2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(Json2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * @Description:json的嵌套一个json,返回该嵌套json的map类型
	 * @param jsonStr
	 * @return Map<String,Object>
	 * @author: iversoncl
	 * @time:2015年4月16日 下午3:07:29
	 */
	public static Map<String, Object> subJson2Map(String jsonStr) {
		Map<String, Object> map = JSONObject.fromObject(jsonStr);
		Map<String, Object> subMap = new HashMap<String, Object>();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			if (entry.getValue() instanceof JSONObject) {
				subMap = JsonUtil.Json2Map(entry.getValue().toString());
			}
		}
		return subMap;
	}

	/**
	 * @param jsonStr
	 * @return List<Map<String,Object>>
	 * @author: iversoncl
	 * @time:2015年4月16日 下午3:07:53
	 */
	public static List<Map<String, Object>> subsJson2Map(String jsonStr) {
		Map<String, Object> map = JSONObject.fromObject(jsonStr);
		List<Map<String, Object>> subMapList = new ArrayList<Map<String, Object>>();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			if (entry.getValue() instanceof JSONObject) {
				subMapList.add(JsonUtil.Json2Map(entry.getValue().toString()));
			}
		}
		return subMapList;
	}

	/**
		* @Description:json的嵌套多个json,返回该嵌套json的List类型
		* @param jsonStr
		* @return List<Map<String,Object>>
		* @author: iversoncl
		* @time:2015年4月17日 下午3:19:51
	*/
	@Test
	public static List<Map<String, Object>> multiSubJson2Map(String jsonStr) {
		Map<String, Object> map = JSONObject.fromObject(jsonStr);
		List<Map<String, Object>> subMapList = new ArrayList<Map<String, Object>>();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			if (entry.getValue() instanceof JSONArray) {
				JSONArray ay = JSONArray.fromObject(entry.getValue());
				for (int i = 0; i < ay.size(); i++) {
					subMapList.add(JsonUtil.Json2Map(ay.getString(i)));
				}
			}
		}
		return subMapList;
	}
}