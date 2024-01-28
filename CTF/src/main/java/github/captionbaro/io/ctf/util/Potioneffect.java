package github.captionbaro.io.ctf.util;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Potioneffect {

    public static void removePotionEffects(Player player){
        for (PotionEffect effect : player.getActivePotionEffects()){
             player.removePotionEffect(effect.getType());
        }
    }

}
