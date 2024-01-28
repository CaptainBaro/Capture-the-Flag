package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.kit.fähigkeiten.Fähigkeiten;
import github.captionbaro.io.ctf.teams.Team;
import github.captionbaro.io.ctf.teams.TeamManager;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import github.captionbaro.io.ctf.util.PrestigeManager;
import me.TSMR.Currency.Balance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndingState extends GameState{

    private CTF plugin;
    private GameStateManager gameStateManager;
    private TeamManager teamManager;

    public EndingState(CTF plugin){
        this.plugin = plugin;
    }
    @Override
    public void start() {
        if(plugin.getScoreManager().getScoreRed() >= 1){
            for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                all.sendMessage("§aDas Team §cRot§a hat gewonnen.");
                all.teleport(new ConfigLocationUtil(plugin, "Lobby").loadLocation());
                if (plugin.getTeamManager().getPlayerTeam(all) == Team.RED){
                    Balance.getInstance().add(all, 30);
                    PrestigeManager.setEP(all, PrestigeManager.getEP(all) + 30);
                }else {
                    Balance.getInstance().add(all, 10);
                    PrestigeManager.setEP(all, PrestigeManager.getEP(all) + 15);
                }
             }
        }else {
            for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                all.sendMessage("§aDas Team §1Blau§a hat gewonnen.");
                all.teleport(new ConfigLocationUtil(plugin, "Lobby").loadLocation());
                if (plugin.getTeamManager().getPlayerTeam(all) == Team.BLUE){
                    Balance.getInstance().add(all, 30);
                    PrestigeManager.setEP(all, PrestigeManager.getEP(all) + 30);
                }else {
                    Balance.getInstance().add(all, 10);
                    PrestigeManager.setEP(all, PrestigeManager.getEP(all) + 15);
                }
            }
        }
        stop();
    }

    @Override
    public void stop() {
        plugin.getArrayListManager().getIngamePlayers().clear();
        for (Player all : Bukkit.getOnlinePlayers()){
            plugin.getScoreboardAnimator().setup(all);
            if(plugin.getArrayListManager().getShadowrunPlayers().contains(all)){
                Fähigkeiten fähigkeiten = new Fähigkeiten(all, plugin);
                fähigkeiten.hadesNormal2Stop();
            }
        }
        plugin.getArrayListManager().getShadowrunPlayers().clear();
        CTF.getPlugin().newGameInit();
    }
}
