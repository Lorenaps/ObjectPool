package Controller;
import Library.Analise;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matheus
 */
public class Calculator extends Analise{
     //private Analise analise;

     public Calculator(){
     }
        /**
     *
     * @param expressao
     * @return
     */
    public String calcularExpressao(String expressao){
        String result = "";
        this.setExpressao(expressao);

        if(!expressao.isEmpty())
            result = this.getResultado();

        return result;
    }
    
    public static void main(String[] args){
        
        System.out.println("calcularExpressao");
        Calculator instance = new Calculator();
        
        System.out.println(instance.calcularExpressao("2+2"));
    }
}
