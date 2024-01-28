package github.captionbaro.io.ctf.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvents implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if (event.getView().getTitle().equalsIgnoreCase("§aPlayer Teleporter")){
            event.setCancelled(true);
            if (event.getCurrentItem() != null){
               String playerName = event.getCurrentItem().getItemMeta().getLocalizedName();
               event.getWhoClicked().closeInventory();
               event.getWhoClicked().teleport(Bukkit.getPlayer(playerName));
            }
        }else if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Goodluck")){
            event.setCancelled(true);
        }
    }


}
