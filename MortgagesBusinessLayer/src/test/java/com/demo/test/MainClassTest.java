package com.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.mortgage.businesslayer.demo.BusinessLayerDemoApplicationMain;

@RunWith(SpringRunner.class)
public class MainClassTest {
  @Test
  public void applicationStarts() {
	  BusinessLayerDemoApplicationMain.main(new String[] {});
  }
}