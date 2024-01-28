package github.captionbaro.io.ctf.gui;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.ItemBuilder;
import me.TSMR.Currency.Balance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Gamble {

    private static List<Inventory> invs = new ArrayList<>();
    public static ItemStack[] contents;
    private static int itemIndex = 0;


    private CTF plugin;

    public Gamble(CTF plugin){
        this.plugin = plugin;
    }

    public static void gamble(Player player){
        if(Balance.getInstance().get(player) >= 100){
            Balance.getInstance().withdraw(player, 100);
            CTF.getPlugin().getScoreboardAnimator().setup(player);
            spin(player);
            return;
        }
        player.closeInventory();
        player.sendMessage("§cDu brauchst 100 Drachmen um das zu machen.");
    }
    public static void shuffle(Inventory inventory){
        if(contents == null){
            ItemStack[] items = new ItemStack[15];

            items[1] = new ItemBuilder(Material.GOLDEN_APPLE).setName("§aAphrodite Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[1] = new ItemBuilder(Material.SWEET_BERRIES).setName("§aDionysus Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[10] = new ItemBuilder(Material.STICK).setName("§aHermes Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[3] = new ItemBuilder(Material.BOW).setName("§aApollon Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[4] = new ItemBuilder(Material.BOW).setName("§aArtemis Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[5] = new ItemBuilder(Material.TRIDENT).setName("§aPoseidon Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[11] = new ItemBuilder(Material.IRON_INGOT).setName("§aHephaistos Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[7] = new ItemBuilder(Material.IRON_AXE).setName("§aAres Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[14] = new ItemBuilder(Material.BOOK).setName("§aAthene Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[9] = new ItemBuilder(Material.WHEAT).setName("§aDemeter Kit").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
            items[2] = new ItemBuilder(Material.SUNFLOWER).setName("Drachmen").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setModelData(100).build();
            items[6] = new ItemBuilder(Material.SUNFLOWER).setName("Drachmen").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setModelData(100).build();
            items[0] = new ItemBuilder(Material.SUNFLOWER).setName("Drachmen").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setModelData(100).build();
            items[13] = new ItemBuilder(Material.SUNFLOWER).setName("Drachmen").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setModelData(100).build();
            items[8] = new ItemBuilder(Material.SUNFLOWER).setName("Drachmen").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setModelData(100).build();
            contents = items;
        }
        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for (int index = 0; index < startingIndex; index++){
            for (int itemstacks = 9; itemstacks < 18; itemstacks++){
                inventory.setItem(itemstacks, contents[(itemstacks + itemstacks) % contents.length]);
            }
            itemIndex++;
        }

        //Customize
        ItemStack item = new ItemBuilder(Material.HOPPER).setName("|").addItemFlags(ItemFlag.HIDE_ENCHANTS).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build();
        inventory.setItem(4, item);
    }
    public static void spin(final Player player){
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Goodluck");
        shuffle(inventory);
        invs.add(inventory);
        player.openInventory(inventory);

        Random r = new Random();
        double seconds = 5.0 + (10.0 - 5.0) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            @Override
            public void run() {
                if(done)
                    return;
                ticks++;
                delay += 1 / (20 * seconds);
                if(ticks > delay * 10){
                    ticks = 0;

                    for (int itemstacks = 9; itemstacks < 18; itemstacks++){
                            inventory.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
                    }
                    itemIndex++;

                    if(delay >= 2){
                        done = true;
                        new BukkitRunnable(){

                            @Override
                            public void run() {
                                ItemStack itemStack = inventory.getItem(13);
                                switch (itemStack.getType()){
                                    case BOW:
                                        if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§aApollon Kit")){
                                            if (CTF.getPlugin().getConfigKits().containsBuyedKit(player.getName() + ".Kits", "Apollon")){
                                                player.sendMessage("§aDa du dieses Kit schon besitzt erhälts du stattdessen 50 Drachmen.");
                                                Balance.getInstance().add(player, 50);
                                                player.closeInventory();
                                            }else {
                                                CTF.getPlugin().getConfigKits().addBuyedKit(player.getName() + ".Kits","Apollon");
                                                player.sendMessage("§aDu hast das Kit Apollon erhalten.");
                                                player.closeInventory();
                                            }
                                        }
                                        break;
                                    case GOLDEN_APPLE:
                                        if (CTF.getPlugin().getConfigKits().containsBuyedKit(player.getName() + ".Kits", "Aphrodite")){
                                            player.sendMessage("§aDa du dieses Kit schon besitzt erhälts du stattdessen 50 Drachmen.");
                                            Balance.getInstance().add(player, 50);
                                            player.closeInventory();
                                        }else {
                                            CTF.getPlugin().getConfigKits().addBuyedKit(player.getName() + ".Kits","Aphrodite");
                                            player.sendMessage("§aDu hast das Kit Aphrodite erhalten.");
                                            player.closeInventory();
                                        }
                                    case IRON_AXE:
                                        if(CTF.getPlugin().getConfigKits().containsBuyedKit(player.getName() + ".Kits", "Ares")){
                                            player.sendMessage("§aDu du dieses Kit schon besitzt erhältst du stattdessen 50 Drachmen.");
                                            Balance.getInstance().add(player, 50);
                                            player.closeInventory();
                                        }else {
                                            CTF.getPlugin().getConfigKits().addBuyedKit(player.getName() + ".Kits", "Ares");
                                            player.sendMessage("§aDu hat das Kit Ares erhalten.");
                                            player.closeInventory();
                                        }
                                    case SUNFLOWER:
                                        Balance.getInstance().add(player, 30);
                                        player.sendMessage("§aDu hast 30 Drachmen gewonnen.");
                                        player.closeInventory();
                                        break;
                                    default:
                                        player.getInventory().addItem(itemStack);
                                        player.updateInventory();
                                        player.closeInventory();
                                        break;
                                }
                                cancel();
                            }
                        }.runTaskLater(CTF.getPlugin(), 50);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(CTF.getPlugin(), 0, 1);
    }

}
