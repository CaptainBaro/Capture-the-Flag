package github.captionbaro.io.ctf.util;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageRenderer extends MapRenderer {

    private BufferedImage image;
    private boolean done;

    public ImageRenderer(){
        done = false;
    }
    public ImageRenderer(String url){
        load(url);
        done = false;
    }

    public boolean load(String url){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(url));
            image = MapPalette.resizeImage(image);
        }catch (IOException e){
            return false;
        }
        this.image = image;
        return true;
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        if (done){
            return;
        }
        mapCanvas.drawImage(0,0,image);

        mapView.setTrackingPosition(false);
        done = true;
    }
}
