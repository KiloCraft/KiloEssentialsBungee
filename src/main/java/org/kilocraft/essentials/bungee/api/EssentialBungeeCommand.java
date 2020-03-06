package org.kilocraft.essentials.bungee.api;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kilocraft.essentials.bungee.KiloEssentialsBungee;
import org.kilocraft.essentials.bungee.KiloEssentialsBungeePlugin;

import java.util.*;
import java.util.logging.Logger;

public abstract class EssentialBungeeCommand extends Command implements IEssentialCommand {
    private final String label;
    protected final transient String[] alias;
    protected final transient String permission;
    protected final transient KiloEssentialsBungeePlugin essentials = KiloEssentialsBungee.getInstance();
    protected final transient ProxyServer server = essentials.getServer();
    protected final transient Logger logger = server.getLogger();
    private transient String usage;

    public EssentialBungeeCommand(final String label) {
        this(label, null, new String[]{});
    }

    public EssentialBungeeCommand(final String label, final String[] alias) {
        this(label, null, alias);
    }

    public EssentialBungeeCommand(final String label, final String permission) {
        this(label, permission, new String[]{});
    }

    public EssentialBungeeCommand(final String label, final String permission, String[] alias) {
        super(label, permission, alias);
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

    public static class CompletionsFactory {
        private String[] args;
        private Map<Integer, String[]> map;

        public CompletionsFactory(String[] args) {
            this.args = args;
            this.map = new HashMap<>();
        }

        public CompletionsFactory suggest(int arg, String... strings) {
            this.map.put(arg, strings);
            return this;
        }

        public Iterable<String> complete() {
            if (args.length > this.map.size() || this.map.isEmpty() || this.args.length == 0) {
                return Collections.emptyList();
            }

            String[] strings = this.map.get(this.args.length - 1);
            if (strings != null) {
                final List<String> suggestions = new ArrayList<>();

                for (String string : strings) {
                    if (string.toLowerCase(Locale.ROOT).startsWith(this.args[this.args.length - 1].toLowerCase(Locale.ROOT))) {
                        suggestions.add(string);
                    }
                }

                return suggestions.isEmpty() ? Collections.emptyList() : suggestions;
            }

            return Collections.emptyList();
        }

    }
}
