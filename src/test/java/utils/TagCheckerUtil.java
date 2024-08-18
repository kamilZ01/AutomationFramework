package utils;

import io.cucumber.java.Scenario;

import java.util.Collection;

public class TagCheckerUtil {
    private static final String TEST_API_TAG = "@TestAPI";

    public boolean hasTestAPITag(Scenario scenario) {
        Collection<String> tags = scenario.getSourceTagNames();
        return tags.contains(TEST_API_TAG);
    }

}
