package ru.yandex.qatools.allure.logback.appender;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import org.joda.time.DateTime;
import ru.yandex.qatools.allure.annotations.Attachment;

import static ch.qos.logback.classic.Level.*;

/**
 * @author Andrey Epifanov
 * andrey.a.epifanov@gmail.com
 *         Date: 2017.01.25
 */
public class LogbackToAllureAppender extends UnsynchronizedAppenderBase {

    private static final String BEGIN = "";
    private static String accumulatedStr = BEGIN;
    private static int lastLevelOfEvent = INFO_INT;

    @Override
    protected void append(Object eventObject) {
        LoggingEvent event = (LoggingEvent)eventObject;
//        if (event.getFormattedMessage().contains(START_STEP_MESSAGE)) { // => start == flush
//            if (!accumulatedStr.equals(BEGIN)) {
//                flush(lastLevelOfEvent);
//                accumulatedStr = BEGIN;
//            }
//            return;
//        }
//        if (event.getFormattedMessage().contains(FINISH_STEP_MESSAGE)) { // => static flush
//            flush(lastLevelOfEvent);
//            accumulatedStr = BEGIN;
//            return;
//        }
        // logic : this is for change level of event - > another message
        if (lastLevelOfEvent != event.getLevel().levelInt) {
            flush(lastLevelOfEvent);
            accumulatedStr = new DateTime(event.getContextBirthTime()).toString("HH:mm:ss") + " " + event.getFormattedMessage();
            lastLevelOfEvent = event.getLevel().levelInt;
            return;
        }
        // all events with equal level into one message
        accumulatedStr = accumulatedStr + "\n"
                + new DateTime(event.getContextBirthTime()).toString("HH:mm:ss") + " " + event.getFormattedMessage();
    }

    public static void flush() {
        flush(lastLevelOfEvent);
        accumulatedStr = BEGIN;
    }

    private static void flush(int lastEventOfEvent) {
        toAllure(lastEventOfEvent);
    }

    private static String toAllure(int lastEventOfEvent) {
        switch (lastEventOfEvent) {
            case INFO_INT: return toAllureInfo();
            case DEBUG_INT: return toAllureDebug();
            case TRACE_INT: return toAllureTrace();
            case WARN_INT: return toAllureWarn();
            case ERROR_INT: return toAllureError();
            default: return toAllureInfo();
        }
    }

    @Attachment(value = "Info", type = "text/plain")
    private static String toAllureInfo() {
        return accumulatedStr;
    }

    @Attachment(value = "Debug", type = "text/plain")
    private static String toAllureDebug() {
        return accumulatedStr;
    }

    @Attachment(value = "Trace", type = "text/plain")
    private static String toAllureTrace() {
        return accumulatedStr;
    }

    @Attachment(value = "Warn", type = "text/plain")
    private static String toAllureWarn() {
        return accumulatedStr;
    }

    @Attachment(value = "Error", type = "text/plain")
    private static String toAllureError() {
        return accumulatedStr;
    }

//    protected Encoder<E> encoder;
//
//    public Encoder<E> getEncoder() {
//        return encoder;
//    }
//
//    public void setEncoder(Encoder<E> encoder) {
//        this.encoder = encoder;
//    }
//
//    /** encoder = new LayoutWrappingEncoder<E>();
//     *
//     * @param layout
//     */
//    public void setLayout(Layout<E> layout) {
//        addWarn("This appender no longer admits a layout as a sub-component, set an encoder instead.");
//        addWarn("To ensure compatibility, wrapping your layout in LayoutWrappingEncoder.");
//        addWarn("See also "+CODES_URL+"#layoutInsteadOfEncoder for details");
//        LayoutWrappingEncoder<E> lwe = new LayoutWrappingEncoder<E>();
//        lwe.setLayout(layout);
//        lwe.setContext(context);
//        this.encoder = lwe;
//        encoder.doEncode(e);
//    }

}
