package com.pdm.ifsudestemg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pdm.ifsudestemg.R;
import com.pdm.ifsudestemg.dao.UserDAO;
import com.pdm.ifsudestemg.model.User;

import java.util.List;

public class PlacarActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar);
        initViews();
    }

    //Obtém os componentes da activity e define exibe aos usuários.
    private void initViews() {
        listView = (ListView) findViewById(R.id.list_records);
        textView = (TextView) findViewById(R.id.text_no_records);

        loadArrayAdapter();
    }//initViews()

    public void deleteRecords(View view){
        UserDAO dao = new UserDAO(getBaseContext());
        dao.deleteAll();
        loadArrayAdapter();
        Toast.makeText(this, R.string.erase_register, Toast.LENGTH_SHORT).show();
    }

    //Carrega as informações do ListView para serem exibidas na activity.
    private void loadArrayAdapter(){
        UserDAO dao = new UserDAO(getBaseContext());
        List<User> users = dao.getAll();

        if (users.isEmpty()){
            listView.setVisibility(View.INVISIBLE);
            textView.setText(R.string.text_no_records);
        } else {
            ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
            textView.setText(R.string.text_records);
            listView.setAdapter(adapter);
        }
    }


}
