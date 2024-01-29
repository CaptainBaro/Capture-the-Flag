package darkyiu.me.ctf3.kits;

import darkyiu.me.ctf3.kits.abilities.Abilities;
import darkyiu.me.ctf3.kits.abilities.HermesAbilities;

import java.util.Objects;

public enum Kits {

    HERMES("Hermes","The god of everything that walks on streets.", "Dash", "Speed", "Mix Up",200, new HermesAbilities(), 15, 10, 60);

    private final String name;
    private String description;
    private String ability_1;
    private String ability_2;
    private String ability_ult;
    private int cost;
    private Abilities abilities;
    private int cooldown_1;
    private int cooldown_2;
    private int cooldown_ult;
    Kits(String name, String description,String ability_1,String ability_2, String ability_ult,int cost, Abilities abilities, int cooldown_1, int cooldown_2, int cooldown_ult){
        this.name = name;
        this.description = description;
        this.ability_1 = ability_1;
        this.ability_2 = ability_2;
        this.ability_ult = ability_ult;
        this.cost = cost;
        this.abilities = abilities;
        this.cooldown_1 = cooldown_1;
        this.cooldown_2 = cooldown_2;
        this.cooldown_ult = cooldown_ult;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public int getCooldown_1() {
        return cooldown_1;
    }

    public int getCooldown_2() {
        return cooldown_2;
    }

    public int getCooldown_ult() {
        return cooldown_ult;
    }

    public String getAbility_1() {
        return ability_1;
    }

    public String getAbility_2() {
        return ability_2;
    }

    public String getAbility_ult() {
        return ability_ult;
    }

    public static Kits getKit(String kit){
        for(Kits kits : Kits.values()){
            if(Objects.equals(kits.getName(), kit)){
                return kits;
            }
        }
        return null;
    }
}
