package github.captionbaro.io.ctf.gui;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class LobbyItems {

    private CTF plugin;

    public LobbyItems(CTF plugin){
        this.plugin = plugin;
    }
    public void setItems(Player player){
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§aTeleporter").addItemFlags(ItemFlag.HIDE_UNBREAKABLE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build());
        player.getInventory().setItem(2, new ItemBuilder(Material.EMERALD).setName("§aShop").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
    }

}
