import com.bp3.file.FileLoaderService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static Logger logger = LoggerFactory.getLogger(App.class);
  private static final FileLoaderService loaderService = new FileLoaderService();

  public String getGreeting() {
    return "Hello world.";
  }

  public static void main(String[] args) throws IOException {
    loaderService.process();
    logger.info(new App().getGreeting());
  }
}
