package tcc.ufpb.com.br.tcc;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Deyvison on 03/10/2016.
 */
public class Contexto {

    private String nome;
    private String pathIagem;
    private ArrayList<Palavra> palavras;

    public Contexto(String nome){
        this.nome = nome;
        this.palavras = new ArrayList<>();


        Palavra p = new Palavra("aranha",R.drawable.aranha);
        Palavra p1 = new Palavra("cachorro",R.drawable.cachorro);
        Palavra p2 = new Palavra("coelho",R.drawable.coelho);
        Palavra p3 = new Palavra("coruja",R.drawable.coruja);
        Palavra p4 = new Palavra("elefante",R.drawable.elefante);
        Palavra p5 = new Palavra("galinha",R.drawable.galinha);
        Palavra p6 = new Palavra("girafa",R.drawable.girafa);
        Palavra p7 = new Palavra("gato",R.drawable.gato);
        Palavra p8 = new Palavra("leão",R.drawable.leao);
        Palavra p9 = new Palavra("macaco",R.drawable.macaco);
        Palavra p10 = new Palavra("peixe",R.drawable.peixe);
        Palavra p11 = new Palavra("porco",R.drawable.porco);
        Palavra p12 = new Palavra("sapo",R.drawable.sapo);
        Palavra p13 = new Palavra("tartaruga",R.drawable.tartaruga);
        Palavra p14 = new Palavra("urso",R.drawable.urso);

        palavras.add(p);
        palavras.add(p1);
        palavras.add(p2);
        palavras.add(p3);
        palavras.add(p4);
        palavras.add(p5);
        palavras.add(p6);
        palavras.add(p7);
        palavras.add(p8);
        palavras.add(p9);
        palavras.add(p10);
        palavras.add(p11);
        palavras.add(p12);
        palavras.add(p13);
        palavras.add(p14);
    }

    public Palavra getPalavraAleatoria(){
        Random rd = new Random();
        int i = rd.nextInt(palavras.size());
        return palavras.get(i);
    }

}
