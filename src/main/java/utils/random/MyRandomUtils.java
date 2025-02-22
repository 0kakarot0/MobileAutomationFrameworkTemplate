package utils.random;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.WebElement;

public class MyRandomUtils {
    private static final Random RANDOM = new Random();

    public static int getRandomNumber(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    public static WebElement getRandomElement(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Element list is empty or null");
        }
        return elements.get(getRandomNumber(0, elements.size() - 1));
    }
}
