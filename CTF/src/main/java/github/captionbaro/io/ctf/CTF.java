package github.captionbaro.io.ctf;

import github.captionbaro.io.ctf.util.Config;
import github.captionbaro.io.ctf.util.warp.WarpManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CTF extends JavaPlugin {

    private WarpManager warpManager;
    public Config warpsFile;
    public static CTF plugin;


    @Override
    public void onEnable() {

        //Init Manager
        warpManager = new WarpManager();
        //Init Configs
        warpsFile = new Config("warps.yml", getDataFolder());

    }

    private void init(PluginManager pluginManager){

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Config getWarpsFile() {
        return warpsFile;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    public static CTF getPlugin() {
        return plugin;
    }
}
