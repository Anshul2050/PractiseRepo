package com.nop.testLayer;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.nop.baseTest4.BaseTest4;

public class CheckTest extends BaseTest4{
    
    @Test
    public void checkTest() throws InterruptedException
    {
       
    	Thread.sleep(3000);
    	hp_obj.getUrl();
        Assert.assertTrue(true);
    }
}
