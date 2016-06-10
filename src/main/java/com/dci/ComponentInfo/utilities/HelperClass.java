package com.dci.ComponentInfo.utilities;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dci.ComponentInfo.beans.Component_ID;

public class HelperClass {

	private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		} else {
			return df.format(date);
		}
	}
	
	public static Date stringToDate(String date) throws ParseException {
		if (date == null || date.equals("")) {
			return null;
		} else {
			return new Date(df.parse(date).getTime());
		}
	}
	
	public static BigDecimal getBigDecimal(Integer value) {
		if (value == null || value.toString().trim().equals(""))
			return null;
		return new BigDecimal(value.toString().trim());	
	}
	
	public static Component_ID parseComponentUTF8(String queryString) {
		return null;
	}
}
