package christmas.dto;

import christmas.constants.Badge;

public record BadgeResponse(String badgeName) {
    public static BadgeResponse of(Badge badge) {
        return new BadgeResponse(badge.name());
    }
}
