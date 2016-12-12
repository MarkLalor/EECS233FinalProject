package com.marklalor.eecs233.finalproject.project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.marklalor.eecs233.finalproject.data.ImageSet;

public class ProjectInput
{
    private File inputFolder;
    
    private Map<String, ImageSet> inputs;
    private String[] selectedInputs;
    
    public ProjectInput(File inputBaseFolder, String setName, String[] selectedInputs)
    {
        this.inputFolder = new File(inputBaseFolder, setName);
        this.selectedInputs = selectedInputs;
        
        final String[] directoryPaths = getInputFolder().list(new FilenameFilter()
        {
            @Override
            public boolean accept(File current, String name)
            {
                return new File(current, name).isDirectory();
            }
        });
        
        if(directoryPaths == null)
            throw new RuntimeException("The given directory " + getInputFolder().getAbsolutePath() + " does not denote a directory!");
        
        this.inputs = new HashMap<>(directoryPaths.length);
        for(String path : directoryPaths)
        {
            final File directoryFile = new File(inputFolder, path);
            this.inputs.put(directoryFile.getName(), new ImageSet(directoryFile.getName(), directoryFile));
        }
    }
    
    public String[] getSelectedInputs()
    {
        return selectedInputs;
    }
    
    public File getInputFolder()
    {
        return inputFolder;
    }
    
    public Map<String, ImageSet> getInputs()
    {
        return inputs;
    }
    
    public ImageSet[] getImageSets()
    {
        ImageSet[] imageSets = new ImageSet[selectedInputs.length];
        
        for(int i = 0; i < selectedInputs.length; i++)
            imageSets[i] = this.inputs.get(this.selectedInputs[i]);
        
        return imageSets;
    }
    
    public static void createScaledImages(File rootFolder, int number)
    {
        File baseFolder = new File(rootFolder, "base");
        
        final String[] directoryPaths = baseFolder.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File current, String name)
            {
                return new File(current, name).isDirectory();
            }
        });
        
        if(directoryPaths == null)
            return;
        
        for(String path : directoryPaths)
        {
            ImageSet set = new ImageSet(path, new File(baseFolder, path));
            createScaledImagesFor(rootFolder, set, number);
        }
    }
    
    private static void createScaledImagesFor(File rootFolder, ImageSet set, int number)
    {
        File inputsFolder = new File(rootFolder, "inputs");
        File setFolder = new File(inputsFolder, set.getName());
        
        BufferedImage inputImage = set.getInput().getImage();
        BufferedImage outputImage = set.getOutput().getImage();
        
        if((inputImage.getWidth() != outputImage.getWidth()) || (inputImage.getHeight() != outputImage.getHeight()))
            throw new RuntimeException("Image dimension mismatch for set '" + set.getName() + "'");
        
        double width = inputImage.getWidth();
        double height = inputImage.getHeight();
        
        double intervalX = width / (number + 1);
        double intervalY = height / (number + 1);
        
        double widthPrime = width;
        double heightPrime = height;
        
        for(int i = number - 1; i >= 0; i--)
        {
            createAndSaveResized(set, new File(setFolder, String.valueOf(i)), (int) (Math.round(widthPrime)), (int) (Math.round(heightPrime)));
            
            widthPrime -= intervalX;
            heightPrime -= intervalY;
        }
    }
    
    private static BufferedImage toBufferedImage(Image img)
    {
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
    
    private static void createAndSaveResized(ImageSet set, File folder, int widthPrime, int heightPrime)
    {
        try
        {
            Image scaledInput = set.getInput().getImage().getScaledInstance(widthPrime, heightPrime, Image.SCALE_DEFAULT);
            BufferedImage inputImage = toBufferedImage(scaledInput);
            File inputImageFile = new File(folder, "in.png");
            inputImageFile.mkdirs();
            inputImageFile.createNewFile();
            ImageIO.write(inputImage, "png", inputImageFile);
            
            Image scaledOutput = set.getOutput().getImage().getScaledInstance(widthPrime, heightPrime, Image.SCALE_DEFAULT);
            BufferedImage outputImage = toBufferedImage(scaledOutput);
            File outputImageFile = new File(folder, "out.png");
            outputImageFile.mkdirs();
            outputImageFile.createNewFile();
            ImageIO.write(outputImage, "png", outputImageFile);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("Could not created resized images.");
        }
    }
}
