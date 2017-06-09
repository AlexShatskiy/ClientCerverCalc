package com.gmail.shatskiy.leha.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.gmail.shatskiy.leha.controller.command.Command;

public class ClientRequestHendlerCalc implements Runnable {

	private final String STOP_COMMAND = "STOP";
	private final long clientNumber;

	private ServerControllerCalc myServer;
	private Socket socket;

	private CommandProvider provider;

	public ClientRequestHendlerCalc(Socket socket, final long clientNumber, ServerControllerCalc serverControllerCalc,
			CommandProvider provider) {
		this.socket = socket;
		this.clientNumber = clientNumber;
		myServer = serverControllerCalc;
		this.provider = provider;
	}

	@Override
	public void run() {

		try (DataInputStream dataIn = new DataInputStream(socket.getInputStream());
				DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

			String clientRequest = null;

			Command command;
			String responseToClient;

			while (!STOP_COMMAND.equals(clientRequest)) {

				clientRequest = dataIn.readUTF();

				String[] params = clientRequest.split("\\s+");

				command = provider.getCommand(params[0]);

				responseToClient = command.execute(clientRequest);

				dataOut.writeUTF(responseToClient);

				dataOut.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			myServer.deliteHandler(clientNumber);
		}
	}
}
