package darkyiu.me.ctf3.utils;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class ArrayListManager {

    private CTF3 plugin;
    private ArrayList<Player> players;

    public ArrayListManager(CTF3 plugin){
        this.plugin = plugin;
        players = new ArrayList<>();
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
}
