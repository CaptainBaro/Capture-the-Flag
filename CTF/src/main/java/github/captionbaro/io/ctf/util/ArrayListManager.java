package github.captionbaro.io.ctf.util;


import github.captionbaro.io.ctf.CTF;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ArrayListManager {

    private CTF plugin;

    private ArrayList<String> kits;
    private ArrayList<Player> players;
    private ArrayList<Player> ingamePlayers;
    private ArrayList<Player> vanishedPlayers;
    private ArrayList<String> fähigkeitennormal1namen;
    private ArrayList<String> fähigkeitennormal2namen;
    private ArrayList<Player> aphroditeNormal1;
    private ArrayList<String> ultinamen;
    private ArrayList<Player> shadowrunPlayers;




    public ArrayListManager(CTF plugin){
        this.plugin = plugin;
        players = new ArrayList<>();
        ingamePlayers = new ArrayList<>();
        vanishedPlayers = new ArrayList<>();
        kits = new ArrayList<>();
        kits.add("Aphrodite");
        kits.add("Hermes");
        kits.add("Dionysus");
        kits.add("Hades");
        kits.add("Athene");
        kits.add("Ares");
        fähigkeitennormal1namen = new ArrayList<>();
        fähigkeitennormal2namen = new ArrayList<>();
        ultinamen = new ArrayList<>();
        shadowrunPlayers = new ArrayList<>();
        aphroditeNormal1 = new ArrayList<>();

    }

    public ArrayList<Player> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public ArrayList<Player> getIngamePlayers() {
        return ingamePlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<String> getFähigkeitenNormal1namen() {
        return fähigkeitennormal1namen;
    }

    public ArrayList<String> getFähigkeitennormal2namen() {
        return fähigkeitennormal2namen;
    }

    public ArrayList<String> getUltinamen() {
        return ultinamen;
    }

    public ArrayList<Player> getShadowrunPlayers() {
        return shadowrunPlayers;
    }

    public ArrayList<Player> getAphroditeNormal1() {
        return aphroditeNormal1;
    }


    public void init(){
        for (String kit : kits){
            fähigkeitennormal1namen.add(kit + ".Normal.1");
            fähigkeitennormal2namen.add(kit + ".Normal.2");
            ultinamen.add(kit + ".Ulti");
        }


    }


}
