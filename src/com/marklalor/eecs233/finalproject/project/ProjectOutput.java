package com.marklalor.eecs233.finalproject.project;

import com.marklalor.eecs233.finalproject.data.ImageSet;
import com.marklalor.eecs233.finalproject.gui.IntegratedManager;

public class ProjectOutput
{
    private IntegratedManager integratedManager = null;
    
    public ProjectOutput()
    {
        this.integratedManager = new IntegratedManager();
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
