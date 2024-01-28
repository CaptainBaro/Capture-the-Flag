package github.captionbaro.io.ctf.gui;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.kit.utils.Kit;
import github.captionbaro.io.ctf.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class ShopGui implements Listener {

    private static final String TITLE = "§aShop:";
    private CTF plugin;

    public ShopGui(CTF plugin){
        this.plugin = plugin;
    }
    public void openMainPage(Player player,CTF plugin){
        Inventory mainpage = new GuiBuilder(9*5, TITLE).setBackground(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setName("  ").setLocalizedName("Gui.Nothing").build()).setCloseButton().build();
        mainpage.setItem(10, new ItemBuilder(Material.EMERALD).setName("§aKits").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer("MrSnowDK"));
        skullMeta.setDisplayName("§aGamlbe");
        itemStack.setItemMeta(skullMeta);
        mainpage.setItem(13, itemStack);
        player.openInventory(mainpage);
    }
    public void openKitPage(Player player, CTF plugin){
        Inventory kitpage = new GuiBuilder(9*5,"§aKits:").setBackground(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setName("  ").setLocalizedName("Gui.Nothing").build()).setCloseButton().setBackButtoon().build();
        if(plugin.getConfig().getList(player.getName() + ".Kits") == null){
            plugin.getConfig().set(player.getName() + ".Kits", new ArrayList<String>());
        }
        ArrayList<String> buyedKits = (ArrayList<String>) plugin.getConfig().get(player.getName() + ".Kits");
        kitpage.setItem(0, new ItemBuilder(Material.LEATHER_BOOTS).setName("§aKind des Hermes(owned)").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("§aDieses Kit gibt dir:","§aSchnelligkeit,","§aSprungkraft!").build());
        if (buyedKits.contains(Kit.APHRODITE.getKitName())){
            kitpage.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setName("§aKind der Aphrodite(owned)").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else {
            kitpage.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setName("Kind der Aphrodite").setLore("Costs: 200").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }
        if(buyedKits.contains(Kit.DIONYSUS.getKitName())){
            kitpage.setItem(2, new ItemBuilder(Material.SWEET_BERRIES).setName("Kind des Dionysus(owned)").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else {
            kitpage.setItem(2, new ItemBuilder(Material.SWEET_BERRIES).setName("Kind des Dionysus").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("Costs: 200").build());
        }
        if(buyedKits.contains(Kit.ARES.getKitName())){
            kitpage.setItem(3, new ItemBuilder(Material.IRON_AXE).setName("§aKind des Ares(owned)").setLore("§7Dieses Kit ist ein Kit mit einer guten Rüstung", "§7und einem guten Schwert.").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else {
            kitpage.setItem(3, new ItemBuilder(Material.IRON_AXE).setName("§cKind des Ares").setLore("§7Dieses Kit ist ein mit einer guten Rüstung", "§7und einem guten Schwert.", "§cCosts:200 Drachmen").addItemFlags(ItemFlag.HIDE_UNBREAKABLE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build());
        }
        if(buyedKits.contains(Kit.HADES.getKitName())){
            kitpage.setItem(4, new ItemBuilder(Material.SKELETON_SKULL).setName("§aKind des Hades(owned)").setLore("§7Dieses Kit hat besonders starke Fähigkeiten.").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else
            kitpage.setItem(4, new ItemBuilder(Material.SKELETON_SKULL).setName("§cKind des Hades").setLore("§7Dieses Kit hat besonders starke Fähigkeiten.", "§cCost:" + ChatColor.GOLD + "600").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        player.openInventory(kitpage);
    }
    public void gamblePage(Player player, CTF plugin){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§aGamble:");
        inventory.setItem(2, new ItemBuilder(Material.DIAMOND).setName(ChatColor.GOLD + "Gamble").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("Du brauchst 100 Drachmen.").build());
        player.openInventory(inventory);
    }
    @EventHandler
    public void on_Click(InventoryClickEvent event){
        if(event.getCurrentItem() == null)return;
        if(event.getCurrentItem().getItemMeta().getLocalizedName().contains("Gui")){
            if(event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Gui.Close"))
                event.getWhoClicked().closeInventory();
            if (event.getCurrentItem().getItemMeta().getLocalizedName().equalsIgnoreCase("Gui.Back")) {
                event.getWhoClicked().closeInventory();
                openMainPage((Player) event.getWhoClicked(), plugin);
            }
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equalsIgnoreCase(TITLE)){
            switch (event.getCurrentItem().getType()){
                case EMERALD:
                    event.getWhoClicked().closeInventory();
                    openKitPage((Player) event.getWhoClicked(), plugin);
                    event.setCancelled(true);
                    break;
                case PLAYER_HEAD:
                    event.getWhoClicked().closeInventory();
                    gamblePage((Player) event.getWhoClicked(), plugin);
                    event.setCancelled(true);
                default:
                    event.setCancelled(true);
                    break;
            }
        }else if(event.getView().getTitle().equalsIgnoreCase("§aGamble:")){
            switch (event.getCurrentItem().getType()){
                case DIAMOND:
                    Gamble.gamble((Player) event.getWhoClicked());
                    break;
                default:
                    break;
            }
        }else if(event.getView().getTitle().equalsIgnoreCase("§aKits:")){
            Player player = (Player) event.getWhoClicked();
            switch (event.getCurrentItem().getType()) {

                case LEATHER_BOOTS:
                    event.setCancelled(true);
                    break;
                case GOLDEN_APPLE:
                    if (!plugin.getConfig().contains(player.getName())) {
                        plugin.getConfig().set(player.getName(), new ArrayList<String>());
                    }
                    if (plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.APHRODITE.getKitName())) {
                        event.setCancelled(true);
                        break;
                    } else {
                        if (plugin.getBalance().insufficient(player, 200)) {
                            player.sendMessage("Du hast nicht genügend Geld das Kit zu kaufen.");
                            event.setCancelled(true);
                            break;
                        } else {
                            plugin.getBalance().withdraw(player, 200);
                            plugin.getConfigKits().addBuyedKit(player.getName(), Kit.APHRODITE.getKitName());
                            plugin.getScoreboardAnimator().setup(player);
                            event.setCancelled(true);
                            break;
                        }
                    }
                case SWEET_BERRIES:
                    if (!plugin.getConfig().contains(player.getName())) {
                        plugin.getConfig().set(player.getName(), new ArrayList<String>());
                    }
                    if (plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.DIONYSUS.getKitName())) {
                        event.setCancelled(true);
                        break;
                    } else {
                        if (plugin.getBalance().insufficient(player, 200)) {
                            player.sendMessage("§cDu hast nicht genügend Geld das Kit zu kaufen.");
                            event.setCancelled(true);
                            break;
                        } else {
                            plugin.getBalance().withdraw(player, 200);
                            plugin.getConfigKits().addBuyedKit(player.getName(), Kit.DIONYSUS.getKitName());;
                            plugin.getScoreboardAnimator().setup(player);
                            event.setCancelled(true);
                            break;
                        }
                    }
                case IRON_AXE:
                    if(!plugin.getConfig().contains(player.getName())){
                        plugin.getConfig().set(player.getName() + "Kits", new ArrayList<String>());
                    }
                    if(plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.ARES.getKitName())){
                        event.setCancelled(true);
                        break;
                    }else {
                        if(plugin.getBalance().insufficient(player, 300)){
                            player.sendMessage("§cDu hast nicht genügend Geld das Kit zu kaufen.");
                            event.setCancelled(true);
                            break;
                        }else {
                            plugin.getBalance().withdraw(player, 300);
                            plugin.getConfigKits().addBuyedKit(player.getName(), Kit.ARES.getKitName());
                            plugin.getScoreboardAnimator().setup(player);
                            event.setCancelled(true);
                            break;
                        }
                    }
                case SKELETON_SKULL:
                    if(!plugin.getConfig().contains(player.getName())){
                        plugin.getConfig().set(player.getName() + "Kits", new ArrayList<String>());
                    }
                    if(plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.HADES.getKitName())){
                        event.setCancelled(true);
                        break;
                    }else {
                        if(plugin.getBalance().insufficient(player, 600)){
                            player.sendMessage("§cDu hast nicht genügend Geld das Kit zu kaufen.");
                            event.setCancelled(true);
                            break;
                        }else {
                            plugin.getBalance().withdraw(player, 600);
                            plugin.getConfigKits().addBuyedKit(player.getName(), Kit.HADES.getKitName());
                            plugin.getScoreboardAnimator().setup(player);
                            event.setCancelled(true);
                            break;
                        }
                    }

            }
        }
    }


}
