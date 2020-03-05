package org.kilocraft.essentials.bungee.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import org.kilocraft.essentials.bungee.KiloEssentialsBungee;
import org.kilocraft.essentials.bungee.api.EssentialBungeeCommand;

import java.util.Collections;
import java.util.List;

public class EssentialsBungeeCommand extends EssentialBungeeCommand {
    public EssentialsBungeeCommand() {
        super("essentialsbungee", new String[]{"essbungee"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(
                TextComponent.fromLegacyText(
                        KiloEssentialsBungee.getLang("command.essentials.info", KiloEssentialsBungee.getServer().getVersion()),
                        ChatColor.GRAY)
        );
    }

    @Override
    public List<String> onCommandCompletion(CommandSender sender, String cursor, String[] args) {
        return Collections.emptyList();
    }

}
