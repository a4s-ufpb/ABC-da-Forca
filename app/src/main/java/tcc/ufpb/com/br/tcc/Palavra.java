package tcc.ufpb.com.br.tcc;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Deyvison on 31/07/2016.
 */
public class Palavra implements Serializable{

    private boolean itemDefault;
    private String palavra;
    private String pathImagem;
    private Niveis nivel;

    public Palavra(String palavra, String drawablePath, Niveis nivel, boolean itemDefault){
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

    public void setPathImagem(String drawablePath) {
        this.pathImagem = drawablePath;
    }
    // palavra e imagem


    public Niveis getNivel(){
        return this.nivel;
    }

    public boolean getDefault(){
        return this.itemDefault;
    }
}
