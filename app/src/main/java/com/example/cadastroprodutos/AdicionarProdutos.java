package com.example.cadastroprodutos;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cadastroprodutos.DBhelper.ProdutosBD;
import com.example.cadastroprodutos.model.Produto;

public class AdicionarProdutos extends AppCompatActivity {
    EditText nome_produto;
    EditText valor_produto;
    Button btn_salvar;
    Produto editarProduto,produto;
    ProdutosBD bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produtos);

        Intent intent = getIntent();
        editarProduto = (Produto) intent.getSerializableExtra("produto-escolhido");
        produto = new Produto();
        bdHelper = new ProdutosBD(this);

      nome_produto = (EditText) findViewById(R.id.nome_produto);
      valor_produto = (EditText) findViewById(R.id.valor_produto);
      btn_salvar = (Button) findViewById(R.id.btn_salvar);

      if(editarProduto !=null){
          btn_salvar.setText("Editar");
          nome_produto.setText(editarProduto.getNome());
          valor_produto.setText(editarProduto.getValor() + "");

          produto.setId(editarProduto.getId());


      }else {
          btn_salvar.setText("Adicionar");
      }


      btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto.setNome(nome_produto.getText().toString());
                produto.setValor(Integer.parseInt(valor_produto.getText().toString()));

                if(btn_salvar.getText().toString().equals("Adicionar")){
                    bdHelper.salvarProduto(produto);
                    bdHelper.close();
                    Intent intent = new Intent(AdicionarProdutos.this,MainActivity.class);
                    startActivity(intent);



                }else{
                    bdHelper.editarProduto(produto);
                    bdHelper.close();
                    Intent intent = new Intent(AdicionarProdutos.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}