package keilapallo.plugin.EasyQueue;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import keilapallo.plugin.EasyQueue.commands.*;
import keilapallo.plugin.EasyQueue.util.WtfYamlConfiguration;

import java.io.File;
import java.util.logging.Logger;

public class EasyQueuePlugin extends JavaPlugin {

    private Logger logger;
    private WtfYamlConfiguration configYaml;
    private WtfYamlConfiguration prioritizedPlayersYaml;

    private PrioritizedPlayerConfigHandler prioritizedPlayerHandler;
    private PlayerQueue playerQueue;

    @Override
    public void onEnable() {
        logger = Logger.getLogger("Minecraft");

        setupConfigs();

        prioritizedPlayerHandler = new PrioritizedPlayerConfigHandler(getServer(), logger, prioritizedPlayersYaml);
        playerQueue = new PlayerQueue(prioritizedPlayerHandler, configYaml);

        registerEvents();
        setCommandExecutors();

        PluginDescriptionFile pdFile = getDescription();
        logger.info(String.format(
                "%s %s has been enabled!",
                pdFile.getName(),
                pdFile.getVersion()
        ));
    }

    @Override
    public void onDisable() {

    }

    private void setupConfigs() {
        configYaml = new WtfYamlConfiguration(logger, new File(getDataFolder(), "config.yml"),
                "/defaultConfig/config.yml");
        prioritizedPlayersYaml = new WtfYamlConfiguration(logger, new File(getDataFolder(), "prioritizedPlayers.yml"),
                "/defaultConfig/prioritizedPlayers.yml");
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new OnPlayerLoginListener(getServer(), logger, playerQueue, configYaml), this);
    }

    private void setCommandExecutors() {
        getCommand("easyversion").setExecutor(new CommandSqVersion(this));
        getCommand("easylist").setExecutor(new CommandSqList(configYaml, prioritizedPlayerHandler));
        getCommand("easyqueue").setExecutor(new CommandSqQueue(playerQueue));
        getCommand("easyadd").setExecutor(new CommandSqAdd(configYaml, prioritizedPlayerHandler));
        getCommand("easyremove").setExecutor(new CommandSqRemove(configYaml, prioritizedPlayerHandler));
    }
}
