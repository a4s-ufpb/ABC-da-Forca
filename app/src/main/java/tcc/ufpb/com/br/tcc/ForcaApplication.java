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

    private List<Contexto> contextosDefault;
    private List<Contexto> contextos;
    private DBManager db;


    @Override
    public void onCreate() {
        super.onCreate();
        db = new DBManager(this);

        contextosDefault = new ArrayList<>();
        contextos = new ArrayList<>();

        criarContextos();

        // verificar se já existe no banco antes de inserir, alterar ou remover

        // varrer o contexto default e adicionar no bd os que não existem
        for(Contexto c : this.contextosDefault){
            Contexto retorno= db.selectContextoByNome(c.getNome()); // se for nulo, não existe no banco ainda
            if(retorno == null){
                db.addContexto(c); // adiciona o contexto no banco

                for(Palavra p : c.getPalavrasNivelFacil()){ // adicionar palavra fácil no banco
                    db.insertPalavraFacil(c.getNome(),p);
                }
                for(Palavra p : c.getPalavrasNivelMedio()){ // adicionar palavra medio no banco

                }
                for(Palavra p : c.getPalavrasNivelDificil()){ // adicionar palavra fácil no banco

                }
            }
        }

        this.contextos = db.getContextos();

        for(Contexto c : this.contextos){ // carregar palavras dos contextos
            c.setPalavraNivelFacil(db.selectPalavraFacil(c.getNome()));
        }

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
        frutas.adicionarPalavra(new Palavra("Amora", R.drawable.amora+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Caju", R.drawable.caju+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Coco", R.drawable.coco+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Figo", R.drawable.figo+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Goiaba", R.drawable.goiaba+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Pera", R.drawable.pera+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Tomate", R.drawable.tomate+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Umbu", R.drawable.umbu+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Uva", R.drawable.uva+"",Niveis.FACIL, true));



        //medio

        frutas.adicionarPalavra(new Palavra("Abacate",R.drawable.abacate+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Banana",R.drawable.banana+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Laranja",R.drawable.laranja+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Manga",R.drawable.manga+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Mangaba",R.drawable.mangaba+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Pitanga",R.drawable.pitanga+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Sapoti",R.drawable.sapoti+"", Niveis.MEDIO, true));


        //dificil
        frutas.adicionarPalavra(new Palavra("Jabuticaba",R.drawable.jabuticaba+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Jambo",R.drawable.jambo+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Limão",R.drawable.limao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mamão",R.drawable.mamao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mexerica",R.drawable.mexerica+"", Niveis.DIFICIL, true));




        contextosDefault.add(animais);
        contextosDefault.add(frutas);


    }

    private void criarPalavras(){

    }

    public void adicionarContexto(Contexto c){
        Contexto contexto = db.selectContextoByNome(c.getNome());
        Log.i("lol","retorno do contexto = "+ contexto);
        if(contexto == null){
            this.contextos.add(c);
            db.addContexto(c);
        }else{
            Toast.makeText(this,"Contexto já cadastrado",Toast.LENGTH_LONG).show();
        }

    }

    public List<Contexto> getContextos(){
        return contextos;
    }

    public void adiconarPalavraAoContexto(Contexto contextoEscolhido, Palavra palavra) {

        for(Contexto c : this.contextos){
            if(c.getNome().equals(contextoEscolhido.getNome())){


                if(palavra.getNivel() == Niveis.FACIL){
                    Palavra p = db.selectPalavraEasyByName(c.getNome(),palavra.getNome());
                    if(p == null){ // verificar se já existe a palavra no banco
                        c.adicionarPalavra(palavra);
                        db.insertPalavraFacil(c.getNome(),palavra);
                    }else{
                        Toast.makeText(this,"Palavra já cadastrada",Toast.LENGTH_LONG).show();
                    }
                } // fazer o mesmo para médio e dificil.

//                else if(palavra.getNivel() == Niveis.MEDIO)
//                    // palavra medio
//                else
                    // palavra dificil

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
        Contexto contexto = db.selectContextoByNome(c.getNome());
        if(contexto != null){
            this.contextos.remove(c);
            db.delContexto(c.getNome());
        }else{
            Toast.makeText(getApplicationContext(),"Contexto não existe",Toast.LENGTH_SHORT).show();
        }


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
