package org.nl.magiamerlini.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;

import org.nl.magiamerlini.utils.Logger;

public class ClientHandler implements Runnable {
	private Logger logger;
	private static Socket socket;
	private OutputStream outputStream;
	private OutputStreamWriter outplutStreamWriter;
	private BufferedWriter bufferedWriter;
	private OutputCommunication communication;

	public ClientHandler() {
		logger = new Logger(this.getClass().getSimpleName(), true);
	}

	public void connect(OutputCommunication communication, String host, int port, boolean displayMessages) {
		this.communication = communication;

		try {
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address, port);
			outputStream = socket.getOutputStream();
			outplutStreamWriter = new OutputStreamWriter(outputStream);
			bufferedWriter = new BufferedWriter(outplutStreamWriter);

		} catch (Exception exception) {
			exception.printStackTrace();

			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String message) {
		logger.log(Level.INFO, "Message sent: " + message);

		try {
			bufferedWriter.write(message + ";");
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			getIncommingMessage();
		}
	}

	private void getIncommingMessage() {
		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
			logger.log(Level.INFO, "Message received: " + message);
		} catch (Exception exception) {
			exception.printStackTrace();

			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}