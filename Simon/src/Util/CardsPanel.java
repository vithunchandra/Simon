/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Util.Text.*;
import Util.Component.*;
import java.awt.*;
import java.util.ArrayList;

public class CardsPanel extends ClickedText{
    private ActionComponent cards;

    public CardsPanel(ActionComponent cards, ArrayList<ComponentData<String, ActionComponent>> actionText) {
        super(actionText);
        this.cards = cards;
    }

    public CardsPanel(ActionComponent cards) {
        this.cards = cards;
    }

    @Override
    public void clicked() {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.show(cards, (String) this.getClickedText().getData());
    }
    
}
