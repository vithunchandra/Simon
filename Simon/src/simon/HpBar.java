/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HpBar extends JProgressBar{
    HpBar(int minimum, int maximum){
        this.setMinimum(100);
        this.setMaximum(200);
        this.setStringPainted(false);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        this.setForeground(Color.BLACK);
    }
    
    public void fillBar(){
        while(this.getValue() < 150){
            this.setValue(this.getValue() + 1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
