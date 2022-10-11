import com.bp3.service.ProcessService;
import java.io.IOException;
import java.util.List;

public class App {
  private static final ProcessService processService = new ProcessService();
  private static final String FILE_DIR = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\";
  private static final String SAVE_FILE_DIR = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\parsed\\";
  private static final List<String> FILES =
      List.of("1-simple-process.json", "2-multiple-human-services.json", "3-branching-process.json", "4-recursive-branching-process.json");

  public static void main(String[] args) {
    FILES.forEach(file -> {
      try {
        processService.reduce(FILE_DIR.concat(file), SAVE_FILE_DIR.concat(file));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
