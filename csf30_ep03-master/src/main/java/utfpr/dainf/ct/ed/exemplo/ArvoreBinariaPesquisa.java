package utfpr.dainf.ct.ed.exemplo;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * Exemplo de implementação de árvore binária de pesquisa.
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós da árvore
 */
public class ArvoreBinariaPesquisa<E extends Comparable<E>> extends ArvoreBinaria<E> {

    public ArvoreBinariaPesquisa<E> pai;
    protected Stack<ArvoreBinariaPesquisa<E>> pilha;
    protected ArvoreBinariaPesquisa<E> ultimoVisitado;
    private boolean start = true;
    private ArvoreBinariaPesquisa<E> noPos;

    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }

    public void reinicia() {
        inicializaPilha();
        pilha.clear();
        ultimoVisitado = this;
        start = true;
    }

    /**
     * Retorna a árvore esqueda.
     *
     * @return A árvore esquerda.
     */
    protected ArvoreBinaria<E> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna a árvore direita.
     *
     * @return A árvore direita.
     */
    protected ArvoreBinaria<E> getDireita() {
        return direita;
    }

    /**
     * Inicializa a árvore esquerda.
     *
     * @param esquerda A árvore esquerda.
     */
    protected void setEsquerda(ArvoreBinariaPesquisa<E> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Inicializa a árvore direita.
     *
     * @param direita A árvore direita.
     */
    protected void setDireita(ArvoreBinariaPesquisa<E> direita) {
        this.direita = direita;
    }

    public ArvoreBinariaPesquisa<E> getRoot() {
        ArvoreBinariaPesquisa<E> raiz = this;

        while (raiz.pai != null) {
            raiz = raiz.pai;
        }
        return raiz;
    }

    public ArvoreBinariaPesquisa<E> insereEsquerda(ArvoreBinariaPesquisa<E> x) {
        ArvoreBinariaPesquisa<E> y = (ArvoreBinariaPesquisa<E>) esquerda;
        ArvoreBinariaPesquisa<E> z = x;
        esquerda = x;
        while (z.esquerda != null) {
            z = (ArvoreBinariaPesquisa<E>) z.esquerda;
        }
        z.esquerda = y;
        return x;
    }

    public ArvoreBinariaPesquisa<E> insereDireita(ArvoreBinariaPesquisa<E> x) {
        ArvoreBinariaPesquisa<E> y = (ArvoreBinariaPesquisa<E>) direita;
        ArvoreBinariaPesquisa<E> z = x;
        direita = x;
        while (z.direita != null) {
            z = (ArvoreBinariaPesquisa<E>) z.direita;
        }
        z.direita = y;
        return x;
    }

    public void visitaEmOrdem(ArvoreBinariaPesquisa<E> raiz) {
        if (raiz != null) {
            visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            visitaEmOrdem(raiz.direita);
        }
    }

    public ArvoreBinariaPesquisa<E> proximoEmOrdem() {
        ArvoreBinariaPesquisa<E> retorno = null;
        if (start) {
            reinicia();
            start = false;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = (ArvoreBinariaPesquisa<E>) ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            retorno = ultimoVisitado;
            ultimoVisitado = (ArvoreBinariaPesquisa<E>) ultimoVisitado.direita;
        }
        return retorno;
    }

    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }

    /**
     * Cria uma árvore com valor nulo na raiz.
     */
    public ArvoreBinariaPesquisa() {
    }

    /**
     * Cria uma árvore com o valor especificado na raiz.
     *
     * @param valor O valor armazenado na raiz.
     */
    public ArvoreBinariaPesquisa(E valor) {
        super(valor);
    }

    /**
     * Inicializa o nó pai deste nó.
     *
     * @param pai O nó pai.
     */
    protected void setPai(ArvoreBinariaPesquisa<E> pai) {
        this.pai = pai;
    }

    /**
     * Retorna o nó pai deste nó.
     *
     * @return O nó pai.
     */
    public ArvoreBinariaPesquisa<E> getPai() {
        return pai;
    }

    public ArvoreBinariaPesquisa<E> pesquisa(E value) {
        
        if (value.compareTo(this.value) == 0) {
            return this;
        } else if (value.compareTo(this.value) < 0) {
            if (this.esquerda == null) {
                return null;
                
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.esquerda).pesquisa(value);
            }
        } else {
            if (this.direita == null) {
                return null;
                
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.direita).pesquisa(value);
            }
        }
    }

    public ArvoreBinariaPesquisa<E> getMinimoRamo() 
    {
        ArvoreBinariaPesquisa<E> retorno = this;
        while (retorno.esquerda != null) { 
            retorno = (ArvoreBinariaPesquisa) retorno.esquerda;
        }
        return retorno;
    }
	
    public ArvoreBinariaPesquisa<E> getMinimo() 
    {
        ArvoreBinariaPesquisa<E> raiz = getRoot();
      
        ArvoreBinariaPesquisa<E> retorno = raiz;
        while (retorno.esquerda != null) { 
            retorno = (ArvoreBinariaPesquisa) retorno.esquerda;
        }
        return retorno;
    }

    
    
    
    public ArvoreBinariaPesquisa<E> getMaximoRamo(){      
        ArvoreBinariaPesquisa<E> retorno = this;
        while (retorno.direita != null) { 
            retorno = (ArvoreBinariaPesquisa) retorno.direita;
        }
        return retorno;
    }
	
    public ArvoreBinariaPesquisa<E> getMaximo() {
        ArvoreBinariaPesquisa<E> raiz = getRoot();
      
        ArvoreBinariaPesquisa<E> retorno = raiz;
        while (retorno.direita != null) { 
            retorno = (ArvoreBinariaPesquisa) retorno.direita;
        }
        return retorno;
    }

    
    public ArvoreBinariaPesquisa<E> sucessor(ArvoreBinariaPesquisa<E> node) {
        ArvoreBinariaPesquisa<E> raiz = getRoot();
        
        node = raiz.pesquisa(node.valor);
        if(node==null){
            return null;
        }
        if (node.direita != null) {
            return ((ArvoreBinariaPesquisa<E>) node.direita).getMinimoRamo();
        }

        ArvoreBinariaPesquisa<E> pai = node.pai;

        while (pai != null && node.equals(pai.direita)) {
            node = pai;
            pai = pai.pai;
        }

        return pai;
    }

    public ArvoreBinariaPesquisa<E> predecessor(ArvoreBinariaPesquisa<E> node) {
        ArvoreBinariaPesquisa<E> raiz = getRoot();
        
        node = root.pesquisa(node.valor);
        
        if (node != null && node.esquerda != null) {
            return ((ArvoreBinariaPesquisa<E>) node.esquerda).getMaximoRamo();
        }

        ArvoreBinariaPesquisa<E> pai = node.pai;

        while (pai != null && no.equals(pai.esquerda)) {
            node = pai;
            pai = pai.pai;
        }
        return pai;
    }

    public ArvoreBinariaPesquisa<E> insere(E valor) {
        return insere(new ArvoreBinariaPesquisa<>(valor));
    }

        
    public ArvoreBinariaPesquisa<E> insere(ArvoreBinariaPesquisa<E> node) {
        ArvoreBinariaPesquisa<E> raiz = getRoot();
        
        return raiz.inserir(node);
    }
    
    public ArvoreBinariaPesquisa<E> inserir(ArvoreBinariaPesquisa<E> node) {
        
        if (node.valor.compareTo(this.valor) < 0) {
            if (this.esquerda == null) {

                this.setEsquerda(node);
                node.setPai(this);
                return no;
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.esquerda).inserir(node);
            }
        } else {
            if (this.direita == null) {

                this.setDireita(node);
                node.setPai(this);
                return no;
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.direita).inserir(node);
            }
        }
    }

    
    
    public ArvoreBinariaPesquisa<E> exclui(ArvoreBinariaPesquisa<E> node) {
        node = pesquisa(node.valor);
        ArvoreBinariaPesquisa<E> retorno = node;

        if (retorno.direita == null && retorno.esquerda == null) {
            if (retorno.pai == null)
            {
                node.valor = null;
            } else {
                if (retorno == retorno.pai.direita) {
                    retorno.pai.direita = null;
                } else if (retorno == retorno.pai.esquerda) {
                    retorno.pai.esquerda = null;
                }
            }
        } 
        else if (retorno.direita != null && retorno.esquerda == null) 
        {
            if (retorno.pai != null) 
            {
                ((ArvoreBinariaPesquisa<E>) (retorno.direita)).setPai(retorno.pai);
                if (retorno == retorno.pai.direita) {
                    retorno.pai.direita = retorno.direita;
                } else if (retorno == retorno.pai.esquerda) {
                    retorno.pai.esquerda = retorno.direita;
                }
            } else 
            {
                retorno.valor = retorno.direita.valor;
                retorno.direita = null;
            }
        } else if (retorno.direita == null && retorno.esquerda != null) 
        {
            if (retorno.pai != null) {
                ((ArvoreBinariaPesquisa<E>) (retorno.esquerda)).setPai(retorno.pai); 
                if (retorno == retorno.pai.direita) {
                    retorno.pai.direita = retorno.esquerda;
                } else if (retorno == retorno.pai.esquerda) {
                    retorno.pai.esquerda = retorno.esquerda;
                }
            } else 
            {
                retorno.valor = retorno.esquerda.valor;
                retorno.esquerda = null;
            }
        } 
        else if (retorno.direita != null && retorno.esquerda != null) {
            while (retorno.direita != null && retorno.esquerda != null) 
            {
                retorno = sucessor(retorno);
            }
            exclui(retorno);
            node.valor = retorno.valor;

        }
        return this; 
    }
}
