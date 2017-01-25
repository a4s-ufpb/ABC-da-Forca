package tcc.ufpb.com.br.tcc;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

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
        animais = new Contexto("Animais", R.drawable.animais+"", true);
        frutas = new Contexto("Frutas", R.drawable.frutas+"", true);

        // Animais

        // facil
        animais.adicionarPalavra(new Palavra("Sapo",R.drawable.sapo+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Macaco",R.drawable.macaco+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Gato",R.drawable.gato+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Pato",R.drawable.pato+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Jabuti",R.drawable.jabuti+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Baleia",R.drawable.baleia+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Rato",R.drawable.rato+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Galo",R.drawable.galo+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Arara",R.drawable.arara+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Vaca",R.drawable.vaca+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Boi",R.drawable.boi+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Girafa",R.drawable.girafa+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Cavalo",R.drawable.cavalo+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Bode",R.drawable.bode+"",Niveis.FACIL, true));

        //medio
        animais.adicionarPalavra(new Palavra("Coelho",R.drawable.coelho+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Cachorro",R.drawable.cachorro+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Tartaruga",R.drawable.tartaruga+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Leão",R.drawable.leao+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Porco",R.drawable.porco+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Tigre",R.drawable.tigre+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Abelha",R.drawable.abelha+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Zebra",R.drawable.zebra+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Urso",R.drawable.urso+"", Niveis.MEDIO, true));

        //dificil

        animais.adicionarPalavra(new Palavra("Caranguejo", R.drawable.caranguejo+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Caracol", R.drawable.caracol+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Pulga", R.drawable.pulga+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Periquito", R.drawable.periquito+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Ovelha", R.drawable.ovelha+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Elefante", R.drawable.elefante+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Borboleta", R.drawable.borboleta+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Tubarão", R.drawable.tubarao+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Hipopótamo", R.drawable.hipopotamo+"", Niveis.DIFICIL, true));

        // Frutas

        //facil
        frutas.adicionarPalavra(new Palavra("Goiaba", R.drawable.goiaba+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Figo", R.drawable.figo+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Tomate", R.drawable.tomate+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Caju", R.drawable.caju+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Pera", R.drawable.pera+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Amora", R.drawable.amora+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Coco", R.drawable.coco+"",Niveis.FACIL, true));

        //medio
        frutas.adicionarPalavra(new Palavra("Laranja",R.drawable.laranja+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Abacate",R.drawable.abacate+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Mangaba",R.drawable.mangaba+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Sapoti",R.drawable.sapoti+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Banana",R.drawable.banana+"", Niveis.MEDIO, true));

        //dificil
        frutas.adicionarPalavra(new Palavra("Limão",R.drawable.limao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mexerica",R.drawable.mexerica+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Jabuticaba",R.drawable.jabuticaba+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Jambo",R.drawable.jambo+"", Niveis.DIFICIL, true));


        contextos.add(animais);
        contextos.add(frutas);


    }

    private void criarPalavras(){

    }

    public void adicionarContexto(Contexto c){
        this.contextos.add(c);
    }

    public List<Contexto> getContextos(){
        return contextos;
    }

    public void adiconarPalavraAoContexto(Contexto contextoEscolhido, Palavra palavra) {

        for(Contexto c : this.contextos){
            if(c.getNome().equals(contextoEscolhido.getNome())){

                c.adicionarPalavra(palavra);
                Toast.makeText(this,"entrouuuu",Toast.LENGTH_SHORT).show();
                break;

            }

        }
    }

    public Contexto getContexto(Contexto contexto){
        Contexto lol = null;
        for(Contexto c : this.contextos){
            if(c.getNome().equals(contexto.getNome())){
                lol = c;
                break;

            }

        }
        return lol;
    }

    public void removerContexto(Contexto c){
        this.contextos.remove(c);
    }

    public void alterarContexto(Contexto atual, String nome, String path){

        for(Contexto c : this.contextos){
            if(c.equals(atual)){
                Toast.makeText(getApplicationContext(),"entrouuuuuuu?",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),path,Toast.LENGTH_SHORT).show();
                c.setNome(nome);
                c.setPath(path);

                break;
            }
        }
    }


    public void removerPalavra(Contexto contexto, Palavra palavra){

        for(Contexto c : this.contextos){
            if(c.equals(contexto)){
                c.removerPalavra(palavra,palavra.getNivel());
            }
        }
    }

    public void alterarPalavra(Contexto contexto, Palavra palavra, String nome, String path,Niveis nivel){

        for(Contexto c : this.contextos){

            if(nivel == Niveis.FACIL){
                for(Palavra p : c.getPalavrasNivelFacil()){
                    if(p.equals(palavra)){
                        p.setPalavra(nome);
                        p.setPathImagem(path);
                    }
                }
            }
        }


    }
}
