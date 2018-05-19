
import java.util.Arrays;
import java.util.Comparator;
import utfpr.dainf.ct.ed.exemplo.ArvoreAVL;
import utfpr.dainf.ct.ed.exemplo.ArvoreBinaria;
import utfpr.dainf.ct.ed.exemplo.ArvoreBinariaPesquisa;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.testaArvoreAVL();
    }

    private void testaArvoreAVL() {
        System.out.println("\n\nTESTE DE ARVORE BINÁRIA DE PESQUISA");

        // monta a árvore binária de pesquisa
        // e armazena os nós em um vetor para uso posterior
        ArvoreAVL<Integer> a = new ArvoreAVL<>(1);
        System.out.println("\nTESTES DE INSERÇÃO");
        ArvoreAVL[] nos = {a, // 0 raiz
            a.insere(3), // 1
            a.insere(4), // 2
            a.insere(6), // 3
            a.insere(7), // 4
            a.insere(8), // 5
            a.insere(10), // 6
            a.insere(13), // 7
            a.insere(14) // 8
    };
        a = (ArvoreAVL<Integer>) a.getRoot();

        System.out.println("\nPERCURSO RECURSIVO");
        a.visitaEmOrdem();
        System.out.println("\nPERCURSO ITERATIVO");
        ArvoreBinaria<Integer> no;
        while ((no = a.proximoEmOrdem()) != null) {
            System.out.print(" " + no.getValor());
        }
        System.out.println();

        System.out.println("\nPesquisa pelo nó com chave 6");
        ArvoreBinaria<Integer> no6 = a.pesquisa(6);
        System.out.println("Nó localizado: " + (no6 == null ? no6 : no6.getValor()));

        System.out.println("\nPesquisa pelo nó com chave 12");
        ArvoreBinaria<Integer> no12 = a.pesquisa(12);
        System.out.println("Nó localizado: " + (no12 == null ? no12 : no12.getValor()));

        System.out.println("\nNó mínimo");
        ArvoreBinaria<Integer> min = a.getMinimo();
        System.out.println("Nó localizado: " + (min == null ? min : min.getValor()));

        System.out.println("\nNó máximo");
        ArvoreBinaria<Integer> max = a.getMaximo();
        System.out.println("Nó localizado: " + (max == null ? max : max.getValor()));

        Arrays.sort(nos, new Comparator<ArvoreBinariaPesquisa>() {
            @Override
            public int compare(ArvoreBinariaPesquisa o1, ArvoreBinariaPesquisa o2) {
                return ((Comparable) o1.getValor()).compareTo(o2.getValor());
            }
        });
        System.out.println("\nTODOS OS SUCESSORES");
        for (ArvoreBinariaPesquisa abp : nos) {
            ArvoreBinariaPesquisa<Integer> s = a.sucessor(abp);
            System.out.format("Sucessor de %s: %s\n", abp.getValor(), s == null ? s : s.getValor());
        }

        System.out.println("\nTODOS OS PREDECESSORES");
        for (ArvoreBinariaPesquisa abp : nos) {
            ArvoreBinariaPesquisa<Integer> p = a.predecessor(abp);
            System.out.format("Predecessor de %s: %s\n", abp.getValor(), p == null ? p : p.getValor());
        }

        a = (ArvoreAVL) a.getRoot();

        System.out.println("\nDESENHANDO A ARVORE");
        a.imprimePreOrdem(a, 1);
        ////////////////////////////////////////////////////////////////////////////////

        System.out.println("\nTESTES DE EXCLUSÃO");
        for (ArvoreAVL abp : nos) {
            System.out.println("\nExcluindo " + abp.getValor());
            a = a.exclui(abp); // arvore pode mudar
            if (a != null) {
                 a.imprimePreOrdem(a, 1);
            }
        }
    }
}
