package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VanishCommand implements CommandExecutor {

    private CTF plugin;

    public VanishCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            PermissionTester permissionTester = new PermissionTester();
            if (!permissionTester.staffCommandTester(player)){
                player.sendMessage(CTF.NO_PERMISSION);
                return false;
            }
            if(args.length == 0){
                if(!plugin.getArrayListManager().getVanishedPlayers().contains(player)){
                    player.sendMessage("§aDu bist jetzt im Vanish.");
                    plugin.getArrayListManager().getVanishedPlayers().add(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 5));
                    for (Player all : Bukkit.getOnlinePlayers()){
                        all.hidePlayer(plugin, player);
                    }
                }else {
                    player.sendMessage("§a Du bist nicht mehr im Vanish.");
                    for (Player all : Bukkit.getOnlinePlayers()){
                        all.showPlayer(plugin, player);
                    }
                    plugin.getArrayListManager().getVanishedPlayers().remove(player);
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }else {
                player.sendMessage("§c/vanish");
            }
        }
        return false;
    }
}
