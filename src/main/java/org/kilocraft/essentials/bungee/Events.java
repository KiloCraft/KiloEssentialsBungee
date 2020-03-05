package org.kilocraft.essentials.bungee;

import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {

    @EventHandler
    public void tabCompleteEvent(TabCompleteEvent event) {
        System.out.println(event.getSuggestions().toString());
        System.out.println(event.getCursor());
    }

}
