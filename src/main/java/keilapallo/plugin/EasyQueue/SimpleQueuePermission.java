package keilapallo.plugin.EasyQueue;

import org.bukkit.permissions.Permissible;

public enum SimpleQueuePermission {

    WILDCARD("*"),
    IGNORE_SLOT_LIMIT("ignoreslotlimit"),
    PRIORITIZED_PLAYER("prioritized"),
    COMMAND_SQVERSION("easyversion"),
    COMMAND_SQQUEUE("easyqueue"),
    COMMAND_SQLIST("easylist"),
    COMMAND_SQADD("easyadd"),
    COMMAND_SQREMOVE("easyremove"),
    ;

    private final String permission;
    private static final String PREFIX = "easyqueue.";

    SimpleQueuePermission(String permission)  {
        this.permission = permission;
    }

    /**
     * @return Returns the name of the permission
     */
    public String getPermission() {
        return PREFIX + permission;
    }

    /**
     * Checks if a Permissible has access to a given right
     * @param permissible
     * @return true if able to use this permission
     */
    public boolean hasPermission(Permissible permissible) {
        return permissible.hasPermission(WILDCARD.getPermission()) || permissible.hasPermission(getPermission());
    }
}
