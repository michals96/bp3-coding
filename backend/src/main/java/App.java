import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static Logger logger = LoggerFactory.getLogger(App.class);

  public String getGreeting() {
    return "Hello world.";
  }

  public static void main(String[] args) {
    logger.info(new App().getGreeting());
  }
}
