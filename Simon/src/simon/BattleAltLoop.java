/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import BattleCanvas.CanvasMouseListener;
import BattleCanvas.Drawable;
import BattleCanvas.InBattleCanvas;
import BattleCanvas.ItemCanvas;
import BattleCanvas.SwitchCanvas;
import java.awt.Graphics;
import java.io.IOException;

/**
 *
 * @author LVOILA
 */
public class BattleAltLoop implements Drawable {
    

    private CanvasMouseListener mouse;    
    private boolean battling;
    private String nowState;
    //battle component
    private InBattleCanvas inBattleCanvas;
    //switch component
    private SwitchCanvas switchCanvas;
    //Item component
    private ItemCanvas itemCanvas;
    
    public BattleAltLoop(CanvasMouseListener mouse) throws IOException {
        this.mouse = mouse;
       
        this.battling = true;
        this.nowState = "battle";
        
        //inBattle component
        this.inBattleCanvas = new InBattleCanvas(this, mouse);
        //switch component
        this.switchCanvas = new SwitchCanvas(mouse, this);
        //item component
        this.itemCanvas = new ItemCanvas(mouse, this);
    }

    @Override
    public void draw(Graphics g) {
        if(this.nowState.equals("battle")) {
            this.inBattleCanvas.draw(g);
        }
        else if(this.nowState.equals("switch")) {
            this.switchCanvas.draw(g);
        }
        else if(this.nowState.equals("item")) {
            this.itemCanvas.draw(g);
        }
    }
    
    
    public void logicLoop(long diff) {
        graphicLogic(diff);
    }
    
    private void graphicLogic(long diff) {
        if(this.nowState.equals("battle")) {
            this.inBattleCanvas.graphicLogic(diff);
        }
        else if(this.nowState.equals("switch")) {
            this.switchCanvas.logicLoop(diff);
        }
        else if(this.nowState.equals("item")) {
            this.itemCanvas.logicLoop(diff);
            
        }
    }

    public boolean isBattling() {
        return battling;
    }
    
    public void setBattling(Boolean battlingNow) {
        this.battling = battlingNow;
    }

    public void setNowState(String nowState) {
        this.nowState = nowState;
    }

    public ItemCanvas getItemCanvas() {
        return itemCanvas;
    }

    public SwitchCanvas getSwitchCanvas() {
        return switchCanvas;
    }

    public InBattleCanvas getInBattleCanvas() {
        return inBattleCanvas;
    }
    
}
