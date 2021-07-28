package de.quatschvirus.essentialvirus.scoreboard;

import org.bukkit.entity.Player;

public class TestScoreboard extends ScoreboardBuilder{
    public TestScoreboard(Player player) {
        super(player, "---TestScoreboard---");
    }

    @Override
    public void createScoreboard() {
        setScore("ABC", 0);
    }

    @Override
    public void update() {

    }
}
