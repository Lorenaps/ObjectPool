package Model;

import java.net.*;
import java.io.*;
import Controller.Calculator;
import Controller.ServerThreadPool;

public class ServerThread extends Thread {
	private Socket socket;
        private int idThread;
        private Calculator calculator;

	public ServerThread() {
            super();
            this.calculator = new Calculator();
	}

        public void setSocket(Socket socket){
            this.socket = socket;
        }
        
        public int getIdThread(){
            return this.idThread;
        }

        public void setIdThread(int idThread){
            this.idThread = idThread;
        }

        public boolean equals(ServerThread serverThread){
            if(this.idThread==serverThread.getIdThread())
                return true;
            return false;
        }

        @Override
	public void run() {
            boolean chave=true;
		try {
                        
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
                        while(chave){
                            out.println("Informe a expressao:");
                            String doCliente = in.readLine();
                            if(doCliente.equals("sair"))
                                break;
                            
                            out.println(idThread+"-"+this.calculator.calcularExpressao(doCliente));
                            doCliente = in.readLine();
                        }
                        ServerThreadPool.getInstance().release(this);
                        out.close();
                        in.close();
                        
			socket.close();
		} catch (Exception e1) { 
			System.out.println(e1);
		}
	}
}
