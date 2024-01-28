package github.captionbaro.io.ctf.commands;


import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.util.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        PermissionTester permissionTester = new PermissionTester();
        if(!permissionTester.adminCommandTester(player));
        if(args.length == 0){
            NPC.createNPC(player, player.getName());
            player.sendMessage("NPC CREATED!");

            return true;
        }

            NPC.createNPC(player, args[0]);
            player.sendMessage("NPC CREATED!");
            return true;

    }
}
