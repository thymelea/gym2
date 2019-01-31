package com.example.gym2.nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @Author: zhangbo
 * @Date: 2019/1/29/029 11:47
 * @Version 1.0
 */
public class NettyS {
    public void bind(int nPort) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
//                        @Override
//                        public void initChannel(SocketChannel ch) throws Exception{
//                            ch.pipeline()
//                                    .addLast(
//                                            new ObjectDecoder(Integer.MAX_VALUE,
//                                                    ClassResolvers.weakCachingConcurrentResolver(
//                                                            this.getClass().getClassLoader())))
//                                    .addLast(new ObjectEncoder())
//                                    .addLast(new SubReqServerHandler());

                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(
                                        new ObjectEncoder(),
                                        new ObjectDecoder(Integer.MAX_VALUE,ClassResolvers.cacheDisabled(null)),
                                        new SubReqServerHandler());

                        }
                    });
            b.bind(nPort).sync().channel().closeFuture().sync();
//            ChannelFuture f = b.bind(nPort).sync();
            System.out.println("---------------wait for connect");
//            f.channel().closeFuture().sync();
        }finally {
            System.out.println("---------------wait for connect  Error!");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args){
        int nPort = 5656;
        nPort = Integer.valueOf(nPort);
        System.out.println("---------------Main start");
        try {
            new NettyS().bind(nPort);
        } catch (Exception e) {
            System.out.println("---------------Main Error");
            e.printStackTrace();
        }
    }
}
