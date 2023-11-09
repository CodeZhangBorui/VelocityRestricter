package com.codezhangborui.velocityrestricter;

import com.google.inject.Inject;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
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

    }

    @Subscribe
    public void onServerPreConnect(ServerPreConnectEvent event) {
        server.getEventManager().register(this, new ServerPreConnectListener(event, logger));
    }
}

