package darkyiu.me.ctf3.listener;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.kits.Kits;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;


public class PassiveListener implements Listener {

    private CTF3 plugin;

    public PassiveListener(CTF3 plugin){
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))return;
        if(event.getEntityType()== EntityType.PLAYER
                &&(event.getCause()== EntityDamageEvent.DamageCause.FALL || event.getCause()== EntityDamageEvent.DamageCause.FLY_INTO_WALL)
                &&plugin.getArrayListManager().getFlying().contains(event.getEntity()))event.setCancelled(true);
        Player player = (Player) event.getEntity();
        if(plugin.getKitManager().getKit(player)==null)return;
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            if (plugin.getKitManager().getKit(player).getName().equals("Hermes")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {
        if(event.getEntityType() == EntityType.PLAYER && plugin.getKitManager().getKit((Player) event.getEntity()).equals(Kits.ZEUS) && plugin.getArrayListManager().getFlying().contains(event.getEntity()))event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.SURVIVAL)return;
        if(plugin.getKitManager().getKit(player)!=Kits.ZEUS)return;
        event.setCancelled(true);
        event.getPlayer().setGliding(true);
        plugin.getArrayListManager().getFlying().add(player);
    }




}
