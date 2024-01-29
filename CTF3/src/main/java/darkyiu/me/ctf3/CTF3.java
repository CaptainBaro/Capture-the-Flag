package darkyiu.me.ctf3;

import darkyiu.me.ctf3.commands.KitCommand;
import darkyiu.me.ctf3.kits.KitManager;
import darkyiu.me.ctf3.kits.abilities.CooldownManager;
import darkyiu.me.ctf3.listener.PassiveListener;
import darkyiu.me.ctf3.listener.AbilitiesListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CTF3 extends JavaPlugin {

    public static CTF3 plugin;
    private KitManager kitManager;
    private CooldownManager cooldownManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        kitManager = new KitManager();
        cooldownManager = new CooldownManager();

        getCommand("kit").setExecutor(new KitCommand());
        Bukkit.getPluginManager().registerEvents(new AbilitiesListener(), this);
        Bukkit.getPluginManager().registerEvents(new PassiveListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CTF3 getPlugin() {
        return plugin;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}
