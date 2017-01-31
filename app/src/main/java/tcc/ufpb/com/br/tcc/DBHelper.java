package tcc.ufpb.com.br.tcc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Deyvison on 30/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "abcdaforca";

    private static String TABLE_CONTEXT = "CREATE TABLE contexto("+
            "nome TEXT PRIMARY KEY,"+
            "pathImagem TEXT,"+
            "isDefault TEXT,"+
            "id int AUTO INCREMENT"+
            ");";


    private static String TABLE_EASY = "CREATE TABLE easy("+
            "nome TEXT,"+
            "nomeContexto TEXT,"+
            "pathImagem TEXT,"+
            "isDefault TEXT,"+
            "id int AUTO INCREMENT,"+
            "FOREIGN KEY (nomeContexto) REFERENCES contexto(nome)"+
            ");";

    private static String TABLE_MEDIUM = "CREATE TABLE medium("+
            "nome TEXT,"+
            "nomeContexto TEXT,"+
            "pathImagem TEXT,"+
            "isDefault TEXT,"+
            "id int AUTO INCREMENT,"+
            "FOREIGN KEY (nomeContexto) REFERENCES contexto(nome)"+
            ");";


    private static String TABLE_HARD = "CREATE TABLE hard("+
            "nome TEXT,"+
            "nomeContexto TEXT,"+
            "pathImagem TEXT,"+
            "isDefault TEXT,"+
            "id int AUTO INCREMENT,"+
            "FOREIGN KEY (nomeContexto) REFERENCES contexto(nome)"+
            ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CONTEXT);
        db.execSQL(TABLE_EASY);
        db.execSQL(TABLE_MEDIUM);
        db.execSQL(TABLE_HARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
