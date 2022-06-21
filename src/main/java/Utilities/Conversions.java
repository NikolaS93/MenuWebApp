package Utilities;

public class Conversions {

    public static double convert(String input) {
        input = input.replace(',', '.');
        int decimalSeperator = input.lastIndexOf('.');

        if (decimalSeperator > -1) {
            input = input.substring(0, decimalSeperator).replace(".", "") + input.substring(decimalSeperator);
        }

        return Double.parseDouble(input);
    }

}
