package org.kilocraft.essentials.bungee.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.kilocraft.essentials.bungee.KiloEssentialsBungee;
import org.kilocraft.essentials.bungee.api.EssentialBungeeCommand;

public class InfoCommand extends EssentialBungeeCommand implements TabExecutor {
    public InfoCommand() {
        super("essentialsbungee", new String[]{"essbungee"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(TextComponent.fromLegacyText(essentials.getLang("command.essentials.usage")));
            return;
        }

        switch (args[0]) {
            case "version": {
                sender.sendMessage(TextComponent.fromLegacyText(
                        essentials.getLang("command.essentials.version",
                                essentials.getDescription().getVersion(),
                                KiloEssentialsBungee.getServer().getVersion())
                ));

                break;
            }

            case "info": {
                sendInfo(sender);
                break;
            }

            default:
                sender.sendMessage(TextComponent.fromLegacyText(essentials.getLang("command.essentials.usage")));
        }
    }

    private void sendInfo(CommandSender sender) {
        sender.sendMessage(
                TextComponent.fromLegacyText(
                        essentials.getLang("command.essentials.info", KiloEssentialsBungee.getServer().getVersion()),
                        ChatColor.GRAY)
        );
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return new CompletionsFactory(args).suggest(0, "info", "version").complete();
    }
}
