package utils;

import hooks.AppHooks;
import org.automation.framework.enums.LogType;

import java.time.ZonedDateTime;

import static org.automation.framework.utils.DateUtil.formatDate;

public class CustomLogger {

    private AppHooks appHooks;

    public CustomLogger(AppHooks appHooks) {
        this.appHooks = appHooks;
    }

    public void logInfo(String message, Object... args) {
        String formatted = String.format(message, args);
        appHooks.scenario.log(String.format("   %s: %s", LogType.INFO, formatted));
        printLog(formatted);
    }

    public void logStep(String message, Object... args) {
        String formatted = String.format(message, args);
        appHooks.scenario.log(String.format("%s (%s): %s", LogType.TEST_STEP, formatDate(ZonedDateTime.now()), formatted));
        printLog(formatted);
    }

    private void printLog(String message) {
        System.out.println(message);
    }
}
