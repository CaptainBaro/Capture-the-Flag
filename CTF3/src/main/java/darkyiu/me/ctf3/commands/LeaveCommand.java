package darkyiu.me.ctf3.commands;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.countdowns.LobbyCountdown;
import darkyiu.me.ctf3.gamestates.LobbyState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {

    private CTF3 plugin;
    public LeaveCommand(CTF3 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player))return false;
        if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState) ||!(plugin.getArrayListManager().getPlayers().contains(commandSender))){
            commandSender.sendMessage(CTF3.prefix + "§cDu kannst keine laufende Runde verlassen.");
            return false;
        }
        Player player = (Player)commandSender;
        plugin.getArrayListManager().getPlayers().remove(player);
        for (Player player1 : plugin.getArrayListManager().getPlayers()){
            player1.sendMessage("§c" + player.getDisplayName() + " §7hat das Spiel verlassen. [" + plugin.getArrayListManager().getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");
        }
        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if (plugin.getArrayListManager().getPlayers().size() < LobbyState.MIN_PLAYERS){
            if (countdown.isRunning()){
                countdown.stop();
                countdown.startIdle();
            }
        }
        return false;
    }
}
