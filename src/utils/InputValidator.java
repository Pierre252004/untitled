package src.utils;

public class InputValidator {
    public static boolean isValidHotelCategory(int category) {
        return category >= 1 && category <= 5;
    }

    public static boolean isValidRoomCapacity(String capacity) {
        return capacity.equals("Single") || capacity.equals("Double") || capacity.equals("Suite");
    }
}