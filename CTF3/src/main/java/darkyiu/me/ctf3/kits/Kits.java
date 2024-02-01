package darkyiu.me.ctf3.kits;

import darkyiu.me.ctf3.kits.abilities.*;

import java.util.Objects;

public enum Kits {

    ARTEMIS("Artemis", "Goddes of the moon and hunting", "Wolf attack", "Great Hunt", "Moon, Give me power", 400, new ArtemisAbilities(), 10, 20, 60),
    HEPHAISTOS("Hephaistos", "God of fire and smithing", "Fireball", "Protection Turret", "Ultimate Upgrade", 300, new HephaistosAbilities(), 10, 20, 50),
    HADES("Hades", "God of the underworld and the undead", "Spell of Blinding", "Spell of Gemstones", "Shadow Travel", 400, new HadesAbilities(), 20, 15, 60),
    ARES("Ares", "God of War", "Violent SLash", "Throwaway", "Enrage", 300, new AresAbilities(), 5, 5, 30),
    ZEUS("Zeus", "God of lightning", "Fly high", "Knockback", "Lightning storm", 500, new ZeusAbilities(), 10, 10, 60),
    APHRODITE("Aphrodite", "The godess of love and desire.", "Brace yourself", "Healing Circle", "Charm", 200, new AphroditeAbilities(), 20, 20, 60),
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
