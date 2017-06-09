package com.gmail.shatskiy.leha.start;

import com.gmail.shatskiy.leha.controller.ServerControllerCalc;

public class Main {

	public static void main(String[] args) {

		ServerControllerCalc server;

		server = new ServerControllerCalc(1235);
		server.startServer();
	}
}
