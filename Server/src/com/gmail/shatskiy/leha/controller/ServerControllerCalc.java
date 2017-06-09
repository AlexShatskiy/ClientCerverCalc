package com.gmail.shatskiy.leha.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerControllerCalc {
	private int port;

	private ServerSocket server;

	private static long clientCounter = 1;
	private Map<Long, ClientRequestHendlerCalc> clients;

	private static final CommandProvider provider = CommandProvider.getInstance();

	public ServerControllerCalc(int port) {
		this.port = port;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.clients = new ConcurrentHashMap<>();
	}

	public void startServer() {
		Socket socket = null;
		try {
			while (true) {
				System.out.println("[Сервер]Сервер ожидает подключения клиента...");

				socket = server.accept();

				createHandler(socket);

				System.out.println("[Сервер]Поток обработки запроса клиента запущен...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createHandler(Socket socket) {
		ClientRequestHendlerCalc clientHendler = new ClientRequestHendlerCalc(socket, clientCounter, this, provider);
		new Thread(clientHendler).start();

		clients.put(clientCounter, clientHendler);
		clientCounter++;
		System.out.println(clients.size());
	}

	public void deliteHandler(Long hendlerKey) {
		clients.remove(hendlerKey);
		System.out.println(clients.size());
	}
}
