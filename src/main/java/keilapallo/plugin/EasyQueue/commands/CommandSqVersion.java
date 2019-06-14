package keilapallo.plugin.EasyQueue.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import keilapallo.plugin.EasyQueue.SimpleQueuePermission;
import keilapallo.plugin.EasyQueue.EasyQueuePlugin;

public class CommandSqVersion implements CommandExecutor {

    private final PluginDescriptionFile pdf;

    public CommandSqVersion(EasyQueuePlugin plugin) {
        pdf = plugin.getDescription();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getLabel().equals("easyversion") || !SimpleQueuePermission.COMMAND_SQVERSION.hasPermission(sender))
            return false;

        sender.sendMessage(String.format("[EasyQueue]: You are running %s version %s", pdf.getName(), pdf.getVersion()));


        return true;
    }
}
