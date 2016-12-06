package com.marklalor.eecs233.finalproject.project;

import java.util.HashMap;
import java.util.Map;

import com.marklalor.eecs233.finalproject.algorithm.GeneralProjectAlgorithm;
import com.marklalor.eecs233.finalproject.data.ImageSet;
import com.marklalor.eecs233.finalproject.manager.InputManager;
import com.marklalor.eecs233.finalproject.manager.LogicManager;
import com.marklalor.eecs233.finalproject.manager.OutputManager;

public class ProjectLogic extends LogicManager
{
    private Map<String, Map<Integer, Double>> runtimes;
    
    public ProjectLogic(InputManager inputManager, OutputManager outputManager)
    {
        super(inputManager, outputManager);
        
        runtimes = new HashMap<>();
    }
    
    @Override
    public void run(ImageSet imageSet, GeneralProjectAlgorithm algorithm)
    {
        imageSet.resetResult();
        getOutputManager().initiate(imageSet);
        
        algorithm.begin();
        while(!algorithm.isComplete())
        {
            algorithm.tick();
            getOutputManager().update();
        }
        
        double seconds = (double) algorithm.nanoRuntime() / 1000000000.0;
        
        System.out.println(algorithm.getName() + ": run " + imageSet.getName() + ": " + seconds + " s.");
        
        if(runtimes.containsKey(algorithm.getName()))
        {
            Map<Integer, Double> map = runtimes.get(algorithm.getName());
            map.put(Integer.valueOf(imageSet.getName()), seconds);
        }
        else
        {
            Map<Integer, Double> map = new HashMap<>();
            map.put(Integer.valueOf(imageSet.getName()), seconds);
            runtimes.put(algorithm.getName(), map);
        }
        
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
        getOutputManager().complete();
    }
}
