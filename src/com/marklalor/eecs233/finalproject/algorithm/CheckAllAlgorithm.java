package com.marklalor.eecs233.finalproject.algorithm;

import com.marklalor.eecs233.finalproject.data.ImageSet;

public class CheckAllAlgorithm extends CreationAlgorithmBase
{
    public CheckAllAlgorithm(ImageSet imageSet)
    {
        super(imageSet);
    }
    
    @Override
    public int getClosestIndex(int colorOnOutputImage)
    {
        int closestIndex = 0;
        int closestDifference = Integer.MAX_VALUE;
        
        for(int i = 0; i < getInputColors().size(); i++)
        {
            int testColor = getInputColors().get(i);
            int difference = Math.abs(testColor - colorOnOutputImage);
            if(difference <= closestDifference)
            {
                closestDifference = difference;
                closestIndex = i;
            }
        }
        
        return closestIndex;
    }
    
    @Override
    public String getName()
    {
        return "CheckAll";
    }
    
}
