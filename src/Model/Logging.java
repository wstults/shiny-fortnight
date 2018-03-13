/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author William
 */

// Class designed to facilitate logging
public class Logging {
    
    public Logger logger;
    FileHandler fh;
    
    // Checks to see if a log file currently exists and if not, creates it
    public Logging(String name) throws SecurityException, IOException {
        File file = new File(name);
        if (!file.exists()) {
            file.createNewFile();
        }
        fh = new FileHandler(name, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}
