package org.kilocraft.essentials.bungee.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kilocraft.essentials.bungee.KiloEssentialsBungee;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.UUID;

public class ProxiedPlayerUtil {
    private static final ProxyServer proxy = KiloEssentialsBungee.getServer();

    public static SocketAddress getRealSocketAddress(UUID uuid) {
        return proxy.getPlayer(uuid).getSocketAddress();
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

    public static void setListHeader(final UUID uuid, final TextComponent header, final TextComponent footer) {
        get(uuid).setTabHeader(header, footer);
    }

}
