package me.nivyox.duels.listeners;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Niek on 8-2-2017.
 */
public class PacketListener {

    public static void create(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
                super.channelRead(context, packet);
            }

            @Override
            public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
                super.write(context, packet, promise);
            }
        };

        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.pipeline().addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }

}
