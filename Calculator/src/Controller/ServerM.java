package Controller;

import Model.ServerThread;
import java.net.*;
import java.io.*;

public class ServerM {
	public static void run() {
		boolean chave = true;
		try {
			ServerSocket serverSocket = new ServerSocket(4444);
			while (chave) {
				System.out.println("Servidor aguardando conexão");
				Socket socket = serverSocket.accept();
				ServerThread serverThread = ServerThreadPool.getInstance().acquire();
				serverThread.setSocket(socket);
                                serverThread.start();
			}
			serverSocket.close();
		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}
	}

	public static void main(String[] args) throws IOException {
		ServerM.run();
	}
        
        private Calculator calculator = new Calculator();

    /**
     * @return the calculator
     */
    public Calculator getCalculator() {
        return calculator;
    }
}
