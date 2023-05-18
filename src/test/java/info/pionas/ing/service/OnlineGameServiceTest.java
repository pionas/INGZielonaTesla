package info.pionas.ing.service;

import info.pionas.ing.model.onlinegame.Clan;
import info.pionas.ing.model.onlinegame.Group;
import info.pionas.ing.model.onlinegame.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OnlineGameServiceTest {

    private final OnlineGameService service = new OnlineGameService();

    @Test
    void getOrder() {
        // given
        // when
        final var order = service.getOrder(getPlayers());
        //then
        Assertions.assertThat(order).hasSize(5);
        Assertions.assertThat(order.get(0)).isEqualTo(getGroup(getClan(2, 70), getClan(4, 50)));
        Assertions.assertThat(order.get(1)).isEqualTo(getGroup(getClan(6, 60)));
        Assertions.assertThat(order.get(2)).isEqualTo(getGroup(getClan(3, 45), getClan(1, 15), getClan(1, 12)));
        Assertions.assertThat(order.get(3)).isEqualTo(getGroup(getClan(4, 40)));
        Assertions.assertThat(order.get(4)).isEqualTo(getGroup(getClan(5, 40)));
    }

    private Group getGroup(Clan... clans) {
        final var group = new Group();
        group.addAll(List.of(clans));
        return group;
    }

    private Players getPlayers() {
        final var players = new Players();
        players.groupCount(6);
        players.addClansItem(getClan(4, 50));
        players.addClansItem(getClan(2, 70));
        players.addClansItem(getClan(6, 60));
        players.addClansItem(getClan(1, 15));
        players.addClansItem(getClan(5, 40));
        players.addClansItem(getClan(3, 45));
        players.addClansItem(getClan(1, 12));
        players.addClansItem(getClan(4, 40));
        return players;
    }

    private Clan getClan(int numberOfPlayers, int points) {
        return new Clan()
                .numberOfPlayers(numberOfPlayers)
                .points(points);
    }
}