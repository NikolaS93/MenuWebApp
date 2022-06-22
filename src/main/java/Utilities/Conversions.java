package Utilities;

public class Conversions {

    public static double convert(String input) {

        return Double.parseDouble(input.replace(",", ""));
    }

}
