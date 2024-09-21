package christmas.dto;

import java.util.List;

public record OrderResponse(List<String> names, List<Integer> quantities) {
    public int size() {
        return names.size();
    }

    public String getName(int index) {
        return names.get(index);
    }

    public int getQuantity(int index) {
        return quantities.get(index);
    }
}