package de.quatschvirus.essentialvirus.utils;

import java.util.HashMap;
import java.util.Map;

public class IntConverter {
    private static final Map<Integer, String> ROMAN_DIGITS = new HashMap<>();

    public static void init() {
        ROMAN_DIGITS.put(1000, "M");
        ROMAN_DIGITS.put(900, "CM");
        ROMAN_DIGITS.put(500, "D");
        ROMAN_DIGITS.put(400, "CD");
        ROMAN_DIGITS.put(100, "C");
        ROMAN_DIGITS.put(90, "XC");
        ROMAN_DIGITS.put(50, "L");
        ROMAN_DIGITS.put(40, "XL");
        ROMAN_DIGITS.put(10, "X");
        ROMAN_DIGITS.put(9, "IX");
        ROMAN_DIGITS.put(5, "V");
        ROMAN_DIGITS.put(4, "IV");
        ROMAN_DIGITS.put(1, "I");
    }
    public static String toRoman(int n) {
        System.out.println(ROMAN_DIGITS.entrySet());
        StringBuilder out = new StringBuilder();
        for (Map.Entry<Integer, String> e : ROMAN_DIGITS.entrySet()) {
            while (n >= e.getKey()) {
                n = n - e.getKey();
                out.append(e.getValue());
            }
        }
        return out.toString();
    }
}
