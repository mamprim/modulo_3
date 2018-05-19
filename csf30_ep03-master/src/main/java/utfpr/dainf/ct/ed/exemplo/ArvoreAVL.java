package utfpr.dainf.ct.ed.exemplo;

import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * Exemplo de implementação de árvore AVL.
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós da árvore
 */
public class ArvoreAVL<E extends Comparable<E>> extends ArvoreBinariaPesquisa<E> {

    protected byte fb;
    /**
     * Cria uma árvore AVL com o valor da raiz nulo
     */
    public ArvoreAVL() {
    }

    /**
     * Cria uma árvore AVL cuja raiz armazena o valor especificado.
     *
     * @param valor O valor a ser armazenado
     */
    public ArvoreAVL(E valor) {
        super(valor);
    }

    /**
     * Retorna o fator de balanço deste nó.
     *
     * @return O fator de balanço deste nó
     */
    public byte getFb() {
        return fb;
    }

    /**
     * Inicializa o fator de balaço deste nó.
     *
     * @param fb O fator de balanço a ser atribuído
     */
    protected void setFb(byte fb) {
        this.fb = fb;
    }

    /**
     * Executa a operação de rotação à esquerda em torno do nó especificado.
     *
     * @param x O nó pivo (desregulado)
     * @return O nó que ocupou o lugar de x
     */
    protected ArvoreAVL<E> rotacaoEsquerda(ArvoreAVL<E> x) {
        ArvoreAVL<E> raiz = (ArvoreAVL<E>) getRoot();
        ArvoreAVL<E> y = (ArvoreAVL) x.direita;

        x.setDireita(y.esquerda);
        y.setPai(x.pai);

        if (x.pai == null) {
            raiz = y;
        } else {
            if (x == x.pai.esquerda) {
                x.pai.setEsquerda(y);
            } else {
                x.pai.setDireita(y);
            }
        }

        y.setEsquerda(x);

        x.setPai(y);

        return raiz;
    }

    /**
     * Executa a operação de rotação à direita em torno do nó especificado.
     *
     * @param y O nó pivo (desregulado)
     * @return O nó que ocupou o lugar de y
     */
    protected ArvoreAVL<E> rotacaoDireita(ArvoreAVL<E> y) {
        ArvoreAVL<E> root = (ArvoreAVL<E>) getRoot();
        ArvoreAVL<E> x = (ArvoreAVL) y.esquerda;

        y.setEsquerda(x.direita);
        x.setPai(y.pai);

        if (y.pai == null) {
            root = x;
        } else {
            if (y == y.pai.esquerda) {
                y.pai.setEsquerda(x);
            } else {
                y.pai.setDireita(x);
            }
        }

        x.setDireita(y);

        y.setPai(x);

        return root;
    }

    protected ArvoreAVL<E> rotacao(ArvoreAVL<E> p, ArvoreAVL<E> q) {
        ArvoreAVL<E> root = (ArvoreAVL<E>) getRoot();
        ArvoreAVL<E> u = q;

        while (u.pai != p && u.pai != null) {
            u = (ArvoreAVL) u.pai;
        }

        if (p.fb < 0) {
            if (u.fb < 0) {
                root = root.rotacaoDireita(p);
            } else {
                root = root.rotacaoEsquerda((ArvoreAVL) p.esquerda);
                root = root.rotacaoDireita(p);
            }
        } else {
            if (u.fb > 0) {
                root = root.rotacaoEsquerda(p);
            } else {
                root = root.rotacaoDireita((ArvoreAVL) p.direita);
                root = root.rotacaoEsquerda(p);
            }
        }

        return root;
    }

    
    protected ArvoreAVL<E> ajustaFbInsercao(ArvoreAVL<E> node) {
        ArvoreAVL<E> comp = node;
        while (comp != null) {
            int a = calculaAltura(comp.esquerda);
            int b = calculaAltura(comp.direita);
            comp.setFb((byte) (b - a));
            comp = (ArvoreAVL) comp.pai;
        }

        return this;
    }

    protected ArvoreAVL<E> ajustaFbExclusao(ArvoreAVL<E> no) {
        ArvoreAVL<E> comp = (ArvoreAVL<E>) getMinimo();

        while (comp != null) {
            comp.fb = (byte) (calculaAltura(comp.direita) - calculaAltura(comp.esquerda));
            comp = (ArvoreAVL<E>) sucessor(comp);
        }

        return this;
    }

    protected ArvoreAVL<E> insere(ArvoreAVL<E> node) {
        super.insere(node);

        ArvoreAVL<E> comp = node;
        ajustaFbInsercao(node);

        while (comp != null) {
            
            if (Math.abs(comp.fb) > 1) {
                break;
            }
            comp = (ArvoreAVL) comp.pai;
        }
        
        if (comp != null) {
            rotacao(comp, node);
            ajustaFbExclusao(node);
        }

        return node;
        
    }

    public ArvoreAVL<E> insere(E valor) {
        return insere(new ArvoreAVL<>(valor));
    }

    public ArvoreAVL<E> exclui(ArvoreAVL<E> node) {
        ArvoreAVL<E> raiz = (ArvoreAVL<E>) getRoot();

        node = (ArvoreAVL) raiz.pesquisa(node.valor);

        if (raiz == node && (raiz.esquerda == null || raiz.direita == null)) {
            if (raiz.esquerda != null) {
                ((ArvoreAVL) raiz.esquerda).pai = null;
                return (ArvoreAVL) raiz.esquerda;
            } else {
                if (raiz.direita != null) {
                    ((ArvoreAVL) raiz.direita).pai = null;
                }
                return (ArvoreAVL) raiz.direita;
            }
        }

        if (node.esquerda != null && node.direita != null) {
            ArvoreAVL<E> proximo = node;
            do {
                proximo = (ArvoreAVL) sucessor(proximo);
            } while (proximo.direita == null
                    || proximo.esquerda == null);

            E value = proximo.valor;
            exclui(proximo);
            node.valor = value;

        } else {
            

            boolean isFilhoDaEsquerda = (node.pai.esquerda == node);
            boolean isTemFilhoEsquerda = (node.esquerda != null);

            
            if (node.esquerda == null && node.direita == null) {
                if (isFilhoDaEsquerda) {
                    node.pai.esquerda = null;
                } else {
                    node.pai.direita = null;
                }
            }
            else {
                if (isFilhoDaEsquerda) {
                    if (isTemFilhoEsquerda) {
                        node.pai.esquerda = node.esquerda;
                        ((ArvoreAVL<E>) node.esquerda).pai = node.pai;
                    } else {
                        node.pai.esquerda = node.direita;
                        ((ArvoreAVL<E>) node.direita).pai = node.pai;
                    }
                } else {
                    if (isTemFilhoEsquerda) {
                        node.pai.direita = node.esquerda;
                        ((ArvoreAVL<E>) node.esquerda).pai = node.pai;
                    } else {
                        node.pai.direita = node.direita;
                        ((ArvoreAVL<E>) node.direita).pai = node.pai;
                    }
                }
            }

            node = (ArvoreAVL) node.pai;
            int h;
            while (node != null) {
                h = (calculaAltura(node.direita) - calculaAltura(node.esquerda));

                if (Math.abs(h) > 1) {
                    if (h < 0) {
                        raiz = raiz.rotacaoDireita(node);
                    } else {
                        raiz = raiz.rotacaoEsquerda(node);
                    }
                }

                node = (ArvoreAVL) node.pai;
            }

            ajustaFbExclusao(node);
        }

        return raiz;
    }

}
