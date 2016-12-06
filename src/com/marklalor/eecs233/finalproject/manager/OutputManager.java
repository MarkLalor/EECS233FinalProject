package com.marklalor.eecs233.finalproject.manager;

import com.marklalor.eecs233.finalproject.data.ImageSet;

public interface OutputManager
{
    public void initiate(ImageSet imageSet);
    
    public void update();
    
    public void complete();
}
