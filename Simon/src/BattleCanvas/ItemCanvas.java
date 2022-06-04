/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Pokemon.ImagePath;
import Util.ImageLoader;
import Util.MyFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import simon.BattleAltLoop;
import simon.Player;

/**
 *
 * @author LVOILA
 */
public class ItemCanvas implements Drawable{
    private BattleAltLoop battleAltLoop;
    private CanvasMouseListener mouse;
    private boolean usingItem;
    
    private ArrayList<CanvasButton> buttonList;
    private ArrayList<Integer> itemNumList;
    private CanvasButton backButton;
    private String itemUsed;
    
    private Image bgImg;
    
    public ItemCanvas(CanvasMouseListener mouse,BattleAltLoop battleAltLoop) throws IOException {
        this.battleAltLoop = battleAltLoop;
        this.mouse = mouse;
        this.itemUsed = null;
        this.itemNumList = new ArrayList<>();
        itemNumList.add(Player.pokeBall);itemNumList.add(Player.greatBall);itemNumList.add(Player.ultraBall);
        itemNumList.add(Player.potion);itemNumList.add(Player.superPotion);itemNumList.add(Player.fullRestore);
        
        this.bgImg = ImageLoader.loadImage(ImagePath.BG_SWITCH_BATTLE);
        
        this.buttonList = new ArrayList<>();
        if(battleAltLoop.isIsGym()) {
            this.buttonList.add(new CanvasButton("Poke Ball(disabled)", 50, 75, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Great Ball(disabled)", 50, 175, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Ultra Ball(disabled)", 50, 275, 300, 75, mouse));
        }
        else {
            this.buttonList.add(new CanvasButton("Poke Ball" + "(" + Player.pokeBall +")", 50, 75, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Great Ball" + "(" + Player.greatBall +")" , 50, 175, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Ultra Ball" + "(" + Player.ultraBall +")", 50, 275, 300, 75, mouse));
        }
        
        this.buttonList.add(new CanvasButton("Potion" + "(" + Player.potion +")", 450, 75, 300, 75, mouse));
        this.buttonList.add(new CanvasButton("Super Potion" + "(" + Player.superPotion +")", 450, 175, 300, 75, mouse));
        this.buttonList.add(new CanvasButton("Full Restore" + "(" + Player.fullRestore +")", 450, 275, 300, 75, mouse));
        
        this.backButton = new CanvasButton("Back", 0, 0, 100, 50, mouse);
        
    }
    
    public void reInit() {
        this.itemNumList = new ArrayList<>();
        itemNumList.add(Player.pokeBall);itemNumList.add(Player.greatBall);itemNumList.add(Player.ultraBall);
        itemNumList.add(Player.potion);itemNumList.add(Player.superPotion);itemNumList.add(Player.fullRestore);
    
        this.buttonList = new ArrayList<>();
        if(battleAltLoop.isIsGym()) {
            this.buttonList.add(new CanvasButton("Poke Ball(disabled)", 50, 75, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Great Ball(disabled)", 50, 175, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Ultra Ball(disabled)", 50, 275, 300, 75, mouse));
        }
        else {
            this.buttonList.add(new CanvasButton("Poke Ball" + "(" + Player.pokeBall +")", 50, 75, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Great Ball" + "(" + Player.greatBall +")" , 50, 175, 300, 75, mouse));
            this.buttonList.add(new CanvasButton("Ultra Ball" + "(" + Player.ultraBall +")", 50, 275, 300, 75, mouse));
        }
        
        this.buttonList.add(new CanvasButton("Potion" + "(" + Player.potion +")", 450, 75, 300, 75, mouse));
        this.buttonList.add(new CanvasButton("Super Potion" + "(" + Player.superPotion +")", 450, 175, 300, 75, mouse));
        this.buttonList.add(new CanvasButton("Full Restore" + "(" + Player.fullRestore +")", 450, 275, 300, 75, mouse));
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImg,0,0,MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT,null);
        for(int i = 0;i < buttonList.size();i++) {
            buttonList.get(i).draw(g);
        }
        backButton.draw(g);
    }
    
    private void decreaseItemNum(int idxItem) {
        if(idxItem == 0) {
            Player.pokeBall -= 1;
        }
        else if(idxItem == 1) {
            Player.greatBall -= 1;
        }
        else if(idxItem == 2) {
            Player.ultraBall -= 1;
        }
        else if(idxItem == 3) {
            Player.potion -= 1;
        }
        else if(idxItem == 4) {
            Player.superPotion -= 1;
        }
        else if(idxItem == 5) {
            Player.fullRestore -= 1;
        }
    }
    
    public void logicLoop(long diff) { 
        if(!this.battleAltLoop.isIsGym()) {
            for(int i = 0;i < 6;i++) {
                if(buttonList.get(i).clicked() && buttonList.get(i).isRendered() && this.itemNumList.get(i) > 0) {
                    this.usingItem = true;
                    battleAltLoop.getInBattleCanvas().getDialogueNow().add("You Use " + buttonList.get(i).getTextNoNum() +"!");
                    battleAltLoop.getInBattleCanvas().getCanvasTextArea().nextText();
                    itemUsed = buttonList.get(i).getTextNoNum();
                    decreaseItemNum(i);
                    battleAltLoop.setNowState("battle");
                    
                }
            }
        }
        else {
            for(int i = 3;i < 6;i++) {
                if(buttonList.get(i).clicked() && buttonList.get(i).isRendered() && this.itemNumList.get(i) > 0) {
                    this.usingItem = true;
                    battleAltLoop.getInBattleCanvas().getDialogueNow().add("You Use " + buttonList.get(i).getTextNoNum()+"!");
                    battleAltLoop.getInBattleCanvas().getCanvasTextArea().nextText();
                    itemUsed = buttonList.get(i).getTextNoNum();
                    decreaseItemNum(i);
                    battleAltLoop.setNowState("battle");
                }
            }
        }
        
        if(backButton.clicked() && backButton.isRendered()) {
            battleAltLoop.setNowState("battle");
        }
    }

    public boolean isUsingItem() {
        return usingItem;
    }

    public void setUsingItem(boolean usingItem) {
        this.usingItem = usingItem;
    }

    public String getItemUsed() {
        return itemUsed;
    }

    
    
    
}
