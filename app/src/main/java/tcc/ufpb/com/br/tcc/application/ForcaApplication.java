package tcc.ufpb.com.br.tcc.application;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import tcc.ufpb.com.br.tcc.activity.SplashActivity;
import tcc.ufpb.com.br.tcc.entity.Niveis;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.db.DBManager;
import tcc.ufpb.com.br.tcc.entity.Contexto;
import tcc.ufpb.com.br.tcc.entity.Palavra;

/**
 * Created by Deyvison on 05/10/2016.
 */

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

        // if contexto default not exists on database, add it
        for(Contexto c : this.contextosDefault){
            Contexto retorno= db.selectContextoByNome(c.getNome()); // if not null, don't exists in databae yet.
            if(retorno == null){
                db.addContexto(c); // add the contexto in database
                for(Palavra p : c.getPalavrasNivelFacil()){ //add easy word default in dabatase
                    db.insertPalavraFacil(c.getNome(),p);
                }
                for(Palavra p : c.getPalavrasNivelMedio()){ // add medium word default in dabatase
                    db.insertPalavraMedio(c.getNome(),p);
                }
                for(Palavra p : c.getPalavrasNivelDificil()){ // add hard word default in dabatase
                    db.insertPalavraHard(c.getNome(),p);
                }
            }
        }

        this.contextos = db.getContextos(); // get all contextos from database
        for(Contexto c : this.contextos){ // load words into contexts
            c.setPalavraNivelFacil(db.selectPalavraFacil(c.getNome()));
            c.setPalavraNivelMedio(db.selectPalavraMedio(c.getNome()));
            c.setPalavraNivelHard(db.selectPalavraHard(c.getNome()));
        }
    }

    private void criarContextos(){
        Contexto animais,frutas;
        animais = new Contexto("Animais", R.drawable.animais+"", true);
        frutas = new Contexto("Frutas", R.drawable.frutas+"", true);

        // Animais
        // easy
        animais.adicionarPalavra(new Palavra("Sapo",R.drawable.sapo+"", Niveis.FACIL, true));
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

        //medium
        animais.adicionarPalavra(new Palavra("Coelho",R.drawable.coelho+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Cachorro",R.drawable.cachorro+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Tartaruga",R.drawable.tartaruga+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Leão",R.drawable.leao+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Porco",R.drawable.porco+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Tigre",R.drawable.tigre+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Abelha",R.drawable.abelha+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Zebra",R.drawable.zebra+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Urso",R.drawable.urso+"", Niveis.MEDIO, true));

        //hard
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
        //easy
        frutas.adicionarPalavra(new Palavra("Amora", R.drawable.amora+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Caju", R.drawable.caju+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Coco", R.drawable.coco+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Figo", R.drawable.figo+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Goiaba", R.drawable.goiaba+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Pera", R.drawable.pera+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Tomate", R.drawable.tomate+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Umbu", R.drawable.umbu+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Uva", R.drawable.uva+"",Niveis.FACIL, true));

        //medium
        frutas.adicionarPalavra(new Palavra("Abacate",R.drawable.abacate+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Banana",R.drawable.banana+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Laranja",R.drawable.laranja+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Manga",R.drawable.manga+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Mangaba",R.drawable.mangaba+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Pitanga",R.drawable.pitanga+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Sapoti",R.drawable.sapoti+"", Niveis.MEDIO, true));

        //hard
        frutas.adicionarPalavra(new Palavra("Jabuticaba",R.drawable.jabuticaba+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Jambo",R.drawable.jambo+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Limão",R.drawable.limao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mamão",R.drawable.mamao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mexerica",R.drawable.mexerica+"", Niveis.DIFICIL, true));

        contextosDefault.add(animais);
        contextosDefault.add(frutas);
    }

    // insert contexto on database
    public void adicionarContexto(Contexto c){
        Contexto contexto = db.selectContextoByNome(c.getNome());
        Log.i("lol","retorno do contexto = "+ contexto);
        if(contexto == null){ // se o contexto não existe, pode adicionar
            this.contextos.add(c);
            db.addContexto(c);
        }else{
            Toast.makeText(this,"Contexto já cadastrado",Toast.LENGTH_LONG).show();
        }
    }

    // delete contexto on database
    public void removerContexto(Contexto c){
        Contexto contexto = db.selectContextoByNome(c.getNome());
        if(contexto != null){
            this.contextos.remove(c);
            db.delContexto(c.getNome());
        }else{
            Toast.makeText(getApplicationContext(),"Contexto não cadastrado",Toast.LENGTH_SHORT).show();
        }
    }

    // update contexto on database
    public void alterarContexto(Contexto atual, String nome, String path){

        for(Contexto c : this.contextos){
            if(c.equals(atual)){
                String oldname = c.getNome();
                c.setNome(nome);
                c.setPath(path);
                Log.i("lol","valor de atual.getNome() = " + atual.getNome());
                db.setContexto(oldname,c);

                break;
            }
        }
    }

    // getAll contexts
    public List<Contexto> getContextos(){
        return contextos;
    }

    // get especifc contexto
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

    // insert Palavra on dabatase
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
                }else if(palavra.getNivel() == Niveis.MEDIO){
                    Palavra p = db.selectPalavraMediumByName(c.getNome(),palavra.getNome());
                    if(p == null){ // verificar se já existe a palavra no banco
                        c.adicionarPalavra(palavra);
                        db.insertPalavraMedio(c.getNome(),palavra);
                    }else{
                        Toast.makeText(this,"Palavra já cadastrada",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Palavra p = db.selectPalavraHardByName(c.getNome(),palavra.getNome());
                    if(p == null){ // verificar se já existe a palavra no banco
                        c.adicionarPalavra(palavra);
                        db.insertPalavraHard(c.getNome(),palavra);
                    }else{
                        Toast.makeText(this,"Palavra já cadastrada",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
        }
    }

    // delete palavra from database
    public void removerPalavra(Contexto contexto, Palavra palavra){

        for(Contexto c : this.contextos){
            if(c.getNome().equals(contexto.getNome())){
                if(palavra.getNivel() == Niveis.FACIL){
                    Palavra p = db.selectPalavraEasyByName(c.getNome(),palavra.getNome());
                    Log.i("lol","retorno palavra = "+ p.getNome());
                    if(p != null){ // palavra existe no banco e pode ser removida
                        c.removerPalavra(palavra,palavra.getNivel());
                        db.deletePalavraEasy(c.getNome(),palavra.getNome());
                    }else{
                        Toast.makeText(this,"Palavra não cadastrada",Toast.LENGTH_LONG).show();
                    }
                }else if(palavra.getNivel() == Niveis.MEDIO){
                    Palavra p = db.selectPalavraMediumByName(c.getNome(),palavra.getNome());
                    if(p != null){ // verificar se já existe a palavra no banco
                        c.removerPalavra(palavra,palavra.getNivel());
                        db.deletePalavraMedio(c.getNome(),palavra.getNome());
                    }else{
                        Toast.makeText(this,"Palavra não cadastrada",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Palavra p = db.selectPalavraHardByName(c.getNome(),palavra.getNome());
                    if(p != null){ // if word exists on dabatase
                        c.removerPalavra(palavra,palavra.getNivel());
                        db.deletePalavraHard(c.getNome(),palavra.getNome());
                    }else{
                        Toast.makeText(this,"Palavra não cadastrada",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
        }
    }
}
