package github.captionbaro.io.ctf.teams;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    private HashMap<Player, Team> teamConnector;

    public TeamManager(){
        teamConnector = new HashMap<>();
    }

    public Team getPlayerTeam(Player player){
        return teamConnector.containsKey(player) ? teamConnector.get(player) : null;
    }
    public void setPlayerTeam(Player player, Team team){
        teamConnector.put(player, team);
    }
    public void removePlayer(Player player){
        if(teamConnector.containsKey(player)){
            teamConnector.remove(player);
        }
    }

}
