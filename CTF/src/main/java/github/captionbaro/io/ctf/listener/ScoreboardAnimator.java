package github.captionbaro.io.ctf.listener;

import de.baro.cryptoscore.permissions.Rank;
import github.captionbaro.io.ctf.CTF;
import me.TSMR.Currency.Balance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardAnimator implements Listener{

    private CTF plugin;

    private final String ANIMATION_TITLE = "WILLKOMMEN";
    private final long ANIMATION_SPEED = 10;

    private char[] letters;
    private int animationState;
    private String currentTitle;
    private Balance balance = Balance.getInstance();

    public ScoreboardAnimator(CTF plugin){
        this.plugin = plugin;
        letters = ANIMATION_TITLE.toCharArray();
        animationState = 0;
        currentTitle = "";
    }

    public void setup(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        String prefix = "";
        Objective objective = scoreboard.registerNewObjective("abcd", "abcd");
        scoreboard.registerNewTeam("07vip");
        scoreboard.getTeam("07vip").setPrefix("§a[Vip]§r");
        scoreboard.registerNewTeam("05spartan");
        scoreboard.getTeam("05spartan").setPrefix("§5[Spartan]§r");
        scoreboard.registerNewTeam("005builder");
        scoreboard.getTeam("005builder").setPrefix("§e[Builder]§r");
        scoreboard.registerNewTeam("004developer");
        scoreboard.getTeam("004developer").setPrefix("§3[Developer]§r");
        scoreboard.registerNewTeam("002admin");
        scoreboard.getTeam("002admin").setPrefix("§4[Admin]§r");

        objective.setDisplayName(currentTitle);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setDisplayName(player.getName());
        //permissions zeug
        if(plugin.getPermissionManager().havePermission(player, "vip")){
            prefix = scoreboard.getTeam("07vip").getPrefix();
            player.setDisplayName(prefix + player.getDisplayName());
            scoreboard.getTeam("07vip").addPlayer(player);
            player.setPlayerListName(prefix + ChatColor.DARK_GREEN + player.getName());
            plugin.getRankManager().setPlayerRank(player, Rank.VIP);
        }
        if(plugin.getPermissionManager().havePermission(player, "spartan")){
            prefix = scoreboard.getTeam("05spartan").getPrefix() + prefix;
            player.setDisplayName(prefix + "§5" + player.getName());
            scoreboard.getTeam("05spartan").addPlayer(player);
            player.setPlayerListName(scoreboard.getTeam("05spartan").getPrefix() + "§5" + player.getName());
            plugin.getRankManager().setPlayerRank(player, Rank.SPARTAN);
        }
        if(plugin.getPermissionManager().havePermission(player, "builder")){
            prefix = scoreboard.getTeam("005builder").getPrefix() + prefix;
            scoreboard.getTeam("005builder").addPlayer(player);
            player.setPlayerListName(scoreboard.getTeam("005builder").getPrefix() + "§e" + player.getName());
            player.setDisplayName(prefix + "§e" + player.getName());
            plugin.getRankManager().setPlayerRank(player, Rank.Builder);
        }

        if(plugin.getPermissionManager().havePermission(player, "developer")){
            prefix = scoreboard.getTeam("004developer").getPrefix() + prefix;
            scoreboard.getTeam("004developer").addPlayer(player);
            player.setPlayerListName(scoreboard.getTeam("004developer").getPrefix() + "§3" + player.getName());
            player.setDisplayName(prefix + "§3" + player.getName());
            plugin.getRankManager().setPlayerRank(player, Rank.DEVELOPER);
        }
        if(plugin.getPermissionManager().havePermission(player, "admin")){
            prefix = scoreboard.getTeam("002admin").getPrefix() + prefix;
            scoreboard.getTeam("002admin").addPlayer(player);
            player.setPlayerListName(scoreboard.getTeam("002admin").getPrefix() + "§4" + player.getName());
            player.setDisplayName(prefix + "§4" + player.getName());
            plugin.getRankManager().setPlayerRank(player, Rank.ADMIN);
        }


        switch (plugin.getRankManager().getPlayerRank(player)){
            case VIP:
                objective.getScore(ChatColor.GREEN + player.getName()).setScore(1);
                break;
            case SPARTAN:
                objective.getScore("§5" + player.getName()).setScore(1);
            case Builder:
                objective.getScore("§e" + player.getName()).setScore(1);
            case DEVELOPER:
                objective.getScore("§3" + player.getName()).setScore(1);
                break;
            case ADMIN:
                objective.getScore("§4" + player.getName()).setScore(1);
                break;
            case NORMAL:
                objective.getScore(player.getName()).setScore(1);
                break;
        }
        //
        objective.getScore("Money: " + balance.get(player)).setScore(0);
        objective.getScore("Player:").setScore(2);
        player.setScoreboard(scoreboard);
    }
    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event){
        plugin.getRankManager().setPlayerRank(event.getPlayer(), Rank.NORMAL);
        setup(event.getPlayer());

    }


}
