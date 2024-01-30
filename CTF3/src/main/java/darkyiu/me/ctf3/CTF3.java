package darkyiu.me.ctf3;

import darkyiu.me.ctf3.commands.KitCommand;
import darkyiu.me.ctf3.gamestates.GameState;
import darkyiu.me.ctf3.gamestates.GameStateManager;
import darkyiu.me.ctf3.kits.KitManager;
import darkyiu.me.ctf3.kits.abilities.CooldownManager;
import darkyiu.me.ctf3.kits.abilities.ZeusAbilities;
import darkyiu.me.ctf3.listener.PassiveListener;
import darkyiu.me.ctf3.listener.AbilitiesListener;
import darkyiu.me.ctf3.listener.PlayerConnectionListener;
import darkyiu.me.ctf3.utils.ArrayListManager;
import darkyiu.me.ctf3.utils.BasicUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CTF3 extends JavaPlugin {

    public static CTF3 plugin;
    private KitManager kitManager;
    private CooldownManager cooldownManager;
    private BasicUtil basicUtil;
    private GameStateManager gameStateManager;
    private ArrayListManager arrayListManager;

    public static String prefix = "§a[CTF]";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        arrayListManager = new ArrayListManager(this);
        kitManager = new KitManager();
        cooldownManager = new CooldownManager();
        gameStateManager = new GameStateManager(this);
        basicUtil = new BasicUtil();

        getCommand("kit").setExecutor(new KitCommand());
        Bukkit.getPluginManager().registerEvents(new AbilitiesListener(), this);
        Bukkit.getPluginManager().registerEvents(new PassiveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ZeusAbilities(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(this), this);

        start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void start(){
        gameStateManager.setGameState(GameState.LOBBY_STATE);
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

    public BasicUtil getBasicUtil() {
        return basicUtil;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ArrayListManager getArrayListManager() {
        return arrayListManager;
    }
}
