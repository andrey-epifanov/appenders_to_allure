package common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Created by Epifanov on 11/28/2016.
 */

@Features("Логирование.")
@Stories("Простое логирование - без Allure.")
public class LoggingTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    @Test
    public void executeTestSimple() throws Exception {
        logger.info("info");
        logger.info("info + something");

        logger.debug("debug");
        logger.trace("trace");
        logger.error("error");
    }

    @Test
    public void executeTestWithCommand() throws Exception {
        logger.info("===== 1 =============");
        logger.info("one");

        logger.info("===== 2 =============");
        logger.info("two");

        logger.info("===== 3 =============");
        logger.info("three");
    }

    @Test
    public void executeTestWithException() throws Exception {
        logger.info("Some action before exception.");
        logger.warn("Warning!");
        logger.error("error");
        //TODO: for test
        //throw new Exception("My message!");
    }
}
