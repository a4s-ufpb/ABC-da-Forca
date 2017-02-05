package tcc.ufpb.com.br.tcc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import tcc.ufpb.com.br.tcc.entity.Niveis;
import tcc.ufpb.com.br.tcc.entity.Palavra;
import tcc.ufpb.com.br.tcc.entity.Contexto;

/**
 * Created by Deyvison on 30/01/2017.
 */
public class DBManager {

    private static DBHelper dbHelper = null;

    public DBManager(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }

    // contexto
    // add context in to db
    public void addContexto(Contexto contexto){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", contexto.getNome());
        values.put("pathImagem", contexto.getPathImagem());
        values.put("isDefault", contexto.getDefault()+"");
        database.insert("contexto", null, values);
    }

    // get all contextos from db
    public ArrayList<Contexto> getContextos(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM contexto";
        Cursor cursor = database.rawQuery(sql,null);
        ArrayList<Contexto> contextos = null;
        if(cursor != null && cursor.moveToFirst()){
            contextos = new ArrayList<>();
            do{
                Contexto c = new Contexto(cursor.getString(0),cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));
                contextos.add(c);
            }while (cursor.moveToNext());
        }
        return contextos;
    }

    // select contexto by name (primary key)
    public Contexto selectContextoByNome(String nome){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM contexto WHERE nome=?", new String[] {nome + ""});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            Contexto c = new Contexto(cursor.getString(0),cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));
            return c;
        }else{
            return null;
        }
    }

    // delete contexto
    public void delContexto(String nome) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("contexto", "nome=?", new String[]{nome});
    }

    // update contexto in to db
    public void setContexto(String nome, Contexto contexto) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", contexto.getNome());
        values.put("pathImagem", contexto.getPathImagem());
        values.put("isDefault", contexto.getDefault()+"");
        database.delete("contexto", "nome=?", new String[]{nome});
        database.insert("contexto", null, values);
    }

    //easy word table
    // insert easy word
    public void insertPalavraFacil(String foreignkey ,Palavra p){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", p.getNome() );
        values.put("nomeContexto", foreignkey);
        values.put("pathImagem", p.getPathImagem());
        values.put("isDefault", p.getDefault()+"");
        database.insert("easy", null, values);
    }

    // get all easy words from contexto
    public ArrayList<Palavra> selectPalavraFacil(String foreignKey){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // get all registers of db
        String query = "select easy.nome, easy.nomeContexto, easy.pathImagem, easy.isDefault " +
                "from contexto,easy " +
                "where contexto.nome = easy.nomeContexto";

        Cursor cursor = database.rawQuery(query,null);
        ArrayList<Palavra> palavras = null;
        if(cursor != null && cursor.moveToFirst()){
            palavras = new ArrayList<>();
            do{
                Palavra p = new Palavra(cursor.getString(0),cursor.getString(2), Niveis.FACIL,Boolean.parseBoolean(cursor.getString(3)));
                if(cursor.getString(1).equals(foreignKey))
                    palavras.add(p);
            }while (cursor.moveToNext());
        }
        return palavras;
    }

    // select easy word from contexto by name
    public Palavra selectPalavraEasyByName(String foreignKey, String name){
        ArrayList<Palavra> retorno = selectPalavraFacil(foreignKey);
        for(Palavra p : retorno){
            if(p.getNome().equals(name))
                return p;
        }
        return null;
    }

    // remove easy word from contexto by name
    public void deletePalavraEasy(String foreignkey, String name){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("easy", "nomeContexto=? and nome=?", new String[]{foreignkey,name});
    }

    ////medium word table
    // insert medium word
    public void insertPalavraMedio(String foreignkey ,Palavra p){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", p.getNome() );
        values.put("nomeContexto", foreignkey);
        values.put("pathImagem", p.getPathImagem());
        values.put("isDefault", p.getDefault()+"");
        database.insert("medium", null, values);
    }

    // get all medium words from contexto
    public ArrayList<Palavra> selectPalavraMedio(String foreignKey){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // get all registers of db
        String query = "select medium.nome, medium.nomeContexto, medium.pathImagem, medium.isDefault " +
                "from contexto,medium " +
                "where contexto.nome = medium.nomeContexto";
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<Palavra> palavras = null;
        if(cursor != null && cursor.moveToFirst()){
            palavras = new ArrayList<>();
            do{
                Palavra p = new Palavra(cursor.getString(0),cursor.getString(2),Niveis.MEDIO,Boolean.parseBoolean(cursor.getString(3)));
                if(cursor.getString(1).equals(foreignKey))
                    palavras.add(p);
            }while (cursor.moveToNext());
        }
        return palavras;
    }

    // select medium word from contexto by name
    public Palavra selectPalavraMediumByName(String foreignKey, String name){
        ArrayList<Palavra> retorno = selectPalavraMedio(foreignKey);
        for(Palavra p : retorno){
            if(p.getNome().equals(name))
                return p;
        }
        return null;
    }

    // delete palavra fom db
    public void deletePalavraMedio(String foreignkey, String name){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("medium", "nomeContexto=? and nome=?", new String[]{foreignkey,name});
    }

    //tabela palavra nível hard
    // insert hard word
    public void insertPalavraHard(String foreignkey ,Palavra p){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", p.getNome() );
        values.put("nomeContexto", foreignkey);
        values.put("pathImagem", p.getPathImagem());
        values.put("isDefault", p.getDefault()+"");
        database.insert("hard", null, values);
    }

    // get all hard words from contexto
    public ArrayList<Palavra> selectPalavraHard(String foreignKey){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // get all registers from db
        String query = "select hard.nome, hard.nomeContexto, hard.pathImagem, hard.isDefault " +
                "from contexto,hard " +
                "where contexto.nome = hard.nomeContexto";
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<Palavra> palavras = null;
        if(cursor != null && cursor.moveToFirst()){
            palavras = new ArrayList<>();
            do{
                Palavra p = new Palavra(cursor.getString(0),cursor.getString(2),Niveis.DIFICIL,Boolean.parseBoolean(cursor.getString(3)));
                if(cursor.getString(1).equals(foreignKey))
                    palavras.add(p);
            }while (cursor.moveToNext());
        }
        return palavras;
    }

    // select hard word from db by name
    public Palavra selectPalavraHardByName(String foreignKey, String name){
        ArrayList<Palavra> retorno = selectPalavraHard(foreignKey);
        for(Palavra p : retorno){
            if(p.getNome().equals(name))
                return p;
        }
        return null;
    }

    // delete hard word from contexto
    public void deletePalavraHard(String foreignkey, String name){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("hard", "nomeContexto=? and nome=?", new String[]{foreignkey,name});
    }
}
