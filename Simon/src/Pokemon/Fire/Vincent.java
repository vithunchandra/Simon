/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Fire;

import Pokemon.*;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class Vincent extends Pokemon{

    public Vincent(String nama, int maxHp, int damage, String path, int numSprite) throws IOException {
        super(nama, maxHp, damage, path, numSprite,numSprite);
    }

    public Vincent(Pokemon object) {
        super(object);
    }
    
    public Vincent(int level) throws IOException{
        super("Vincent", 100 + (level * 10), 5 + (level * 2), "", 4,4);
    }
    
    @Override
    public void logicLoop(long timediff) {
        super.defaultGraphicLoop(timediff);
    }
    
}
