/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.*;

public class SetBorder {
    Border normal, raisedEtched, loweredEtched, raisedBevel, loweredBevel, empty;
    Color color;
    public SetBorder(Color color){
        this.color = color;
        normal = BorderFactory.createLineBorder(color);
        raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedBevel = BorderFactory.createBevelBorder(BevelBorder.RAISED, color, Color.BLACK);
        loweredBevel = BorderFactory.createBevelBorder(BevelBorder.LOWERED, color, Color.BLACK);
        empty = BorderFactory.createEmptyBorder();
    }
    
    public Border niceFrame(){
        return BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
    }
    
    public Border outlineBorder(){
        return BorderFactory.createCompoundBorder(normal, 
                BorderFactory.createCompoundBorder(raisedBevel, loweredBevel));
    }

    public Border getNormal() {
        return normal;
    }

    public void setNormal(Border normal) {
        this.normal = normal;
    }

    public Border getRaisedEtched() {
        return raisedEtched;
    }

    public void setRaisedEtched(Border raisedEtched) {
        this.raisedEtched = raisedEtched;
    }

    public Border getLoweredEtched() {
        return loweredEtched;
    }

    public void setLoweredEtched(Border loweredEtched) {
        this.loweredEtched = loweredEtched;
    }

    public Border getRaisedBevel() {
        return raisedBevel;
    }

    public void setRaisedBevel(Border raisedBevel) {
        this.raisedBevel = raisedBevel;
    }

    public Border getLoweredBevel() {
        return loweredBevel;
    }

    public void setLoweredBevel(Border loweredBevel) {
        this.loweredBevel = loweredBevel;
    }

    public Border getEmpty() {
        return empty;
    }

    public void setEmpty(Border empty) {
        this.empty = empty;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
