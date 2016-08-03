package tcc.ufpb.com.br.tcc;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Deyvison on 02/08/2016.
 */
public class GerenciadorDePalavras {

    private ArrayList<Palavra> palavras;

    public GerenciadorDePalavras(){
        this.palavras = new ArrayList<Palavra>();

        Palavra p = new Palavra("BOLA",R.drawable.bola);

        Palavra p1 = new Palavra("CACHORRO",R.drawable.cachorro);

        Palavra p2 = new Palavra("CARRO",R.drawable.carro);

        Palavra p3 = new Palavra("CASA",R.drawable.casa);

        Palavra p4 = new Palavra("MACACO",R.drawable.macaco);


        palavras.add(p);
        palavras.add(p1);
        palavras.add(p2);
        palavras.add(p3);
        palavras.add(p4);
    }


    public Palavra getPalavraAleatoria(){
        Random rd = new Random();
        int i = rd.nextInt(palavras.size());
        return palavras.get(i);
    }



}
