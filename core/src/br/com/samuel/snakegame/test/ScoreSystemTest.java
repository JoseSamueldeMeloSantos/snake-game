package br.com.samuel.snakegame.test;

import br.com.samuel.snakegame.entities.Score;
import br.com.samuel.snakegame.model.ScoreSystem;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreSystemTest {
    private ScoreSystem scoreSystem;

    @Before
    public void setUp() {
        scoreSystem = new ScoreSystem();
    }

    @Test
    public void testAddScore() {
        Score score1 = new Score("John", 100);
        Score score2 = new Score("Alice", 200);

        scoreSystem.addScore(score1);
        scoreSystem.addScore(score2);

        List<Score> scores = scoreSystem.getScores();

        assertEquals(2, scores.size());
        assertEquals(score2, scores.get(0));  // Highest score first
        assertEquals(score1, scores.get(1));
    }

    @Test
    public void testRemoveLastScore() {
        Score score1 = new Score("John", 100);
        Score score2 = new Score("Alice", 200);

        scoreSystem.addScore(score1);
        scoreSystem.addScore(score2);

        scoreSystem.removeLastScore();

        List<Score> scores = scoreSystem.getScores();

        assertEquals(1, scores.size());
        assertEquals(score1, scores.get(0));  // Only the first score remains
    }

    @Test
    public void testResearchValueByName() {
        Score score1 = new Score("John", 100);
        Score score2 = new Score("Alice", 200);

        scoreSystem.addScore(score1);
        scoreSystem.addScore(score2);

        Score foundScore = scoreSystem.reseachValueByName("Alice");
        assertNotNull(foundScore);
        assertEquals(score2, foundScore);
    }

    @Test
    public void testCheckQuantity() {
        Score score1 = new Score("John", 100);
        Score score2 = new Score("Alice", 200);

        scoreSystem.addScore(score1);
        scoreSystem.addScore(score2);

        int quantity = scoreSystem.checkQuantity();
        assertEquals(2, quantity);
    }

    @Test
    public void testCheckLimit() {
        Score score1 = new Score("John", 100);
        Score score2 = new Score("Alice", 200);
        Score score3 = new Score("Bob", 300);

        scoreSystem.addScore(score1);
        scoreSystem.addScore(score2);
        scoreSystem.addScore(score3);

        boolean isWithinLimit = scoreSystem.checkLimit();
        assertTrue(isWithinLimit);
    }

    @Test
    public void testCheckLimitExceeded() {
        Score score1 = new Score("John", 100);
        Score score2 = new Score("Alice", 200);
        Score score3 = new Score("Bob", 300);
        Score score4 = new Score("Eve", 400);
        Score score5 = new Score("Mallory", 500);
        Score score6 = new Score("Oscar", 600);

        scoreSystem.addScore(score1);
        scoreSystem.addScore(score2);
        scoreSystem.addScore(score3);
        scoreSystem.addScore(score4);
        scoreSystem.addScore(score5);

        boolean isWithinLimit = scoreSystem.checkLimit();
        assertFalse(isWithinLimit);  // The limit is exceeded
    }
}
