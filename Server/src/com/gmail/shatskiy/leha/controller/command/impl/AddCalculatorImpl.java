package com.gmail.shatskiy.leha.controller.command.impl;

import com.gmail.shatskiy.leha.controller.command.Command;

public class AddCalculatorImpl implements Command {

	@Override
	public String execute(String request) {

		String response;
		String[] params;

		params = request.split("\\s+");

		if (params.length != 3) {
			response = "WRONG count argument";
		} else {

			if (NumberValidation.isNumber(params[1]) && NumberValidation.isNumber(params[2])) {
				int result = Integer.parseInt(params[1]) + Integer.parseInt(params[2]);
				response = result + "";
			} else {
				response = "Illegal argument";
			}
		}
		return response;
	}
}
