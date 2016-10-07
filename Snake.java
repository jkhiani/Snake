package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{
	
	public JFrame jFrame;
	public RenderPanel renderPanel;
	public static Snake snake;
	public Timer timer = new Timer(20, this);
	public Random random;
	public Dimension dim;
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public Point head, cherry;
	public static final int SCALE = 10, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	public int ticks = 0, direction = DOWN, score, tailLength=5;
	public boolean over, paused;
	
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame = new JFrame("Snake");
		jFrame.setVisible(true);
		jFrame.setSize(805, 700);
		jFrame.setResizable(false);
		jFrame.setLocation(dim.width/2-jFrame.getWidth()/2, dim.height/2-jFrame.getHeight()/2);
		jFrame.add(renderPanel = new RenderPanel());
		jFrame.addKeyListener(this);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		startGame();
	}
	
	@Override 
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();
		ticks++;

		if (ticks%2==0 && head!=null && !over && !paused){
			snakeParts.add(new Point(head.x, head.y));
			if (direction == UP){
				if (head.y-1>=0 && noTailAt(head.x, head.y-1))
					head = new Point(head.x,head.y-1);
				else
					over = true;
			}
			if (direction == DOWN){
				if (head.y+1<67 && noTailAt(head.x, head.y+1))
					head = new Point(head.x,head.y+1);					
				else
					over = true;
			}
			if (direction == LEFT){
				if (head.x-1>=0 && noTailAt(head.x-1, head.y))
					head = new Point(head.x-1,head.y);					
				else
					over = true;
			}
			if (direction == RIGHT){
				if (head.x+1<80 && noTailAt(head.x+1, head.y))
					head = new Point(head.x+1,head.y);	
				else
					over = true;
			}
			if (snakeParts.size()>tailLength){
				snakeParts.remove(0);
			}
			if (over == true){
				renderPanel.repaint();
			}
			if (cherry!=null){
				if(head.x == cherry.x && head.y == cherry.y){
					score+=10;
					tailLength++;
					cherry.setLocation(random.nextInt(79),random.nextInt(66));
				}
			}
			
		}
	}
	
	private boolean noTailAt(int x, int y) {
		for (Point point: snakeParts){
			if (point.equals(new Point(x,y))){
				return false;
			}
		}
		return true;
	}

	public void startGame(){
		over = false;
		tailLength = 5;
		score = 0;
		ticks = 0;
		paused = false;
		direction = DOWN;
		head = new Point(0,-1);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(79), random.nextInt(66));
		for (int i=0; i<tailLength; i++){
			snakeParts.add(new Point(head.x, head.y));
		}
		timer.start();
	}
	
	public static void main(String[] args) {
		snake = new Snake();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int x=e.getKeyCode();
		if (x==KeyEvent.VK_A && direction != RIGHT){
			direction = LEFT;
		}
		if (x==KeyEvent.VK_D && direction != LEFT){
			direction = RIGHT;
		}
		if (x==KeyEvent.VK_W && direction != DOWN){
			direction = UP;
		}
		if (x==KeyEvent.VK_S && direction != UP){
			direction = DOWN;
		}
		if (x==KeyEvent.VK_SPACE){
			if (over){
				startGame();
			}
			else{
				paused = !paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
