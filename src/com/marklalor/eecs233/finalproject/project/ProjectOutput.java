package com.marklalor.eecs233.finalproject.project;

import com.marklalor.eecs233.finalproject.data.ImageSet;
import com.marklalor.eecs233.finalproject.gui.IntegratedManager;
import com.marklalor.eecs233.finalproject.manager.OutputManager;

public class ProjectOutput implements OutputManager
{
    // private SideBySideManager windowManager = null;
    private IntegratedManager integratedManager = null;
    
    public ProjectOutput()
    {
        this.integratedManager = new IntegratedManager();
        this.integratedManager.showFullscreen(true);
    }
    
    @Override
    public void initiate(ImageSet imageSet)
    {
        this.integratedManager.updateImages(imageSet.getInput().getImage(), imageSet.getResult());
        this.integratedManager.getFrame().pack();
        this.integratedManager.refreshAll();
    }
    
    @Override
    public void update()
    {
        this.integratedManager.refreshResult();
    }
    
    @Override
    public void complete()
    {
        this.integratedManager.refreshAll();
    }
}
