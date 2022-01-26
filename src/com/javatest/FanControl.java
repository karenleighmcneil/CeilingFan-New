package com.javatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FanControl extends JFrame  
{
	private static final long serialVersionUID = 1L;
	private JPanel controlPanel;
	private JButton speedCord;
	private JButton directionCord;
	private Timer timer;
	private String direction;
	private int speed = 0;



	public FanControl()
	{
		super();
		direction = "clockwise";
		setLayout(new BorderLayout());
		ArcsPanel ArcFrame = new ArcsPanel();
		choicePanel();
		getContentPane().add(ArcFrame, BorderLayout.CENTER);
		getContentPane().add(controlPanel, BorderLayout.SOUTH);	
	}
	
	class ArcsPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		private int radius = 0, xCenter = 0, yCenter = 0, x = 0, y = 0, theta = 0;
		

		
		protected void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			
			radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
			
			x = xCenter - radius;
			y = yCenter - radius;
			

			
			g2d.translate(getWidth() / 2, getHeight() / 2);

			if (direction.equals("clockwise")) {
				g2d.rotate(theta);
			}
			else {
				g2d.rotate(-theta);
			}
			
			theta -= 1;
			
			g.setColor(Color.LIGHT_GRAY);
			
			g.fillArc(x, y, 2 * radius, 2 * radius, 0, 30);
			g.fillArc(x, y, 2 * radius, 2 * radius, 90, 30);
			g.fillArc(x, y, 2 * radius, 2 * radius, 180, 30);
			g.fillArc(x, y, 2 * radius, 2 * radius, 270, 30); 

			g.drawOval(-1,-1, 2, 2); 
			g.drawOval(-2,-2, 4, 4); 
			g.drawOval(-3,-3, 6, 6);  
			g.drawOval(-10,-10, 20, 20); 
			g.drawOval(-150,-150, 300, 300);
			g.drawOval(-160,-160, 320, 320);  
	 	}	
	}
	
	public void choicePanel()
	{
		controlPanel = new JPanel();

		speedCord = new JButton("Pull Cord");		
		directionCord = new JButton("Change Direction");
		
		controlPanel.add(speedCord);
		controlPanel.add(directionCord);

		
		ActionHandler Handler = new ActionHandler();
		
		speedCord.addActionListener(Handler);
		directionCord.addActionListener(Handler);
	}
	
	private class ActionHandler implements ActionListener
	{
		private int count;
		
		public void actionPerformed(ActionEvent event) 
		{

			if(event.getSource() == (speedCord))
			{
				count++;
				
				if(count == 1)
				{
					speed = 90;
					System.out.println("Fan is on speed LOW (1) and running " + direction);
					timer = new Timer(speed, new TimerListener());
		   			timer.start();
				}	
				else if (count == 2)
				{	
					speed = 60;
					System.out.println("Fan is on speed MEDIUM (2) and running " + direction);
					timer.stop();
					timer = new Timer(speed, new TimerListener());
					timer.start();
				}
				else if (count == 3)
				{	
					speed = 30;
					System.out.println("Fan is on speed HIGH (3) and running " + direction);
					timer.stop();
					timer = new Timer(speed, new TimerListener());
					timer.start();
				}
				else if (count == 4)
				{	
					speed = 0;
					System.out.println("Fan is OFF");
					count = 0;
					timer.stop();
				}	
			}
			else if(event.getSource() == (directionCord)) {
			

				if(direction.equals("clockwise")) {
					direction = "counter-clockwise";
				}
				else {
					direction = "clockwise";
				}
				System.out.println("Fan was changed to run " + direction);
				
				// Only turn fan on if it's currently running
				if(speed != 0) {
					timer.stop();
					timer = new Timer(speed, new TimerListener());
					timer.start();
				}
			}
						  
		}
	}	
	
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			repaint();
		}
	}
}