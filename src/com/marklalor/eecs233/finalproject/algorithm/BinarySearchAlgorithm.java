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
    
    public int binarySearch(int low, int high, int k)
    {
        
        int mid = (low + high) / 2;
        
        if(low >= high)
        {
            return -1;
        }
        else if(getInputColors().get(mid) == k)
        {
            return getInputColors().get(mid);
        }
        else if(getInputColors().get(mid) < k)
        {
            return binarySearch(mid + 1, high, k);
        }
        else
        {
            return binarySearch(low, mid - 1, k);
        }
    }
    
    @Override
    public int getClosestIndex(int colorOnOutputImage)
    {
        int low = 0;
        int high = getInputColors().size() - 1;
        
        while(low < high)
        {
            int mid = (low + high) / 2;
            
            int difference1 = Math.abs(getInputColors().get(mid) - colorOnOutputImage);
            int difference2 = Math.abs(getInputColors().get(mid + 1) - colorOnOutputImage);
            
            if(difference2 < difference1)
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }
        
        return high;
    }
    
    @Override
    public String getName()
    {
        return "BinarySearch";
    }
    
}
