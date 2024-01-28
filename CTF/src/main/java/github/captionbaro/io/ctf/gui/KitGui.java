package github.captionbaro.io.ctf.gui;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.kit.utils.Kit;
import github.captionbaro.io.ctf.kit.utils.KitGive;
import github.captionbaro.io.ctf.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;

public class KitGui implements Listener {

    private static final String TITLE = "§aGötter";
    private KitGive kitGive;
    public CTF plugin;

    public KitGui(CTF plugin){
        this.plugin = plugin;
    }

    public static void openGui(Player player, CTF plugin){
        if(plugin.getConfig().getList(player.getName() + ".Kits") == null){
            plugin.getConfig().set(player.getName() + ".Kits", new ArrayList<String>());
        }
        ArrayList<String> buyedKits = (ArrayList<String>) plugin.getConfig().get(player.getName() + ".Kits");
        Inventory inv = new GuiBuilder(9*5, TITLE).setBackground(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setName("  ").setLocalizedName("Gui.Nothing").build()).build();
        inv.setItem(0, new ItemBuilder(Material.LEATHER_BOOTS).setName("§aKind des Hermes(owned)").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("§7Dieses Kit hat Fähigkeiten durch die du schneller wirst", "und kriegt keinen Fallschaden.").build());
        if (buyedKits.contains(Kit.APHRODITE.getKitName())){
            inv.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setName("§aKind der Aphrodite(owned)").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else {
            inv.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setName("§cKind der Aphrodite").setLore("§7Dieses Kit hat Support Fähigkeiten.", "§cCosts: 200").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }
        if(buyedKits.contains(Kit.DIONYSUS.getKitName())){
            inv.setItem(2, new ItemBuilder(Material.SWEET_BERRIES).setName("§aKind des Dionysus(owned)").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore("Dieses Kit hat Fähigkeiten, die dein Team unterstützen", "und gleichzeitig dem Gegnerteam schaden.").addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else {
            inv.setItem(2, new ItemBuilder(Material.SWEET_BERRIES).setName("§cKind des Dionysus").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("Dieses Kit hat Fähigkeiten, die dein Team unterstützen", "und gleichzeitig dem Gegnerteam schaden.","Costs: 200").build());
        }
        if(buyedKits.contains(Kit.ARES.getKitName())){
            inv.setItem(3, new ItemBuilder(Material.IRON_AXE).setName("§aKind des Ares(owned)").setLore("§7Dieses Kit ist ein Kit mit einer guten Rüstung", "§7und einem guten Schwert.").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else {
            inv.setItem(3, new ItemBuilder(Material.IRON_AXE).setName("§cKind des Ares").setLore("§7Dieses Kit ist ein mit einer guten Rüstung", "§7und einem guten Schwert.", "§cCosts:200 Drachmen").addItemFlags(ItemFlag.HIDE_UNBREAKABLE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build());
        }
        if(buyedKits.contains(Kit.HADES.getKitName())){
            inv.setItem(4, new ItemBuilder(Material.SKELETON_SKULL).setName("§aKind des Hades(owned)").setLore("§7Dieses Kit hat besonders starke Fähigkeiten.").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        }else
            inv.setItem(4, new ItemBuilder(Material.SKELETON_SKULL).setName("§cKind des Hades").setLore("§7Dieses Kit hat besonders starke Fähigkeiten.", "§cCost:" + ChatColor.GOLD + "600").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
        player.openInventory(inv);
    }


    @EventHandler
    public void handleClickInv(InventoryClickEvent event) {
        if(!event.getView().getTitle().equalsIgnoreCase(TITLE))return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null)
            player.sendMessage("§cDu hast einen leeren Slot ausgewählt!");
        if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Gui")){
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Gui.Close"))
                player.closeInventory();
        }
        switch (event.getCurrentItem().getType()) {
            case LEATHER_BOOTS:
                KitGive.kitGiveHermes(player);
                plugin.getKitManager().setPlayerKit(player, Kit.HERMES);
                break;
            case GOLDEN_APPLE:
                if (!plugin.getConfig().contains(player.getName())) {
                    plugin.getConfig().set(player.getName() + ".Kits", new ArrayList<String>());
                }
                if (plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.APHRODITE.getKitName())) {
                    KitGive.kitGiveAphrodite(player);
                    plugin.getKitManager().setPlayerKit(player, Kit.APHRODITE);
                    break;
                } else {
                    if (plugin.getBalance().insufficient(player, 200)) {
                        player.sendMessage("Du hast nicht genügend Geld das Kit zu kaufen.");
                        event.setCancelled(true);
                        break;
                    } else {
                        plugin.getBalance().withdraw(player, 200);
                        plugin.getConfigKits().addBuyedKit(player.getName(), Kit.APHRODITE.getKitName());
                        KitGive.kitGiveAphrodite(player);
                        plugin.getKitManager().setPlayerKit(player, Kit.APHRODITE);
                        plugin.getScoreboardAnimator().setup(player);
                        break;
                    }
                }
            case SWEET_BERRIES:
                if (!plugin.getConfig().contains(player.getName())) {
                    plugin.getConfig().set(player.getName() + ".Kits", new ArrayList<String>());
                }
                if (plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.DIONYSUS.getKitName())) {
                    KitGive.kitGiveDionysos(player);
                    plugin.getKitManager().setPlayerKit(player, Kit.DIONYSUS);
                    break;
                } else {
                    if (plugin.getBalance().insufficient(player, 200)) {
                        player.sendMessage("§cDu hast nicht genügend Geld das Kit zu kaufen.");
                        event.setCancelled(true);
                        break;
                    } else {
                        plugin.getBalance().withdraw(player, 200);
                        plugin.getConfigKits().addBuyedKit(player.getName(), Kit.DIONYSUS.getKitName());
                        KitGive.kitGiveDionysos(player);
                        plugin.getKitManager().setPlayerKit(player, Kit.DIONYSUS);
                        plugin.getScoreboardAnimator().setup(player);
                        break;
                    }
                }
            case IRON_AXE:
                if(!plugin.getConfig().contains(player.getName())){
                    plugin.getConfig().set(player.getName() + "Kits", new ArrayList<String>());
                }
                if(plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.ARES.getKitName())){
                    KitGive.kitGiveAres(player);
                    plugin.getKitManager().setPlayerKit(player, Kit.ARES);
                    break;
                }else {
                    if(plugin.getBalance().insufficient(player, 300)){
                        player.sendMessage("§cDu hast nicht genügend Geld das Kit zu kaufen.");
                        event.setCancelled(true);
                        break;
                    }else {
                        plugin.getBalance().withdraw(player, 300);
                        plugin.getConfigKits().addBuyedKit(player.getName(), Kit.ARES.getKitName());
                        KitGive.kitGiveAres(player);
                        plugin.getKitManager().setPlayerKit(player, Kit.ARES);
                        plugin.getScoreboardAnimator().setup(player);
                        break;
                    }
                }
            case SKELETON_SKULL:
                if(!plugin.getConfig().contains(player.getName())){
                    plugin.getConfig().set(player.getName() + "Kits", new ArrayList<String>());
                }
                if(plugin.getConfigKits().containsBuyedKit(player.getName(), Kit.HADES.getKitName())){
                    KitGive.kitGiveHades(player);
                    plugin.getKitManager().setPlayerKit(player, Kit.HADES);
                    break;
                }else {
                    if(plugin.getBalance().insufficient(player, 600)){
                        player.sendMessage("§cDu hast nicht genügend Geld das Kit zu kaufen.");
                        event.setCancelled(true);
                        break;
                    }else {
                        plugin.getBalance().withdraw(player, 600);
                        plugin.getConfigKits().addBuyedKit(player.getName(), Kit.HADES.getKitName());
                        KitGive.kitGiveHades(player);
                        plugin.getKitManager().setPlayerKit(player, Kit.HADES);
                        plugin.getScoreboardAnimator().setup(player);
                        break;
                    }
                }

        }
    }

}
