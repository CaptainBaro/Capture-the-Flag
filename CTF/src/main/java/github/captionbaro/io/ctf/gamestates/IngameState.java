package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gui.KitGui;
import github.captionbaro.io.ctf.kit.utils.KitManager;
import github.captionbaro.io.ctf.teams.Team;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class IngameState extends GameState{

    private CTF plugin;
    private KitManager kitManager;
    private KitGui kitGui;


    public IngameState(CTF plugin, KitManager kitManager){
        this.plugin = plugin;
        this.kitManager = kitManager;
        kitGui = new KitGui(plugin);
    }

    @Override
    public void start() {
        ConfigLocationUtil locationUtilred = new ConfigLocationUtil(plugin, "SpawnRed");
        ConfigLocationUtil locationUtilblue = new ConfigLocationUtil(plugin, "SpawnBlue");
        for (Player current : plugin.getArrayListManager().getIngamePlayers()){

            Team team = plugin.getTeamManager().getPlayerTeam(current);
            if(team.getTeamName().equalsIgnoreCase(ChatColor.BLUE + "Blau")){
                if(locationUtilblue.loadLocation() != null){
                    current.teleport(locationUtilblue.loadLocation());
                }else
                    Bukkit.getConsoleSender().sendMessage("§cDer Spawn von deinem Team wurde noch nicht gesetzt!");
            }else if (team.getTeamName().equalsIgnoreCase(ChatColor.DARK_RED + "Rot")){
                if (locationUtilred.loadLocation() != null){
                    current.teleport(locationUtilred.loadLocation());
                }else
                    Bukkit.getConsoleSender().sendMessage("§cDer Spawn von deinem Team wurde noch nicht gesetzt!");
            }
            KitGui.openGui(current, plugin);
        }

        System.out.println("IngameState gestartet.");
    }

    @Override
    public void stop() {

    }
}
