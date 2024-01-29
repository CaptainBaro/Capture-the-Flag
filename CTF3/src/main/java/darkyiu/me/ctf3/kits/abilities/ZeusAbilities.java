package darkyiu.me.ctf3.kits.abilities;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.kits.Kits;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ZeusAbilities implements Abilities, Listener {

    private List<Player> flying = new ArrayList<>();
    private CTF3 plugin = CTF3.getPlugin();

    public ZeusAbilities(){
        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getWorld("world").getPlayers().forEach(player -> {
                    if (player.getGameMode()!=GameMode.SURVIVAL)return;
                    if (plugin.getKitManager().getKit(player)!=Kits.ZEUS)return;
                    player.setAllowFlight(true);
                    if (flying.contains(player)&&!player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()){
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.setGliding(false);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            flying.remove(player);
                        },5);
                    }
                });
            }
        },0,3);
    }
    @Override
    public void ability_1(Player player, CTF3 plugin) {
        Vector vector = new Vector();
        vector.setY(30);
        player.setVelocity(vector);
        player.setAllowFlight(true);
    }

    @Override
    public void ability_2(Player player, CTF3 plugin) {
        List<LivingEntity> livingEntities = new ArrayList<>();
        Vector original = player.getLocation().getDirection();
        int size = 1;
        for (int i = 1; i<6; i++){
                Vector inFront = original.clone().multiply(i);
                for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation().add(inFront.toLocation(player.getWorld())), size,size, size)){
                    if (entity.isDead())continue;
                    if (livingEntities.contains(entity))continue;
                    livingEntities.add((LivingEntity) entity);
                }
                size++;

        }
        livingEntities.remove(player);
        for (LivingEntity entity : livingEntities){
            entity.setVelocity(original.add(new Vector(0,0.5,0)).multiply(2));
        }
    }

    @Override
    public void ultimate(Player player, CTF3 plugin) {
        List<LivingEntity> outerCircle = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(15, 100, 15)){
            if (entity instanceof LivingEntity) {
                outerCircle.add((LivingEntity) entity);
            }
        }
        for (Entity entity : player.getNearbyEntities(3, 100, 3)){
            if (outerCircle.contains(entity)){
                outerCircle.remove(entity);
            }
        }
        for (LivingEntity entity : outerCircle){
            player.getWorld().strikeLightning(entity.getLocation());
        }

    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {
        if(event.getEntityType() == EntityType.PLAYER && flying.contains(event.getEntity()))event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.SURVIVAL)return;
        if(plugin.getKitManager().getKit(player)!=Kits.ZEUS)return;
        event.setCancelled(true);
        event.getPlayer().setGliding(true);
        flying.add(player);
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntityType()== EntityType.PLAYER
                &&(event.getCause()== EntityDamageEvent.DamageCause.FALL || event.getCause()== EntityDamageEvent.DamageCause.FLY_INTO_WALL)
                &&flying.contains(event.getEntity()))event.setCancelled(true);
    }
}
