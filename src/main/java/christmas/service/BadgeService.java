package christmas.service;

import christmas.constants.Badge;

import static christmas.constants.Badge.NONE;
import static christmas.constants.Badge.SANTA;
import static christmas.constants.Badge.STAR;
import static christmas.constants.Badge.TREE;

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
        if (totalDiscount >= SANTA.getMinimum()) {
            return SANTA;
        }
        if (totalDiscount >= TREE.getMinimum()) {
            return TREE;
        }
        if (totalDiscount >= STAR.getMinimum()) {
            return STAR;
        }
        return NONE;
    }
}
