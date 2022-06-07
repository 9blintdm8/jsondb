package com.users_data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import java.util.logging.SimpleFormatter;

public class Log {

    Logger logger = LoggerFactory.getLogger(Log.class);

    @RequestMapping("/")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }
//    public Logger logger;
//    FileHandler fh;
//
//    public Log(String file_name) throws SecurityException, IOException {
//
//        File f = new File(file_name);
//        if(!f.exists()){
//            f.createNewFile();
//        }
//
//        fh = new FileHandler(file_name, true);
//        logger = Logger.getLogger("test");
//        logger.addHandler(fh);
//        SimpleFormatter formatter = new SimpleFormatter();
//        fh.setFormatter(formatter);
//
//    }

}
