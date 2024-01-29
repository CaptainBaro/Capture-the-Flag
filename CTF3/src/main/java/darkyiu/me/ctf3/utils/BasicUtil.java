package darkyiu.me.ctf3.utils;

import org.bukkit.entity.LivingEntity;

public class BasicUtil {

    public void healSavely(LivingEntity entity, double health){
        if(entity.getHealth()+health>= entity.getMaxHealth()){
            entity.setHealth(entity.getMaxHealth());
        }else {
            entity.setHealth(entity.getHealth()+health);
        }
    }

}
