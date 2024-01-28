package github.captionbaro.io.ctf.countdowns;

import github.captionbaro.io.ctf.CTF;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UltiManager {

    private CTF plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public static final long DEFAULT_COOLDOWN = 60;


    public UltiManager(CTF plugin){
        this.plugin = plugin;

    }
    public void setCooldown(UUID player, long time ){
        if(time < 1){
            cooldowns.remove(player);
        }else {
            cooldowns.put(player, time);
        }
    }
    public Long getCooldown(UUID player){
        return cooldowns.getOrDefault(player, (long) 0);
    }

}
