// i would typically use a record class to store these data but those are probably beyond ch 8
// one round of the game contains the player's name, the outcome of that round, and the list of dice rolls in that round
public class Round {
    private final String player;
    private final GameOutcome outcome;
    private final Roll[] rolls;

    // no default constructor because we add the Round to rounds after the round is over
    public Round(String user, GameOutcome outcome, Roll[] rolls) {
        this.player = user;
        this.outcome = outcome;
        this.rolls = rolls;
    }

    public String getPlayer() {
        return this.player;
    }

    public GameOutcome getOutcome() {
        return this.outcome;
    }

    public Roll[] getRolls() {
        return this.rolls;
    }
}