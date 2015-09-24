package com.cmcc.inter.data.util;

import java.io.IOException;
import java.util.List;

import com.cmcc.inter.tools.PropertiesHandle;

public class TransferData {

	private static Object[] paramName = null;

	public static Object[] getParamName() {
		return paramName;
	}

	private static List<Object[]> getDataFromExcel() {
		try {
			return new ReadExcel().readExcel(PropertiesHandle
					.readValue("excel_path"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Object[][] getObjectData(String caseId) throws IOException {
		List<Object[]> paramList = getDataFromExcel();
		Object[][] params = new Object[paramList.size()][];
		int num = 0;
		for (int i = 0; i < paramList.size(); i++) {
			if (paramList.get(i)[0].toString().equals(caseId)) {
				params[num] = paramList.get(i);
				num++;
			}
		}
		Object[][] data = new Object[num - 1][];
		paramName = new Object[params[0].length];
		paramName = params[0];
		for (int j = 0; j < num - 1; j++) {
			data[j] = params[j + 1];
		}
		return data;
	}

}
