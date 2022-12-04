package seasonlogic.calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Matchups implements Iterable<Matchup> {

    private final List<Matchup> matchupList;

    public Matchups(){
        matchupList = new ArrayList<>();
    }

    @Override
    public Iterator<Matchup> iterator() {
        return matchupList.iterator();
    }

    public void add(Matchup matchup) {
        matchupList.add(matchup);
    }

    public Stream<Matchup> stream(){
        return matchupList.stream();
    }

    public void addAll(Matchups otherMatchups) {
        otherMatchups.forEach(matchup -> matchupList.add(matchup));
    }

    public void shuffle() {
        Collections.shuffle(matchupList);
    }

    public List<String> getResultsStrings() {
        return matchupList
                .stream()
                .map(Matchup::getResult)
                .collect(toList());
    }

    public List<String> getStrings() {
        return matchupList
                .stream()
                .map(Matchup::toString)
                .collect(toList());
    }
}
