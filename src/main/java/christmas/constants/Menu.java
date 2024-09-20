package christmas.constants;

import static christmas.constants.MenuType.APPETIZER;
import static christmas.constants.MenuType.DESSERT;
import static christmas.constants.MenuType.DRINK;
import static christmas.constants.MenuType.MAIN;

public enum Menu {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6000, APPETIZER.getType()),
    TAPAS("타파스", 5500, APPETIZER.getType()),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER.getType()),
    T_BONE_STEAK("티본스테이크", 55000, MAIN.getType()),
    BARBECUE_RIBS("바비큐립", 54000, MAIN.getType()),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN.getType()),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN.getType()),
    CHOCO_CAKE("초코케이크", 15000, DESSERT.getType()),
    ICE_CREAM("아이스크림", 5000, DESSERT.getType()),
    ZERO_COLA("제로콜라", 3000, DRINK.getType()),
    RED_WINE("레드와인", 60000, DRINK.getType()),
    CHAMPAGNE("샴페인", 25000, DRINK.getType());

    private final String name;
    private final int price;
    private final String type;

    Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
