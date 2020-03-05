package org.kilocraft.essentials.bungee.api;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginLogger;
import org.kilocraft.essentials.bungee.KiloEssentialsBungee;
import org.kilocraft.essentials.bungee.KiloEssentialsBungeePlugin;

import java.util.UUID;

public abstract class EssentialBungeeCommand implements IEssentialCommand {
    private final String label;
    protected final transient String[] alias;
    protected final transient String permission;
    protected final transient KiloEssentialsBungeePlugin essentials = KiloEssentialsBungee.getInstance();
    protected final transient ProxyServer server = essentials.getServer();
    protected final transient PluginLogger logger = (PluginLogger) server.getLogger();
    private transient String usage;

    public EssentialBungeeCommand(final String label) {
        this(label, null, new String[]{});
    }

    public EssentialBungeeCommand(final String label, final String[] alias) {
        this(label, null, alias);
    }

    public EssentialBungeeCommand(final String label, final String permission, String[] alias) {
        this.label = label;
        this.permission = permission;
        this.alias = alias;
    }

    @Override
    public void withUsage(final String key) {
        this.usage = key;
    }

    @Override
    public ProxiedPlayer getPlayer(final String name) {
        return this.server.getPlayer(name);
    }

    @Override
    public ProxiedPlayer getPlayer(final UUID uuid) {
        return this.server.getPlayer(uuid);
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String[] getAlias() {
        return this.alias;
    }

    public Command build() {
        return new Command(this.label, this.permission, this.alias) {
            @Override
            public void execute(CommandSender sender, String[] args) {
                this.execute(sender, args);
            }
        };
    }

}
