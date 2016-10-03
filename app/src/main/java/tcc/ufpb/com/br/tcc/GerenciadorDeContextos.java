package tcc.ufpb.com.br.tcc;

import java.util.ArrayList;

/**
 * Created by Deyvison on 03/10/2016.
 */
public class GerenciadorDeContextos {

    private ArrayList<Contexto> contextos;

    public GerenciadorDeContextos(){
        this.contextos = new ArrayList<>();
    }

    public void addContexto(Contexto c){
        this.contextos.add(c);
    }
}
