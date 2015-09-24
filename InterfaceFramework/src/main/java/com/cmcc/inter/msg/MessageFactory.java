package com.cmcc.inter.msg;


/**
 * 
 * @author zhouyelin@chinamobile.com
 *
 */
public class MessageFactory {
	public static Message toMessage(String str, MessageType type) {
		if (type == MessageType.JSON) {
			if (!str.startsWith("{")) {
				Message msg = new MessageJSON();
				msg.addItem(ConstantsUtils.GLOBAL_MSG, str);
				return msg;
			}

			return new MessageJSON(str);
		}
		return null;
		
	}

	public static Message createMessage(MessageType type) {
		if (type == MessageType.JSON) {
			return new MessageJSON();
		} 
		return null;
	}
}
