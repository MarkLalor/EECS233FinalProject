package com.marklalor.eecs233.finalproject.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ImageFrameManager
{
    private JFrame frame;
    private ImagePanel panel;
    private BufferedImage image;
    
    public ImageFrameManager(String title, BufferedImage image)
    {
        this.panel = new ImagePanel(image);
        this.image = image;
        
        this.frame = new JFrame(title);
        this.frame.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.frame.add(this.panel);
        this.frame.pack();
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
