package darkyiu.me.ctf3.listener;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;



public class PassiveListener implements Listener {

    public CTF3 plugin = CTF3.getPlugin();

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))return;
        Player player = (Player) event.getEntity();
        if(plugin.getKitManager().getKit(player)==null)return;
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            if (plugin.getKitManager().getKit(player).getName().equals("Hermes")) {
                event.setCancelled(true);
            }
        }
    }
}
