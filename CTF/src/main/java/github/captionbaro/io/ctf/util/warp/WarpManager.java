package github.captionbaro.io.ctf.util.warp;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.Location;

public class WarpManager {



    public  Location getWarp(String name){
        return CTF.getPlugin().getWarpsFile().getConfiguration().getLocation(name);
    }
    public void createWarp(String name, Location location){
        CTF.getPlugin().getWarpsFile().set(name, location);
        CTF.getPlugin().getWarpsFile().save();
    }
    public void deleteWarp(String name){
        CTF.getPlugin().getWarpsFile().set(name, null);
        CTF.getPlugin().getWarpsFile().save();
    }

}
