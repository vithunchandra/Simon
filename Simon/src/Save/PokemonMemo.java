/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Save;

import Pokemon.Pokemon;
import Pokemon.Skill.Skill;
import Pokemon.Water.*;
import Pokemon.Fire.*;
import Pokemon.grass.PlantSimon;
import Pokemon.grass.PlantSimonEvo;
import Pokemon.grass.Venusaur;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author LVOILA
 */
public class PokemonMemo implements Serializable{
    private String name;
    private int lvl,hp, maxHp, damage;
    private int exp,expNeededToLevelUp;
    protected ArrayList<Skill> skillList;
    public PokemonMemo(Pokemon pokemon) {
        this.skillList = new ArrayList<>();
        this.name = pokemon.getNama();
        
        this.lvl = pokemon.getLvl();
        this.hp = pokemon.getHp();
        this.maxHp = pokemon.getMaxHp();
        this.damage = pokemon.getDamage();
        this.exp = pokemon.getExp();
        this.expNeededToLevelUp = pokemon.getExpNeededToLevelUp();
        
        for(int i = 0;i < pokemon.getNumberOfSkill();i++) {
            
            skillList.add(pokemon.getSkill(i));
        }
    }
    
    public Pokemon load() throws IOException {
        Pokemon now = null;
        if(name.equals("Lapras")) {
            now = new Lapras();
            now.loadMemo(this);
        } else if(name.equals("Plant Simon")) {
            now = new PlantSimon();
            now.loadMemo(this);
        } else if(name.equals("Evolved Plant Simon")) {
            now = new PlantSimonEvo();
            now.loadMemo(this);
        } else if(name.equals("Venusaur")) {
            now = new Venusaur();
            now.loadMemo(this);
        }
        return now;
    }

    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getDamage() {
        return damage;
    }

    public int getExp() {
        return exp;
    }

    public int getExpNeededToLevelUp() {
        return expNeededToLevelUp;
    }

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }
    
    
    
}
