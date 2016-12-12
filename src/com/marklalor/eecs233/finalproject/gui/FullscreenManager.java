package com.marklalor.eecs233.finalproject.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FullscreenManager
{
    private JFrame frame;
    private ImagePanel input, result;
    
    public FullscreenManager()
    {
        this.input = new ImagePanel(null);
        this.result = new ImagePanel(null);
        
        this.frame = new JFrame("EECS 233 Final Project – Mark Lalor");
        this.frame.setLayout(new BorderLayout());
        
        JPanel left = new JPanel();
        left.setLayout(new GridBagLayout());
        input.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.add(input);
        
        JPanel right = new JPanel();
        right.setLayout(new GridBagLayout());
        result.setAlignmentX(Component.CENTER_ALIGNMENT);
        right.add(result);
        
        this.frame.add(left, BorderLayout.WEST);
        this.frame.add(right, BorderLayout.EAST);
    }
    
    public ImagePanel getInput()
    {
        return input;
    }
    
    public ImagePanel getResult()
    {
        return result;
    }
    
    public void showFullscreen(boolean trueFullscreen)
    {
        // this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if(gd.isFullScreenSupported())
        {
            this.getFrame().setUndecorated(true);
            gd.setFullScreenWindow(this.getFrame());
            this.frame.setVisible(true);
        }
    }
    
    public void updateImages(BufferedImage input, BufferedImage result)
    {
        this.input.setNewImage(input);
        this.result.setNewImage(result);
    }
    
    public void close()
    {
        this.frame.dispose();
    }
    
    public void refreshResult()
    {
        this.result.repaint();
    }
    
    public void refreshAll()
    {
        this.getFrame().repaint();
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
