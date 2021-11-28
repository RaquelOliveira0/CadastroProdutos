package com.example.cadastroprodutos.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cadastroprodutos.model.Produto;

import java.util.ArrayList;

public class ProdutosBD extends SQLiteOpenHelper {

    private static final String DATABASE = "bdprodutos";
    private static final int VERSION = 1;


    public ProdutosBD(Context context) {
        super(context,DATABASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,nome TEXT NOT NULL, valor INTEGER  NOT NULL)";
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);
    }


    public void salvarProduto(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("nome",produto.getNome());
        values.put("valor", produto.getValor());

        getWritableDatabase().insert("produtos", null, values);

    }

    public  void editarProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("valor", produto.getValor());

        String [] args = {produto.getId().toString()};
        getWritableDatabase().update("produtos", values,"id=?",args);

    }

    public ArrayList<Produto> getLista () {
            String [] columns = {"id","nome","valor"};
            Cursor cursor = getWritableDatabase().query("produtos",columns,null,null,null,null,null);
            ArrayList<Produto> produtos = new ArrayList<Produto>();

            while (cursor.moveToNext()){
                Produto produto = new Produto();
                produto.setId(cursor.getLong(0));
                produto.setNome(cursor.getString(1));
                produto.setValor(cursor.getInt(2));
                produtos.add(produto);


            }
            return  produtos;



        }

    }











