package filehandling;

import java.io.Serializable;
import lombok.Builder;
import seasonlogic.Season;

@Builder
public class SaveData implements Serializable {

  private final Season season;

  public Season getSeason() {
    return season;
  }
}
