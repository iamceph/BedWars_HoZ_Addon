package hoz.bedwars.utils;

import hoz.bedwars.Main;

import java.util.logging.Logger;

public class Debuger {
    public static void debug(String debug, boolean forceDebug) {
        if (Main.getInstance().getUseDebug() || forceDebug) {
            Logger logger = Logger.getLogger("NetCore");

            logger.info(debug);
        }
    }
}
