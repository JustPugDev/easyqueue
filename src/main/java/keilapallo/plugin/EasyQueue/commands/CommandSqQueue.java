package keilapallo.plugin.EasyQueue.commands;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import keilapallo.plugin.EasyQueue.PlayerQueue;
import keilapallo.plugin.EasyQueue.SimpleQueuePermission;

import java.util.List;
import java.util.UUID;

public class CommandSqQueue implements CommandExecutor {

    private final PlayerQueue playerQueue;

    public CommandSqQueue(PlayerQueue playerQueue) {
        this.playerQueue = playerQueue;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equals("easyqueue") || !SimpleQueuePermission.COMMAND_SQQUEUE.hasPermission(sender))
            return false;
        Server server = sender.getServer();

        StringBuilder sb = new StringBuilder();
        List<UUID> uuidList = playerQueue.getQueuedPlayers();
        int listSize = uuidList.size();
        int index = 0;

        if(listSize == 1)
            sb.append(String.format("[EasyQueue]: There is currently 1 player in the queue: "));
        else
            sb.append(String.format("[EasyQueue]: There are currently %d players in the queue:\n", listSize));

        for(UUID playerUUID : uuidList) {
            sb.append(server.getOfflinePlayer(playerUUID).getName());
            if(++index < listSize)
                sb.append(", ");
        }

        sender.sendMessage(sb.toString());

        return true;
    }
}
