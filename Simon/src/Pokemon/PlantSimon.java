/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import java.awt.Graphics;
import java.io.IOException;
import Util.MyFrame;

/**
 *
 * @author LVOILA
 */
public class PlantSimon extends Pokemon{

    public PlantSimon(String nama,int maxHp, int damage,int x,int y,int width,int height) throws IOException {
        super(nama, maxHp, damage, ImagePath.SIMON_FRONT_PATH, 8,x,y,width,height);
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(frontSpriteImage.get(standCt), x, y,width,height, null);
    }

    @Override
    public void logicLoop(long timediff) {
        graphicLoop(timediff);
    }
    
    private void graphicLoop(long timediff) {
        super.defaultGraphicLoop(timediff);
    }
}
