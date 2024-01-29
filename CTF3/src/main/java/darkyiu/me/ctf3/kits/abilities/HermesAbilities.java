package darkyiu.me.ctf3.kits.abilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class HermesAbilities implements Abilities{
    @Override
    public void ability_1(Player player) {
        double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
        double yaw  = ((player.getLocation().getYaw() + 90)  * Math.PI) / 180;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);
        Vector vector = new Vector(x, z, y);

        player.setVelocity(vector.multiply(1));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2.0f);
    }

    @Override
    public void ability_2(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20, 1));
        player.playSound(player.getLocation(), Sound.BLOCK_COMPOSTER_FILL_SUCCESS, 1, 2.0f);
    }

    @Override
    public void ultimate(Player player) {

    }
}
