package keilapallo.plugin.EasyQueue.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import keilapallo.plugin.EasyQueue.PrioritizedPlayerConfigHandler;
import keilapallo.plugin.EasyQueue.SimpleQueuePermission;
import keilapallo.plugin.EasyQueue.util.WtfYamlConfiguration;

public class CommandSqRemove implements CommandExecutor {

    private final WtfYamlConfiguration configYaml;
    private final PrioritizedPlayerConfigHandler prioritizedPlayerConfigHandler;

    public CommandSqRemove(WtfYamlConfiguration configYaml, PrioritizedPlayerConfigHandler prioritizedPlayerConfigHandler) {
        this.configYaml = configYaml;
        this.prioritizedPlayerConfigHandler = prioritizedPlayerConfigHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equals("easyremove") || !SimpleQueuePermission.COMMAND_SQREMOVE.hasPermission(sender))
            return false;

        if(!configYaml.getBoolean("allowPrioritizedPlayersViaConfig")) {
            sender.sendMessage("Please enable 'allowPrioritizedPlayersViaConfig' in the config.yml");
            return true;
        }

        if(args.length != 1)
            return false;

        Server server = sender.getServer();
        OfflinePlayer foundPlayer = server.getOfflinePlayer(args[0]);

        prioritizedPlayerConfigHandler.removePlayer(foundPlayer);

        sender.sendMessage(String.format("%s was successfully removed from the prioritizedPlayers.yml.", foundPlayer.getPlayer()));

        return true;
    }
}
