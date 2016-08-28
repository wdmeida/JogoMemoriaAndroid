package com.pdm.ifsudestemg.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pdm.ifsudestemg.R;
import com.pdm.ifsudestemg.controller.ColorsController;

public class MainActivity extends AppCompatActivity {
    private ColorsController colorsController;
    private Button button_one, button_two, button_three,
                    button_four, button_five, button_six;
    private Drawable initialColor;
    private ProgressBar progressBar;
    private int valueOfProgression, userProgress;
    private static int numberOfMoves = 0;
    private TextView congratulations,
                     final_mensage,
                     progress_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    //Obtém as referências e inicializa os componentes da activity.
    private void initViews(){
        initialColor = getWindow().getDecorView().getBackground();

        //Obtém a referência dos componentes da view;
        button_one = (Button) findViewById(R.id.button_one);
        button_two = (Button) findViewById(R.id.button_two);
        button_three = (Button) findViewById(R.id.button_three);
        button_four = (Button) findViewById(R.id.button_four);
        button_five = (Button) findViewById(R.id.button_five);
        button_six = (Button) findViewById(R.id.button_six);

        congratulations = (TextView) findViewById(R.id.congratulations);
        final_mensage = (TextView) findViewById(R.id.final_mensage);
        progress_text = (TextView) findViewById(R.id.progress_text);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        //Adiciona os botões ao controller e sorteia a ordem para a aplicação.
        colorsController = new ColorsController();
        colorsController.initializeColors(button_one, button_two, button_three,
                button_four, button_five, button_six);

        userProgress = colorsController.numberOfButtons();
        valueOfProgression = 100 / colorsController.numberOfButtons();
        colorsController.shuffleColors();
    }

    //Verifica se o botão correto foi escolhido pelo usuário.
    public void checkChoise(View view){
        Button button = colorsController.checkChoise(view);
        if(button != null) {
            alterProperties(view);
        } else {
            restaureInitialProperties();
        }
    }

    //Alterar as propriedades da janela após acerto.
    private void alterProperties(View view){
        view.setVisibility(View.INVISIBLE);
        progressBar.setProgress(progressBar.getProgress() + valueOfProgression);
        userProgress--;
        getWindow().getDecorView().setBackground(view.getBackground());
        if (userProgress == 0) view_congratulations();
    }

    //Restaura as propriedades iniciais da aplicação.
    private void restaureInitialProperties() {
        colorsController.backToStart();
        button_one.setVisibility(View.VISIBLE);
        button_two.setVisibility(View.VISIBLE);
        button_three.setVisibility(View.VISIBLE);
        button_four.setVisibility(View.VISIBLE);
        button_five.setVisibility(View.VISIBLE);
        button_six.setVisibility(View.VISIBLE);
        congratulations.setVisibility(View.INVISIBLE);
        final_mensage.setVisibility(View.INVISIBLE);
        progressBar.setProgress(0);
        numberOfMoves++;
        userProgress = colorsController.numberOfButtons();
        getWindow().getDecorView().setBackground(initialColor);
    }

    //Exibe a mensagem de sucesso.
    private void view_congratulations() {
        progressBar.setVisibility(View.INVISIBLE);
        progress_text.setVisibility(View.INVISIBLE);
        congratulations.setVisibility(View.VISIBLE);
        final_mensage.setVisibility(View.VISIBLE);

        //Cria uma janela para o novo contexto, e passa a pontuação para ser acessada nessa nova janela.
        Intent intent = new Intent(MainActivity.this, DadosJogadorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("points", numberOfMoves);
        numberOfMoves = 0;
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //Restaura as configurações iniciais e define uma nova ordem.
    public void restartApp(View view) {
        numberOfMoves = 0;
        restaureInitialProperties();
        colorsController.shuffleColors();
    }
}
