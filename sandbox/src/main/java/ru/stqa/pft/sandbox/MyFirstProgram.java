package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Seva");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p = new Point(10,6,4,18);
        System.out.println("Расстояние между двумя точками с координатами " + p.x1 + "," + p.y1 + " и " + p.x2 + "," + p.y2 + " = " + p.distance());
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }



}
