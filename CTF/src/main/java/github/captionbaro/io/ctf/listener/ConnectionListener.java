package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gui.LobbyItems;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import github.captionbaro.io.ctf.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ConnectionListener implements Listener {

    private CTF plugin;

    public ConnectionListener(CTF plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void on_Join(PlayerJoinEvent event){
        CTF.getTeleportInv().addItem(getItem(event.getPlayer()));
        LobbyItems lobbyItems = new LobbyItems(plugin);
        lobbyItems.setItems(event.getPlayer());
        event.getPlayer().teleport(new ConfigLocationUtil(plugin,"GameLobby").loadLocation());
    }

    @EventHandler
    public void on_Quit(PlayerQuitEvent event){
        CTF.getTeleportInv().clear();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
            if(onlinePlayer != event.getPlayer()){
                CTF.getTeleportInv().addItem(getItem(onlinePlayer));
            }
        }
    }
    public static ItemStack getItem(Player player){
        ItemStack itemStack = new ItemBuilder(Material.PLAYER_HEAD).setName("§a" + player.getName()).setLore("§aKlicke um dich zu diesem Spieler zu teleportieren!").build();
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        skullMeta.setLocalizedName(String.valueOf(player.getName()));
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
