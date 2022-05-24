/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Pokemon.ImagePath;
import Util.DoubleLinkList;
import Util.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.MyFrame;

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
    
    private Image dialogueBox;
    
    public CanvasTextArea(int height, CanvasMouseListener mouse) throws IOException {
        super(0, MyFrame.DEFAULT_HEIGHT - height, MyFrame.DEFAULT_WIDTH , height, mouse);

        this.fontSize = default_font_size + 4;
        this.fontPx = default_font_size - 5;
        
        
        this.pl = 20;
        this.pt = 10;
        this.pr = MyFrame.DEFAULT_WIDTH/3;
        
        this.textList = new DoubleLinkList<>();
        this.dialogueBox = ImageLoader.loadImage(ImagePath.BATTLE_DIALOGUE);
        
        text = textList.pop();
    }
    
    public CanvasTextArea(int height, CanvasMouseListener mouse,DoubleLinkList textList) throws IOException {
        super(0, MyFrame.DEFAULT_HEIGHT - height, MyFrame.DEFAULT_WIDTH , height, mouse);
        this.fontSize = default_font_size + 4;
        this.fontPx = default_font_size - 5;
        
        this.dialogueBox = ImageLoader.loadImage(ImagePath.BATTLE_DIALOGUE);
        this.pl = 20;
        this.pt = 10;
        this.pr = MyFrame.DEFAULT_WIDTH/3;
        
        this.textList = textList;
        this.text = this.textList.pop();
    }
    

    public CanvasTextArea(int x, int y, int width, int height, CanvasMouseListener mouse) {
        super(x, y, width, height, mouse);
    }

    public void nextText() {
        if(!textList.isEmpty()) {
            text = textList.pop();
        }
    }
    
    public Boolean haveNextDialogue() {
        return !textList.isEmpty();
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(DEFAULT_FONT);
        g.setColor(Color.white);
        g.drawImage(dialogueBox, x, y, width, height,null);


        g.setColor(Color.black);
        //g.fillRect(x + width - pr, y, width, height);

        int baseX = x + pl;
        int nowX = x + pl;
        int nowY = y + fontSize + pt;
        String daftarKata[] = text.split(" ");
        for (String daftarKataI : daftarKata) {
            String daftarKata2[] = daftarKataI.split("\n");
            for (int j = 0;j < daftarKata2.length;j++) {
                String daftarKataJ = daftarKata2[j];
                if(nowX + fontPx*(daftarKataJ.length()) >= x + width - pr) {
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

    public void setTextList(DoubleLinkList<String> textList) {
        this.textList = textList;
    }

    public String getTextNow() {
        return text;
    }
    
    
    
}
