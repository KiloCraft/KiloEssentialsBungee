package org.kilocraft.essentials.bungee.api;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kilocraft.essentials.bungee.KiloEssentialsBungee;

import java.util.Collection;
import java.util.UUID;

public class ProxiedPlayerUtil {
    private static final ProxyServer proxy = KiloEssentialsBungee.getServer();

    public static String getRealSocketAddress(UUID uuid) {
        return proxy.getPlayer(uuid).getSocketAddress().toString();
    }

    public static ProxiedPlayer get(UUID uuid) {
        return proxy.getPlayer(uuid);
    }

    public static ProxiedPlayer get(String name) {
        return proxy.getPlayer(name);
    }

    public static Collection<ProxiedPlayer> getAll() {
        return proxy.getPlayers();
    }

}
