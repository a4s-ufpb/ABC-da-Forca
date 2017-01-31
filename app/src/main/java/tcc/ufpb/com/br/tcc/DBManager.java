package tcc.ufpb.com.br.tcc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

    // adicionar contexto ao bd
    public void addContexto(Contexto contexto){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", contexto.getNome());
        values.put("pathImagem", contexto.getPathImagem());
        values.put("isDefault", contexto.getDefault()+"");

        database.insert("contexto", null, values);

    }

    // retorna todos os contextos do bd
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

    // selecionar contexto pelo nome (primary key)
    public Contexto selectContextoByNome(String nome){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String where = "nome" + " = ?";
        String[] whereArgs = {nome};

        Cursor cursor = database.rawQuery("SELECT * FROM contexto WHERE nome=?", new String[] {nome + ""});

        if(cursor.getCount() > 0) {

            cursor.moveToFirst();
            Contexto c = new Contexto(cursor.getString(0),cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));
            return c;
        }else{
            return null;
        }

    }

    // deletar contexto
    public void delContexto(String nome) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("contexto", "nome=?", new String[]{nome});
    }

    // alterar contexto cadastrado no bd
    public void setContexto(String nome, Contexto contexto) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", contexto.getNome());
        values.put("pathImagem", contexto.getPathImagem());
        values.put("isDefault", contexto.getDefault()+"");

        database.delete("contexto", "nome=?", new String[]{nome});
        database.insert("contexto", null, values);
    }


    //tabela palavra nível fácil

    // inserir palavra nível fácil
    public void insertPalavraFacil(String foreignkey ,Palavra p){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", p.getNome() );
        values.put("nomeContexto", foreignkey);
        values.put("pathImagem", p.getPathImagem());
        values.put("isDefault", p.getDefault()+"");

        database.insert("easy", null, values);
    }

    // retorna todas as palavras fáceis cadastradas em um contexto
    public ArrayList<Palavra> selectPalavraFacil(String foreignKey){ // retorna todas as palavras fáceis de um contexto
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // pega todos os registros do banco
        String query = "select easy.nome, easy.nomeContexto, easy.pathImagem, easy.isDefault " +
                "from contexto,easy " +
                "where contexto.nome = easy.nomeContexto";

        Cursor cursor = database.rawQuery(query,null);

        ArrayList<Palavra> palavras = null;
        if(cursor != null && cursor.moveToFirst()){
            palavras = new ArrayList<>();

            do{

                Palavra p = new Palavra(cursor.getString(0),cursor.getString(2),Niveis.FACIL,Boolean.parseBoolean(cursor.getString(3)));
                if(cursor.getString(1).equals(foreignKey)) // verifica se o foreign key são iguais
                    palavras.add(p);
            }while (cursor.moveToNext());
        }
        return palavras;
    }


    // pesquisa palavra nível fácil em determinado contexto
    public Palavra selectPalavraEasyByName(String foreignKey, String name){ // seleciona a palavra pelo nome

        ArrayList<Palavra> retorno = selectPalavraFacil(foreignKey);

        for(Palavra p : retorno){
            if(p.getNome().equals(name))
                return p;
        }
        return null;

    }

    // remove palavra de nível fácil de um contexto
    public void deletePalavraEasy(String foreignkey, String name){ // deleta uma palavra de determinado contexto
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("easy", "nomeContexto=? and nome=?", new String[]{foreignkey,name});
    }

    // alterar palavra fácil de um determinado contexto
    public void setPalavraEasy(String foreignkey, String nome, Palavra p){
        deletePalavraEasy(foreignkey,nome);
        insertPalavraFacil(foreignkey,p);
    }



}
