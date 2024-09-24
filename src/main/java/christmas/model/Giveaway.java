package christmas.model;

public class Giveaway {
    private final boolean giveawayStatus;

    private Giveaway(boolean giveawayStatus) {
        this.giveawayStatus = giveawayStatus;
    }

    public static Giveaway of(boolean giveawayStatus) {
        return new Giveaway(giveawayStatus);
    }

    public boolean getGiveawayStatus() {
        return giveawayStatus;
    }
}
