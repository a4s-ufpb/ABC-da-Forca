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
        Contexto animais, frutas, brinquedos, meiosDeTransporte;
        animais = new Contexto("Animais", R.drawable.animais+"", true);
        frutas = new Contexto("Frutas", R.drawable.frutas+"", true);
        brinquedos = new Contexto("Brinquedos", R.drawable.brinquedos+"", true);
        meiosDeTransporte = new Contexto("Meios De Transporte", R.drawable.meiosdetransporte+"", true);

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

        animais.adicionarPalavra(new Palavra("Cobra",R.drawable.cobra+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Lobo",R.drawable.lobo+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Tatu",R.drawable.tatu+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Mosca",R.drawable.mosca+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Barata",R.drawable.barata+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Ganso",R.drawable.ganso+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Panda",R.drawable.panda+"",Niveis.FACIL, true));
        animais.adicionarPalavra(new Palavra("Camelo",R.drawable.camelo+"",Niveis.FACIL, true));


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


        animais.adicionarPalavra(new Palavra("Polvo",R.drawable.polvo+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Peixe",R.drawable.peixe+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Canguru",R.drawable.canguru+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Veado",R.drawable.veado+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Lagarta",R.drawable.lagarta+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Castor",R.drawable.castor+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Raposa",R.drawable.raposa+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Capivara",R.drawable.capivara+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Formiga",R.drawable.formiga+"", Niveis.MEDIO, true));
        animais.adicionarPalavra(new Palavra("Flamingo",R.drawable.flamingo+"", Niveis.MEDIO, true));

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

        animais.adicionarPalavra(new Palavra("Crocodilo", R.drawable.crocodilo+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Gambá", R.drawable.gamba+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Camarão", R.drawable.camarao+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Pássaro", R.drawable.passaro+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Centopéia", R.drawable.centopeia+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Camaleão", R.drawable.camaleao+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Pavão", R.drawable.pavao+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Búfalo", R.drawable.bufalo+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Escorpião", R.drawable.escorpiao+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Babuíno", R.drawable.babuino+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Rinoceronte", R.drawable.rinoceronte+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Hiena", R.drawable.hiena+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Golfinho", R.drawable.golfinho+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Pinguim", R.drawable.pinguim+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Avestruz", R.drawable.avestruz+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Esquilo", R.drawable.esquilo+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Preguiça", R.drawable.preguica+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Aranha", R.drawable.aranha+"", Niveis.DIFICIL, true));
        animais.adicionarPalavra(new Palavra("Minhoca", R.drawable.minhoca+"", Niveis.DIFICIL, true));


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

        frutas.adicionarPalavra(new Palavra("Jaca", R.drawable.jaca+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Cacau", R.drawable.cacau+"",Niveis.FACIL, true));
        frutas.adicionarPalavra(new Palavra("Pinha", R.drawable.pinha+"",Niveis.FACIL, true));


        //medium
        frutas.adicionarPalavra(new Palavra("Abacate",R.drawable.abacate+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Banana",R.drawable.banana+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Laranja",R.drawable.laranja+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Manga",R.drawable.manga+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Mangaba",R.drawable.mangaba+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Pitanga",R.drawable.pitanga+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Sapoti",R.drawable.sapoti+"", Niveis.MEDIO, true));

        frutas.adicionarPalavra(new Palavra("Oliveira",R.drawable.oliveira+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Acerola",R.drawable.acerola+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Ameixa",R.drawable.ameixa+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Cereja",R.drawable.cereja+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Melancia",R.drawable.melancia+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Morango",R.drawable.morango+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Abacaxi",R.drawable.abacaxi+"", Niveis.MEDIO, true));
        frutas.adicionarPalavra(new Palavra("Caqui",R.drawable.caqui+"", Niveis.MEDIO, true));

        //hard
        frutas.adicionarPalavra(new Palavra("Jabuticaba",R.drawable.jabuticaba+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Jambo",R.drawable.jambo+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Limão",R.drawable.limao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mamão",R.drawable.mamao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Mexerica",R.drawable.mexerica+"", Niveis.DIFICIL, true));

        frutas.adicionarPalavra(new Palavra("Maracujá",R.drawable.maracuja+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Romã",R.drawable.roma+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Guaraná",R.drawable.guarana+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Framboesa",R.drawable.framboesa+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Pêssego",R.drawable.pessego+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Carambola",R.drawable.carambola+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Graviola",R.drawable.graviola+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Melão",R.drawable.melao+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Kiwi",R.drawable.kiwi+"", Niveis.DIFICIL, true));
        frutas.adicionarPalavra(new Palavra("Maça",R.drawable.maca+"", Niveis.DIFICIL, true));


        //easy
        brinquedos.adicionarPalavra(new Palavra("Bola", R.drawable.bola+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Lego", R.drawable.lego+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Pipa", R.drawable.pipa+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Dama", R.drawable.dama+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Dado", R.drawable.dado+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Corda", R.drawable.corda+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Carro", R.drawable.carro+"", Niveis.FACIL, true));
        brinquedos.adicionarPalavra(new Palavra("Cartas", R.drawable.cartas+"", Niveis.FACIL, true));

        // medium
        brinquedos.adicionarPalavra(new Palavra("Peteca", R.drawable.peteca+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Boneca", R.drawable.boneca+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Boneco", R.drawable.boneco+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Patins", R.drawable.patins+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Raquete", R.drawable.raquete+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Skate", R.drawable.skate+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Bicicleta", R.drawable.bicicleta+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Patinete", R.drawable.patinete+"", Niveis.MEDIO, true));
        brinquedos.adicionarPalavra(new Palavra("Marionete", R.drawable.marionete+"", Niveis.MEDIO, true));

        // hard
        brinquedos.adicionarPalavra(new Palavra("Bumerangue", R.drawable.bumerangue+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Bambolê", R.drawable.bambole+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Estilingue", R.drawable.estilingue+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Escorregador", R.drawable.escorregador+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Fantoche", R.drawable.fantoche+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Gangorra", R.drawable.gangorra+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Balanço", R.drawable.balanco+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Pião", R.drawable.piao+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Dominó", R.drawable.domino+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Robô", R.drawable.robo+"", Niveis.DIFICIL, true));
        brinquedos.adicionarPalavra(new Palavra("Ioiô", R.drawable.ioio+"", Niveis.DIFICIL, true));

        //easy
        meiosDeTransporte.adicionarPalavra(new Palavra("Trem", R.drawable.trem+"", Niveis.FACIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Bonde", R.drawable.bonde+"", Niveis.FACIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Barco", R.drawable.barco+"", Niveis.FACIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Navio", R.drawable.navio+"", Niveis.FACIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Balsa", R.drawable.balsa+"", Niveis.FACIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Canoa", R.drawable.canoa+"", Niveis.FACIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Trator", R.drawable.trator+"", Niveis.FACIL, true));

        //medium
        meiosDeTransporte.adicionarPalavra(new Palavra("Lancha", R.drawable.lancha+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Bicicleta", R.drawable.bicicleta+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Patins", R.drawable.patins+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Patinete", R.drawable.patinete+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Foguete", R.drawable.foguete+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Paraquedas", R.drawable.paraquedas+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Submarino", R.drawable.submarino+"", Niveis.MEDIO, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Skate", R.drawable.skate+"", Niveis.MEDIO, true));

        //hard
        meiosDeTransporte.adicionarPalavra(new Palavra("Automóvel", R.drawable.automovel+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Helicóptero", R.drawable.helicoptero+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Caminhão", R.drawable.caminhao+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Ônibus", R.drawable.onibus+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Avião", R.drawable.aviao+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Balão", R.drawable.balao+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Metrô", R.drawable.metro+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Carroça", R.drawable.carroca+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Motocicleta", R.drawable.motocicleta+"", Niveis.DIFICIL, true));
        meiosDeTransporte.adicionarPalavra(new Palavra("Charrete", R.drawable.charrete+"", Niveis.DIFICIL, true));


        contextosDefault.add(animais);
        contextosDefault.add(frutas);
        contextosDefault.add(brinquedos);
        contextosDefault.add(meiosDeTransporte);
    }

    // insert contexto on database
    public void adicionarContexto(Contexto c){
        Contexto contexto = db.selectContextoByNome(c.getNome());
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
