/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Pokemon.PlantSimon;
import Pokemon.Pokemon;
import Pokemon.Skill.BodySlam;
import Pokemon.Venusaur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class Player {
    public static ArrayList<Pokemon> pokemonInParty;
    public static ArrayList<Pokemon> pokemonInBox;
    public static void loadInfo() {
        pokemonInParty = new ArrayList<>();
        pokemonInBox =  new ArrayList<>();
        try {
            pokemonInParty.add(new Venusaur() );
            
            pokemonInParty.add(new PlantSimon(200, 5));
//            pokemonInParty.add(new PlantSimon(300, 30));
            
            pokemonInParty.get(1).levelUp();
            pokemonInParty.get(1).levelUp();
            pokemonInParty.get(1).levelUp();
            
//            pokemonInParty.get(2).addSkill(new BodySlam());
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
//            pokemonInParty.get(2).levelUp();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
