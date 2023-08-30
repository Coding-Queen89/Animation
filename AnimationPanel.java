import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class AnimationPanel extends JPanel implements ActionListener{
		
	final int PANEL_WIDTH = 500;
	final int PANEL_HEIGHT = 375;
	Image enemy;
	Image re;
	Timer timer;
	int xVelocity = 10;
	int yVelocity = 10;
	int x=0;
	int y=0;
	
	AnimationPanel(){
		this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		this.setBackground(Color.black);
		enemy = new ImageIcon("C:\\Users\\96656\\Documents\\Pictures\\ufo1.png").getImage();
		re = new ImageIcon("C:\\Users\\96656\\Documents\\Pictures\\earth.jpg").getImage();
		timer = new Timer(1/10, this);//this means it will perform an action every second(1000millisecs) from the 
		//actionPerfomed method. To make the image move faster, reduce the time it takes for the timer to invoke the 
		//actionListener and repaint the image. Every 10 ms, the timer will start and the image will move by 1 px.
		//If you change the int(xVelocity) and keep the time, the image will move, but it will appear to 'jump' every
		//100 px. Moving by 1 px in a smaller time frame is more flawless.
		timer.start();
		
	}
	//We need to define our paint method. It's called behind the scenes when we instantiate the JPanel, because the 
	//JPanel is a subclass of the component class.
	
	public void paint(Graphics g) {
		//The first thing we do within the paint method is cast the Graphics g object as Graphics2D: more options.
		//to set the background as black we need to call on the super class:
		super.paint(g);
		Graphics2D i2D = (Graphics2D)g;
		Graphics2D g2D = (Graphics2D)g;
		i2D.drawImage(re, 0, 0, null);
  		g2D.drawImage(enemy, x, y, null);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(x>=PANEL_WIDTH-enemy.getWidth(null)|| x<0) {
			//Now whenever our image moves, it will continue moving along the x-axis, if we want it to change direction 
			//whenever it collides with the border of the frame, we will set if: 
			//if x (the position it currently is) is greater than or equal to the panel width, reverse direction.
			//we can reverse direction by reversing the sign of the unit it is moving by. xVelocity is 1 x unit: ie. 1 unit
			//to the right. To the left is -1. So, multiply by -1. Now, the system has the unit -1, it will go to the next line
			//where x = x + (-1). However, the image starts from the bottom left. So when the bottom left touches the frame it will
			//reverse. We don't want that. We will make it so that the image width is subtracted from the frame width. This way the 
			//whenever the border of the image touches the border of the frame, it will reverse direction.
			//Setting the OR operator. NOT another if statement. And the x is less than 0, since the frame's side is 0,0. 
			xVelocity = xVelocity*-1;
			}
		
		if(y>=PANEL_HEIGHT-enemy.getHeight(null)|| y<0) {
			yVelocity=yVelocity*-1;
		}
		
		//we want to move the image on the x-axis.
		x= x+xVelocity;//The image doesn't appear to move, but it is behind the scenes. We need to repaint it. So we
		//need to call the paint method again every time we update the position of the image.
		//According to the description of paint:
		//Applications should not invoke paint directly,but should instead use the repaint method to schedule the 
		//component for redrawing. 
		y=y+yVelocity;
		repaint();
		
	}
	
	
	
}
