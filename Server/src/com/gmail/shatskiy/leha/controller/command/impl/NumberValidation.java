package com.gmail.shatskiy.leha.controller.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidation {

	private NumberValidation() {}
	
	public static boolean isNumber(String argum){
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(argum);
		
		return m.matches();
	}

}
