package utils.uiCommonComponents.scrollMethods;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.driverUtils.SeleniumUtils;

import java.time.Duration;
import java.util.List;

public class ScrollingMethods extends SeleniumUtils {
    private static final Logger logger = LogManager.getLogger(ScrollingMethods.class);

    private final PointerInput finger;

    public ScrollingMethods(AppiumDriver driver) {
        super(driver);
        this.finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    }

    public void swipeUp(Point startPoint, Point endPoint, Duration duration) {
        swipe(startPoint, endPoint, duration);
    }

    public void swipeDown(Point startPoint, Point endPoint, Duration duration) {
        swipe(startPoint, endPoint, duration);
    }

    public void swipeHorizontal(Point startPoint, Point endPoint, Duration duration) {
        swipe(startPoint, endPoint, duration);
    }

    private void swipe(Point start, Point end, Duration duration) {
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration,
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }

    public void tapOnElementUsingCoordinates(Point tapPoint) {
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }
}
