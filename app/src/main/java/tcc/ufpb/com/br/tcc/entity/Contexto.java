package tcc.ufpb.com.br.tcc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Deyvison on 03/10/2016.
 */
public class Contexto implements Serializable {

    private boolean itemDefault;
    private String nome;
    private String pathImagem;
    private ArrayList<Palavra> facil;
    private ArrayList<Palavra> medio;
    private ArrayList<Palavra> dificil;

    public Contexto(String nome, String pathImagem, boolean itemDefault){
        this.itemDefault = itemDefault;
        this.nome = nome;
        this.pathImagem = pathImagem;
        this.facil = new ArrayList<>();
        this.medio = new ArrayList<>();
        this.dificil = new ArrayList<>();
    }

    public List<Palavra> getPalavraPorNivel(Niveis nivel){
        if(nivel == Niveis.FACIL)
            return this.facil;
        else if(nivel == Niveis.MEDIO)
            return this.medio;
        else
            return this.dificil;
    }

    public String getPathImagem(){
        return this.pathImagem;
    }

    public String getNome(){
        return this.nome;
    }

    public void adicionarPalavra(Palavra p){
        if(p.getNivel() == Niveis.FACIL)
            this.facil.add(p);
        else if(p.getNivel() == Niveis.MEDIO)
            this.medio.add(p);
        else
            this.dificil.add(p);
    }

    public List<Palavra> getPalavras(){
        List<Palavra> palavras = new ArrayList<>();
        palavras.addAll(facil);
        palavras.addAll(medio);
        palavras.addAll(dificil);
        return palavras;
    }

    public boolean getDefault(){
        return this.itemDefault;
    }

    public List<Palavra> getPalavrasNivelDificil() {
        return this.dificil;
    }

    public List<Palavra> getPalavrasNivelMedio() {
        return this.medio;
    }

    public List<Palavra> getPalavrasNivelFacil() {
        return this.facil;
    }

    public void setNome(String nome){this.nome = nome;}

    public void setPath(String path){
        this.pathImagem = path;
    }

    public void removerPalavra (Palavra p, Niveis nivel){
        if(nivel == Niveis.FACIL){
            facil.remove(p);
        }else if(nivel == Niveis.MEDIO){
            medio.remove(p);
        }else{
            dificil.remove(p);
        }
    }

    public void setPalavraNivelFacil(ArrayList<Palavra> palavras){
        this.facil = palavras;
    }

    public void setPalavraNivelMedio(ArrayList<Palavra> palavras){
        this.medio = palavras;
    }

    public void setPalavraNivelHard(ArrayList<Palavra> palavras){
        this.dificil = palavras;
    }
}
