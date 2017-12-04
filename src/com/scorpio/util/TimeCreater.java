package com.scorpio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCreater {

	public static String creat() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date =sf.format(new Date());
		return date;
	}
}
