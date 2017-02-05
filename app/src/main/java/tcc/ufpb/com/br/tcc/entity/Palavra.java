package tcc.ufpb.com.br.tcc.entity;

import java.io.Serializable;

/**
 * Created by Deyvison on 31/07/2016.
 */
public class Palavra implements Serializable{

    private Boolean itemDefault;
    private String palavra;
    private String pathImagem;
    private Niveis nivel;

    public Palavra(String palavra, String drawablePath, Niveis nivel, Boolean itemDefault){
        this.itemDefault = itemDefault;
        this.palavra = palavra.toUpperCase();
        this.pathImagem = drawablePath;
        this.nivel = nivel;
    }

    public String getNome() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public Niveis getNivel(){
        return this.nivel;
    }

    public boolean getDefault(){
        return this.itemDefault;
    }
}
