package keilapallo.plugin.EasyQueue.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import keilapallo.plugin.EasyQueue.PrioritizedPlayerConfigHandler;
import keilapallo.plugin.EasyQueue.SimpleQueuePermission;
import keilapallo.plugin.EasyQueue.util.WtfYamlConfiguration;

import java.util.List;

public class CommandSqList implements CommandExecutor {

    private final WtfYamlConfiguration configYaml;
    private final PrioritizedPlayerConfigHandler prioritizedPlayerConfigHandler;

    public CommandSqList(WtfYamlConfiguration configYaml, PrioritizedPlayerConfigHandler prioritizedPlayerConfigHandler) {
        this.configYaml = configYaml;
        this.prioritizedPlayerConfigHandler = prioritizedPlayerConfigHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equals("easylist") || !SimpleQueuePermission.COMMAND_SQLIST.hasPermission(sender))
            return false;

        if(!configYaml.getBoolean("allowPrioritizedPlayersViaConfig")) {
            sender.sendMessage("[EasyQueue]: Please enable 'allowPrioritizedPlayersViaConfig' in the config.yml");
            return true;
        }

        StringBuilder sb = new StringBuilder();
        List<OfflinePlayer> prioritizedPlayerList = prioritizedPlayerConfigHandler.getPrioritizedPlayerList();
        int listSize = prioritizedPlayerList.size();
        int index = 0;

        sb.append(String.format("[EasyQueue]: There are %d in the prioritizedPlayers.yml:\n", listSize));

        for(OfflinePlayer player : prioritizedPlayerList) {
            sb.append(player.getName());
            if(++index < listSize)
                sb.append(", ");
        }

        sender.sendMessage(sb.toString());

        return true;
    }
}
