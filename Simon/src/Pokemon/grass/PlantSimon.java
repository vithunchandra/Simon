/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.grass;

import Pokemon.ImagePath;
import Pokemon.Pokemon;
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
        super("Plant Simon", maxHp, damage, ImagePath.SIMON_FRONT_PATH, 4,4);
        this.skillList.set(0, new Tackle());
    }

}
