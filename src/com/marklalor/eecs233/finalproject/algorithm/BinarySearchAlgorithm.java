package com.marklalor.eecs233.finalproject.algorithm;

import java.util.Collections;

import com.marklalor.eecs233.finalproject.data.ImageSet;

public class BinarySearchAlgorithm extends CreationAlgorithmBase
{
    public BinarySearchAlgorithm(ImageSet imageSet)
    {
        super(imageSet);
    }
    
    @Override
    /**
     * Sort the possible input colors so that we can perform a binary search
     * instead.
     */
    protected void getInputColorsIntegerArray()
    {
        super.getInputColorsIntegerArray();
        Collections.sort(this.getInputColors());
    }
    
    public static int getClosestK(int[] a, int x)
    {
        int idx = java.util.Arrays.binarySearch(a, x);
        if(idx < 0)
        {
            idx = -idx - 1;
        }
        
        if(idx == 0)
        { // littler than any
            return a[idx];
        }
        else if(idx == a.length)
        { // greater than any
            return a[idx - 1];
        }
        
        return Math.abs(x - a[idx - 1]) < Math.abs(x - a[idx]) ? a[idx - 1] : a[idx];
    }
    
    @Override
    public int getClosestIndex(int colorOnOutputImage)
    {
        int low = 0;
        int high = getInputColors().size() - 1;
        
        int mid = 0, lastValue;
        while(low <= high)
        {
            mid = (low + high) / 2;
            lastValue = getInputColors().get(mid);
            if(colorOnOutputImage < lastValue)
            {
                high = mid - 1;
            }
            else if(colorOnOutputImage > lastValue)
            {
                low = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        
        return mid;
    }
    
    @Override
    public String getName()
    {
        return "BinarySearch";
    }
    
}
