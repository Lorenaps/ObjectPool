package Controller;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String idServer;

    public void connect(Connector connector) throws Exception{
       this.socket = new Socket(connector.getIP(), connector.getPort());
       this.out = new PrintWriter(this.socket.getOutputStream(), true);
       this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public String run(String exp) {
        String array[] = new String[2]; 
        String result = "";
       
        try {
            // Cria um socket e conecta-o a uma porta em host remoto.			
            // Fluxo de Entrada e Saída do Socket.
               String fromServer = in.readLine();
               System.out.println(fromServer);
               out.println(exp);
               fromServer=in.readLine();
               array=fromServer.split("-");
               idServer=array[0];
               result=array[1];
               out.println("ok");         
                //System.out.println(fromServer);           
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
            
            return result;
    }

    public void disconnect() throws Exception{
        
        out.println("sair");
        in.close();
        out.close();
         //System.out.println(fromServer);
    }

    public static void main(String[] args) throws Exception {
                    Client c = new Client();
                    System.out.println("Informe o IP");
                    Scanner entrada = new Scanner(System.in);
                    String temp=entrada.next();
                    System.out.println("Informe a Porta");
                    int temp2=entrada.nextInt();
                    Connector con = new Connector(temp, temp2);
                    c.connect(con);

                    System.out.println("Informe a expressao");
                    temp=entrada.next();
                    c.run(temp);

                    c.disconnect();

    }
    
    /* Informação necessária para o testar o pool de calculadoras, 
       verificar se a aquisição e devolução de recursos estão se 
       comportando como o esperado */
    public int getIdServer() {
        return Integer.parseInt(idServer);
    }

}
