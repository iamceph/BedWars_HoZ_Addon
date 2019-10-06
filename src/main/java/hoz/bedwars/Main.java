package hoz.bedwars;

import hoz.bedwars.config.MainConfig;
import hoz.bedwars.listeners.GameListener;
import hoz.bedwars.utils.Debuger;
import misat11.bw.api.BedwarsAPI;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    private static MainConfig mainConfig;
    private static BedwarsAPI bedwarsAPI;

    private PluginDescriptionFile pluginInfo;

    private boolean useDebug = true;

    @Override
    public void onEnable() {
        instance = this;

        Debuger.debug("Initializing config file", false);
        mainConfig = new MainConfig(this);
        mainConfig.createFiles();
        bedwarsAPI = BedwarsAPI.getInstance();

        getServer().getPluginManager().registerEvents(new GameListener(), this);

        pluginInfo = this.getDescription();
        Debuger.debug("Loading of HryoZivot.net addon for BedWars v" + pluginInfo.getVersion() + " is done!", true);
    }

    @Override
    public void onDisable() {
        this.getServer().getServicesManager().unregisterAll(this);
        Debuger.debug("HryoZivot.net addon for BedWars was disabled.", true);
    }

    public static Main getInstance() {
        return instance;
    }

    public static MainConfig getConfigurator() {
        return mainConfig;
    }

    public static BedwarsAPI getBedWars() {
        return bedwarsAPI;
    }

    public boolean getUseDebug() {
        return useDebug;
    }
}