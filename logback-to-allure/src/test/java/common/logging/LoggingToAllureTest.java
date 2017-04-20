package common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Created by Epifanov on 11/28/2016.
 */

@Features("Логирование.")
@Stories("Простое логирование - с Allure.")
public class LoggingToAllureTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggingToAllureTest.class);

    @Test
    public void executeStepOnly() throws Exception {
        loggingInfoStep01();
    }

    @Test
    public void executeTestSimple() throws Exception {
        logger.info("info");
        logger.info("info + something");

        logger.debug("debug");
        logger.trace("trace");
        logger.error("error in main method !");

        loggingInfoStep01();
        loggingInfoStep01();

        loggingInfoWithErrorStep();
    }

    @Test
    public void twoDiffSteps01() throws Exception {
        logger.info("info");

        loggingInfoStep01();

        loggingInfoWithErrorStep();
    }

    @Test
    public void executeTestSimpleWithException01() throws Exception {
        logger.info("info");
        logger.info("info + something");

        logger.error("error");

        loggingInfoStep01();
        logger.info("info before error 01");
        logger.info("info before error 02");
        //TODO: for test
//        if (true)
//            throw new Exception();
        loggingInfoStep01();

        loggingInfoWithErrorStep();
    }

    @Step("For Logging Error")
    private void loggingInfoWithErrorStep() {
        logger.info("info 01");
        logger.info("info 02");

        logger.error("error 01");
        logger.error("error 02");

        logger.info("info 03");
        logger.info("info 04");
    }

    @Test
    public void executeTestSimpleWithException02() throws Exception {
        logger.info("info before error 01");
        logger.info("info before error 02");
        loggingInfoWithException();

        loggingInfoWithErrorStep();
    }

    @Step("For Logging Info")
    private void loggingInfoStep01() {
        logger.info("info 01");
        logger.info("info 02");
    }

    @Step("For Logging With Exception")
    private void loggingInfoWithException() throws Exception {
        logger.info("Step. Info before error 01");
        logger.info("Step. Info before error 02");
        //TODO: for test
//        if (true)
//            throw new Exception();
        logger.info("Step. Info after error 01");
    }
}
