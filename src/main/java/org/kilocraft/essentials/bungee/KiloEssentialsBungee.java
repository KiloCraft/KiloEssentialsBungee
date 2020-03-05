package org.kilocraft.essentials.bungee;

import net.md_5.bungee.api.ProxyServer;

public interface KiloEssentialsBungee {

    static KiloEssentialsBungeePlugin getInstance() {
        if (KiloEssentialsBungeePlugin.instance == null) {
            throw new RuntimeException("Its too early to get a static instance of KiloEssentialsBungee!");
        }

        return KiloEssentialsBungeePlugin.instance;
    }

    static ProxyServer getServer() {
        return getInstance().getServer();
    }

    static String getLang(String key, Object... objects) {
        return getInstance().getLang(key, objects);
    }

}
