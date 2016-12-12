package com.marklalor.eecs233.finalproject.project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.marklalor.eecs233.finalproject.algorithm.GeneralProjectAlgorithm;
import com.marklalor.eecs233.finalproject.algorithm.implementation.BinarySearchAlgorithm;
import com.marklalor.eecs233.finalproject.algorithm.implementation.CheckAllAlgorithm;
import com.marklalor.eecs233.finalproject.data.ImageSet;

public class ProjectLogic
{
    private ProjectInput input;
    private ProjectOutput output;
    private Map<String, Map<Integer, Double>> runtimes;
    
    public ProjectLogic(ProjectInput inputManager, ProjectOutput outputManager)
    {
        this.input = inputManager;
        this.output = outputManager;
        this.runtimes = new HashMap<>();
    }
    
    public void run()
    {
        for(ImageSet set : getInput().getImageSets())
            this.run(set, new CheckAllAlgorithm(set));
        
        for(ImageSet set : getInput().getImageSets())
            this.run(set, new BinarySearchAlgorithm(set));
        
    }
    
    public void run(ImageSet imageSet, GeneralProjectAlgorithm algorithm)
    {
        imageSet.resetResult();
        getOutput().initiate(imageSet);
        
        algorithm.begin();
        while(!algorithm.isComplete())
        {
            algorithm.tick();
            getOutput().update();
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
        
        getOutput().complete();
    }
    
    public ProjectInput getInput()
    {
        return input;
    }
    
    public ProjectOutput getOutput()
    {
        return output;
    }
    
    public Map<String, Map<Integer, Double>> getRuntimes()
    {
        return runtimes;
    }
}
