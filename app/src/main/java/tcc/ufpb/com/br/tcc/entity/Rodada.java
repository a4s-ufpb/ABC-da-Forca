package tcc.ufpb.com.br.tcc.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Deyvison on 15/10/2016.
 */
public class Rodada {

    private Contexto contexto;
    private int qtPalavrasPorRodada;
    private Niveis nivelDaRodada;

    private List<Palavra> palavrasAleatorias;

    public Rodada(){
        this.palavrasAleatorias = new ArrayList<>();
    }

    private void gerarPalavrasAleatorias(){
        Random rd = new Random();
        List<Palavra> todasAsPalavras = contexto.getPalavraPorNivel(nivelDaRodada); // array deve conter no minimo o mesmo valor que qtPalavrasPorRodada
        List<Palavra> palavrasSorteio = new ArrayList<>();
        palavrasSorteio.addAll(todasAsPalavras);
        if(todasAsPalavras.size() >= this.qtPalavrasPorRodada){
            for(int i = 0 ; i < this.qtPalavrasPorRodada ; i++){
                int k = rd.nextInt(palavrasSorteio.size());
                Palavra palavraSorteada = palavrasSorteio.get(k);
                this.palavrasAleatorias.add(palavraSorteada);
                palavrasSorteio.remove(palavraSorteada);
            }
        }
    }

    public Palavra getPalavraDaVez(){
        Palavra p = this.palavrasAleatorias.get(0);
        palavrasAleatorias.remove(0);
        return p;
    }

    public boolean fimDeRodada(){
        if(palavrasAleatorias.size() == 0)
            return true;
        else
            return false;
    }

    public void iniciarRodada(Contexto contexto, Niveis nivel, int qtPalavrasPorRodada ){
        this.contexto = contexto;
        this.nivelDaRodada = nivel;
        this.qtPalavrasPorRodada = qtPalavrasPorRodada;
        gerarPalavrasAleatorias();
    }
}
