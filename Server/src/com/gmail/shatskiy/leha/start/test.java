package com.gmail.shatskiy.leha.start;

import com.gmail.shatskiy.leha.controller.CommandProvider;
import com.gmail.shatskiy.leha.controller.command.Command;
import com.gmail.shatskiy.leha.controller.command.CommandName;
import com.gmail.shatskiy.leha.controller.command.impl.AddCalculatorImpl;
import com.gmail.shatskiy.leha.controller.command.impl.NumberValidation;

public class test {

	public static void main(String[] args) {
		//System.out.println(NumberValidation.isNumber("124"));
		//System.out.println(Integer.parseInt("000123"));
		
		//AddCalculatorImpl a = new AddCalculatorImpl();
		//System.out.println(a.execute("ADD 3 2"));
		
		String clientRequest = "add    3 2";
		String[] params = clientRequest.split("\\s+");
		System.out.println(params[0]);
		System.out.println(params[1]);
		System.out.println(params[2]);
		
		final CommandProvider provider = CommandProvider.getInstance();
		Command command;
		command = provider.getCommand(params[0].toUpperCase());
		
		//String p = params[0].toUpperCase();
		//System.out.println(p.equals(CommandName.ADD));
		System.out.println(command.execute(clientRequest));
	}

}
