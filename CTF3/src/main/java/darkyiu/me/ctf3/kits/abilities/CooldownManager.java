package darkyiu.me.ctf3.kits.abilities;

import darkyiu.me.ctf3.CTF3;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CooldownManager {

    private final Map<String, Long>cooldowns = new HashMap<>();

    public void setCooldown(String abilityname, long time){
        if(time<1){
            cooldowns.remove(abilityname);
        }else {
            cooldowns.put(abilityname, time);
        }
    }

    public Long getCooldown(String abilityname){ return cooldowns.getOrDefault(abilityname, (long)0);}

    public boolean onCooldown(Player player, double abilityCooldown, int ability){
        String playerAbility = player.getUniqueId().toString() + ability;
        long cooldown = (long) (abilityCooldown * 1000L);
        long timeLeft =System.currentTimeMillis() - CTF3.getPlugin().getCooldownManager().getCooldown(playerAbility);
        if (TimeUnit.MILLISECONDS.toMillis(timeLeft) >= cooldown){
            return true;
        }
        player.sendMessage("§cThis ability is still charging for " + TimeUnit.MILLISECONDS.toSeconds(cooldown - timeLeft) + " seconds!.");
        return false;

    }

}
