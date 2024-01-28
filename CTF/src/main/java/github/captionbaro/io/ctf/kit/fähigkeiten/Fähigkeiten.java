package github.captionbaro.io.ctf.kit.fähigkeiten;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.Effects;
import github.captionbaro.io.ctf.util.ParticleData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Fähigkeiten {

    private Player player;
    private CTF plugin;
    private int shadowrunseconds = 20 * 20;
    private Effects effects;

    public Fähigkeiten(Player player, CTF plugin){
        this.player = player;
        this.plugin = plugin;
        this.effects = new Effects(player, plugin);
    }
    public void hermesNormal(){
        double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
        double yaw  = ((player.getLocation().getYaw() + 90)  * Math.PI) / 180;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);
        Vector vector = new Vector(x, z, y);

        player.setVelocity(vector.multiply(1));
    }
    public void hermesNormal2(){
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0));
        player.playSound(player.getLocation(), Sound.BLOCK_COMPOSTER_FILL_SUCCESS, 5, 1);
    }
    public void hermesUlti(){
        for (Entity entity : player.getNearbyEntities(5,5,5)){
            if (entity instanceof Player){
                Player player = (Player) entity;

            }
        }
    }
    public void aphroditenormal() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 1));
        effects.aphroditenormal2Effect();
    }
    public void aphroditenormal2(){
        effects.aphroditenormal2Effect();
        for (Entity entity : player.getNearbyEntities(7,7,7)){
            if(entity instanceof Player){
                Player player1 = (Player) entity;
                player1.playSound(player1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 5, 1);
                if(plugin.getTeamManager().getPlayerTeam(player1) == plugin.getTeamManager().getPlayerTeam(player)){
                    player1.setHealth(player1.getHealth() + 5);
                }
            }
        }
    }
    public void aphroditeUlti(){
        Location location = player.getLocation();
        for (int i = 0; i <=30; i++){
            location = location.add(location.getDirection().getX(), location.getDirection().getY() - 0.005,
                    location.getDirection().getZ());
            player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location, 1);
            for (Player player : getEntitiesByLocation(location, 1)){
                for (int f = 0; f <= 10 * 360; i++){
                    player.getLocation().setYaw(f);
                }
            }
        }
    }
    public List<Player> getEntitiesByLocation(Location loc, int r){
        List<Player> players = new ArrayList<>();
        for (Player current : plugin.getArrayListManager().getIngamePlayers()){
            if (current.getLocation().distanceSquared(loc) >= r){
                if (plugin.getTeamManager().getPlayerTeam(current) != plugin.getTeamManager().getPlayerTeam(player)){
                    players.add(current);
                }
            }
        }
        return players;
    }
    public void aresNormal1(){
    }
    public void aresulti(){
        effects.aresUltiEffect();
        BukkitTask taskID = Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 1, true));
            }
        },60);

    }
    public void hadesNormal2(){
        plugin.getArrayListManager().getShadowrunPlayers().add(player);
        effects.hadesnormal2Effect();
        for (Player all : plugin.getArrayListManager().getIngamePlayers()){
            if(plugin.getTeamManager().getPlayerTeam(all) != plugin.getTeamManager().getPlayerTeam(player)){
                all.hidePlayer(plugin, player);
            }
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, shadowrunseconds, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, shadowrunseconds, 1));
        BukkitTask taskID = Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                    if(all.canSee(player))
                        all.showPlayer(plugin, player);
                }
                hadesNormal2Stop();

            }
        }, shadowrunseconds);

    }
    public void hadesNormal2Stop(){
        plugin.getArrayListManager().getShadowrunPlayers().remove(player);
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        player.removePotionEffect(PotionEffectType.SPEED);
        for (Player all : plugin.getArrayListManager().getIngamePlayers()){
            if(all.canSee(player))
                all.showPlayer(plugin, player);
        }
        ParticleData particle = new ParticleData(player.getUniqueId().toString() + ".Shadowrun");
        particle.endTask();
        particle.removeID();
    }
    public void dionysusUlti(){
        for (Player current : plugin.getArrayListManager().getIngamePlayers()){
            if(plugin.getTeamManager().getPlayerTeam(current) == plugin.getTeamManager().getPlayerTeam(player)){
                current.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40 * 20, 0));
            }else {
                current.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 0));
            }
        }
    }
    public void dionysusNormal1(){
        for (Entity entity : player.getNearbyEntities(6,6,6)){
            if (entity instanceof Player){
                Player targets = (Player) entity;
                if(plugin.getTeamManager().getPlayerTeam(targets) != plugin.getTeamManager().getPlayerTeam(player)){
                    targets.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5*20, 0));
                }else{
                    targets.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 0));
                }
            }
        }
    }
    public void hadesNormal1(){
        for (Entity entity : player.getNearbyEntities(6,6,6)){
            if(entity instanceof  Player){
                Player targets = (Player) entity;
                if(plugin.getTeamManager().getPlayerTeam(targets) != plugin.getTeamManager().getPlayerTeam(player)){
                    targets.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10*20, 0));
                }
            }
        }
    }

}
