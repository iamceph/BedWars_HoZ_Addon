package hoz.bedwars.config;

import hoz.bedwars.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainConfig {

    public File configf;
    public FileConfiguration config;

    public final File datafolder;
    public final Main netCore;

    public MainConfig(Main netCore) {
        this.datafolder = netCore.getDataFolder();
        this.netCore = netCore;
    }

    public void createFiles() {
        configf = new File(datafolder, "config.yml");

        if (!configf.exists()) {
            netCore.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        AtomicBoolean modify = new AtomicBoolean(false);
        checkOrSetConfig(modify, "locale", "cz");
        checkOrSetConfig(modify, "dropArmorOnDeath", false);
        checkOrSetConfig(modify, "addResourcesToKillerOnDeath", true);

        if (modify.get()) {
            try {
                config.save(configf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkOrSetConfig(AtomicBoolean modify, String path, Object value) {
        checkOrSet(modify, this.config, path, value);
    }

    private void checkOrSetBell(AtomicBoolean modify, String path, Object value) {
        checkOrSet(modify, this.config, path, value);
    }

    private static void checkOrSet(AtomicBoolean modify, FileConfiguration config, String path, Object value) {
        if (!config.isSet(path)) {
            config.set(path, value);
            modify.set(true);
        }
    }
}
