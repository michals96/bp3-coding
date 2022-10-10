import com.bp3.service.ProcessService;
import java.io.IOException;

public class App {
  private static final ProcessService processService = new ProcessService();

  public static void main(String[] args) throws IOException {
    final var pathToFile = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\1-simple-process.json";
    //final var pathToFile = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\2-multiple-human-services.json";
    //final var pathToFile = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\3-branching-process.json";
    //final var pathToFile = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\4-recursive-branching-process.json";
    final var saveFilePath = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\parsed.json";

    processService.reduce(pathToFile, saveFilePath);
  }
}
