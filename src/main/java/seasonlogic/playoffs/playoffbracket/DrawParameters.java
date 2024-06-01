package seasonlogic.playoffs.playoffbracket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
class DrawParameters {

  private int x;
  private int y;
  private int rectangleXSide;
  private int rectangleYSide;
}
