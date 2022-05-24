/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import java.awt.Graphics;
import java.util.ArrayList;
import simon.BattleAltLoop;

/**
 *
 * @author LVOILA
 */
public class ItemCanvas implements Drawable{
    private BattleAltLoop battleAltLoop;
    private CanvasMouseListener mouse;
    private boolean usingPokeBall;
    
    private ArrayList<CanvasButton> buttonList;
    
    public ItemCanvas(CanvasMouseListener mouse,BattleAltLoop battleAltLoop) {
        this.battleAltLoop = battleAltLoop;
        this.mouse = mouse;
        
        this.buttonList = new ArrayList<>();
        this.buttonList.add(new CanvasButton("Poke Ball", 50, 25, 300, 75, mouse));
        this.buttonList.add(new CanvasButton("Great Ball", 50, 125, 300, 75, mouse));
        this.buttonList.add(new CanvasButton("Ultra Ball", 50, 225, 300, 75, mouse));
    }
    

    @Override
    public void draw(Graphics g) {
        for(int i = 0;i < buttonList.size();i++) {
            buttonList.get(i).draw(g);
        }
    }
    
    public void logicLoop(long diff) { 
        for(int i = 0;i < 3;i++) {
            if(buttonList.get(i).clicked() && buttonList.get(i).isRendered()) {
                this.usingPokeBall = true;
                battleAltLoop.getDialogueNow().add("You Use " + buttonList.get(i).getText() +"!");
                battleAltLoop.getCanvasTextArea().nextText();
                battleAltLoop.setNowState("battle");
            }
        }
    }

    public boolean isUsingPokeBall() {
        return usingPokeBall;
    }

    public void setUsingPokeBall(boolean usingPokeBall) {
        this.usingPokeBall = usingPokeBall;
    }

    
    
    
}
