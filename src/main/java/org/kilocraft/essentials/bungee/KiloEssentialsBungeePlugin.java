package org.kilocraft.essentials.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.kilocraft.essentials.bungee.api.EssentialBungeeCommand;
import org.kilocraft.essentials.bungee.commands.BungeePluginsCommand;
import org.kilocraft.essentials.bungee.commands.InfoCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public final class KiloEssentialsBungeePlugin extends Plugin implements KiloEssentialsBungee {
    private static final String MOD_CLASS = "org.kilocraft.essentials.KiloEssentials";
    static KiloEssentialsBungeePlugin instance;
    private Logger logger;
    private Properties lang;
    private List<EssentialBungeeCommand> commands;

    @Override
    public void onEnable() {
        this.logger = this.getLogger();
        this.lang = new Properties();
        instance = this;

        logger.info("Setting up KiloEssentials-Bungee");
        logger.info("This Plugin is only for connecting KiloEssentials to BungeeCord");

        try {
            this.lang.load(this.getClass().getClassLoader().getResourceAsStream("lang.properties"));
        } catch (IOException e) {
            logger.warning("Couldn't load lang.properties for KiloEssentials-Bungee");
            e.printStackTrace();
        }

        commands = new ArrayList<EssentialBungeeCommand>(){{
            this.add(new InfoCommand());
            this.add(new BungeePluginsCommand());
        }};

        for (EssentialBungeeCommand command : commands) {
            this.getProxy().getPluginManager().registerCommand(this, command);
        }

        this.getProxy().getPluginManager().registerListener(this, new Events());
    }

    @Override
    public void onDisable() {
        logger.info("Disabling KiloEssentials-Bungee");

        this.getProxy().getPluginManager().unregisterCommands(this);
        this.getProxy().getPluginManager().unregisterListeners(this);
    }

    public ProxyServer getServer() {
        return this.getProxy();
    }

    public String getLang(String key, Object... objects) {
        String lang = instance.lang.getProperty(key);
        return objects == null ? lang : String.format(lang, objects);
    }

    public static boolean isEnabled() {
        return instance != null;
    }

}
