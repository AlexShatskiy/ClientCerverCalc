package com.gmail.shatskiy.leha.controller.command.impl;

import com.gmail.shatskiy.leha.controller.command.Command;

public class WrongCommand implements Command {

	@Override
	public String execute(String request) {
		
		return "WRONG REQUEST";
	}
}
