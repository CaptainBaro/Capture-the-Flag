package github.captionbaro.io.ctf.teams;


import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public enum Team {

    RED("Rot", ChatColor.DARK_RED, (short) 14, new ConfigLocationUtil(CTF.getPlugin(), "SpawnRed").loadLocation(), false),
    BLUE("Blau", ChatColor.BLUE, (short) 11, new ConfigLocationUtil(CTF.getPlugin(), "SpawnBlue").loadLocation(), false);


    private String teamName;
    private ChatColor chatColor;
    private short colorID;
    private Location bannerLocation;
    private boolean isFlagStolen;

    private Team(String teamName, ChatColor chatColor, short colorID, Location bannerLocation, boolean isFlagStolen){
        this.teamName = teamName;
        this.chatColor = chatColor;
        this.colorID = colorID;
        this.isFlagStolen = isFlagStolen;
        this.bannerLocation = bannerLocation;
    }
    public String getTeamName(){
        return chatColor + teamName;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public short getColorID() {
        return colorID;
    }

    public Location getBannerLocation() {
        return bannerLocation;
    }

    public boolean isFlagStolen() {
        return isFlagStolen;
    }

    public void setFlagStolen(boolean flagStolen) {
        isFlagStolen = flagStolen;
    }
}
