package christmas.constants;

public enum Badge {
    SANTA("산타"),
    TREE("트리"),
    STAR("별"),
    NONE("없음");

    private final String badge;

    Badge(String badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        return badge;
    }
}
