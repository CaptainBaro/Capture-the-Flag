package darkyiu.me.ctf3.kits.abilities;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AphroditeAbilities implements Abilities {

    private final int size = 7;
    private int taskID;
    @Override
    public void ability_1(Player player, CTF3 plugin) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*2, 4));
        player.playSound(player.getLocation(), Sound.BLOCK_COMPOSTER_FILL_SUCCESS, 1, 2.0f);
    }

    @Override
    public void ability_2(Player player, CTF3 plugin) {
        circleAbility(player, plugin);

    }

    @Override
    public void ultimate(Player player, CTF3 plugin) {

    }

    public void circleAbility(Player player, CTF3 plugin){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 200;
            double var = 0;
            Location first, second;
            Location loc = player.getLocation();
            @Override
            public void run() {
                if(time >= 0){
                    var += Math.PI / 16;
                    first = loc.clone().add(size*Math.cos(var), 0 , size*Math.sin(var));

                    player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                    if (time % 20 == 0){
                        for (Entity entity : loc.getWorld().getNearbyEntities(loc, size, size, size)){
                            if(entity.isDead())continue;
                            LivingEntity livingEntity = (LivingEntity) entity;
                            plugin.getBasicUtil().healSavely(livingEntity, 2);
                            livingEntity.playEffect(EntityEffect.VILLAGER_HEART);
                        }
                    }
                    time--;
                }else {
                    Bukkit.getScheduler().cancelTask(taskID);
                }

            }
        }, 0 , 1);
    }
}
