package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestForPoint {
    @Test
    public void testArea(){
        Point p1 = new Point(3,5,4,6);
        Point p2 = new Point(10,6,4,18);
        Assert.assertEquals(p1.distance(), 2.8284271247461903);
        Assert.assertEquals(p2.distance(), 14.560219778561036);
    }

}
