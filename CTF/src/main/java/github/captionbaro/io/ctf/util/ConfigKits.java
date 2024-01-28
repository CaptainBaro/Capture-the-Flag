package github.captionbaro.io.ctf.util;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ConfigKits {

    private CTF plugin;
    public ConfigKits(CTF plugin){
        this.plugin = plugin;
    }
    public void addBuyedKit(String root, String buyedKit){

        FileConfiguration config = plugin.getConfig();
        if(!config.contains(root + ".Kits")){
            config.set(root + ".Kits", new ArrayList<String>());
        }
        ArrayList<String> buyedKits = (ArrayList<String>) config.get(root + ".Kits");
        buyedKits.add(buyedKit);
        plugin.saveConfig();
    }
    public boolean containsBuyedKit(String root, String buyedKit){
        FileConfiguration config = plugin.getConfig();
        if(!config.contains(root + ".Kits")){
            config.set(root + ".Kits", new ArrayList<String>());
        }
        ArrayList<String> buyedKits = (ArrayList<String>) config.get(root + ".Kits");
        if (buyedKits.contains(buyedKit)){
            return true;
        }else{
            return false;
        }

    }

}
