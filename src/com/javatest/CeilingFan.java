package com.javatest;

import javax.swing.*;

public class CeilingFan 
{
	public static void main(String[] args)
	{
		FanControl myFan = new FanControl();

		myFan.setTitle("My Ceiling Fan");
		myFan.setSize(500,500);
		myFan.setLocationRelativeTo(null);
		myFan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFan.setVisible(true);
		
	}
}
