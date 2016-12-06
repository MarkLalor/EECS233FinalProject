package com.marklalor.eecs233.finalproject;

import java.io.File;

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
        
        System.out.println("Creating InputManager.");
        ProjectInput inputManager = new ProjectInput(new File(baseDirectory, "inputs"), setName, inputs);
        
        System.out.println("Creating OutputManager.");
        ProjectOutput outputManager = new ProjectOutput();
        
        System.out.println("Creating LogicManager.");
        ProjectLogic logicManager = new ProjectLogic(inputManager, outputManager);
        logicManager.run();
        
        System.out.println("Complete.");
        System.exit(0);
    }
}
