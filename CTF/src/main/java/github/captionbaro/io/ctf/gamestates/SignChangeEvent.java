package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.countdowns.LobbyCountdown;
import github.captionbaro.io.ctf.gui.KitGui;
import github.captionbaro.io.ctf.teams.Team;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class SignChangeEvent implements Listener {

    private CTF plugin;

    public SignChangeEvent(CTF plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onChange(org.bukkit.event.block.SignChangeEvent e){
        String s = e.getLine(0);

        if(s.equalsIgnoreCase("[join]")){
            e.setLine(0,"§7[§1Join§7]");
            e.setLine(2, "(" + CTF.getPlugin().getArrayListManager().getIngamePlayers() + "/" + Lobbystate.MAX_PLAYER + ")");
        }
    }
    @EventHandler
    public void on_Click(PlayerInteractEvent event){
        if(plugin.getGameStateManager().getCurrentGameState() instanceof Lobbystate){
            if(event.getClickedBlock() == null)return;

            if(event.getClickedBlock().getState() instanceof Sign){
                String join = ((Sign) event.getClickedBlock().getState()).getLine(1);

                if(join.equalsIgnoreCase("CtF")){
                    Player player = event.getPlayer();
                    plugin.getArrayListManager().getIngamePlayers().add(player);
                    for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                        all.sendMessage(CTF.PREFIX + "§a" + player.getDisplayName() + " §7ist dem Spiel beigetreten. [" + plugin.getArrayListManager().getIngamePlayers().size() + "/" + Lobbystate.MAX_PLAYER + "]");
                    }
                    ConfigLocationUtil locationUtil = new ConfigLocationUtil(plugin, "GameLobby");
                    if(locationUtil.loadLocation() != null){
                        player.teleport(locationUtil.loadLocation());
                    }else
                        Bukkit.getConsoleSender().sendMessage("§cDie Lobby-Location wurde noch nicht gesetzt!");
                    Lobbystate lobbystate = (Lobbystate) plugin.getGameStateManager().getCurrentGameState();
                    LobbyCountdown countdown = lobbystate.getCountdown();
                    if(plugin.getArrayListManager().getIngamePlayers().size() >= Lobbystate.MIN_PLAYERS) {
                        if (!countdown.isRunning()) {
                            countdown.stopIdle();
                            countdown.start();
                        }
                    }
                }
            }

        }else if (plugin.getGameStateManager().getCurrentGameState() instanceof IngameState){
            if(event.getClickedBlock() == null)return;

            if(event.getClickedBlock().getState() instanceof Sign){
                String join = ((Sign) event.getClickedBlock().getState()).getLine(1);

                if(join.equalsIgnoreCase("CtF")) {
                    Player player = event.getPlayer();
                    if(!(plugin.getArrayListManager().getIngamePlayers().size() >= Lobbystate.MAX_PLAYER)){
                        if(plugin.getTeamManager().getPlayerTeam(player) == null){
                            plugin.getTeamManager().setPlayerTeam(player, Team.values()[new Random().nextInt(Team.values().length)]);
                            player.sendMessage(CTF.PREFIX + "§aDu wurdest einem Team zugewiesen.");
                            player.setDisplayName(plugin.getTeamManager().getPlayerTeam(player).getChatColor() + player.getName());
                        }
                        player.sendMessage(CTF.PREFIX + "§aDu bist in Team " + plugin.getTeamManager().getPlayerTeam(player).getTeamName() + "§a.");
                        plugin.getArrayListManager().getIngamePlayers().add(player);
                        ConfigLocationUtil locationUtilred = new ConfigLocationUtil(plugin, "SpawnRed");
                        ConfigLocationUtil locationUtilblue = new ConfigLocationUtil(plugin, "SpawnBlue");
                        Team team = plugin.getTeamManager().getPlayerTeam(player);
                        if(team.getTeamName().equalsIgnoreCase(ChatColor.BLUE + "Blau")){
                            if(locationUtilblue.loadLocation() != null){
                                player.teleport(locationUtilblue.loadLocation());
                            }else
                                Bukkit.getConsoleSender().sendMessage("§cDer Spawn von deinem Team wurde noch nicht gesetzt!");
                        }else if (team.getTeamName().equalsIgnoreCase(ChatColor.DARK_RED + "Rot")){
                            if (locationUtilred.loadLocation() != null){
                                player.teleport(locationUtilred.loadLocation());
                            }else
                                Bukkit.getConsoleSender().sendMessage("§cDer Spawn von deinem Team wurde noch nicht gesetzt!");
                        }
                        KitGui.openGui(player,plugin);
                    }else {
                        player.sendMessage("§cDiese Runde ist voll.");
                    }
                }
            }
        }
    }
    public static void updateSigns(){
        Bukkit.getWorld("world").getBlockAt(new ConfigLocationUtil(CTF.getPlugin(), "Sign").loadLocation()).setType(Material.OAK_SIGN);
        Sign sign = (Sign) Bukkit.getWorld("world").getBlockAt(new ConfigLocationUtil(CTF.getPlugin(), "Sign").loadLocation());
        sign.setLine(0,"§7[§1Join§7]");
        sign.setLine(1, "Ctf");
        sign.setLine(2, "(" + CTF.getPlugin().getArrayListManager().getIngamePlayers() + "/" + Lobbystate.MAX_PLAYER + ")");

    }


}
