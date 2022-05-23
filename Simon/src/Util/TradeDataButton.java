/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Util.Button.*;
import java.awt.*;
import Util.Component.*;

public class TradeDataButton extends ButtonAction{
    ClickedDataComponent<Image> firstData;
    ClickedDataComponent<Image> secondData;

    public TradeDataButton(ClickedDataComponent<Image> firstData, ClickedDataComponent<Image> secondData, ActionButton button) {
        super(button);
        this.firstData = firstData;
        this.secondData = secondData;
    }

    @Override
    public void clicked() {
        if(firstData.getClickedComponent() != null && secondData.getClickedComponent() != null){
            Image temp = firstData.getClickedComponent().getData();
            firstData.getClickedComponent().setData(secondData.getClickedComponent().getData());
            secondData.getClickedComponent().setData(temp);
            
            ActionComponent firstComponent = firstData.getClickedComponent().getComponent();
            ActionComponent secondComponent = secondData.getClickedComponent().getComponent();
            
            for(int i=0; i<firstComponent.getComponentCount(); i++){
                if(firstComponent.getComponent(i) instanceof DrawImage){
                    DrawImage image = (DrawImage) firstComponent.getComponent(i);
                    image.setImage(firstData.getClickedComponent().getData());
                }
            }
            
            for(int i=0; i<secondComponent.getComponentCount(); i++){
                if(secondComponent.getComponent(i) instanceof DrawImage){
                    DrawImage image = (DrawImage) secondComponent.getComponent(i);
                    image.setImage(secondData.getClickedComponent().getData());
                }
            }
            
            firstComponent.setBorder(null);
            secondComponent.setBorder(null);
        }
    }
    
}
