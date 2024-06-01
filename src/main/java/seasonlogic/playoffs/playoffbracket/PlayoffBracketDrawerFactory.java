package seasonlogic.playoffs.playoffbracket;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.List;
import java.util.Map;
import seasonlogic.playoffs.PlayoffSeries;
import seasonlogic.playoffs.playoffbracket.PlayoffBracketDrawer;

class PlayoffBracketDrawerFactory {

  // one round properties
  private static final int ONE_ROUND_INITIAL_Y = 125;
  private static final int ONE_ROUND_INITIAL_RECTANGLE_X_SIDE = 250;
  private static final int ONE_ROUND_INITIAL_RECTANGLE_Y_SIDE = 250;
  private static final float ONE_ROUND_SCALING_RATE_X = 1f;

  // two round properties
  private static final int TWO_ROUND_INITIAL_Y = 75;
  private static final int TWO_ROUND_INITIAL_RECTANGLE_X_SIDE = 120;
  private static final int TWO_ROUND_INITIAL_RECTANGLE_Y_SIDE = 120;
  private static final float TWO_ROUND_SCALING_RATE_X = 1.3f;

  // three round properties
  private static final int THREE_ROUND_INITIAL_Y = 55;
  private static final int THREE_ROUND_INITIAL_RECTANGLE_X_SIDE = 100;
  private static final int THREE_ROUND_INITIAL_RECTANGLE_Y_SIDE = 58;
  private static final float THREE_ROUND_SCALING_RATE_X = 1.1f;

  private static final PlayoffBracketDrawer.PlayoffBracketDrawerBuilder ONE_ROUND_BUILDER = PlayoffBracketDrawer.builder()
      .initialY(ONE_ROUND_INITIAL_Y)
      .initialRectangleXSide(ONE_ROUND_INITIAL_RECTANGLE_X_SIDE)
      .initialRectangleYSide(ONE_ROUND_INITIAL_RECTANGLE_Y_SIDE)
      .scalingRateX(ONE_ROUND_SCALING_RATE_X);
  private static final PlayoffBracketDrawer.PlayoffBracketDrawerBuilder TWO_ROUND_BUILDER = PlayoffBracketDrawer.builder()
      .initialY(TWO_ROUND_INITIAL_Y)
      .initialRectangleXSide(TWO_ROUND_INITIAL_RECTANGLE_X_SIDE)
      .initialRectangleYSide(TWO_ROUND_INITIAL_RECTANGLE_Y_SIDE)
      .scalingRateX(TWO_ROUND_SCALING_RATE_X);
  private static final PlayoffBracketDrawer.PlayoffBracketDrawerBuilder THREE_ROUND_BUILDER = PlayoffBracketDrawer.builder()
      .initialY(THREE_ROUND_INITIAL_Y)
      .initialRectangleXSide(THREE_ROUND_INITIAL_RECTANGLE_X_SIDE)
      .initialRectangleYSide(THREE_ROUND_INITIAL_RECTANGLE_Y_SIDE)
      .scalingRateX(THREE_ROUND_SCALING_RATE_X);
  private static final Map<Integer, PlayoffBracketDrawer.PlayoffBracketDrawerBuilder> NUM_ROUNDS_TO_BUILDER = ofEntries(
      entry(1, ONE_ROUND_BUILDER),
      entry(2, TWO_ROUND_BUILDER),
      entry(3, THREE_ROUND_BUILDER)
  );


  public static PlayoffBracketDrawer getPlayoffBracketDrawer(
      List<List<PlayoffSeries>> playoffSeries) {
    return NUM_ROUNDS_TO_BUILDER.get(playoffSeries.size())
        .playoffSeries(playoffSeries)
        .build();
  }

}
