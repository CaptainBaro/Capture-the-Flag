package github.captionbaro.io.ctf.gui;

import github.captionbaro.io.ctf.util.ItemBuilder;
import github.captionbaro.io.ctf.util.warp.WarpManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class WarpGui implements Listener {

    public void openTeleporterMenu(Player player){
        Inventory teleporterPage = new GuiBuilder(9*5, "§aTeleport-Menu").setBackground(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setName("  ").setLocalizedName("Gui.Nothing").build()).setCloseButton().build();
        teleporterPage.setItem(4, new ItemBuilder(Material.NETHER_STAR).setName("§aSpawn").setLocalizedName("Warp.Spawn").build());
        player.openInventory(teleporterPage);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().equalsIgnoreCase("§aTeleport-Menu"))return;
        if(event.getCurrentItem() == null)return;
        if(event.getCurrentItem().getItemMeta().getLocalizedName().contains("Warp")){
            String warpname = event.getCurrentItem().getItemMeta().getLocalizedName().replace("Warp.", "");
            if(WarpManager.getWarp(warpname) != null){
                event.getWhoClicked().teleport(WarpManager.getWarp(warpname));
            }else {
                event.getWhoClicked().sendMessage("§cIrgendetwas ist schief gelaufen. Melde dies bitte bei einem Team Mitglied.");
            }
        }
    }
}
