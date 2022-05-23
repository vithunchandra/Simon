/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import java.awt.event.*;
import java.util.ArrayList;

public class GetDataButton<Data> extends MouseAdapter{
    private ArrayList<ButtonWithData<Data>> buttons;
    private Data clickedData;

    public GetDataButton(ArrayList<ButtonWithData<Data>> buttons, Data clickedData) {
        this.buttons = buttons;
        this.clickedData = clickedData;
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).addMouseListener(this);
        }
    }
    
    public GetDataButton(){
        buttons = new ArrayList<>();
        clickedData = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).setPressed(true);
            if(!buttons.get(i).isClicked()){
                buttons.get(i).setPressed(true);
                buttons.get(i).setImageBackground(buttons.get(i).getPressedState());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        for(int i=0; i<buttons.size(); i++){
            if(buttons.get(i) == e.getSource()){
                buttons.get(i).checkClicked(e.getX(), e.getY());
                if(buttons.get(i).isClicked()){
                    clickedData = buttons.get(i).getData();
                }else{
                    buttons.get(i).setImageBackground(buttons.get(i).getDefaultState());
                }
                break;
            }
        }
    }

    public ArrayList<ButtonWithData<Data>> getButton() {
        return buttons;
    }

    public void setButton(ArrayList<ButtonWithData<Data>> buttons) {
        this.buttons = buttons;
    }

    public Data getClickedData() {
        return clickedData;
    }

    public void setClickedData(Data clickedData) {
        this.clickedData = clickedData;
    }
    
    public void AddButton(ButtonWithData<Data> button){
        this.buttons.add(button);
        button.addMouseListener(this);
    }
}
