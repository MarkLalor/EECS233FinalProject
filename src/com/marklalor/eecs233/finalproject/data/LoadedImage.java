package com.marklalor.eecs233.finalproject.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadedImage
{
    private final File file;
    private final BufferedImage image;
    private final int[][] pixels;
    
    // private final List<>
    
    public LoadedImage(File file) throws IOException
    {
        this.file = file;
        this.image = ImageIO.read(this.file);
        this.pixels = loadRGBBitPixels(image);
    }
    
    /**
     * http://stackoverflow.com/a/9470843/1246275
     */
    // private int[][] loadRGBBitPixels(BufferedImage image)
    // {
    // final byte[] pixels = ((DataBufferByte)
    // image.getRaster().getDataBuffer()).getData();
    // final int width = image.getWidth(), height = image.getHeight();
    //
    // int[][] result = new int[height][width];
    // for(int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += 3)
    // {
    // int rgb = 0;
    // rgb += -16777216; // 255 alpha
    // rgb += ((int) pixels[pixel] & 0xff); // blue
    // rgb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
    // rgb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
    // result[row][col] = rgb;
    // col++;
    // if(col == width)
    // {
    // col = 0;
    // row++;
    // }
    // }
    //
    // return result;
    // }
    private static int[][] loadRGBBitPixels(BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];
        
        for(int row = 0; row < height; row++)
            for(int col = 0; col < width; col++)
                result[row][col] = image.getRGB(col, row);
            
        return result;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
    public int[][] getPixels()
    {
        return pixels;
    }
    
    public int getNumberOfPixels()
    {
        return this.pixels.length * this.pixels[0].length;
    }
}
