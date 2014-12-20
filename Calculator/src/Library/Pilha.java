package Library;

/*
 * Created on 09/04/2005
 */


import java.util.LinkedList;

/**
 * Implementa��o de uma classe que fornece os servi�os de pilha
 * 
 * @author <a href="mailto:bridee@gmail.com">Erko Bridee de Almeida Cabrera</a>
 */
public class Pilha {
    
    /*------------------------------------------------------------------------*
     *  Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/
    /** Objeto para representar a pilha */
    private LinkedList pilha = new LinkedList();
    /*------------------------------------------------------------------------*
     *  Fim Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*
     *  Construtores da classe
     *------------------------------------------------------------------------*/
    /** Construtor sem parametros da classe */
    public Pilha() { this.setPilha( new LinkedList() ); }
    /*------------------------------------------------------------------------*
     *  Fim Construtores da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    /**
     * @return LinkedList pilha.
     */
    public LinkedList getPilha() {
        return pilha;
    }
    /**
     * @param LinkedList pilha.
     */
    public void setPilha(LinkedList pilha) {
        this.pilha = pilha;
    }
    /*------------------------------------------------------------------------*
     *  Fim M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  Servi�os da classe
     *------------------------------------------------------------------------*/
    /**
     * M�todo para empilhar
     * 
     * @param Object object
     */
    public void push( Object object ) {
        this.getPilha().addLast( object );
    }
    /**
     * M�todo para desempilhar
     * 
     * @return Object
     */
    public Object pop() {
        return this.getPilha().removeLast();
    }
    /**
     * M�todo que retorna o objeto do topo da pilha
     * 
     * @return Object
     */
    public Object stackTop() {
        return this.getPilha().getLast();
    }
    /**
     * Indica se a pilha est� ou n�o vazia
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return this.getPilha().isEmpty();
    }
    /**
     * M�todo que retorna a quantidade atual de elementos na pilha
     * 
     * @return int
     */
    public int size() {
        return this.getPilha().size();
    }
    /*------------------------------------------------------------------------*
     *  Fim Servi�os da classe
     *------------------------------------------------------------------------*/
}
