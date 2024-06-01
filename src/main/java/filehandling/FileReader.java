package filehandling;

import static common.Const.SAVE_FILE_NAME;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileReader {

  public static SaveData readFromFile() throws Exception {
    return (SaveData) new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME)).readObject();
  }

}
