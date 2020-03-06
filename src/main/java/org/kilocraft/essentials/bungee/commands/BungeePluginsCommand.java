package org.kilocraft.essentials.bungee.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.kilocraft.essentials.bungee.api.EssentialBungeeCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class BungeePluginsCommand extends EssentialBungeeCommand implements TabExecutor {
    public BungeePluginsCommand() {
        super("bungeeplugins", "keb.command.bungeeplugins", new String[]{"bplugins", "pluginsbungee"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Collection<Plugin> plugins = server.getPluginManager().getPlugins();

        if (args.length == 1) {
            Plugin plugin = null;

            for (Plugin p : plugins) {
                if (p.getDescription().getName().equalsIgnoreCase(args[0])) {
                    plugin = p;
                    break;
                }
            }

            if (plugin == null) {
                sender.sendMessage(new TextComponent(ChatColor.LIGHT_PURPLE + essentials.getLang("command.bungeeplugins.unknown")));
                return;
            }

            PluginDescription desc = plugin.getDescription();
            sender.sendMessage(
                    newLine("Name", desc.getName(), ChatColor.GOLD),
                    newLine("Version", desc.getVersion(), ChatColor.LIGHT_PURPLE),
                    newLine("Description", desc.getName(), ChatColor.YELLOW),
                    newLine("Author", desc.getAuthor(), ChatColor.GREEN)
            );
            return;
        }

        final TextComponent text = new TextComponent(essentials.getLang("command.bungeeplugins.header", plugins.size()));

        int i = 0;
        boolean nextColor = false;
        for (Plugin plugin : plugins) {
            final TextComponent pluginComponent = new TextComponent(plugin.getDescription().getName());
            pluginComponent.setColor(nextColor ? ChatColor.GRAY : ChatColor.WHITE);
            pluginComponent.setHoverEvent(
                    new HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            new BaseComponent[]{new TextComponent(ChatColor.YELLOW + essentials.getLang("command.bungeeplugins.hover"))})
            );
            pluginComponent.setClickEvent(
                    new ClickEvent(
                            ClickEvent.Action.RUN_COMMAND,
                            "/bungeeplugins " + plugin.getDescription().getName().toLowerCase()
                    )
            );

            text.addExtra(pluginComponent);

            if (i + 1 != plugins.size()) {
                text.addExtra(new TextComponent(ChatColor.DARK_GRAY + ", "));
            }

            i++;
            nextColor = !nextColor;
        }

        sender.sendMessage(text);
    }

    private TextComponent newLine(String title, String desc, ChatColor color) {
        final TextComponent text = new TextComponent(ChatColor.GRAY + "* " + title + ": ");
        text.addExtra(new TextComponent(color + desc));
        return text;
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        final List<String> strings = new ArrayList<>();
        for (Plugin plugin : server.getPluginManager().getPlugins()) {
            strings.add(plugin.getDescription().getName().toLowerCase(Locale.ROOT));
        }

        return new CompletionsFactory(args).suggest(0, strings.toArray(new String[]{})).complete();
    }

}
