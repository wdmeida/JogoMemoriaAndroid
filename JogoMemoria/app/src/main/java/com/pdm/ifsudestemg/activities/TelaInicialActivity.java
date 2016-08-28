package com.pdm.ifsudestemg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pdm.ifsudestemg.R;

public class TelaInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //Obtém a referência dos botões da activity e define a ação dos mesmos.
        Button newGame = (Button) findViewById(R.id.button_new_game);
        Button viewRecords = (Button) findViewById(R.id.button_records);

        //Registra a ação do botão newGame.
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityIntent = new Intent(TelaInicialActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        //Registra a ação do botão viewRecords
        viewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent placarActivityIntent = new Intent(TelaInicialActivity.this, PlacarActivity.class);
                startActivity(placarActivityIntent);
            }
        });
    }
}
