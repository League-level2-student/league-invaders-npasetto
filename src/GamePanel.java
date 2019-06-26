import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
final int MENU = 0;
final int GAME = 1;
final int END = 2;
int currentState=MENU;
Font titleFont;
Font textFont;
Timer frameDraw;
Timer alienSpawn;
Rocketship ship = new Rocketship(250,600,50,50);
ObjectManager manager = new ObjectManager(ship);
public static BufferedImage image;
public static boolean needImage = true;
public static boolean gotImage = false;
public GamePanel(){
	titleFont=new Font("Arial",Font.PLAIN,48);
	textFont=new Font("Arial",Font.PLAIN,12);
	frameDraw=new Timer(1000/100,this);
	frameDraw.start();
	if(needImage) {
		loadImage("space.png");
	}
}
void startGame() {
	alienSpawn=new Timer(1000,manager);
	alienSpawn.start();
}
@Override
public void paintComponent(Graphics g){
if(currentState==MENU) {
	drawMenuState(g);
}else if(currentState==GAME) {
	drawGameState(g);
}else if(currentState==END) {
	drawEndState(g);
}
}

void updateMenuState() {
	
}
void updateGameState() {
	if(ship.isActive==false) {
		currentState=END;
	}
	manager.update();
}
void updateEndState() {
	
}
void drawMenuState(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	g.drawString("LEAGUE INVADERS", 20, 100);
	g.setFont(textFont);
	g.drawString("Press ENTER to start", 180, 330);
	g.drawString("Press SPACE for instructions",150,480);
}
void drawGameState(Graphics g) {
	if(gotImage) {
		g.drawImage(image,0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT,null);
	}else {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	}
	g.setColor(Color.BLUE);
	g.drawString((manager.getScore()+""), 10, 10);
	manager.draw(g);
}
void drawEndState(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	g.drawString("Game Over", 115, 100);
	g.setFont(textFont);
	g.drawString("You killed "+manager.getScore()+" enemies", 190, 330);
	g.drawString("Press ENTER to restart", 175, 450);
}
void loadImage(String imageFile) {
    if (needImage) {
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	    gotImage = true;
        } catch (Exception e) {
            
        }
        needImage = false;
    }
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(currentState == MENU){
	    updateMenuState();
	}else if(currentState == GAME){
	    updateGameState();
	}else if(currentState == END){
	    updateEndState();
	}
	//System.out.println("action");
	repaint();
}
@Override
public void keyPressed(KeyEvent arg0) {
	if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		if(currentState==END) {
			ship=new Rocketship(250,600,50,50);
			manager=new ObjectManager(ship);
			currentState=MENU;
		}else {
			if(currentState==MENU) {
				startGame();
			}
			if(currentState==GAME) {
				alienSpawn.stop();
			}
			currentState++;
		}
	}
	if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
		manager.addProjectile(ship.getProjectile());
		if(currentState==MENU) {
			JOptionPane.showMessageDialog(null, "Use arrows to move, space to shoot, and watch out for aliens.");
		}
	}
	if(arg0.getKeyCode()==KeyEvent.VK_UP) {
		//System.out.println("UP");
		if(ship.y>0) {
			ship.up();
		}
	}
	if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		//System.out.println("DOWN");
		if(ship.y<LeagueInvaders.HEIGHT-50) {
			ship.down();
		}
	}
	if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		//System.out.println("LEFT");
		if(ship.x>0) {
			ship.left();
		}
	}
	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		
		//System.out.println("RIGHT");
		if(ship.x<LeagueInvaders.WIDTH-50) {
			ship.right();
		}
	}
	
}
@Override
public void keyReleased(KeyEvent arg0) {
	
	
}
@Override
public void keyTyped(KeyEvent arg0) {
	
	
}
}

