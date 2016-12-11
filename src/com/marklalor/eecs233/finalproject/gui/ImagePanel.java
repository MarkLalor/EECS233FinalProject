package com.marklalor.eecs233.finalproject.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
    private static final long serialVersionUID = 4033051592138414966L;
    
    private BufferedImage image;
    
    public ImagePanel(BufferedImage loadedImage)
    {
        setNewImage(loadedImage);
    }
    
    public void setNewImage(BufferedImage image)
    {
        if(image != null)
            this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.image = image;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        if(image != null)
            graphics.drawImage(image, 0, 0, this);
    }
    
}
