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
import java.util.Random;

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
            pokemonInParty.add(new PlantSimon(300, 30));
            
            pokemonInParty.get(1).levelUp();
            pokemonInParty.get(1).levelUp();
            pokemonInParty.get(1).levelUp();
            
            pokemonInParty.get(2).addSkill(new BodySlam());
            pokemonInParty.get(2).levelUp();
            pokemonInParty.get(2).levelUp();
            pokemonInParty.get(2).levelUp();
            pokemonInParty.get(2).levelUp();
            pokemonInParty.get(2).levelUp();
            
            Random rand = new Random();
            for(int i=0; i<18; i++){
                pokemonInBox.add(new PlantSimon(rand.nextInt(100, 401), rand.nextInt(2, 40)));
                int level = rand.nextInt(5);
                for(int j=0; j<level; j++){
                    pokemonInBox.get(pokemonInBox.size() - 1).levelUp();
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
