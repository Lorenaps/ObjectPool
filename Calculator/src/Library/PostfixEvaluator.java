package Library;

/*
 * Created on 10/04/2005
 */


import java.util.LinkedList;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe que avalia e executa o calculo de uma nota��o p�s-fixa
 * 
 * @author <a href="mailto:bridee@gmail.com">Erko Bridee de Almeida Cabrera</a>
 */
public class PostfixEvaluator {
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
    /** monta a estrutura de �rvore de an�lise */
    private DefaultMutableTreeNode rootNode;
    /** n� corrente sendo processado */
    private DefaultMutableTreeNode currentNode;
    /** resultado do calculo da nota��o */
    private Object[] resultado;
    /*------------------------------------------------------------------------*
     *  Fim Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*
     *  Construtores da classe
     *------------------------------------------------------------------------*/
    /**
     * Construtor sem parametros da classe
     */
    public PostfixEvaluator() {
        this.setNotacaoPosFixa( new LinkedList() );
        this.setPilha( new Pilha() );
        this.setInfix("");
        this.setPostfix("");
    }
    /**
     * Construtor da classe que recebe a estrutura da nota��o p�s-fixa
     * @param LinkedList notacaoPosFixa
     */
    public PostfixEvaluator( LinkedList notacaoPosFixa ) {
        this.setNotacaoPosFixa( notacaoPosFixa );
        this.setPilha( new Pilha() );
        this.setInfix("");
        this.setPostfix("");
    }
    /**
     * Construtor da classe que recebe a nota��o p�s-fixa a ser processada
     * @param String notacaoPosFixa
     */
    public PostfixEvaluator( String notacaoPosFixa ) {
        this.setNotacaoPosFixa( new LinkedList() );
        this.setPilha( new Pilha() );
        this.setInfix("");
        this.setPostfix( notacaoPosFixa );
    }
    /**
     * Construtor da classe que recebe um objeto da classe
     * onde se encontram a estrutura da nota��o p�s-fixa
     * e a nota��o infixa
     * @param InfixToPostfix infixToPostfix
     */
    public PostfixEvaluator( InfixToPostfix infixToPostfix ) {
        this.setNotacaoPosFixa( infixToPostfix.getNotacaoPosFixa() );
        this.setPilha( new Pilha() );
        this.setInfix( infixToPostfix.getInfix() );
        this.montaAnalise();
        this.processaNotacao();
    }
    /*------------------------------------------------------------------------*
     *  Fim Construtores da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    /**
     * @return LinkedList pilha.
     */
    public Pilha getPilha() {
        return pilha;
    }
    /**
     * @return String resultado.
     */
    public Object[] getResultado() {
        return resultado;
    }
    /**
     * @param String resultado.
     */
    public void setResultado(Object[] resultado) {
        this.resultado = resultado;
    }
    /**
     * @param LinkedList pilha.
     */
    public void setPilha(Pilha pilha) {
        this.pilha = pilha;
    }
    /**
     * @return DefaultMutableTreeNode currentNode.
     */
    public DefaultMutableTreeNode getCurrentNode() {
        return currentNode;
    }
    /**
     * @param DefaultMutableTreeNode currentNode.
     */
    public void setCurrentNode(DefaultMutableTreeNode currentNode) {
        this.currentNode = currentNode;
    }
    /**
     * @return DefaultMutableTreeNode rootNode.
     */
    public DefaultMutableTreeNode getRootNode() {
        return rootNode;
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
    /**
     * @param DefaultMutableTreeNode rootNode.
     */
    public void setRootNode(DefaultMutableTreeNode rootNode) {
        this.rootNode = rootNode;
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
        if( notacaoPosFixa.size() > 0 ) {
            this.notacaoPosFixa = this.cloneLinkedList( notacaoPosFixa );            
        } else {
            this.notacaoPosFixa = notacaoPosFixa;
        }
    }
    /**
     * @return String postfix.
     */
    public String getPostfix() {
        if( ( this.postfix == null ) && ( this.getNotacaoPosFixa() != null ) ) {
            String tmp = "";
            for( int i = 0; i < this.getNotacaoPosFixa().size(); i++ ) {
                tmp += (String)this.getNotacaoPosFixa().get(i) + " ";
            }
            this.postfix = tmp;
        }
        return this.postfix;
    }
    /**
     * @param String postfix.
     */
    public void setPostfix(String postfix) {
        this.postfix = postfix;
        if( this.postfix.length() > 0 ) {
            this.geraEstruturaNotacao();
        }
    }
    /*------------------------------------------------------------------------*
     *  Fim M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  Servi�os da classe
     *------------------------------------------------------------------------*/
    /**
     * M�todo para clonar uma estrutura de lista
     */
    private LinkedList cloneLinkedList( LinkedList lk ) {
        LinkedList clone = new LinkedList();        
        for( int i = 0; i < lk.size(); i++ ) {                
            clone.addLast( lk.get(i).toString() );
        }
        return clone;
    }
    /**
     * M�todo que inverte a ordem de uma lista
     * @param LinkedList lk - lista a inverter
     * @return LinkedList - ordem invertida
     */
    private LinkedList reverseLinkedList( LinkedList lk ) {
    	LinkedList reverse = new LinkedList();
    	while( lk.size() > 0 ) {                
    		reverse.addLast( lk.removeLast() );
        }
        return reverse;
    }
    /**
     * Converte a string da nota��o p�s-fixa para o formato de estrutura
     * que � analisada pela classe, da qual � calculado o seu resultado
     */
    private void geraEstruturaNotacao() {
        /*
         * TODO:
         * C�digo para a partir de uma string em nota��o p�s-fixa
         * gerar a sua estrutura em forma de lista para depois 
         * ser calculada
         */
        
        // servi�os chamados ao final do m�todo
        this.montaAnalise();
        this.processaNotacao();
    }
    /**
     * M�todo que realiza o calculo da nota��o p�s-fixa
     */
    private void processaNotacao() {
        LinkedList resultado = this.cloneLinkedList( this.getNotacaoPosFixa() );
        /*
         * faz enquanto a quantidade de elementos dentro s da lista for maior 
         * que um, quando for 1 � o que indica que o resultado foi achado
         */
        while( resultado.size() >= 1 ) {            
            if( ( resultado.size() == 1 ) && ( !this.isOperator( resultado.getFirst() ) ) ) { break; }
            // verifica se � um operador
            if( this.isOperator( resultado.getFirst() ) ) {
                Object o1, o2;
                int opType = this.typeOperator( resultado.getFirst() );
                if( ( this.getPilha().size() >= 2 ) && ( opType != 1 ) ) {
                    o2 = this.getPilha().pop();
                    o1 = this.getPilha().pop();
                } else {
                    o2 = this.getPilha().pop();
                    o1 = "0";
                }
                resultado.addFirst( this.calcula(o1,resultado.removeFirst(),o2) );
                while( this.getPilha().size() > 0 ) {
                    resultado.addFirst( this.getPilha().pop() );
                }
            } else {
                this.getPilha().push( resultado.removeFirst() );
            }
        }
        this.setResultado( resultado.toArray() );
    }
    /**
     * M�todo que analisa a nota��o e gera uma representa��o no formato
     * de uma �rvore
     */
    private void montaAnalise() {
        this.setRootNode( new DefaultMutableTreeNode("<express�o>") );
        this.addNode( this.getRootNode(), "infixa = "+ this.getInfix() );
        this.addNode( this.getRootNode(), "p�s-fixa = "+ this.getPostfix() );
        this.setCurrentNode( this.getRootNode() );

        LinkedList estruturaNotacao = this.cloneLinkedList( this.getNotacaoPosFixa() );
        /*
         * faz enquanto a quantidade de elementos dentro s da lista for maior 
         * que um, quando for 1 � o que indica que o resultado foi achado
         */
        while( estruturaNotacao.size() >= 1 ) {            
            if( ( estruturaNotacao.size() == 1 ) && ( estruturaNotacao.getFirst() instanceof DefaultMutableTreeNode ) ) { 
            	this.getRootNode().add( (DefaultMutableTreeNode )estruturaNotacao.removeFirst() );
            	break; 
            }
            // verifica se � um operador
            if( estruturaNotacao.getFirst() instanceof DefaultMutableTreeNode ) {
            	this.getPilha().push( estruturaNotacao.removeFirst() );
            } else if( this.isOperator( estruturaNotacao.getFirst() ) ) {
                Object o1, o2;
                int opType = this.typeOperator( estruturaNotacao.getFirst() );
                if( ( this.getPilha().size() >= 2 ) && ( opType != 1 ) ) {
                    o2 = this.getPilha().pop();
                    o1 = this.getPilha().pop();
                } else {
                    o2 = this.getPilha().pop();
                    o1 = null;
                }
                
                Object o = estruturaNotacao.removeFirst();
                DefaultMutableTreeNode operador = new DefaultMutableTreeNode("<operador>");                
                operador.add( new DefaultMutableTreeNode( "s�mbolo = " + o ) );                
                operador.add( new DefaultMutableTreeNode( "tipo = " + this.nameOperator( o ) ) );
                if( o1 != null ) { operador.add( ( ( DefaultMutableTreeNode )o1 ) ); }
                operador.add( ( ( DefaultMutableTreeNode )o2 ) );
                
                estruturaNotacao.addFirst( operador );
                
                while( this.getPilha().size() > 0 ) {
                    estruturaNotacao.addFirst( this.getPilha().pop() );
                }
            } else {
            	DefaultMutableTreeNode numero = new DefaultMutableTreeNode("<n�mero>");
	            numero.add( new DefaultMutableTreeNode( "valor = " + estruturaNotacao.removeFirst() ) );
	            this.getPilha().push( numero );
            }
        }        
    } 
    /**
     * M�todo para adicionar um novo elemento na �rvore
     * 
     * @param DefaultMutableTreeNode pai
     * @param Objec obj
     * @return DefaultMutableTreeNode - elemento adicionado
     */
    private DefaultMutableTreeNode addNode( DefaultMutableTreeNode pai, Object obj ) {
        DefaultMutableTreeNode filho = new DefaultMutableTreeNode(obj); 
        pai.add( filho );
        return filho;
    }
    /**
     * M�todo que verifica se o elemento verificado � um operador
     * retorna <b>true</b>, se for um operador
     * 
     * @param Object obj
     * @return boolean
     */
    private boolean isOperator( Object operator ) {
        if( ((String)operator).equals("!") ) {
            return true;
        } else if( ((String)operator).equals("^") ) {
            return true;
        } else if( ((String)operator).equals("*") ) {
            return true;
        } else if( ((String)operator).equals("/") ) {
            return true;
        } else if( ((String)operator).equals("%") ) {
            return true;
        } else if( ((String)operator).equals("+") ) {
            return true;
        } else if( ((String)operator).equals("-") ) {
            return true;
        }
        return false;
    }
    /**
     * M�todo que verifica se o elemento verificado � um operador
     * retorna o n�mero da opera��o
     * 
     * @param Object obj
     * @return int
     */
    private int typeOperator( Object operator ) {
        if( ((String)operator).equals("!") ) {
            return 1;
        } else if( ((String)operator).equals("^") ) {
            return 2;
        } else if( ((String)operator).equals("*") ) {
            return 3;
        } else if( ((String)operator).equals("/") ) {
            return 4;
        } else if( ((String)operator).equals("%") ) {
            return 5;
        } else if( ((String)operator).equals("+") ) {
            return 6;
        } else if( ((String)operator).equals("-") ) {
            return 7;
        }
        return 8;
    }
    /**
     * M�todo que retorna o nome da opera��o
     * 
     * @param Object obj
     * @return String
     */
    private String nameOperator( Object operator ) {
        String out = "";
        if( ((String)operator).equals("!") ) {
            out = "fatorial";
        } else if( ((String)operator).equals("^") ) {
            out = "pot�ncia";
        } else if( ((String)operator).equals("*") ) {
            out = "multiplica��o";
        } else if( ((String)operator).equals("/") ) {
            out = "divis�o";
        } else if( ((String)operator).equals("%") ) {
            out = "resto divis�o";
        } else if( ((String)operator).equals("+") ) {
            out = "soma";
        } else if( ((String)operator).equals("-") ) {
            out = "subtra��o";
        }
        return out;
    }
    /*------------------------------------------------------------------------*
     *  Fim Servi�os da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  M�todos de calculo da classe
     *------------------------------------------------------------------------*/
    /**
     * Metodo que realiza o calculo da seguinte express�o repassada
     * >>> 
     * <code> < n�mero >< operador >< n�mero ></code>
     */
    private String calcula( Object o1, Object operador, Object o2 ) {
        String resultado = "";
        switch( this.typeOperator( operador ) ) {
        	case 1: // fatorial
        	    resultado = this.fatorial( o2 );
        	    break;
        	case 2: // pot�ncia
        	    resultado = this.potencia( o1, o2 );
        	    break;
        	case 3: // multiplica��o
        	    resultado = this.multiplicacao( o1, o2 );
        	    break;
        	case 4: // divis�o
        	    resultado = this.divisao( o1, o2 );
        	    break;
        	case 5: // resto da divis�o
        	    resultado = this.mod( o1, o2 );
        	    break;
        	case 6: // soma
        	    resultado = this.soma( o1, o2 );
        	    break;
        	case 7: // subtra��o
        	    resultado = this.subtracao( o1, o2 );
        	    break;
        }
        return resultado;
    }
    /**
     * M�todo que calcula o fatorial
     * 
     * @param Object o - n�mero a ser calculado o fatorial
     * @return String - resultado do fatorial
     */
    private String fatorial( Object o ) {
        double num = Double.parseDouble( o.toString() );
        double tmp = 1;
        while( num > 1  ) {
            tmp *= num--;
        }
        return Double.toString( tmp );
    }
    /**
     * M�todo que calcula a potencia de um n�mero pelo seu expoente
     * @param Object num
     * @param Object exp
     * @return String - resultado convertido
     */
    private String potencia( Object num, Object exp ) {
        return Double.toString( Math.pow( Double.parseDouble(num.toString()), Double.parseDouble(exp.toString()) ) );
    }
    /**
     * M�todo para calcular a multiplica��o
     * @param Object f1 - n�mero
     * @param Object f2 - n�mero
     * @return String - resultado da multiplica��o
     */
    private String multiplicacao( Object f1, Object f2 ) {
        return Double.toString( Double.parseDouble( f1.toString() ) * Double.parseDouble( f2.toString() ) );
    }
    /**
     * M�todo para calcular a divis�o
     * @param Object f1 - n�mero
     * @param Object f2 - n�mero
     * @return String - resultado da divis�o
     */
    private String divisao( Object f1, Object f2 ) {
        return Double.toString( Double.parseDouble( f1.toString() ) / Double.parseDouble( f2.toString() ) );
    }
    /**
     * M�todo para calcular resto da divis�o
     * @param Object f1 - n�mero
     * @param Object f2 - n�mero
     * @return String - resultado resto da divis�o
     */
    private String mod( Object f1, Object f2 ) {
        return Double.toString( Double.parseDouble( f1.toString() ) % Double.parseDouble( f2.toString() ) );
    }
    /**
     * M�todo para calcular soma
     * @param Object f1 - n�mero
     * @param Object f2 - n�mero
     * @return String - resultado soma
     */
    private String soma( Object f1, Object f2 ) {
        return Double.toString( Double.parseDouble( f1.toString() ) + Double.parseDouble( f2.toString() ) );
    }
    /**
     * M�todo para calcular subtra��o
     * @param Object f1 - n�mero
     * @param Object f2 - n�mero
     * @return String - resultado subtra��o
     */
    private String subtracao( Object f1, Object f2 ) {
        return Double.toString( Double.parseDouble( f1.toString() ) - Double.parseDouble( f2.toString() ) );
    }
    /*------------------------------------------------------------------------*
     *  Fim M�todos de calculo da classe
     *------------------------------------------------------------------------*/
}
