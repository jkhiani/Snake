package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

	public static Color bgColor = new Color(000000);
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.setColor(bgColor);
		g.fillRect(0, 0, 800, 700);
		Snake snake = Snake.snake;
		g.setColor(Color.red);
		for(Point point: snake.snakeParts){
			g.fillRect(point.x*Snake.SCALE, point.y*Snake.SCALE, Snake.SCALE, Snake.SCALE );			
		}	
		g.fillRect(snake.head.x*Snake.SCALE, snake.head.y*Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.blue);
		g.fillRect(snake.cherry.x*Snake.SCALE, snake.cherry.y*Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.white);
		g.drawString("SNAKE", 375, 10);
		g.drawString("Score: "+snake.score, 248, 30);
		g.drawString("Length: "+snake.tailLength, 373, 30);
		g.drawString("Time: "+snake.ticks/60, 498, 30);
		if (snake.over == true){
			g.setColor(Color.white);
			g.drawString("GAME OVER", 365, 300);
			g.drawString("(Space to play again)",343, 320);
		}
	}
}
