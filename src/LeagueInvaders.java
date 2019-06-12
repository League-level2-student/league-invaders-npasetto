import java.awt.Dimension;

import javax.swing.*;

public class LeagueInvaders {
JFrame window;
public static final int WIDTH=500;
public static final int HEIGHT=700;
public static void main(String[] args) {
	LeagueInvaders l = new LeagueInvaders();
	l.setup();
}
public LeagueInvaders(){
	window=new JFrame();
	
}
void setup() {
	window.add(new GamePanel());
	window.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.pack();
}
}

