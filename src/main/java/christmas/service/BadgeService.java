package christmas.service;

import christmas.constants.Badge;

public class BadgeService {
    private static BadgeService instance;

    private BadgeService() {
    }

    public static BadgeService getInstance() {
        if (instance == null) {
            instance = new BadgeService();
        }
        return instance;
    }

    public Badge calculateBadge(int totalDiscount) {
        if (totalDiscount >= 20_000) {
            return Badge.SANTA;
        }
        if (totalDiscount >= 10_000) {
            return Badge.TREE;
        }
        if (totalDiscount >= 5_000) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }
}
