package darkyiu.me.ctf3;

import darkyiu.me.ctf3.commands.JoinCommand;
import darkyiu.me.ctf3.commands.KitCommand;
import darkyiu.me.ctf3.commands.LeaveCommand;
import darkyiu.me.ctf3.commands.VanishCommand;
import darkyiu.me.ctf3.gamestates.GameState;
import darkyiu.me.ctf3.gamestates.GameStateManager;
import darkyiu.me.ctf3.guis.KitGui;
import darkyiu.me.ctf3.kits.KitManager;
import darkyiu.me.ctf3.kits.Kits;
import darkyiu.me.ctf3.kits.abilities.CooldownManager;
import darkyiu.me.ctf3.kits.abilities.ZeusAbilities;
import darkyiu.me.ctf3.listener.PassiveListener;
import darkyiu.me.ctf3.listener.AbilitiesListener;
import darkyiu.me.ctf3.listener.PlayerConnectionListener;
import darkyiu.me.ctf3.utils.ArrayListManager;
import darkyiu.me.ctf3.utils.BasicUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class CTF3 extends JavaPlugin {

    private KitManager kitManager;
    private CooldownManager cooldownManager;
    private BasicUtil basicUtil;
    private GameStateManager gameStateManager;
    private ArrayListManager arrayListManager;

    public static String prefix = "§a[CTF] ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        arrayListManager = new ArrayListManager(this);
        kitManager = new KitManager();
        cooldownManager = new CooldownManager(this);
        gameStateManager = new GameStateManager(this);
        basicUtil = new BasicUtil(this);

        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("kit").setExecutor(new KitCommand(this));
        getCommand("join").setExecutor(new JoinCommand(this));
        getCommand("leave").setExecutor(new LeaveCommand(this));
        Bukkit.getPluginManager().registerEvents(new AbilitiesListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PassiveListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ZeusAbilities(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new KitGui(this), this);


        start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void scheduler(CTF3 plugin){
        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getWorld("world").getPlayers().forEach(player -> {
                    if (player.getGameMode()!= GameMode.SURVIVAL)return;
                    if (plugin.getKitManager().getKit(player)!= Kits.ZEUS)return;
                    player.setAllowFlight(true);
                    if (getArrayListManager().getFlying().contains(player)&&!player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()){
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.setGliding(false);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            getArrayListManager().getFlying().remove(player);
                        },5);
                    }
                });
            }
        },0,3);
    }

    public void start(){
        gameStateManager.setGameState(GameState.LOBBY_STATE);
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
