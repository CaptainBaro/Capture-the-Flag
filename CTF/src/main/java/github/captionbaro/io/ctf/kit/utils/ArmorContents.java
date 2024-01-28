package github.captionbaro.io.ctf.kit.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ArmorContents {

    public static ItemStack[] PoseidonArmorontents = new ItemStack[4];
    public static ItemStack[] AphroditeArmorcontents = new ItemStack[4];
    public static ItemStack[] HermesContents = new ItemStack[4];
    public static ItemStack[] Dionysus = new ItemStack[4];
    public static ItemStack[] Athene = new ItemStack[4];
    public static ItemStack[] Ares = new ItemStack[4];
    public static ItemStack[] HADES = new ItemStack[4];

    public void armorcontentsInit(){
            PoseidonArmorontents[0] = new ItemStack(Material.IRON_BOOTS);
            PoseidonArmorontents[1] = new ItemStack(Material.IRON_LEGGINGS);
            PoseidonArmorontents[2] = new ItemStack(Material.IRON_CHESTPLATE);
            PoseidonArmorontents[3] = new ItemStack(Material.IRON_HELMET);

            AphroditeArmorcontents[0] = new ItemStack(Material.GOLDEN_BOOTS);
            AphroditeArmorcontents[1] = new ItemStack(Material.GOLDEN_LEGGINGS);
            AphroditeArmorcontents[2] = new ItemStack(Material.GOLDEN_CHESTPLATE);
            AphroditeArmorcontents[3] = new ItemStack(Material.GOLDEN_HELMET);

            HermesContents[0] = new ItemStack(Material.LEATHER_BOOTS);
            HermesContents[1] = new ItemStack(Material.LEATHER_LEGGINGS);
            HermesContents[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
            HermesContents[3] = new ItemStack(Material.LEATHER_HELMET);

            Dionysus[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
            Dionysus[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            Dionysus[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            Dionysus[3] = new ItemStack(Material.CHAINMAIL_HELMET);

            Ares[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
            Ares[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            Ares[2] = new ItemStack(Material.IRON_CHESTPLATE);
            Ares[3] = new ItemStack(Material.CHAINMAIL_HELMET);

            HADES[0] = new ItemStack(Material.LEATHER_BOOTS);
            HADES[1] = new ItemStack(Material.LEATHER_LEGGINGS);
            HADES[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            HADES[3] = new ItemStack(Material.LEATHER_HELMET);


    }

}
