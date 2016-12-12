package tcc.ufpb.com.br.tcc;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Deyvison on 31/07/2016.
 */
public class Palavra implements Serializable{

    private String palavra;
    private int drawablePath;
    private Niveis nivel;

    public Palavra(String palavra, int drawablePath, Niveis nivel){
        this.palavra = palavra.toUpperCase();
        this.drawablePath = drawablePath;
        this.nivel = nivel;
    }

    public String getNome() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int getPathImagem() {
        return drawablePath;
    }

    public void setPathImagem(int drawablePath) {
        this.drawablePath = drawablePath;
    }
    // palavra e imagem


    public Niveis getNivel(){
        return this.nivel;
    }
}
