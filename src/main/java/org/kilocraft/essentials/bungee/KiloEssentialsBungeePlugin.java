package org.kilocraft.essentials.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginLogger;
import org.kilocraft.essentials.bungee.command.EssentialsBungeeCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class KiloEssentialsBungeePlugin extends Plugin implements KiloEssentialsBungee {
    private static final String MOD_CLASS = "org.kilocraft.essentials.KiloEssentials";
    static KiloEssentialsBungeePlugin instance;
    private final PluginLogger logger = (PluginLogger) this.getLogger();
    private ProxyServer server;
    private Properties lang;
    private List<EssentialsBungeeCommand> commands;

    @Override
    public void onEnable() {
        instance = this;
        this.server = this.getProxy();
        logger.info("Setting up KiloEssentials-Bungee");
        this.lang = new Properties();

        try {
            this.lang.load(this.getClass().getClassLoader().getResourceAsStream("lang.properties"));
        } catch (IOException e) {
            logger.warning("Couldn't load lang.properties for KiloEssentials-Bungee");
            e.printStackTrace();
        }

        if (!essentialsPresent()) {
            logger.warning("KiloEssentials Mod is not present!");
        }

        commands = new ArrayList<EssentialsBungeeCommand>(){{
            this.add(new EssentialsBungeeCommand());
        }};

        for (EssentialsBungeeCommand command : commands) {
            this.server.getPluginManager().registerCommand(this, command.build());
        }

        this.server.getPluginManager().registerListener(this, new Events());
    }

    @Override
    public void onDisable() {
        logger.info("Disabling KiloEssentials-Bungee");

        this.server.getPluginManager().unregisterCommands(this);
        this.server.getPluginManager().unregisterListeners(this);
    }

    public ProxyServer getServer() {
        return this.server;
    }

    private boolean essentialsPresent() {
        try {
            Class.forName(MOD_CLASS);
            ClassLoader.getSystemClassLoader().loadClass(MOD_CLASS);

            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    String getLang(String key, Object... objects) {
        String lang = instance.lang.getProperty(key);
        return objects == null ? lang : String.format(lang, objects);
    }

}
