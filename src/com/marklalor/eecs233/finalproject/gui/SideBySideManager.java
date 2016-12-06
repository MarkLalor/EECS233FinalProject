package com.marklalor.eecs233.finalproject.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class SideBySideManager
{
    private ImageFrameManager input, result;
    
    public SideBySideManager(BufferedImage input, BufferedImage result)
    {
        this.input = new ImageFrameManager("Input", input);
        this.result = new ImageFrameManager("Result", result);
    }
    
    public void showCentered()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        input.getFrame().setLocation(screenSize.width / 2 - input.getFrame().getSize().width, screenSize.height / 2 - input.getFrame().getSize().height / 2);
        result.getFrame().setLocation(screenSize.width / 2, screenSize.height / 2 - result.getFrame().getSize().height / 2);
        
        input.getFrame().setVisible(true);
        result.getFrame().setVisible(true);
    }
    
    public ImageFrameManager getInput()
    {
        return input;
    }
    
    public ImageFrameManager getResult()
    {
        return result;
    }
    
    public void close()
    {
        input.getFrame().dispose();
        result.getFrame().dispose();
    }
}
