package christmas.constants;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String badge;
    private final int minimum;

    Badge(String badge, int minimum) {
        this.badge = badge;
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        return badge;
    }

    public int getMinimum() {
        return minimum;
    }
}
