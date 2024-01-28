package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    private CTF plugin;

    public SetupCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
           Player player = (Player) sender;
            PermissionTester permissionTester = new PermissionTester();
           if(permissionTester.developerCommandTester(player)){
               if(args.length == 0){
                   player.sendMessage(CTF.PREFIX + "§cBitte nutze §6/setup <LOBBY/REDSPAWN/BLUESPAWN>");
               }else {
                   if(args[0].equalsIgnoreCase("lobby")){
                       if(args.length == 1){
                           new ConfigLocationUtil(plugin, player.getLocation(), "Lobby").saveLocation();
                           player.sendMessage(CTF.PREFIX + "§aDie Lobby wurde neu gesetzt.");
                       }else
                           player.sendMessage(CTF.PREFIX + "§cBitte benutze §6/setup <lobby>§c!");
                   }else if (args[0].equalsIgnoreCase("redspawn")){
                       if(args.length == 1){
                           new ConfigLocationUtil(plugin, player.getLocation(), "SpawnRed").saveLocation();
                           player.sendMessage(CTF.PREFIX + "§aDer Spawn des roten Teams wurde gesetzt.");
                       }else
                           player.sendMessage(CTF.PREFIX + "§cBitte benutze §6/setup<spawnred>§c!");
                   }else if(args[0].equalsIgnoreCase("bluespawn")){
                       if(args.length == 1){
                           new ConfigLocationUtil(plugin, player.getLocation(), "SpawnBlue").saveLocation();
                           player.sendMessage(CTF.PREFIX + "§aDer Spawn des blauen Teams wurde gesetzt.");
                       }
                   }else if(args[0].equalsIgnoreCase("FlagBlue")) {
                       if (args.length == 1) {
                           new ConfigLocationUtil(plugin, player.getLocation(), "FlagBlue").saveLocation();
                           player.sendMessage(CTF.PREFIX + "§aDer Flaggenplatz des blauen Teams wurde gesetzt.");
                       }
                   } else if(args[0].equalsIgnoreCase("FlagRed")) {
                       if (args.length == 1) {
                           new ConfigLocationUtil(plugin, player.getLocation(), "FlagRed").saveLocation();
                           player.sendMessage(CTF.PREFIX + "§aDer Flaggenplatz des roten Teams wurde gesetzt.");
                       }
                   }else if (args[0].equalsIgnoreCase("GameLobby")){
                       if(args.length == 1){
                           new ConfigLocationUtil(plugin, player.getLocation(), "GameLobby").saveLocation();
                           player.sendMessage(CTF.PREFIX + "§aDie GameLobby wurde gesetzt.");
                       }
                   }
               }
           }else
               player.sendMessage(CTF.NO_PERMISSION);
        }
        return false;
    }
}
