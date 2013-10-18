package org.livecoding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class g_VirtualExtensionMethods {

    public static void main(String[] args) {
        new MyService().process();
    }
}


class MyService {
    public static final Logger LOG = LoggerFactory.getLogger(MyService.class);

    public void process() {
        LOG.info("Hey I am {}", "processing!");
    }
}


