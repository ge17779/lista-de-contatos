package br.com.gonzales.listadecontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseController {

    private SQLiteDatabase db;
    private CreateDataBase banco;

    public DataBaseController(Context context){
        banco = new CreateDataBase(context);
    }

    public String insereDado(String nome, String telefone, String dataNascimento, String cep,
                             String estado, String cidade, String bairro, String rua, String numero){
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDataBase.NOME, nome);
        values.put(CreateDataBase.TELEFONE, telefone);
        values.put(CreateDataBase.DATA_NASCIMENTO, dataNascimento);
        values.put(CreateDataBase.CEP, cep);
        values.put(CreateDataBase.ESTADO, estado);
        values.put(CreateDataBase.CIDADE, cidade);
        values.put(CreateDataBase.BAIRRO, bairro);
        values.put(CreateDataBase.RUA, rua);
        values.put(CreateDataBase.NUMERO, numero);

        resultado = db.insert(CreateDataBase.TABELA, null, values);
        db.close();

        if (resultado ==-1){
            return "Erro ao inserir registro";
        }
        else{
            Log.d("Teste", "Registro inserido com sucesso!");
            return "Registro Inserido com sucesso";
        }
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.TELEFONE,banco.DATA_NASCIMENTO,banco.CEP,
                banco.ESTADO,banco.CIDADE,banco.BAIRRO,banco.RUA,banco.NUMERO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}

