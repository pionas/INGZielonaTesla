package info.pionas.ing.service;

import info.pionas.ing.model.onlinegame.Clan;
import info.pionas.ing.model.onlinegame.Group;
import info.pionas.ing.model.onlinegame.Order;
import info.pionas.ing.model.onlinegame.Players;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class OnlineGameService {

    public Order getOrder(Players players) {
        final var order = new Order();
        order.addAll(getGroups(players));
        return order;
    }

    private Collection<Group> getGroups(Players players) {
        final var groupCount = players.getGroupCount();
        List<Clan> clans = Optional.of(players)
                .stream()
                .map(Players::getClans)
                .flatMap(Collection::stream)
                .filter(haveSpace(groupCount))
                .sorted(clanComparator())
                .toList();
        return makeGroup(clans, groupCount);
    }

    private Predicate<Clan> haveSpace(int groupCount) {
        return clans -> clans.getNumberOfPlayers() <= groupCount;
    }

    private Comparator<Clan> clanComparator() {
        return Comparator.comparing(Clan::getPoints, Comparator.reverseOrder())
                .thenComparing(Clan::getNumberOfPlayers);
    }

    private Collection<Group> makeGroup(List<Clan> clans, int groupCount) {
        final var groups = new ArrayList<Group>();

        for (Clan clan : clans) {
            Group group = getGroupIndexForClan(groups, clan, groupCount);
            group.add(clan);
        }

        return groups;
    }

    private Group getGroupIndexForClan(ArrayList<Group> groups, Clan clan, int groupCount) {
        if (groups.isEmpty()) {
            groups.add(new Group());
            return groups.get(0);
        }
        for (Group group : groups) {
            if (groupHasFreeSpace(group, clan.getNumberOfPlayers(), groupCount)) {
                return group;
            }
        }
        groups.add(new Group());
        return groups.get(groups.size() - 1);
    }

    private boolean groupHasFreeSpace(Group group, Integer numberOfPlayers, int groupCount) {
        Integer playersOfGroup = group.stream().map(Clan::getNumberOfPlayers)
                .reduce(0, Integer::sum, Integer::sum);
        return (playersOfGroup + numberOfPlayers) <= groupCount;
    }
}
