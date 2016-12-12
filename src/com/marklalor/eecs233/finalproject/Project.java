package com.marklalor.eecs233.finalproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import com.marklalor.eecs233.finalproject.project.ProjectInput;
import com.marklalor.eecs233.finalproject.project.ProjectLogic;
import com.marklalor.eecs233.finalproject.project.ProjectOutput;

public class Project
{
    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.err.println("Must pass 3 inputs. An base directory (with an \"inputs\" folder)" +
                    "an image set name (a subdirectory of inputs folder), " +
                    "and a list of folders within the input folder from which to extract input/output image pairs.");
            System.exit(1);
        }
        
        String baseDirectory = args[0];
        String setName = args[1];
        String inputsRaw = args[2];
        String[] inputs = inputsRaw.split(",");
        
        // ProjectInput.createScaledImages(new File(baseDirectory), 20);
        // System.exit(0);
        
        ProjectInput inputManager = new ProjectInput(new File(baseDirectory, "inputs"), setName, inputs);
        
        ProjectOutput outputManager = new ProjectOutput();
        
        ProjectLogic logicManager = new ProjectLogic(inputManager, outputManager);
        logicManager.run();
        
        try
        {
            saveRuntimes(new File(baseDirectory, "runtimes"), logicManager.getRuntimes());
        }
        catch(IOException e)
        {
            System.err.println("Could not save runtimes to csv files!");
        }
        
        System.out.println("Complete.");
        System.exit(0);
    }
    
    private static void saveRuntimes(File file, Map<String, Map<Integer, Double>> runtimes) throws IOException
    {
        for(String algorithm : runtimes.keySet())
        {
            File saveFile = new File(file, algorithm + ".csv");
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
            
            StringBuilder output = new StringBuilder();
            Map<Integer, Double> algorithmRuntime = runtimes.get(algorithm);
            for(Entry<Integer, Double> entry : algorithmRuntime.entrySet())
            {
                output.append(entry.getKey());
                output.append(",");
                output.append(entry.getValue());
                output.append("\n");
            }
            
            FileOutputStream outputStream = new FileOutputStream(saveFile, false);
            outputStream.write(output.toString().getBytes());
            outputStream.close();
        }
    }
}
