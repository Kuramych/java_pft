package ru.stqa.pft.sandbox;

import java.util.Objects;

public class Equality {
    public static void main(String[] args) {
        String s1 = "chrome";
        String s2 = new String(s1);
        System.out.println(s1 == s2);
        System.out.println(Objects.equals(s1, s2));
    }
}
