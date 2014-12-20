/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lorena
 */
public class ClientTest {
    
    private Connector connector;

    public ClientTest() {
    }
    
    @Before
    public void setUp() {
        
        System.out.println("Iniciando o Servidor");
        connector = new Connector("127.0.0.1", 4444);
        //server.run();
    }

    /**
     * Test of connect method, of class Client.
     */
    @Test
    public void testClient() throws Exception{
        System.out.println("connect");
        
        ArrayList<Client> clientes =new ArrayList<>();
        
        //Iterator it = clientes.iterator();
        
        for(int i=0; i<8; i++)
            clientes.add(new Client());
        
        clientes.get(0).connect(connector);
        assertEquals("4.0", clientes.get(0).run("2+2"));
        assertEquals(0, clientes.get(0).getIdServer());
        
        clientes.get(1).connect(connector);
        assertEquals("4.0", clientes.get(1).run("2+2"));
        assertEquals(1, clientes.get(1).getIdServer());
 
        clientes.get(2).connect(connector);
        assertEquals("4.0", clientes.get(2).run("2+2"));
        assertEquals(2, clientes.get(2).getIdServer());
        
        clientes.get(3).connect(connector);
        assertEquals("4.0", clientes.get(3).run("2+2"));
        assertEquals(3, clientes.get(3).getIdServer());
        
        clientes.get(4).connect(connector);
        assertEquals("4.0", clientes.get(4).run("2+2"));
        assertEquals(4, clientes.get(4).getIdServer());
        
        clientes.get(5).connect(connector);
        assertEquals("4.0", clientes.get(5).run("2+2"));
        assertEquals(5, clientes.get(5).getIdServer());
        
        clientes.get(6).connect(connector);
        assertEquals("4.0", clientes.get(6).run("2+2"));
        assertEquals(6, clientes.get(6).getIdServer());
        
        clientes.get(0).disconnect();

        clientes.get(7).connect(connector);
        assertEquals("4.0", clientes.get(7).run("2+2"));
        assertEquals(0, clientes.get(7).getIdServer());
        
        clientes.remove(0);
        Iterator it = clientes.iterator();
        
        while(it.hasNext()){
            ((Client)it.next()).disconnect();
        }
        
    }

}
