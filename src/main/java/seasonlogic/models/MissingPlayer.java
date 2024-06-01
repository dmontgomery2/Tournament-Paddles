package seasonlogic.models;

import java.io.Serializable;

public class MissingPlayer extends PlayerProfile implements Serializable {

  public MissingPlayer() {
    super("", -1);
  }
}
