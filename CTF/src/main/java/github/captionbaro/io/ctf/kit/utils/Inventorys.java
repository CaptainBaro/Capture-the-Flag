package github.captionbaro.io.ctf.kit.utils;

import github.captionbaro.io.ctf.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Inventorys {

    public static Inventory Poseidon = Bukkit.createInventory(null, 36);
    public static Inventory Aphrodite = Bukkit.createInventory(null, 36);
    public static Inventory Hermes = Bukkit.createInventory(null, 36);
    public static Inventory Athene = Bukkit.createInventory(null, 36);
    public static Inventory Dionysos = Bukkit.createInventory(null, 36);
    public static Inventory ARES = Bukkit.createInventory(null, 36);
    public static Inventory HADES = Bukkit.createInventory(null, 36);

    public void inventorysinit(){
            Poseidon.setItem(0,new ItemStack(Material.DIAMOND_SWORD));

            Aphrodite.setItem(0, new ItemBuilder(Material.GOLDEN_SWORD).setName("§aAphrodite's Schwert").setLore("§cAphrodite's Heiliges", "§cSchwert").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());
            Aphrodite.setItem(1, new ItemStack(Material.GOLDEN_APPLE, 5));
            Aphrodite.setItem(3, new ItemBuilder(Material.GOLDEN_APPLE).setName("§eNormal§7:§aGeschenk der Aphrodite").setLocalizedName("Aphrodite.Normal.1").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLore("§7Bei Aktivierung erhältst:", "§7einen 10 Sekunden langen Resistanz Effekt").build());
            Aphrodite.setItem(4,new ItemBuilder(Material.GOLDEN_APPLE).setName("§eNormal§7:§aHeileinder Kreis").setLocalizedName("Aphrodite.Normal.2").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLore("§7Bei Aktivierung wird ein Kreis um", "§7dich gebildet der deine Verbündeten heilt.").build());
            Aphrodite.setItem(5, new ItemBuilder(Material.GOLDEN_APPLE).setName("§eUlti§7:§aAblenkender Strahl").setLocalizedName("Aphrodite.Ulti").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLore("§7Schießt einen Strahl in die Richtung wo du schaust, ", "der durch Spieler und Wände geht und jeden Gegner, ", "den er trifft, im Kreis drehen lässt.").build());

            Hermes.setItem(0, new ItemBuilder(Material.STICK).setName("§cCadeceus").setAmount(1).setModelData(13).build());
            Hermes.setItem(3, new ItemBuilder(Material.STICK).setName("§eNormal§7:§aDash").setLocalizedName("Hermes.Normal.1").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("§7Ein Dash der dich nach vorne springen lässt.").build());
            Hermes.setItem(4, new ItemBuilder(Material.STICK).setName("§eNormal§7:§aBoost").setLocalizedName("Hermes.Normal.2").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("§7Diese Fähigkeit gibt dir für 10 Sekunden Schnelligkeit.").build());
            Hermes.setItem(5, new ItemBuilder(Material.STICK).setName("§eNormal§7:§aHermes.Ulti").setLocalizedName("Hermes.Ulti").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore("§7Diese Fähigkeit vertauscht die Items deiner Gegner in der Nähe.").build());

            Dionysos.setItem(0, new ItemStack(Material.STONE_SWORD));

            Athene.setItem(0, new ItemBuilder(Material.IRON_SWORD).build());
            Athene.setItem(1, new ItemBuilder(Material.IRON_SWORD).setName("Athene.Normal.1").setLocalizedName("Athene.Normal.1").setLore("§7Noch keinen Zweck ausgedacht!").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());
            Athene.setItem(2, new ItemBuilder(Material.IRON_SWORD).setName("Athene.Normal.2").setLocalizedName("Athene.Normal.2").setLore("§7Noch keinen Zweck ausgedacht!").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());
            Athene.setItem(3, new ItemBuilder(Material.IRON_SWORD).setName("Athene.Ulti.3").setLocalizedName("Athene.Ulti.3").setLore("§7Noch keinen Zweck ausgedacht!").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());


            ARES.setItem(0, new ItemStack(Material.IRON_SWORD));
            ARES.setItem(3, new ItemBuilder(Material.IRON_AXE).setName("§eNormal§7:§aAres.Normal.1").setLocalizedName("Ares.Normal.1").setLore("§7Noch keinen Zweck ausgedacht!").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());
            ARES.setItem(4, new ItemBuilder(Material.IRON_AXE).setName("§eNormal§7:§aAres.Normal.2").setLocalizedName("Ares.Normal.2").setLore("§7Noch keinen Zweck ausgedacht!").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());
            ARES.setItem(5, new ItemBuilder(Material.IRON_AXE).setName("§eNormal§7:§aUltimativer Kraftschub").setLocalizedName("Ares.Ulti").setLore("§7Diese Fähigkeit gibt dir für", "§710 Sekunden Stärke!").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_ENCHANTS).build());

            HADES.setItem(0, new ItemStack(Material.STONE_SWORD));
            HADES.setItem(3, new ItemBuilder(Material.SKELETON_SKULL).setName("§eNormal§7:§aHades.Normal.1").setLocalizedName("Hades.Normal.1").setLore("§7Noch keinen Zweck ausgedacht.").build());
            HADES.setItem(4, new ItemBuilder(Material.SKELETON_SKULL).setName("§eNormal§7:§aSchattenreisen").setLocalizedName("Hades.Normal.2").setLore("§7Diese Fähigkeit lässt dich für 20", "§7unsichtbar lassen.Außerdem erhältst du Schnelligkeit, aber auch Langsamkeit.", "§7Du kannst die Fähigkeit mit erneutem Rechtsklick abbrechen.").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());
    }

}
