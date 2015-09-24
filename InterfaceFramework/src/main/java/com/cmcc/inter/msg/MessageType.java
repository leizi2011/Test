package com.cmcc.inter.msg;

public enum MessageType {
	JSON("JSON");

	private String type;

	private MessageType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}
}
