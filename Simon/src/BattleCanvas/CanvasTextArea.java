/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Util.DoubleLinkList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import simon.MyFrame;

/**
 *
 * @author LVOILA
 */
public class CanvasTextArea extends CanvasComponent {
    private String text;
    private DoubleLinkList<String> textList;
    
    private int fontSize;
    private int pl,pt,pr;
    private int fontPx;
    
    public CanvasTextArea(int height, CanvasMouseListener mouse) {
        super(0, MyFrame.DEFAULT_HEIGHT - height, MyFrame.DEFAULT_WIDTH , height, mouse);
        this.fontSize = default_font_size + 4;
        this.fontPx = default_font_size - 5;
        
        
        this.pl = 20;
        this.pt = 10;
        this.pr = MyFrame.DEFAULT_WIDTH/3;
        
        this.textList = new DoubleLinkList<>();
        textList.add("Pages\nyou view in this window won't appear in the browser history and they won't leave other traces, like cookies, on the computer after you close all open Guest windows. Any files you download will be preserved, however.");
        textList.add("Page 2\nPOKEMON");
        
        text = textList.pop();
    }

    public CanvasTextArea(int x, int y, int width, int height, CanvasMouseListener mouse) {
        super(x, y, width, height, mouse);
    }

    public void nextText() {
        if(!textList.isEmpty()) {
            text = textList.pop();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(DEFAULT_FONT);
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        
        
        g.setColor(Color.black);
        g.fillRect(x + width - pr, y, width, height);
        
        int baseX = x + pl;
        int nowX = x + pl;
        int nowY = y + fontSize + pt;
        String daftarKata[] = text.split(" ");
        for (String daftarKataI : daftarKata) {
            String daftarKata2[] = daftarKataI.split("\n");
            for (int j = 0;j < daftarKata2.length;j++) {
                String daftarKataJ = daftarKata2[j];
                if(nowX + fontPx*(daftarKataJ.length()) >= x + MyFrame.DEFAULT_WIDTH - pr) {
                    nowX = baseX;
                    nowY = nowY + fontSize;
                }

                nowX = nowX + 2;
                g.drawString(daftarKataJ, nowX, nowY);
                nowX = nowX + fontPx*(daftarKataJ.length()) ;
                
                if(daftarKata2.length > 1 && j != daftarKata2.length - 1) {
                    nowX = baseX;
                    nowY = nowY + fontSize;
                }
            }
            
        }
        
        
    }
    
}
