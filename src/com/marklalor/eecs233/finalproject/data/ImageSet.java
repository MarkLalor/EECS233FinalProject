package com.marklalor.eecs233.finalproject.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSet
{
    private final String name;
    private final File directory;
    
    private static final String INPUT_FILENAME = "in.png";
    private static final String OUTPUT_FILENAME = "out.png";
    
    private LoadedImage input = null;
    private LoadedImage output = null;
    
    private BufferedImage result;
    
    public ImageSet(String name, File directory)
    {
        this.name = name;
        this.directory = directory;
    }
    
    public String getName()
    {
        return name;
    }
    
    // Only load on demand...
    
    public LoadedImage getInput()
    {
        if(this.input == null)
        {
            this.input = loadImage(new File(directory, INPUT_FILENAME));
            this.result = new BufferedImage(this.input.getImage().getWidth(), this.input.getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        return input;
    }
    
    public LoadedImage getOutput()
    {
        if(this.output == null)
            this.output = loadImage(new File(directory, OUTPUT_FILENAME));
        return output;
    }
    
    public BufferedImage getResult()
    {
        return result;
    }
    
    public File getDirectory()
    {
        return directory;
    }
    
    private static LoadedImage loadImage(File paletteFile)
    {
        try
        {
            return new LoadedImage(paletteFile);
        }
        catch(IOException e)
        {
            System.out.println("Image " + paletteFile.getAbsolutePath() + " does not exist! Terminating.");
            System.exit(1);
        }
        
        return null;
    }
    
    public void resetResult()
    {
        this.result = new BufferedImage(this.input.getImage().getWidth(), this.input.getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
    }
}
