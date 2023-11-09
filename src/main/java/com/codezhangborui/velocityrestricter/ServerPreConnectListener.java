package com.codezhangborui.velocityrestricter;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

public class ServerPreConnectListener {

    private final ServerPreConnectEvent event;
    private final Logger logger;

    public ServerPreConnectListener(ServerPreConnectEvent event, Logger logger) {
        this.event = event;
        this.logger = logger;
    }

    @Subscribe(order = PostOrder.FIRST)
    public void onServerPreConnect(ServerPreConnectEvent event) {
        if(!event.getPlayer().hasPermission("velocityrestricter." + event.getOriginalServer().getServerInfo().getName())) {
            logger.info("Player " + event.getPlayer().getUsername() + " does not have permission to connect to server " + event.getOriginalServer().getServerInfo().getName());
            event.getPlayer().sendMessage(Component.text("你没有权限连接到这个服务器！"));
            event.setResult(ServerPreConnectEvent.ServerResult.denied());
        }
    }
}