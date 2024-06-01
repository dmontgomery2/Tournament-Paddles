package seasonlogic.models;

import java.io.Serializable;

public class Human extends PlayerProfile implements Serializable {

  public Human(String name) {
    super(name, -1);
  }
}
