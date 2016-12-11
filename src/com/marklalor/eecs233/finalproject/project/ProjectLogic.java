package com.marklalor.eecs233.finalproject.project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

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
        
        BufferedImage result = algorithm.getImageSet().getResult();
        File resultFile = new File(new File(algorithm.getImageSet().getDirectory(), "results"), algorithm.getName() + ".png");
        try
        {
            resultFile.mkdirs();
            resultFile.createNewFile();
            ImageIO.write(result, "PNG", resultFile);
        }
        catch(IOException e)
        {
            System.err.println("Could not create result file " + resultFile.getAbsolutePath());
            e.printStackTrace();
        }
        
        double milliseconds = (double) algorithm.nanoRuntime() / 1000000.0;
        
        System.out.println(algorithm.getName() + ": run " + imageSet.getName() + ": " + milliseconds + " ms.");
        
        if(runtimes.containsKey(algorithm.getName()))
        {
            Map<Integer, Double> map = runtimes.get(algorithm.getName());
            map.put(Integer.valueOf(imageSet.getInput().getNumberOfPixels()), milliseconds);
        }
        else
        {
            Map<Integer, Double> map = new HashMap<>();
            map.put(imageSet.getInput().getNumberOfPixels(), milliseconds);
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
    
    public Map<String, Map<Integer, Double>> getRuntimes()
    {
        return runtimes;
    }
}
