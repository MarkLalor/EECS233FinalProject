package com.marklalor.eecs233.finalproject.algorithm.implementation;

import java.util.Collections;

import com.marklalor.eecs233.finalproject.algorithm.ProjectAlgorithmBase;
import com.marklalor.eecs233.finalproject.data.ImageSet;

public class BinarySearchAlgorithm extends ProjectAlgorithmBase
{
    public BinarySearchAlgorithm(ImageSet imageSet)
    {
        super(imageSet);
    }
    
    /**
     * Sort the possible input colors so that we can perform a binary search
     * instead.
     */
    @Override
    protected void getInputColorsIntegerArray()
    {
        super.getInputColorsIntegerArray();
        Collections.sort(this.getInputColors());
    }
    
    @Override
    public int getClosestIndex(int colorOnOutputImage)
    {
        int result = Collections.binarySearch(this.getInputColors(), colorOnOutputImage);
        
        if(result > 0)
            return result;
        else
        {
            result = -result - 2;
            if(result != -1)
            {
                if(result == this.getInputColors().size() - 1)
                    return result;
                
                if(Math.abs(this.getInputColors().get(result) - colorOnOutputImage) < (this.getInputColors().get(result + 1) - colorOnOutputImage))
                    return result;
                else
                    return result + 1;
            }
            else
                return 0;
        }
    }
    
    @Override
    public String getName()
    {
        return "BinarySearch";
    }
}
