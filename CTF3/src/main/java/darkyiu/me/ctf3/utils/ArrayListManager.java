package darkyiu.me.ctf3.utils;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;


public class ArrayListManager {

    private CTF3 plugin;
    private ArrayList<Player> players;
    private ArrayList<Player> inShadow;
    private ArrayList<Player> vanished;
    private List<Player> flying;


    public ArrayListManager(CTF3 plugin){
        this.plugin = plugin;
        players = new ArrayList<>();
        inShadow = new ArrayList<>();
        flying = new ArrayList<>();
        vanished = new ArrayList<>();
    }

    public boolean removePlayer(Player player){
        if (players.contains(player)){
            List<LivingEntity> outerCircle = new ArrayList<>();
            return true;
        }
        return false;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getInShadow() {
        return inShadow;
    }

    public List<Player> getFlying() {
        return flying;
    }

    public ArrayList<Player> getVanished() {
        return vanished;
    }
}
