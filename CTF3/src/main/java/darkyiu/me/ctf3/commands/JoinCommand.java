package darkyiu.me.ctf3.commands;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.countdowns.LobbyCountdown;
import darkyiu.me.ctf3.gamestates.LobbyState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    private CTF3 plugin;
    public JoinCommand(CTF3 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player))return false;
        Player player = (Player) commandSender;

        if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)){
            player.sendMessage(CTF3.prefix + "§cEs läuft bereits eine Runde!");
            return false;
        }
        plugin.getArrayListManager().getPlayers().add(player);
        for (Player player1 : plugin.getArrayListManager().getPlayers()){
            player1.sendMessage("§a" + player.getDisplayName() + " §7ist dem Spiel beigetreten. [" + plugin.getArrayListManager().getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");
        }
        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if(plugin.getArrayListManager().getPlayers().size() >= LobbyState.MIN_PLAYERS){
            if (!countdown.isRunning()){
                countdown.stopIdle();
                countdown.start();
            }
        }
        return false;
    }
}
