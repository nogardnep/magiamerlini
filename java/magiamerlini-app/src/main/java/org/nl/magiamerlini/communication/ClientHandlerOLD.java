package org.nl.magiamerlini.communication;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandlerOLD implements Runnable {

	private Socket connexion = null;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;

	private static int count = 0;
	private String name;

	public ClientHandlerOLD(String host, int port) {
		count++;		
		name = getClass().getSimpleName() + "-" + count;
		
		try {
			connexion = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// nous n'allons faire que 10 demandes par thread...
		for (int i = 0; i < 10; i++) {
			  
//			try {
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			try {
//
//				writer = new PrintWriter(connexion.getOutputStream(), true);
//				reader = new BufferedInputStream(connexion.getInputStream());
//				// On envoie la commande au serveur
//
//				String message = "test";
//				writer.write(message);
//				// TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
//				writer.flush();
//
//				System.out.println(name + ":Message \"" + message + "\" send to server");
//
//				// On attend la réponse
////				String response = read();
////				System.out.println("\t * " + name + ": Get response " + response);
//
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}

//			try {
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}

		System.out.println("close");
		writer.write("CLOSE");
		writer.flush();
		writer.close();
	}

	private String read() throws IOException {
		String response = "";
		int stream;
		byte[] b = new byte[4096];
		stream = reader.read(b);
		response = new String(b, 0, stream);
		return response;
	}
}