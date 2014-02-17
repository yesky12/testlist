package com.leo.test.napoli;

import com.alibaba.napoli.client.NapoliClientException;
import com.alibaba.napoli.client.async.impl.DefaultAsyncSender;
import com.alibaba.napoli.client.connector.NapoliConnector;
import org.apache.ecs.xhtml.address;

/**
 * Created by leo on 2/16/14.
 */
public class NapoliSender {

    public static void main(String[] args) throws NapoliClientException {

        NapoliConnector connector = new NapoliConnector();
        connector.setStorePath("./target");
        connector.setAddress("10.20.154.175:61616");
        connector.setPoolSize(10);
        connector.init();
        // 缓存sender,保持单例
        final DefaultAsyncSender sender = new DefaultAsyncSender();
        sender.setConnector(connector);
        sender.setName("destinationName");
        sender.setStoreEnable(true);
        sender.init();
        sender.send("hello world");
    }
}
