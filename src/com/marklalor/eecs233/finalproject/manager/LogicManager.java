package com.marklalor.eecs233.finalproject.manager;

import com.marklalor.eecs233.finalproject.algorithm.BinarySearchAlgorithm;
import com.marklalor.eecs233.finalproject.algorithm.CheckAllAlgorithm;
import com.marklalor.eecs233.finalproject.algorithm.GeneralProjectAlgorithm;
import com.marklalor.eecs233.finalproject.data.ImageSet;

public abstract class LogicManager
{
    private InputManager inputManager;
    private OutputManager outputManager;
    
    public LogicManager(InputManager inputManager, OutputManager outputManager)
    {
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }
    
    public InputManager getInputManager()
    {
        return inputManager;
    }
    
    public OutputManager getOutputManager()
    {
        return outputManager;
    }
    
    public void run()
    {
        for(ImageSet set : getInputManager().getImageSets())
            this.run(set, new CheckAllAlgorithm(set));
        
        for(ImageSet set : getInputManager().getImageSets())
            this.run(set, new BinarySearchAlgorithm(set));
    }
    
    public abstract void run(ImageSet imageSet, GeneralProjectAlgorithm algorithm);
}
