package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gamestates.Lobbystate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private static final int START_SECONDS = 5;
    private CTF plugin;

    public StartCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            PermissionTester permissionTester = new PermissionTester();
            if(permissionTester.spartanUpCommandTester(player)){
                if(args.length == 0){
                    if (plugin.getGameStateManager().getCurrentGameState() instanceof Lobbystate){
                        Lobbystate lobbystate = (Lobbystate) plugin.getGameStateManager().getCurrentGameState();
                        if(lobbystate.getCountdown().isRunning() && (lobbystate.getCountdown().getSeconds() > START_SECONDS)){
                            lobbystate.getCountdown().setSeconds(START_SECONDS);
                            player.sendMessage(CTF.PREFIX + "§aDer Spielstart wurde beschleunigt.");
                        }else
                            player.sendMessage(CTF.PREFIX + "§cDas Spiel ist bereits gestartet!");
                    }else
                        player.sendMessage(CTF.PREFIX + "§cDas Spiel ist bereits gestartet!");

                }else
                    player.sendMessage(CTF.PREFIX + "§cBitte benutze §6start§c!");
            }else
                player.sendMessage(CTF.NO_PERMISSION);
        }
        return false;
    }
}
