package com.codezhangborui.velocityrestricter;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

@Plugin(
        id = "velocityrestricter",
        name = "VelocityRestricter",
        version = "1.0-SNAPSHOT",
        authors = {"CodeZhangborui"}
)
public class VelocityRestricter {

    @Inject
    private final Logger logger;
    private final ProxyServer server;

    public VelocityRestricter(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Velocity Restricter Loaded.");
    }

    @Subscribe
    public void onServerPreConnect(ServerPreConnectEvent event) {
        if(!event.getPlayer().hasPermission("velocityrestricter." + event.getOriginalServer().getServerInfo().getName())) {
            logger.info("Player " + event.getPlayer().getUsername() + " does not have permission to connect to server " + event.getOriginalServer().getServerInfo().getName());
            event.getPlayer().sendMessage(Component.text("你没有权限连接到这个服务器！"));
            event.setResult(ServerPreConnectEvent.ServerResult.denied());
        }
    }
}

