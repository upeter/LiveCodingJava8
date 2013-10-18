package org.livecoding.solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class g_VirtualExtensionMethods_Final {
    public static void main(String[] args) {
        new MyService1().process();
        new MyService2().process();
    }
}

interface Slf4JLogger {

    default Logger logger() {
        return LoggerFactory.getLogger(getClass());
    }

    public default void info(String msg) {
        logger().info(msg);
    }

    public default void info(String msg, Object param) {
        logger().info(msg, param);
    }

    //etc.

}


class MyService1 implements Slf4JLogger {

    public void process() {
        info("Hey I'm {}", "processing!");
    }
}

class MyService2 implements Slf4JLogger {

    public static final Logger log = LoggerFactory.getLogger(MyService2.class);

    public Logger logger() {
        return log;
    }

    public void process() {
        info("Hey I'm {}", "processing!");
    }
}
