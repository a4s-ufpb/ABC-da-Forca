package tcc.ufpb.com.br.tcc;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Deyvison on 31/07/2016.
 */
public class Palavra {

    private String palavra;
    private int drawablePath;


    public Palavra(String palavra, int drawablePath){
        this.palavra = palavra.toUpperCase();
        this.drawablePath = drawablePath;
    }

    public String getPalavra() {
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
}
