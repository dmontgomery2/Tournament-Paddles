package seasonlogic.playoffs.brackets;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import common.TriFunction;
import java.util.List;
import java.util.Map;
import seasonlogic.calendar.Calendar;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffBracket;
import seasonlogic.playoffs.brackets.EightPlayerPlayoffBracket;
import seasonlogic.playoffs.brackets.FourPlayerPlayoffBracket;
import seasonlogic.playoffs.brackets.TwoPlayerPlayoffBracket;
import settings.SeasonSettings;

public class PlayoffBracketFactory {

  private static final Map<Integer,
      TriFunction<Calendar, List<PlayerProfile>, SeasonSettings, PlayoffBracket>>
      NUMBER_OF_PLAYERS_TO_BRACKET_CONSTRUCTOR = ofEntries(
      entry(2, TwoPlayerPlayoffBracket::new),
      entry(4, FourPlayerPlayoffBracket::new),
      entry(8, EightPlayerPlayoffBracket::new)
  );

  public static PlayoffBracket getPlayoffBracket(Calendar calendar,
      List<PlayerProfile> playoffParticipants, SeasonSettings settings) {
    return NUMBER_OF_PLAYERS_TO_BRACKET_CONSTRUCTOR.get(playoffParticipants.size())
        .apply(calendar, playoffParticipants, settings);
  }

}
