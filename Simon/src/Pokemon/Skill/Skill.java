/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon.Skill;

import Pokemon.Pokemon;
import java.util.Objects;

/**
 *
 * @author LVOILA
 */

public abstract class Skill {
    protected String skillName;
    public Skill(String skillName) {
        this.skillName = skillName;
    }
    
    public abstract String use(Pokemon player,Pokemon enemy,boolean usedByPlayer);
    public abstract String getDescription();
    public String getSkillName() {
        return this.skillName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Skill other = (Skill) obj;
        return Objects.equals(this.skillName, other.skillName);
    }
    
    
}
