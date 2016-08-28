package com.pdm.ifsudestemg.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pdm.ifsudestemg.R;
import com.pdm.ifsudestemg.dao.UserDAO;
import com.pdm.ifsudestemg.model.User;

public class DadosJogadorActivity extends AppCompatActivity {
    private EditText name;
    private EditText points;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_recorde);
        initViews();
    }

    //Recupera os componentes da activity.
    private void initViews(){
        resources = getResources();
        name = (EditText) findViewById(R.id.text_name);
        points = (EditText) findViewById(R.id.text_points);
        //Obtém a pontuação do jogador recebida da outra activity.
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int numberOfPoints = bundle.getInt("points");
        points.setText(String.valueOf(numberOfPoints));

        //Cria um TextWatcher para executar ações nos campos editáveis.
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                callClearErrors(editable);
            }
        };
    }//initViews()

    //Método para limpar erros.
    private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) clearErrorFields(name);
    }

    //Salva as informações do usuário junto com sua pontuação.
    public void saveStatisticsUser(View view) {
        //Verifica se o nome do usuário não está vazio.
        if(!validadeFields()) return;

        //Obtém os dados do usuário.
        UserDAO dao = new UserDAO(getBaseContext());
        User user = new User();
        user.setName(name.getEditableText().toString());
        user.setPoints(myParseInt(points.getEditableText().toString(), 0));

        //Realiza o cadastro das informações e verifica se as mesmas foram realizadas com sucesso.
        long insertId = dao.insert(user);

        //Verifica se o cadastro foi efetuado com sucesso.
        if(insertId != -1) {
            Toast.makeText(getApplicationContext(), R.string.message_save, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.message_unsave, Toast.LENGTH_LONG).show();
        }
    }

    //Valida o campo nome do usuário verificando se o mesmo está sendo preenchido. Caso não, exibe uma mensagem de erro.
    private boolean validadeFields(){
        if(TextUtils.isEmpty(name.getEditableText().toString().trim())){
            name.requestFocus();
            name.setError(resources.getString(R.string.message_name_empty));
            return false;
        }
        return true;
    }

    //Converte a String para um valor inteiro.
    private int myParseInt(String valor, int valorPadrao) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e){
            return valorPadrao;
        }
    }//myParseInt()

    //Limpa os erros disparados nas validações.
    private void clearErrorFields(EditText... editTexts){
        for (EditText editText : editTexts)
            editText.setError(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(DadosJogadorActivity.this, PlacarActivity.class);
        startActivity(intent);
    }
}
