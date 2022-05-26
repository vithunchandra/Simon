/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

import Pokemon.Skill.Tackle;
import java.awt.Graphics;
import java.io.IOException;
import Util.MyFrame;

/**
 *
 * @author LVOILA
 */
public class PlantSimon extends Pokemon{

    public PlantSimon(int maxHp, int damage) throws IOException {
        super("Plant Simon", maxHp, damage, ImagePath.SIMON_FRONT_PATH, 4);
        this.skillList.set(0, new Tackle());
    }

    @Override
    public void logicLoop(long timediff) {
        graphicLoop(timediff);
    }
    
    private void graphicLoop(long timediff) {
        super.defaultGraphicLoop(timediff);
    }
}
