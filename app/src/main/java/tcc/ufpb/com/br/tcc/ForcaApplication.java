package tcc.ufpb.com.br.tcc;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deyvison on 05/10/2016.
 */
// classe vai conter todas as operações de inserção e remoção de dados no bd
public class ForcaApplication extends Application {

    private List<Contexto> contextos;

    @Override
    public void onCreate() {
        super.onCreate();

        contextos = new ArrayList<>();

        criarContextos();


    }

    private void criarContextos(){

        Contexto animais,frutas;
        animais = new Contexto("Animais", R.drawable.animais);
        frutas = new Contexto("Frutas", R.drawable.frutas);

        // Animais

        // facil
        animais.adicionarPalavra(new Palavra("Sapo",R.drawable.sapo,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Macaco",R.drawable.macaco,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Gato",R.drawable.gato,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Pato",R.drawable.pato,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Jabuti",R.drawable.jabuti,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Baleia",R.drawable.baleia,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Rato",R.drawable.rato,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Galo",R.drawable.galo,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Arara",R.drawable.arara,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Vaca",R.drawable.vaca,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Boi",R.drawable.boi,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Girafa",R.drawable.girafa,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Cavalo",R.drawable.cavalo,Niveis.FACIL));
        animais.adicionarPalavra(new Palavra("Bode",R.drawable.bode,Niveis.FACIL));

        //medio
        animais.adicionarPalavra(new Palavra("Coelho",R.drawable.coelho, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Cachorro",R.drawable.cachorro, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Tartaruga",R.drawable.tartaruga, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Leão",R.drawable.leao, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Porco",R.drawable.porco, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Tigre",R.drawable.tigre, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Abelha",R.drawable.abelha, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Zebra",R.drawable.zebra, Niveis.MEDIO));
        animais.adicionarPalavra(new Palavra("Urso",R.drawable.urso, Niveis.MEDIO));

        //dificil

        animais.adicionarPalavra(new Palavra("Caranguejo", R.drawable.caranguejo, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Caracol", R.drawable.caracol, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Pulga", R.drawable.pulga, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Periquito", R.drawable.periquito, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Ovelha", R.drawable.ovelha, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Elefante", R.drawable.elefante, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Borboleta", R.drawable.borboleta, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Tubarão", R.drawable.tubarao, Niveis.DIFICIL));
        animais.adicionarPalavra(new Palavra("Hipopótamo", R.drawable.hipopotamo, Niveis.DIFICIL));

        // Frutas

        //facil
        frutas.adicionarPalavra(new Palavra("Goiaba", R.drawable.goiaba,Niveis.FACIL));
        frutas.adicionarPalavra(new Palavra("Figo", R.drawable.figo,Niveis.FACIL));
        frutas.adicionarPalavra(new Palavra("Tomate", R.drawable.tomate,Niveis.FACIL));
        frutas.adicionarPalavra(new Palavra("Caju", R.drawable.caju,Niveis.FACIL));
        frutas.adicionarPalavra(new Palavra("Pera", R.drawable.pera,Niveis.FACIL));
        frutas.adicionarPalavra(new Palavra("Amora", R.drawable.amora,Niveis.FACIL));
        frutas.adicionarPalavra(new Palavra("Coco", R.drawable.coco,Niveis.FACIL));

        //medio
        frutas.adicionarPalavra(new Palavra("Laranja",R.drawable.laranja, Niveis.MEDIO));
        frutas.adicionarPalavra(new Palavra("Abacate",R.drawable.abacate, Niveis.MEDIO));
        frutas.adicionarPalavra(new Palavra("Mangaba",R.drawable.mangaba, Niveis.MEDIO));
        frutas.adicionarPalavra(new Palavra("Sapoti",R.drawable.sapoti, Niveis.MEDIO));
        frutas.adicionarPalavra(new Palavra("Banana",R.drawable.banana, Niveis.MEDIO));

        //dificil
        frutas.adicionarPalavra(new Palavra("Limão",R.drawable.limao, Niveis.DIFICIL));
        frutas.adicionarPalavra(new Palavra("Mexerica",R.drawable.mexerica, Niveis.DIFICIL));
        frutas.adicionarPalavra(new Palavra("Jabuticaba",R.drawable.jabuticaba, Niveis.DIFICIL));
        frutas.adicionarPalavra(new Palavra("Jambo",R.drawable.jambo, Niveis.DIFICIL));


        contextos.add(animais);
        contextos.add(frutas);


    }

    private void criarPalavras(){

    }

    public List<Contexto> getContextos(){
        return contextos;
    }
}
