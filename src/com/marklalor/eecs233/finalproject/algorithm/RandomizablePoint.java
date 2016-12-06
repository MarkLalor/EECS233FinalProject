package com.marklalor.eecs233.finalproject.algorithm;

import java.awt.Point;
import java.util.Random;

public class RandomizablePoint
{
    private Point point;
    private double weight;
    
    public RandomizablePoint(int x, int y, Random random, int factor)
    {
        this.point = new Point(x, y);
        this.weight = x + random.nextInt(factor) - factor / 2;
    }
    
    public Point getPoint()
    {
        return point;
    }
    
    public double getWeight()
    {
        return weight;
    }
}
