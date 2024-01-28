package github.captionbaro.io.ctf.util;


import github.captionbaro.io.ctf.CTF;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
 /***
  *
     * @author CodedRed
     *
     * Code Provided by CodedRed
     */
 public class ImageManager implements Listener {

        private static ImageManager instance = null;

        public static ImageManager getInstance() {
            if (instance == null)
                instance = new ImageManager();
            return instance;
        }

        private CustomFile dataFile = new CustomFile("data.yml");

        private Map<Integer, String> savedImages = new HashMap<Integer, String>();

        /***
         * Call this method in the onEnable()
         * Code:
         * ImageManager manager = ImageManger.getInstance();
         * manager.init();
         *
         * Once done, just add the below code to the end of your map command:
         * ImageManager manager = ImageManager.getInstance();
         * manager.saveImage(view.getId(), args[0]); // args[0] is the url
         *
         */
        public void init() {
            Bukkit.getServer().getPluginManager().registerEvents(this, CTF.getPlugin(CTF.class));
            loadImages();
        }


        @EventHandler
        public void onMapInitEvent(MapInitializeEvent event) {
            if (hasImage(event.getMap().getId())) {
                MapView view = event.getMap();
                view.getRenderers().clear();
                view.addRenderer(new ImageRenderer(getImage(view.getId())));
                view.setScale(Scale.FARTHEST);
                view.setTrackingPosition(false);
            }
        }


        /***
         * Whenever a new map is created, save the ID and Image to data file.
         *
         * @param id - MapView ID
         * @param url - String url link
         */
        public void saveImage(Integer id, String url) {
            getData().set("ids." + id, url);
            saveData();
        }


        /***
         * Loads images from data file to HashMap.
         */
        private void loadImages() {
            if (getData().contains("ids"))
                getData().getConfigurationSection("ids").getKeys(false).forEach(id -> {
                    savedImages.put(Integer.parseInt(id), getData().getString("ids." + id));
                });
        }


        public boolean hasImage(int id) {
            return savedImages.containsKey(id);
        }


        public String getImage(int id) {
            return savedImages.get(id);
        }


        public FileConfiguration getData() {
            return dataFile.getConfig();
        }


        public void saveData() {
            dataFile.saveConfig();
        }


        /***
         *
         * @author CodedRed
         *
         * CustomFile code provided by CodedRed.
         */
        class CustomFile {

            private final CTF plugin = CTF.getPlugin(CTF.class);
            private FileConfiguration dataConfig = null;
            private File dataConfigFile = null;
            private final String name;

            public CustomFile(String name) {
                this.name = name;
                saveDefaultConfig();
            }

            public void reloadConfig() {
                if (dataConfigFile == null)
                    dataConfigFile = new File(plugin.getDataFolder(),name);

                this.dataConfig = YamlConfiguration
                        .loadConfiguration(dataConfigFile);

                InputStream defConfigStream = plugin.getResource(name);
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration
                            .loadConfiguration(new InputStreamReader(defConfigStream));
                    this.dataConfig.setDefaults(defConfig);
                }
            }

            public FileConfiguration getConfig() {
                if (this.dataConfig == null)
                    reloadConfig();
                return this.dataConfig;
            }

            public void saveConfig() {
                if ((dataConfig == null) || (dataConfigFile == null))
                    return;
                try {
                    getConfig().save(dataConfigFile);
                } catch (IOException e) {
                    plugin.getLogger().log(Level.SEVERE, "Could not save config to "
                            + dataConfigFile, e);
                }
            }

            public void saveDefaultConfig() {
                if (dataConfigFile == null)
                    dataConfigFile = new File(plugin.getDataFolder(), name);
                if (!dataConfigFile.exists())
                    plugin.saveResource(name, false);
            }

        }

}
