/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lorena
 */
public class CalculatorTest {
    
    public CalculatorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calcularExpressao method, of class Calculator.
     */
    @Test
    public void testCalcularExpressao(){
        
        System.out.println("calculando expressao");
        Calculator instance = new Calculator();
        
        assertEquals("4.0", instance.calcularExpressao("2+2"));
    }
    
}
