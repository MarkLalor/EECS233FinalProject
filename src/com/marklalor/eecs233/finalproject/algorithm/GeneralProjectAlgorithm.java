package com.marklalor.eecs233.finalproject.algorithm;

import com.marklalor.eecs233.finalproject.data.ImageSet;

public abstract class GeneralProjectAlgorithm
{
    private String name;
    private ImageSet imageSet;
    private boolean complete = false;
    
    private long startTime, endTime;
    
    public GeneralProjectAlgorithm(ImageSet imageSet)
    {
        this.imageSet = imageSet;
    }
    
    public abstract String getName();
    
    public ImageSet getImageSet()
    {
        return imageSet;
    }
    
    public void begin()
    {
        this.startTime = System.nanoTime();
    }
    
    public abstract void tick();
    
    /**
     * Marks this algorithm as done, having run its full course. The
     * LogicManager will not send call tick.
     */
    public void complete()
    {
        this.endTime = System.nanoTime();
        this.complete = true;
    }
    
    /**
     * @return whether or not the algorithm has finished running.
     */
    public boolean isComplete()
    {
        return complete;
    }
    
    public long nanoRuntime()
    {
        if(!isComplete())
            throw new RuntimeException("Cannot check the time it took for the algorithm to run before it completes!");
        else
            return this.endTime - this.startTime;
    }
}
