package github.captionbaro.io.ctf;

import github.captionbaro.io.ctf.util.Config;
import github.captionbaro.io.ctf.util.warp.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import de.baro.cryptoscore.CryptosCore;
import de.baro.cryptoscore.permissions.PermissionManager;
import de.baro.cryptoscore.permissions.RankManager;
import github.captionbaro.io.ctf.commands.*;
import github.captionbaro.io.ctf.countdowns.FahigkeitenNormal1Manager;
import github.captionbaro.io.ctf.countdowns.FähigkeitenNormal2Manager;
import github.captionbaro.io.ctf.countdowns.UltiManager;
import github.captionbaro.io.ctf.gamestates.*;
import github.captionbaro.io.ctf.gui.KitGui;
import github.captionbaro.io.ctf.gui.ShopGui;
import github.captionbaro.io.ctf.gui.WarpGui;
import github.captionbaro.io.ctf.kit.utils.ArmorContents;
import github.captionbaro.io.ctf.kit.utils.Inventorys;
import github.captionbaro.io.ctf.kit.utils.KitManager;
import github.captionbaro.io.ctf.listener.*;

import github.captionbaro.io.ctf.teams.TeamManager;
import github.captionbaro.io.ctf.util.*;
import github.captionbaro.io.ctf.util.npc.Join;
import me.TSMR.Currency.Balance;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class CTF extends JavaPlugin {

    private GameStateManager gameStateManager;


    private TeamManager teamManager;
    private Inventorys inventorys;
    private ArmorContents armorContents;
    public BukkitRunnable timerRunnable;
    private ScoreboardAnimator scoreboardAnimator;
    private Balance balance;
    private Start start;
    private KitManager kitManager;
    private static Config itemsFile;
    private static Config warpsFile;
    private static Config prestigeFile;
    private static Config playerFile;
    private BossBar bossBar;
    private PermissionManager permissionManager;
    private ScoreManager scoreManager;
    private KitGui kitGui;
    private ConfigKits configKits;
    private RankManager rankManager;
    private static Inventory teleportInv;
    public static CTF plugin;
    private UltiManager ultiManager;
    private FahigkeitenNormal1Manager fähigkeitenNormal1Manager;
    private FähigkeitenNormal2Manager fähigkeitenNormal2Manager;
    private ArrayListManager arrayListManager;
    public static final String PREFIX = "§7[§aCTF§7]", NO_PERMISSION = PREFIX + "§cDazu hast du keine Rechte!";

    @Override
    public void onEnable() {

        teamManager = new TeamManager();
        gameStateManager = new GameStateManager(this);
        inventorys = new Inventorys();
        scoreboardAnimator = new ScoreboardAnimator(this);
        armorContents = new ArmorContents();
        ImageManager imageManager = ImageManager.getInstance();
        imageManager.init();
        kitGui = new KitGui(this);
        configKits = new ConfigKits(this);
        scoreManager = new ScoreManager(this);
        kitManager = new KitManager();
        itemsFile = new Config("items.yml", getDataFolder());
        warpsFile = new Config("warps.yml", getDataFolder());
        playerFile = new Config("players.yml", getDataFolder());
        prestigeFile = new Config("prestige.yml", getDataFolder());
        rankManager = new RankManager();
        start = new Start(this);
        permissionManager = new PermissionManager(CryptosCore.getPlugin());
        arrayListManager = new ArrayListManager(this);
        ultiManager = new UltiManager(this);
        fähigkeitenNormal1Manager = new FahigkeitenNormal1Manager(this);
        fähigkeitenNormal2Manager = new FähigkeitenNormal2Manager(this);


        init(Bukkit.getPluginManager());
        gameStateManager.setGameState(GameState.LOBBY_STATE);
        start.init();
        System.out.println("Das Plugin wurde gestartet.");

    }

    private void init(PluginManager pluginManager) {


        getCommand("nick").setExecutor(new NickCommand());
        getCommand("setup").setExecutor(new SetupCommand(this));
        getCommand("start").setExecutor(new StartCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("leave").setExecutor(new LeaveCommand(this));
        getCommand("money").setExecutor(new MoneyCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("test").setExecutor(new Test(this));
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("gamble").setExecutor(new GambleCommand());
        getCommand("npc").setExecutor(new NPCCommand());
        getCommand("map").setExecutor(new MapCommand());
        getCommand("playertp").setExecutor(new PlayerTpCommand());
        inventorys.inventorysinit();
        armorContents.armorcontentsInit();
        teleportInv = Bukkit.createInventory(null, 9 * 4, "§aPlayer Teleporter");
        arrayListManager.init();

        pluginManager.registerEvents(new LobbyConnectionListener(this), this);
        pluginManager.registerEvents(new ScoreboardAnimator(this), this);
        pluginManager.registerEvents(new FlagListener(this), this);
        pluginManager.registerEvents(new TeamControllListener(this), this);
        pluginManager.registerEvents(new SignChangeEvent(this), this);
        pluginManager.registerEvents(new ActionListener(this), this);
        pluginManager.registerEvents(new KitGui(this), this);
        pluginManager.registerEvents(new Join(), this);
        pluginManager.registerEvents(new ClickEvents(), this);
        pluginManager.registerEvents(new ScoreListenerMove(this),this);
        pluginManager.registerEvents(new FalldamageListener(),this);
        pluginManager.registerEvents(new ShopGui(this), this);
        pluginManager.registerEvents(new ConnectionListener(this), this);
        pluginManager.registerEvents(new WarpGui(),this);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            teleportInv.addItem(ConnectionListener.getItem(onlinePlayer));
            scoreboardAnimator.setup(onlinePlayer);
        }

    }

    @Override
    public void onDisable() {

    }

    public Balance getBalance() {
        return Balance.getInstance();
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }


    public KitManager getKitManager() {
        return kitManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public ScoreboardAnimator getScoreboardAnimator() {
        return scoreboardAnimator;
    }

    public KitGui getKitGui() {
        return kitGui;
    }

    public ConfigKits getConfigKits() {
        return configKits;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }


    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public static CTF getPlugin() {
        return plugin;
    }

    public UltiManager getUltiCountdownManager() {
        return ultiManager;
    }

    public ArrayListManager getArrayListManager() {
        return arrayListManager;
    }

    public static Config getItemsFile() {
        return itemsFile;
    }

    public static Inventory getTeleportInv() {
        return teleportInv;
    }

    public FahigkeitenNormal1Manager getFähigkeitenNromal1Manager() {
        return fähigkeitenNormal1Manager;
    }
    public FähigkeitenNormal2Manager getFähigkeitenNormal2Manager() {
        return fähigkeitenNormal2Manager;
    }

    public static Config getWarpsFile() {
        return warpsFile;
    }

    public void newGameInit(){
        gameStateManager = new GameStateManager(this);
        kitManager = new KitManager();
        scoreManager = new ScoreManager(this);
        teamManager = new TeamManager();
        gameStateManager.setGameState(GameState.LOBBY_STATE);
    }
    public static Config getPrestigeFile(){
        return prestigeFile;
    }
    public static Config getPlayerFile(){
        return playerFile;
    }

}
