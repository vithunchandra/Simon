/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DummyClass;

import java.awt.*;

/**
 *
 * @author asus
 */
public class PokemonDummy {
    Image dummyImage;
    int maxHp, attack, defense, level;
    String name, element;

    public PokemonDummy(Image dummyImage, int maxHp, int attack, int defense, int level, String name, String element) {
        this.dummyImage = dummyImage;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.name = name;
        this.element = element;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Image getDummyImage() {
        return dummyImage;
    }

    public void setDummyImage(Image dummyImage) {
        this.dummyImage = dummyImage;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
