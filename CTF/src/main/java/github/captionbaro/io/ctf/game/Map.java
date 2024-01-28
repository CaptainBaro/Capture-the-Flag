package github.captionbaro.io.ctf.game;

import github.captionbaro.io.ctf.CTF;

public class Map {

    private CTF plugin;
    private String name;

    public Map(CTF plugin, String name){
        this.plugin = plugin;
        this.name = name.toUpperCase();
    }

    public boolean exists(){
        return (plugin.getConfig().getString("Arenas." + name + ".Builder") != null);
    }

}
