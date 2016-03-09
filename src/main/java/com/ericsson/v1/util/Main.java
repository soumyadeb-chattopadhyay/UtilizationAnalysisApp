package com.ericsson.v1.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("01-2014");
		list.add("11-2014");
		list.add("12-2014");
		list.add("03-2014");
		list.add("02-2014");
		Collections.sort(list);
		System.out.println(list);
		
		int i =1;
		int j = i + 01;
		
		System.out.println(j);
		
		ApplicationUtil applicationUtil = new ApplicationUtil();
		System.out.println(applicationUtil.getFileName());
	}

}
