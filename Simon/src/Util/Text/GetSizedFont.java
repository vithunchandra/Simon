/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GetSizedFont {
    public static Font getSizedFont(String text, String fontName, int style, Dimension size){
        int fontSize = 1;
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(new Font(fontName, style, fontSize));
        while(metrics.stringWidth(text) < size.width && metrics.getHeight() < size.height){
            metrics = c.getFontMetrics(new Font(fontName, style, fontSize));
            fontSize++;
        }
        return new Font(fontName, style, fontSize);
    }
}
