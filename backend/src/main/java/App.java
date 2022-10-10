import com.bp3.file.FileService;
import java.io.IOException;

public class App {
  private static final FileService loaderService = new FileService();

  public static void main(String[] args) throws IOException {
    final var pathToFile = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\1-simple-process.json";
    final var saveFilePath = "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\1-simple-process-parsed.json";

    loaderService.execute(pathToFile, saveFilePath);
  }
}
