package darkyiu.me.ctf3.utils;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;

public class BasicUtil {

    private CTF3 plugin;

    public BasicUtil(CTF3 plugin){
        this.plugin = plugin;
    }

    public void healSavely(LivingEntity entity, double health){
        if(entity.getHealth()+health>= entity.getMaxHealth()){
            entity.setHealth(entity.getMaxHealth());
        }else {
            entity.setHealth(entity.getHealth()+health);
        }
    }

    public ArrayList<String> cutLore(String lore){
        int end = -1;
        ArrayList<String> lores = new ArrayList<String>();
        for (int start = 22; start<lore.length(); start++){
            if(lore.charAt(start) == (char) 32){
                lores.add(lore.substring(end+1, start));
                end = start;
                start = start + 23;
            }
        }
        lores.add("§7"+ lore.substring(end+1));

        return lores;
    }

}
