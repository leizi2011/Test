package com.cmcc.inter.msg;

import java.math.BigDecimal;

import com.cmcc.inter.tools.StringTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageJSON implements Message {
	private JSONObject json;

	public MessageJSON() {
		this.json = new JSONObject();
	}

	public MessageJSON(String jsonStr) {
		this.json = StringTools.convertToJson(jsonStr);
	}

	public Message addItem(String key, String value) {
		json.put(key, value);
		return this;
	}

	public Message removeItem(String key) {
		json.remove(key);
		return this;
	}

	public String getString(String key) {
		return json.getString(key);
	}

	public int getInt(String key) {
		return json.getInt(key);
	}

	public long getLong(String key) {
		return json.getLong(key);
	}

	public double getDouble(String key) {
		return json.getDouble(key);
	}

	public boolean getBoolean(String key) {
		return json.getBoolean(key);
	}

	public BigDecimal getBigDecimal(String key) {
		return new BigDecimal(json.getString(key));
	}

	public Message getMessage(String key) {
		return new MessageJSON(json.getJSONObject(key).toString());
	}

	public Message[] getArray(String key) {
		Message[] msgArray = new MessageJSON[0];

		JSONArray jsonArray = json.getJSONArray(key);
		if (jsonArray != null) {
			msgArray = new MessageJSON[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); ++i) {
				msgArray[i] = new MessageJSON(jsonArray.getJSONObject(i)
						.toString());
			}
		}

		return msgArray;
	}

	@Override
	public String toString() {
		return json.toString();
	}
}
