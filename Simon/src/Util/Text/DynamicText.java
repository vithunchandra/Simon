/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DynamicText extends JComponent{
    private Dimension maxSize;
    private String text;
    private ArrayList<String> dynamicText;
    private int height;
    
    public DynamicText(String text, Font font, Dimension maxSize){
        this.maxSize = maxSize;
        this.setFont(font);
        this.dynamicText = new ArrayList<>();
        
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        height = metrics.getHeight();
        
        String str = "";
        int i = 0;
        while(i < text.length()){
            str += text.charAt(i);
            if(metrics.stringWidth(str) + 10 >= maxSize.width){
                dynamicText.add(str);
                str = "";
            }
            i++;
        }
        
        if(str != null){
            dynamicText.add(str);
            str = "";
        }
        
        this.setPreferredSize(maxSize);
        this.setSize(maxSize);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        for(int i=0; i<dynamicText.size(); i++){
            g.drawString(dynamicText.get(i), 0, (height * (i+1)) - 10);
        }
    }

    public Dimension getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Dimension maxSize) {
        this.maxSize = maxSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getDynamicText() {
        return dynamicText;
    }

    public void setDynamicText(ArrayList<String> dynamicText) {
        this.dynamicText = dynamicText;
    }
}
