package com.marklalor.eecs233.finalproject.algorithm;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.marklalor.eecs233.finalproject.data.ImageSet;

public abstract class CreationAlgorithmBase extends GeneralProjectAlgorithm
{
    private List<Integer> inputColors;
    private List<RandomizablePoint> outputPoints;
    
    private long seed = 1480999103459L;
    private final Random random;
    
    int n = 0;
    
    public CreationAlgorithmBase(ImageSet imageSet)
    {
        super(imageSet);
        
        this.random = new Random(seed);
        
        getInputColorsIntegerArray();
        getSortedOutputCoordinatesPointArray();
    }
    
    protected void getSortedOutputCoordinatesPointArray()
    {
        this.outputPoints = new ArrayList<>(getImageSet().getInput().getNumberOfPixels());
        
        for(int x = 0; x < getImageSet().getInput().getImage().getWidth(); x++)
            for(int y = 0; y < getImageSet().getInput().getImage().getHeight(); y++)
                this.outputPoints.add(
                        new RandomizablePoint(x, y, random, (int) Math.pow((getImageSet().getInput().getImage().getWidth() + getImageSet().getInput().getImage().getHeight()) / 2d, 1.5)));
            
        final Point2D origin = new Point(getImageSet().getInput().getImage().getWidth() / 2, getImageSet().getInput().getImage().getHeight() / 2);
        this.outputPoints.sort(new Comparator<RandomizablePoint>()
        {
            @Override
            public int compare(RandomizablePoint o1, RandomizablePoint o2)
            {
                return new Double(o1.getPoint().distanceSq(origin) + o1.getWeight()).compareTo(new Double(o2.getPoint().distanceSq(origin) + o2.getWeight()));
            }
        });
    }
    
    protected void getInputColorsIntegerArray()
    {
        this.inputColors = new ArrayList<>(getImageSet().getInput().getNumberOfPixels());
        
        for(int x = 0; x < getImageSet().getInput().getImage().getWidth(); x++)
            for(int y = 0; y < getImageSet().getInput().getImage().getHeight(); y++)
                this.inputColors.add(getImageSet().getInput().getImage().getRGB(x, y));
    }
    
    public List<Integer> getInputColors()
    {
        return inputColors;
    }
    
    public List<RandomizablePoint> getOutputPoints()
    {
        return outputPoints;
    }
    
    // Done N times
    // So, e.g. for binary.. we do [log(n) and N(?)] N times.
    // but the N on the inside decreases 1 per tick.
    @Override
    public void tick()
    {
        Point point = this.outputPoints.remove(0).getPoint();
        
        // cached on load: O(1)
        int[][] pixels = getImageSet().getOutput().getPixels();
        
        int colorOnOutputImage = pixels[point.y][point.x];
        
        // abstract method up to implementation of algorithm:
        int closestIndex = getClosestIndex(colorOnOutputImage);
        
        // N time&decreases over time: "shifts subsequent elements to the left".
        int selectedColor = this.inputColors.remove(closestIndex);
        
        // Presumably O(1).
        this.getImageSet().getResult().setRGB(point.x, point.y, selectedColor);
        
        // O(1)
        if(this.outputPoints.isEmpty())
            complete();
        
        n++;
    }
    
    public abstract int getClosestIndex(int colorOnOutputImage);
}
