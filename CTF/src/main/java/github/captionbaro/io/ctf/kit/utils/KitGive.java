package github.captionbaro.io.ctf.kit.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class KitGive {



    public static void kitGivePoseidon(Player player){
        player.removePotionEffect(PotionEffectType.SPEED);
        player.getInventory().setContents(Kit.POSEIDON.getContents());
        player.getInventory().setArmorContents(Kit.POSEIDON.getArmorcontents());
        player.closeInventory();
    }

    public static void kitGiveAphrodite(Player player){
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.JUMP);

        player.getInventory().setContents(Kit.APHRODITE.getContents());
        player.getInventory().setArmorContents(Kit.APHRODITE.getArmorcontents());
        player.closeInventory();
    }
    public static void kitGiveHermes(Player player){
        player.getInventory().setContents(Kit.HERMES.getContents());
        player.getInventory().setArmorContents(Kit.HERMES.getArmorcontents());
        player.closeInventory();
    }
    public static void kitGiveDionysos(Player player){

    }
    public static void kitGiveAres(Player player){
        player.getInventory().setContents(Kit.ARES.getContents());
        player.getInventory().setArmorContents(Kit.ARES.getArmorcontents());
        player.closeInventory();
    }
    public static void kitGiveHades(Player player){
        player.getInventory().setContents(Kit.HADES.getContents());
        player.getInventory().setArmorContents(Kit.HADES.getArmorcontents());
        player.closeInventory();
    }

}
