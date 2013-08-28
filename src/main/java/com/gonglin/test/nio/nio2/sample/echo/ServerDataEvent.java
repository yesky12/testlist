package com.gonglin.test.nio.nio2.sample.echo;

import java.nio.channels.SocketChannel;

class ServerDataEvent {

    public NioServer     server;
    public SocketChannel socket;
    public byte[]        data;

    public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data){
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}
