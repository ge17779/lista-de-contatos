package br.com.gonzales.listadecontatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDataBase extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "contatos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";
    public static final String DATA_NASCIMENTO = "dataNascimento";
    public static final String CEP = "cep";
    public static final String ESTADO = "estado";
    public static final String CIDADE = "cidade";
    public static final String BAIRRO = "bairro";
    public static final String RUA = "rua";
    public static final String NUMERO = "numero";
    public static final int VERSAO = 1;

    public CreateDataBase(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + "("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + TELEFONE + " text,"
                + DATA_NASCIMENTO + " text,"
                + CEP + " text,"
                + ESTADO + " text,"
                + CIDADE + " text,"
                + BAIRRO + " text,"
                + RUA + " text,"
                + NUMERO + " integer"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
