package darkyiu.me.ctf3.kits.abilities;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HadesAbilities implements Abilities {

    private int size = 6;
    private int timer = 20;
    private int taskID;
    @Override
    public void ability_1(Player player, CTF3 plugin) {
        for (Entity entity : player.getNearbyEntities(size, size,size)){
            if (entity instanceof LivingEntity){
                LivingEntity entity1 = (LivingEntity) entity;
                entity1.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1));
            }
        }
    }

    @Override
    public void ability_2(Player player, CTF3 plugin) {

    }

    @Override
    public void ultimate(Player player, CTF3 plugin) {
        plugin.getArrayListManager().getInShadow().add(player);
        for (Player players : plugin.getArrayListManager().getPlayers()){
            players.hidePlayer(plugin, player);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 2));
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (timer <= 0) {
                    Bukkit.getScheduler().cancelTask(taskID);
                }
                player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 10);
                timer--;
            }
        }, 0, 10);
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player1 : plugin.getArrayListManager().getPlayers()){
                    player1.showPlayer(plugin, player);
                }
                plugin.getArrayListManager().getInShadow().remove(player);
            }
        },200);

    }
}
