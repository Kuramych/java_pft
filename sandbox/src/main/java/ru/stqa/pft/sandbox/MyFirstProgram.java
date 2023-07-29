package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Seva");

        double len = 5;
        System.out.println("Площадь квадрата со стороной " + len + " = " + area(len));

        double a = 5;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a,b));
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double l){
        return l * l;
    }

    public static double area(double a, double b){
        return a * b;
    }
}
