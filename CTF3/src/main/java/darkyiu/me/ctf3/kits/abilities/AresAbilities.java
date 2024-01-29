package darkyiu.me.ctf3.kits.abilities;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AresAbilities implements Abilities {
    @Override
    public void ability_1(Player player, CTF3 plugin) {

    }

    @Override
    public void ability_2(Player player, CTF3 plugin) {

    }

    @Override
    public void ultimate(Player player, CTF3 plugin) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10*20, 2));
    }
}
