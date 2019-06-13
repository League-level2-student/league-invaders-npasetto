import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
final int MENU = 0;
final int GAME = 1;
final int END = 2;
int currentState=MENU;
Font titleFont;
Font textFont;
Timer frameDraw;
Rocketship ship = new Rocketship(250,600,50,50);
public GamePanel(){
	titleFont=new Font("Arial",Font.PLAIN,48);
	textFont=new Font("Arial",Font.PLAIN,12);
	frameDraw=new Timer(1000/100,this);
	frameDraw.start();
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
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	ship.draw(g);
}
void drawEndState(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	g.drawString("Game Over", 115, 100);
	g.setFont(textFont);
	g.drawString("You killed enemies", 190, 330);
	g.drawString("Press ENTER to restart", 175, 450);
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
			currentState=MENU;
		}else {
			currentState++;
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

