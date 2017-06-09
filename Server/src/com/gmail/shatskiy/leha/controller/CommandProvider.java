package com.gmail.shatskiy.leha.controller;

import java.util.HashMap;
import java.util.Map;

import com.gmail.shatskiy.leha.controller.command.Command;
import com.gmail.shatskiy.leha.controller.command.CommandName;
import com.gmail.shatskiy.leha.controller.command.impl.AddCalculatorImpl;
import com.gmail.shatskiy.leha.controller.command.impl.MinusCalculatorImpl;
import com.gmail.shatskiy.leha.controller.command.impl.WrongCommand;

public class CommandProvider {

	private static final CommandProvider provider = new CommandProvider();

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.ADD, new AddCalculatorImpl());
		commands.put(CommandName.MINUS, new MinusCalculatorImpl());
		commands.put(CommandName.WRONG_REQUEST, new WrongCommand());
	}

	public Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = commands.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = commands.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}

	public static CommandProvider getInstance() {
		return provider;
	}
}
