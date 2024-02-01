package darkyiu.me.ctf3.guis;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.kits.Kits;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class KitGui implements Listener {

    private CTF3 plugin;
    private String title = "§aKit-Selection:";

    public KitGui(CTF3 plugin){
        this.plugin = plugin;
    }

    public Inventory build(Player player){
        Inventory inventory = Bukkit.createInventory(player, InventoryType.CHEST, title);
        int index = 0;
        for (Kits kits : Kits.values()){
            ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§a" + kits.getName());
            itemMeta.setLocalizedName("Kit." + kits.getName());
            ArrayList<String> lores = plugin.getBasicUtil().cutLore(kits.getDescription());
            lores.add("§a   - " + kits.getAbility_1());
            lores.add("§a   - " + kits.getAbility_2());
            lores.add("§a   - " + kits.getAbility_ult());
            itemMeta.setLore(lores);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
        }
        return inventory;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(title)){
            event.getPlayer().openInventory(build((Player) event.getPlayer()));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTitle().equals(title)))return;
        if (event.getCurrentItem()==null)return;
        if (event.getCurrentItem().getItemMeta()==null){
            event.setCancelled(true);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getLocalizedName().contains("Kit.")){
            String kitName = event.getCurrentItem().getItemMeta().getLocalizedName().substring(4);
            if (Kits.getKit(kitName)==null){
                event.setCancelled(true);
                return;
            }
            Kits kit = Kits.getKit(kitName);
            plugin.getKitManager().setKit((Player) event.getWhoClicked(), kit);
        }

    }
}
