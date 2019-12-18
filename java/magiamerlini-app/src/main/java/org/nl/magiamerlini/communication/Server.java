package org.nl.magiamerlini.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.utils.Logger;

public class Server {
	private Scanner scanner;
	private PrintWriter serverPrintOut;
	private Communication communication;
	private Logger logger;

	public Server() {
		this.logger = new Logger(this.getClass().getSimpleName(), true);
	}

	public void connect(Communication communication, int port) {
		this.communication = communication;

		logger.log(Level.INFO, "Waiting for a connection");

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			Socket connectionSocket = serverSocket.accept();
			InputStream inputToServer = connectionSocket.getInputStream();
			OutputStream outputFromServer = connectionSocket.getOutputStream();

			scanner = new Scanner(inputToServer, "UTF-8");
			serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sendResponse("server connected");
		logger.log(Level.INFO, "Connection started --");

		while (scanner.hasNextLine()) {
			readStream();
		}

		logger.log(Level.INFO, "-- Connection stopped");
	}

	private void readStream() {
		String line = scanner.nextLine();

		logger.log(Level.INFO, "RECEIVED: " + line);
		communication.receiveMessage(line.replace(";", "").split(" "));
		logger.log(Level.INFO, "-------------");
	}

	public void sendResponse(String message) {
		message += ";";
		logger.log(Level.INFO, "SEND: " + message);
		serverPrintOut.println(message);
	}

}
