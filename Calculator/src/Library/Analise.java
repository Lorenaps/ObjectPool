package Library;

/*
 * Created on 09/04/2005
 */


import javax.swing.tree.DefaultMutableTreeNode;

//import com.erkobridee.calculadora.lexica.util.InfixToPostfix;
//import com.erkobridee.calculadora.lexica.util.PostfixEvaluator;


/**
 * Classe que analisa uma string para execu��o de uma express�o
 * matem�tiva de 4 opera��es
 * 
 * @author <a href="mailto:erko.bridee@gmail.com">Erko Bridee de Almeida Cabrera</a>
 */
public class Analise {

    /*------------------------------------------------------------------------*
     *  Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/
    /**
     * <code>expressao</code> -
     * express�o a ser analisada
     */
    private String expressao;
    /**
     * <code>resultado</code> - 
     * resultado do calculo da express�o
     */
    private String resultado;
    /**
     * <code>infixToPostfix</code> - 
     * objeto para convers�o da nota��o infixa para nota��o p�s-fixa
     */
    private InfixToPostfix infixToPostfix; 
    /**
     * <code>postfixEvaluator</code> -
     * objeto para calculo/avalia��o da nota��o p�s fixa 
     */
    private PostfixEvaluator postfixEvaluator;
    /** �rvore de an�lise */
    private DefaultMutableTreeNode analise;
    /*------------------------------------------------------------------------*
     *  Fim Declara��o das vari�veis da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  Construtores da classe
     *------------------------------------------------------------------------*/
    /**
     * Construtor sem parametros da classe
     */
    public Analise() {}
    /**
     * Construtor da classe que recebe como parametro
     * a express�o matem�tica que ir� analisar
     * @param String expressao
     */
    public Analise(String expressao) {
        this.setExpressao( expressao );
    }
    /*------------------------------------------------------------------------*
     *  Fim Construtores da classe
     *------------------------------------------------------------------------*/
    
    /*------------------------------------------------------------------------*
     *  M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/
    /**
     * @return String expressao.
     */
    public String getExpressao() {
        return expressao;
    }
    /**
     * @param String expressao.
     */
    public void setExpressao(String expressao) {
        this.expressao = expressao;
        this.calcula();
    }
    /**
     * @return String resultado.
     */
    public String getResultado() {
        this.calcula();
        return this.resultado;
    }
    /**
     * @param String resultado
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    /**
     * @return InfixToPostfix infixToPostfix.
     */
    public InfixToPostfix getInfixToPostfix() {
        return infixToPostfix;
    }
    /**
     * @param InfixToPostfix infixToPostfix.
     */
    public void setInfixToPostfix(InfixToPostfix infixToPostfix) {
        this.infixToPostfix = infixToPostfix;
    }
    /**
     * @return PostfixEvaluator postfixEvaluator.
     */
    public PostfixEvaluator getPostfixEvaluator() {
        return postfixEvaluator;
    }
    /**
     * @param PostfixEvaluator postfixEvaluator.
     */
    public void setPostfixEvaluator(PostfixEvaluator postfixEvaluator) {
        this.postfixEvaluator = postfixEvaluator;
    }
    /**
     * @return DefaultMutableTreeNode analise.
     */
    public DefaultMutableTreeNode getAnalise() {
        return analise;
    }
    /**
     * @param DefaultMutableTreeNode analise.
     */
    public void setAnalise(DefaultMutableTreeNode analise) {
        this.analise = analise;
    }
    /*------------------------------------------------------------------------*
     *  Fim M�todos de acesso a vari�veis da classe
     *------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*
     *  Servi�os da classe
     *------------------------------------------------------------------------*/
    /**
     * M�todo que realiza o calculo da express�o e retorna o seu resultado
     */
    private void calcula() {
        this.setInfixToPostfix( new InfixToPostfix( this.getExpressao() ) );
        this.setPostfixEvaluator( new PostfixEvaluator( this.getInfixToPostfix() ) );
        this.setResultado( this.getPostfixEvaluator().getResultado()[0].toString() );
        this.setAnalise( this.getPostfixEvaluator().getRootNode() );
    }
    /*------------------------------------------------------------------------*
     *  Fim Servi�os da classe
     *------------------------------------------------------------------------*/
}
