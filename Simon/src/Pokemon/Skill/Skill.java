/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import Pokemon.Pokemon;

/**
 *
 * @author LVOILA
 */

public abstract class Skill {
    protected String skillName;
    public Skill(String skillName) {
        this.skillName = skillName;
    }
    
    public abstract String use(Pokemon player,Pokemon enemy);
    public abstract String getDescription();
    public String getSkillName() {
        return this.skillName;
    }
}
