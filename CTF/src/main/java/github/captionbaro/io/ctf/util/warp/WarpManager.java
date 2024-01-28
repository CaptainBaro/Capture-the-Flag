package github.captionbaro.io.ctf.util.warp;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.Location;

public class WarpManager {

    public static Location getWarp(String name){
        return CTF.getWarpsFile().getConfiguration().getLocation(name);
    }
    public static void createWarp(String name, Location location){
        CTF.getWarpsFile().set(name, location);
        CTF.getWarpsFile().save();
    }
    public static void deletWarp(String name){
        CTF.getWarpsFile().set(name, null);
        CTF.getWarpsFile().save();
    }
}
