package darkyiu.me.ctf3.listener;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.kits.Kits;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class AbilitiesListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        useAbility(event.getPlayer(), 1);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
        useAbility(event.getPlayer(), 2);
    }

    public void useAbility(Player player, int ability){
        CTF3 plugin = CTF3.getPlugin();
        if(plugin.getKitManager().getKit(player)==null){return;}
        Kits kit = plugin.getKitManager().getKit(player);
        double cooldown = 0;
        switch (ability){
            case 1:
                cooldown = kit.getCooldown_1();
                break;
            case 2:
                cooldown = kit.getCooldown_2();
            case 3:
                cooldown = kit.getCooldown_ult();
                break;
            default:
                break;
        }
        if (!plugin.getCooldownManager().onCooldown(player, cooldown, ability)){
            player.sendMessage("§cThis ability is still charging!");
            return;
        }
        plugin.getCooldownManager().setCooldown(player.getUniqueId().toString() + ability, (long) kit.getCooldown_1());
        String message = "§aYou used";
        switch (ability){
            case 1:
                message+=kit.getAbility_1();
                break;
            case 2:
                message+=kit.getAbility_2();
                break;
            case 3:
                message+=kit.getAbility_ult();
                break;
            default:
                message+="How do you have more than three abilities?";
                break;
        }
        player.sendMessage(message);
        kit.getAbilities().ability_1(player);
    }
}
