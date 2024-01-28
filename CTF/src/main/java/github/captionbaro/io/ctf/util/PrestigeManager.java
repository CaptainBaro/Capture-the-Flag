package github.captionbaro.io.ctf.util;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.entity.Player;

public class PrestigeManager {

    public static int getPrestige(Player player){
        if (!CTF.getPrestigeFile().contains(player.getName() + ".Prestige")){
            return 0;
        }
        return CTF.getPrestigeFile().getConfiguration().getInt(player.getName() + ".Prestige");
    }
    public static void setPrestige(Player player, int prestige){
        if(!CTF.getPrestigeFile().contains(player.getName() + ".Prestige")){
            CTF.getPrestigeFile().set(player.getName() + ".Prestige", 0);
        }
        CTF.getPrestigeFile().set(player.getName() + ".Prestige", prestige);
        CTF.getPrestigeFile().save();
    }
    public static int getEP(Player player){
        if (!CTF.getPrestigeFile().contains(player.getName() + ".EP")){
            return 0;
        }
        return CTF.getPrestigeFile().getConfiguration().getInt(player.getName() + ".EP");
    }
    public static void setEP(Player player, int ep){
        if(!CTF.getPrestigeFile().contains(player.getName() + ".EP")){
            CTF.getPrestigeFile().set(player.getName() + ".EP", 0);
        }
        CTF.getPrestigeFile().set(player.getName() + ".EP", ep);
        if(getEP(player) >= getEPRequired(player)){
            setPrestige(player,getPrestige(player) + 1);
            setEP(player, 0);
            player.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃\n" +
                    "                         §bLevel Up!\n" +
                    "                 You are now Prestige-Level §2" + getPrestige(player) + "§b!\n" +
                    "§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");

            setEPRequired(player, getPrestige(player) * 100);
        }
        CTF.getPrestigeFile().save();
    }
    public static int getEPRequired(Player player){
        if (!CTF.getPrestigeFile().contains(player.getName() + ".EPRequired")){
            return 0;
        }
        return CTF.getPrestigeFile().getConfiguration().getInt(player.getName() + ".EPRequired");
    }
    public static void setEPRequired(Player player, int epRequired){
        if(!CTF.getPrestigeFile().contains(player.getName())){
            CTF.getPrestigeFile().set(player.getName() + ".EPRequired", 100);
        }
        CTF.getPrestigeFile().set(player.getName() + ".EPRequired","Prestige");
        CTF.getPrestigeFile().save();
    }

}
