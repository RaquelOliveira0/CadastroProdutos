package com.example.cadastroprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cadastroprodutos.DBhelper.ProdutosBD;
import com.example.cadastroprodutos.model.Produto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lista;
    ProdutosBD bdHelper;
    ArrayList<Produto> listview_Produtos;
    Produto produto;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produto = new Produto();
        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdicionarProdutos.class);
                startActivity(intent);
            }


        });

        lista = (ListView) findViewById(R.id.listview_Produtos);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Produto produtoSelecionado = (Produto) adapter.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, AdicionarProdutos.class);
                i.putExtra("produto-escolhido", produtoSelecionado);
                startActivity(i);


            }
        });

    }

        public void onResume() {
            super.onResume();
            carregarProduto();
        }


        public void carregarProduto() {
            bdHelper = new ProdutosBD(MainActivity.this);
            listview_Produtos = bdHelper.getLista();
            bdHelper.close();
            if (listview_Produtos != null) {
                adapter = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, listview_Produtos);
                lista.setAdapter(adapter);
            }

        }


    }















