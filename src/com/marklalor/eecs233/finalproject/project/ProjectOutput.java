package com.marklalor.eecs233.finalproject.project;

import com.marklalor.eecs233.finalproject.data.ImageSet;
import com.marklalor.eecs233.finalproject.gui.FullscreenManager;

public class ProjectOutput
{
    private FullscreenManager integratedManager = null;
    
    public ProjectOutput()
    {
        this.integratedManager = new FullscreenManager();
        this.integratedManager.showFullscreen(true);
    }
    
    public void initiate(ImageSet imageSet)
    {
        this.integratedManager.updateImages(imageSet.getInput().getImage(), imageSet.getResult());
        this.integratedManager.getFrame().pack();
        this.integratedManager.refreshAll();
    }
    
    public void update()
    {
        this.integratedManager.refreshResult();
    }
    
    public void complete()
    {
        this.integratedManager.refreshAll();
    }
}
