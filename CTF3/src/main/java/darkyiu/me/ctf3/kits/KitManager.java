package darkyiu.me.ctf3.kits;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class KitManager {

    private final Map<Player, Kits> playerKitsMap = new HashMap<>();

    public void setKit(Player player, Kits kits){
        playerKitsMap.put(player, kits);
    }

    public Kits getKit(Player player){
        return playerKitsMap.getOrDefault(player, null);
    }

}
