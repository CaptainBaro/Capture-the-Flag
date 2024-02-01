package darkyiu.me.ctf3.commands;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.kits.Kits;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    private CTF3 plugin;

    public KitCommand(CTF3 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))return false;
        Player player = (Player) commandSender;
        if(strings.length==0){
            if(plugin.getKitManager().getKit(player)==null){
                player.sendMessage("§cYou don't have a kit!");
            }else {
                player.sendMessage("§aYou have the kit " + plugin.getKitManager().getKit(player));
            }
            return false;
        }
        if(Kits.getKit(strings[0])==null){
            player.sendMessage("§cThat is not a valid kit.");
            return false;
        }

        Kits kits = Kits.getKit(strings[0]);
        plugin.getKitManager().giveKit(player, kits);
        return false;
    }
}
