package github.captionbaro.io.ctf.kit.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class KitManager {

    private HashMap<Player, Kit> kitConnector;

    public KitManager(){
        kitConnector = new HashMap<>();
    }
    public Kit getPlayerKit(Player player){
        return kitConnector.containsKey(player) ? kitConnector.get(player) : null;
    }
    public void setPlayerKit(Player player, Kit kit){
        kitConnector.put(player, kit);
    }

}
