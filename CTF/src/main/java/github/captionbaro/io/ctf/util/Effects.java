package github.captionbaro.io.ctf.util;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class   Effects {

    private int time;
    private CTF plugin;
    private int taskID;
    private final Player player;

    public Effects(Player player, CTF plugin){
        this.player = player;
        this.plugin = plugin;
    }
    public void startTotem(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 300;
            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId().toString() + ".Totem");
            @Override
            public void run() {
                if(!(time <= 0)){
                    if(!particle.hasID()){
                        particle.setID(taskID);
                    }

                    var += Math.PI / 16;

                    loc = player.getLocation();
                    first = loc.clone().add(2*Math.cos(var), 2*Math.sin(var)  , 2*Math.sin(var));
                    second = loc.clone().add(2*Math.cos(var + Math.PI),2*Math.sin(var), 2*Math.sin(var + Math.PI));

                    player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                    player.getWorld().spawnParticle(Particle.TOTEM, second, 0);
                    player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                    time--;
                }else {
                    particle.endTask();
                    particle.removeID();
                }

            }
        }, 0 , 1);
    }
    public void startTest(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 300;
            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId().toString() + ".Test");
            @Override
            public void run() {
                if(!(time <= 0)){
                    if(!particle.hasID()){
                        particle.setID(taskID);
                    }

                    var += Math.PI / 16;

                    loc = player.getLocation();
                    first = loc.clone().add(Math.cos(var), Math.sin(var) +1 , Math.sin(var));
                    second = loc.clone().add(Math.cos(var + Math.PI),Math.sin(var), Math.sin(var + Math.PI));

                    player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, first, 0);
                    player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, second, 0);
                    player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, first, 0);
                    time--;
                }else {
                    particle.endTask();
                    particle.removeID();
                }
            }
        }, 0, 1);
    }
    public  void startExplosion(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 20;
            double var = 2;
            Location first, second, third;
            ParticleData particle = new ParticleData(player.getUniqueId().toString() + ".Line");
            double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
            double yaw  = ((player.getLocation().getYaw() + 90)  * Math.PI) / 180;
            Double x = Math.sin(pitch) * Math.cos(yaw);
            Double y = Math.sin(pitch) * Math.sin(yaw);
            Double z = Math.cos(pitch);
            Vector vector = new Vector(x, z, y);
            Vector directiontest = player.getLocation().getDirection();
            Location loc = player.getLocation();
            @Override
            public void run() {
                if (time <= 0){
                    particle.endTask();
                    particle.removeID();
                }
                if(!particle.hasID()){
                    particle.setID(taskID);
                }

             //   Vector vector1 = player.getLocation().getDirection();
                first = loc.add(directiontest);

                player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, first, 0);
                time--;

            }
        },0, 1);
    }
    public void aphroditenormal1Effect(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 200;
            double var = 0;
            Location loc, first, second;
            @Override
            public void run() {
                if(!(time <= 0)){


                    var += Math.PI / 16;

                    loc = player.getLocation();
                    first = loc.clone().add(2*Math.cos(var), 0 , 2*Math.sin(var));
                    second = loc.clone().add(2*Math.cos(var + Math.PI),0, 2*Math.sin(var + Math.PI));

                    player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                    player.getWorld().spawnParticle(Particle.TOTEM, second, 0);
                    player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                    time--;
                }else {
                    Bukkit.getScheduler().cancelTask(taskID);
                }

            }
        }, 0 , 1);

    }
    public void aphroditenormal2Effect(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 200;
            double var = 0;
            Location first, second;
            Location loc = player.getLocation();
            @Override
            public void run() {
                if(time >= 0){
                    var += Math.PI / 16;
                    first = loc.clone().add(6*Math.cos(var), 0 , 6*Math.sin(var));

                    player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                    time--;
                }else {
                    Bukkit.getScheduler().cancelTask(taskID);
                }

            }
        }, 0 , 1);
    }
    public void aresUltiEffect(){
         taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
             int time = 60;
             double var = 0;
             Location first,second,third;
             @Override
             public void run() {
                 if (!(time <= 0)){
                     var += Math.PI / 16;

                     Location loc = player.getLocation();
                     first = loc.clone().add( Math.cos(var), Math.sin(var)+1 , Math.sin(var));
                     second = loc.clone().add(Math.cos(var + Math.PI),Math.sin(var)+1, Math.sin(var + Math.PI));

                     player.getWorld().spawnParticle(Particle.CRIMSON_SPORE, first, 0);
                     player.getWorld().spawnParticle(Particle.CRIMSON_SPORE, second, 0);
                     player.getWorld().spawnParticle(Particle.CRIMSON_SPORE, first, 0);
                     time--;
                 }else {
                     player.getWorld().spawnParticle(Particle.CRIMSON_SPORE, player.getLocation().add(0,2,0), 1);
                     Bukkit.getScheduler().cancelTask(taskID);
                 }
             }
         },0,1);
    }
    public void hadesnormal2Effect(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int time = 20 * 20;
            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId().toString() + ".Shadowrun");
            @Override
            public void run() {
                if(!(time <= 0)){
                    if(!particle.hasID()){
                        particle.setID(taskID);
                    }

                    var += Math.PI / 16;

                    loc = player.getLocation();
                    first = loc.clone().add(2*Math.cos(var), 2*Math.sin(var)  , 2*Math.sin(var));
                    second = loc.clone().add(2*Math.cos(var + Math.PI),2*Math.sin(var), 2*Math.sin(var + Math.PI));

                    player.getWorld().spawnParticle(Particle.ASH, first, 0);
                    player.getWorld().spawnParticle(Particle.ASH, second, 0);
                    player.getWorld().spawnParticle(Particle.ASH, first, 0);
                    time--;
                }else {
                    particle.endTask();
                    particle.removeID();
                }

            }
        }, 0 , 1);
    }
    public ArrayList<Location> getCircle(Location center, double radius, int amount){
        World world  = center.getWorld();
        double increment = (2* Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < amount; i++){
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }


}
