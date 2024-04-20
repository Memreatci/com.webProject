package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class MyLogClass {

    private static final Logger logger = Logger.getLogger(MyLogClass.class);

    public MyLogClass(){
        DOMConfigurator.configure("logd4j.properties");
    }

    public void info(String message){
        logger.info(message);
    }

    public void warn(String message){
        logger.warn(message);
    }

    public void error(String message){
        logger.error(message);
    }
}
