package Library;

/*
 * Created on 09/04/2005
 */


import java.util.LinkedList;


/**
 * Classe que recebe uma express�o matem�tica e infixa Ex.: (6+2)*3
 * e a converte para uma nota��o p�s - fixa -> 6 2 + 3 *
 * 
 * @author <a href="mailto:bridee@gmail.com">Erko Bridee de Almeida Cabrera</a>
 */
public class InfixToPostfix {

    /*------------------------------------------------------------------------*
     *  Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/
    /**
     * <code>notacaoPosFixa</code> -
     * estrutura da nota��o p�s-fixa em uma estrutuda de lista 
     */
    private LinkedList notacaoPosFixa;
    /** pilha para auxiliar na convers�o */
    private Pilha pilha;
    /** nota��o infixa a ser convertida */
    private String infix;
    /** resultado, nota��o p�s-fixa */
    private String postfix;
    /*------------------------------------------------------------------------*
     *  Fim Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*
     *  Construtores da classe
     *------------------------------------------------------------------------*/
    /** Construtor sem parametros da classe */
    public InfixToPostfix() {
        // inicializa pilha
        this.setPilha( new Pilha() );
        // inicializando estrutura que armazena a nota��o p�s-fixa como lista
        this.setNotacaoPosFixa( new LinkedList() );
        // inicializa em vazia a nota��o infixa
        this.setInfix("");
        // inicializa em vazia a nota��o p�s-fixa
        this.setPostfix("");
    }
    /**
     * Construtor da classe que recebe como parametro
     * a nota��o infixa a ser convertida
     * 
     * @param String infix
     */
    public InfixToPostfix( String infix ) {
        // inicializa pilha
        this.setPilha( new Pilha() );
        // inicializando estrutura que armazena a nota��o p�s-fixa como lista
        this.setNotacaoPosFixa( new LinkedList() );
        // repassa nota��o infixa
        this.setInfix( infix );
        // inicializa em vazia a nota��o p�s-fixa
        this.setPostfix("");
        // chama m�todo de convers�o
        this.convertToPostfix();
    }
    /*------------------------------------------------------------------------*
     *  Fim Construtores da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    /**
     * @return Pilha pilha.
     */
    public Pilha getPilha() {
        return pilha;
    }
    /**
     * @param Pilha pilha.
     */
    public void setPilha(Pilha pilha) {
        this.pilha = pilha;
    }
    /**
     * @return String postfix.
     */
    public String getPostfix() {
        return postfix;
    }
    /**
     * @return LinkedList notacaoPosFixa.
     */
    public LinkedList getNotacaoPosFixa() {
        return notacaoPosFixa;
    }
    /**
     * @param LinkedList notacaoPosFixa.
     */
    public void setNotacaoPosFixa(LinkedList notacaoPosFixa) {
        this.notacaoPosFixa = notacaoPosFixa;
    }
    /**
     * @param String postfix.
     */
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
    /**
     * @return String infix.
     */
    public String getInfix() {
        return infix;
    }
    /**
     * @param String infix.
     */
    public void setInfix(String infix) {
        this.infix = infix;
    }
    /*------------------------------------------------------------------------*
     *  Fim M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  Servi�os da classe
     *------------------------------------------------------------------------*/    
    /**
     * M�todo que converte de nota��o infixa para p�s-fixa
     * 
     * @param String infix - nota��o infixa
     */
    public void convertToPostfix( String infix ) {
        this.setInfix( infix );
        this.convertToPostfix();
    }
    /**
     * M�todo que converte de nota��o infixa para p�s-fixa
     */
    public void convertToPostfix() {
        String numero = "";
        for( int i = 0; i<this.getInfix().length(); i++ ) {
            char c = this.getInfix().charAt(i);
            // vetificando parenteses
            switch( c ) {
            	// abrindo parenteses
            	case '(':
            	    this.getPilha().push( ""+ c +"" );
            	    break;
            	/*
            	 * Fechando parenteses desempilha tudo at� o fecha
            	 * parenteses 
            	 */
            	case ')':
            	    if( numero.length() > 0 ) {
	            	    this.addNu(numero);
	            	    numero = "";
            	    }
            	    while( !this.getPilha().isEmpty() ) {
            	        String op = this.getPilha().pop().toString();
            	        if( op.equals("(") ) { break; }
            	        this.addOp(op);
            	    }
            	    break;
            }
            // verificando n�mero
            if( this.isNumPart(c)  ) { numero += ""+c; }
            // verificando se � operador
            if( this.isOperator(c) ) {
                this.addNu(numero);
                this.trataOperador(c);
                numero = "";
            }
            if( i == this.getInfix().length()-1 ) {
                this.addNu(numero);
            }
        }
        // se a pilha n�o estiver vazia desempilha operador
        while( !this.getPilha().isEmpty() ) {
	        String op = this.getPilha().pop().toString();
	        if( op.equals("(") ) { break; }
	        this.addOp(op);
	    }
    }
    /**
     * M�todo que realiza as verifica��es necess�rias sobre um
     * novo operador encontrado
     * @param c
     */
    private void trataOperador( char c ) {
        if( this.getPilha().isEmpty() ) {
            this.getPilha().push( ""+ c +"" );
            this.newNu();
        } else {
            /*
             * verificando se o operador inserido na pilha tem
             * precedencia maior que o operador atual
             */
            if( this.precedence( ""+c+"", this.getPilha().stackTop() ) ) {
                this.getPilha().push( ""+ c +"" );
                this.newNu();
            } else {
                /*
                 * enquanto a precedencia do operador for menor
                 * do que os da pilha desempilha operador pilha
                 * no final insere operador atual na pilha 
                 */
                while( true ) {
        	        if( !this.getPilha().isEmpty() ) {
	                    if( this.precedence( ""+c+"", this.getPilha().stackTop() )  ) { 
	        	            this.getPilha().push( ""+ c +"" );
	        	            break; 
	        	        }
        	        } else {
        	            this.getPilha().push( ""+ c +"" );
        	            break;
        	        }
        	        String op = this.getPilha().pop().toString();
        	        this.addOp(op);
        	    }
            }
        }
    }
    /**
     * novo n�mero
     */
    private void newNu() {
        if( !this.getPostfix().endsWith(" ") ) {
            this.setPostfix( this.getPostfix() + " " );
        }
    }
    /**
     * concatena o n�mero na nota��o p�s-fixa
     * @param String nu
     */
    private void addNu( String nu ) {
        if( nu.length() > 0 ) {
	        this.getNotacaoPosFixa().addLast( nu );
	        this.setPostfix( this.getPostfix() + nu );
        }
    }
    /**
     * adiciona um operador na nota��o p�s-fixa
     * @param op
     */
    private void addOp( String op ) {
        this.getNotacaoPosFixa().addLast( op );
        if( this.getPostfix().endsWith(" ") ) {
            this.setPostfix( this.getPostfix() + op + " " );
        } else {
            this.setPostfix( this.getPostfix() + " " + op + " " );
        }
        
    }
    /**
     * M�todo que avalia a precedencia dos operadores e retorna <b>true</b>
     * caso o operador1 tenha precedencia maior que operador2
     * 
     * @param Object operator1
     * @param Object operator2
     * @return boolean
     */
    private boolean precedence( Object operator1, Object operator2 ) {        
        int vo1 = this.operatorPrecedenceValue( operator1 ), vo2 = this.operatorPrecedenceValue( operator2 );
        if( vo1 < vo2 ) {
            return true;
        }
        return false;
    }
    /**
     * M�todo que retorna o valor da pecedencia do operador
     * @param Object operator
     * @return int - valor de precedencia
     */
    private int operatorPrecedenceValue( Object operator ) {
        /*
         * ! = 0 	- fatorial
         * ^ = 1	- exponencia��o
         * * = 2	- multiplica��o
         * / = 2	- divis�o
         * % = 2	- opera��o de resto de divis�o
         * + = 3	- soma
         * - = 3	- subtra��o
         */
        if( ((String)operator).equals("!") ) {
            return 0;
        } else if( ((String)operator).equals("^") ) {
            return 1;
        } else if( ((String)operator).equals("*") ) {
            return 2;
        } else if( ((String)operator).equals("/") ) {
            return 2;
        } else if( ((String)operator).equals("%") ) {
            return 2;
        } else if( ((String)operator).equals("+") ) {
            return 3;
        } else if( ((String)operator).equals("-") ) {
            return 3;
        }
        return 4;
    }
    /**
     * M�todo que verifica um determinado caractere caso seja um operador
     * retorna <b>true</b>, se n�o retorna <b>false</b>
     * @param char c
     * @return boolean
     */
    private boolean isOperator( char c ) {
        switch(c) {
        	case '!':
        	    return true;
        	case '^':
        	    return true;
        	case '*':
        	    return true;
        	case '/':
        	    return true;
        	case '%':
        	    return true;
        	case '+':
        	    return true;
        	case '-':
        	    return true;
        }
        return false;
    }
    /**
     * M�todo que verifica se um caractere faz parte de um n�mero
     * se faz, retorna <b>true</b>
     * 
     * @param char c
     * @return boolean
     */
    private boolean isNumPart( char c ) {
        switch(c) {
	    	case '0':
	    	    return true;
	    	case '1':
	    	    return true;
	    	case '2':
	    	    return true;
	    	case '3':
	    	    return true;
	    	case '4':
	    	    return true;
	    	case '5':
	    	    return true;
	    	case '6':
	    	    return true;
	    	case '7':
	    	    return true;
	    	case '8':
	    	    return true;
	    	case '9':
	    	    return true;
	    	case '.':
	    	    return true;
        }
        return false;
    }
    /*------------------------------------------------------------------------*
     *  Fim Servi�os da classe
     *------------------------------------------------------------------------*/
    

}
