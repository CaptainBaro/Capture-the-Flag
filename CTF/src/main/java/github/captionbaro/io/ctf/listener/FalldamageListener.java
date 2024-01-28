package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.kit.utils.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FalldamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            CTF plugin = CTF.getPlugin();
            if (plugin.getArrayListManager().getShadowrunPlayers().contains(player)){
                event.setCancelled(true);
            }
            if(plugin.getArrayListManager().getIngamePlayers().contains(player)){
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    if(plugin.getKitManager().getPlayerKit(player) == Kit.HERMES){
                        event.setCancelled(true);
                    }
                }
            }else {
                event.setCancelled(true);
            }
        }
    }
}
