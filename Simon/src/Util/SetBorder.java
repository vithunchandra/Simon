/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.*;

public class SetBorder {
    private Border normal, raisedEtched, loweredEtched, raisedBevel, loweredBevel, empty;
    private Color color;
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
}
