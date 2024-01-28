package darkyiu.me.ctf3.kits;

import darkyiu.me.ctf3.kits.abilities.Abilities;
import darkyiu.me.ctf3.kits.abilities.HermesAbilities;

public enum Kits {

    HERMES("Hermes","The god of everything that walks on streets.", 200, new HermesAbilities(), 15.0, 15.0, 60);

    private final String name;
    private String description;
    private int cost;
    private Abilities abilities;
    private double cooldown_1;
    private double cooldown_2;
    private double cooldown_ult;
    Kits(String name, String description,int cost, Abilities abilities, double cooldown_1, double cooldown_2, double cooldown_ult){
        this.name = name;
        this.description = description;
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

    public double getCooldown_1() {
        return cooldown_1;
    }

    public double getCooldown_2() {
        return cooldown_2;
    }

    public double getCooldown_ult() {
        return cooldown_ult;
    }

    public static Kits getKit(String kit){
        for(Kits kits : Kits.values()){
            if(kits.getName()==kit){
                return kits;
            }
        }
        return null;
    }
}
