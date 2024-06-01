package filehandling;

import static common.Const.SAVE_FILE_NAME;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileWriter {


  public static void writeToFile(SaveData saveData) {
    try {
      new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME)).writeObject(saveData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
