package com.pdm.ifsudestemg.controller;

import android.view.View;
import android.widget.Button;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by wagner on 18/08/16.
 */
public class ColorsController {
    private Stack<Button> buttons;
    private Stack<Button> buttonsChecked;

    public ColorsController() {
        this.buttons = new Stack<>();
        this.buttonsChecked = new Stack<>();
    }

    //Preenche o vetor com as referências dos botões.
    public void initializeColors(Button... buttons){
        for (Button button : buttons)
            this.buttons.push(button);
    }

    public int numberOfButtons(){
        return buttons.size();
    }

    //Embaralha a ordem dos botões.
    public void shuffleColors(){
        Collections.shuffle(buttons);
    }

    //Checa se o botão recebido por parâmetro, é o válido.
    public Button checkChoise(View view){
        if (buttons.empty()) return null; //Verifica se ainda existem botões na stack.
        if (buttons.peek().getId() == view.getId()) {
            buttonsChecked.push(buttons.peek());
            return buttons.pop();
        }
        return null;
    }

    //Retorna a configuração inicial do jogo atual.
    public void backToStart() {
        while (!buttonsChecked.empty()) buttons.push(buttonsChecked.pop());
    }
}//ColorsController
