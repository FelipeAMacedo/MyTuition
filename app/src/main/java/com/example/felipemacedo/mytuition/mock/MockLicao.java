package com.example.felipemacedo.mytuition.mock;

import android.widget.ArrayAdapter;

import com.example.felipemacedo.mytuition.enums.TipoConteudo;
import com.example.felipemacedo.mytuition.model.Conteudo;
import com.example.felipemacedo.mytuition.model.Licao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipemacedo on 22/09/17.
 */

public class MockLicao {

    public List<Licao> getLicoes() {
        ArrayList<Licao> licoes = new ArrayList<>();
        ArrayList<Conteudo> conteudos1 = new ArrayList<>();
        ArrayList<Conteudo> conteudos2 = new ArrayList<>();
        ArrayList<Conteudo> conteudos3 = new ArrayList<>();




        //  -------------------




        Licao licao1 = new Licao();
        licao1.setTitulo("Pilha");


        Conteudo c1 = new Conteudo();
        c1.setTexto("É uma lista linear em que todas as inserções e remoções são " +
                "feitas numa mesma extremidade da lista linear. Esta " +
                "extremidade se denomina topo (em inglês “top”) ou lado aberto " +
                "da pilha.");
        c1.setCompletado(true);

        c1.setTipo(TipoConteudo.TEXTO);

        Conteudo c2 = new Conteudo();
        c2.setTexto("Como o último elemento que entrou na pilha será o primeiro a " +
                "sair da pilha, a pilha é conhecida como uma estrutura do tipo " +
                "LIFO (“Last In First Out”)");
        c2.setTipo(TipoConteudo.TEXTO);




        conteudos1.add(c1);
        conteudos1.add(c2);



        licoes.add(licao1);







        //  -------------------

        Conteudo completo = new Conteudo();
        completo.setCompletado(true);

        conteudos2.add(completo);
        conteudos2.add(new Conteudo());
        conteudos2.add(new Conteudo());

        Licao licao2 = new Licao();
        licao2.setTitulo("Fila");
        System.out.println(conteudos2.size());
        licoes.add(licao2);


//        conteudos3.add(c);
//        conteudos3.add(new Conteudo());
//        conteudos3.add(c);
//        conteudos3.add(new Conteudo());
//        conteudos3.add(c);
//        conteudos3.add(new Conteudo());
//        conteudos3.add(c);
//
//
//        Licao licao3 = new Licao();
//        licao3.setTitulo("Lista ligada");
//        licao3.setConteudos(conteudos3);
//        System.out.println(conteudos3.size());
//        licoes.add(licao3);

        return licoes;
    }
}
