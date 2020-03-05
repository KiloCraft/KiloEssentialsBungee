package org.kilocraft.essentials.bungee.api;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;
import java.util.UUID;

public interface IEssentialCommand {
    ProxiedPlayer getPlayer(final String name);

    ProxiedPlayer getPlayer(final UUID uuid);

    String getLabel();

    String[] getAlias();

    void withUsage(final String key);

    List<String> onCommandCompletion(final CommandSender sender, final String cursor, final String[] args);

    void execute(final CommandSender sender, final String[] args);
}
