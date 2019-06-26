import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship ship;
ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
ArrayList<Alien> aliens=new ArrayList<Alien>();
Random random=new Random();
int score=0;
ObjectManager(Rocketship ship){
	this.ship=ship;
}
int getScore() {
	return score;
}
void addProjectile(Projectile p) {
	projectiles.add(p);
}
void addAlien() {
	aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
}
void update() {
	for (Alien alien : aliens) {
		alien.update();
		if(alien.y>LeagueInvaders.HEIGHT) {
			alien.isActive=false;
		}
	}
	for (Projectile projectile : projectiles) {
		projectile.update();
		if(projectile.y<0) {
			projectile.isActive=false;
		}
	}
	if(ship.isActive) {
	checkCollision();
	purgeObjects();
	}
}
void draw(Graphics g) {
	ship.draw(g);
	for (Alien alien : aliens) {
		alien.draw(g);
	}
	for (Projectile projectile : projectiles) {
		projectile.draw(g);
	}
}
void purgeObjects() {
	for (int i = 0; i < aliens.size(); i++) {
		if(aliens.get(i).isActive==false) {
			aliens.remove(i);
		}
	}
	for (int i = 0; i < projectiles.size(); i++) {
		if(projectiles.get(i).isActive==false) {
			projectiles.remove(i);
		}
	}
}
void checkCollision() {
	for (Alien alien : aliens) {
		for (Projectile projectile : projectiles) {
			if(alien.collisionBox.intersects(projectile.collisionBox)) {
				alien.isActive=false;
				projectile.isActive=false;
				score++;
			}
		}
		if(alien.collisionBox.intersects(ship.collisionBox)) {
			alien.isActive=false;
			ship.isActive=false;
		}
	}
}
@Override
public void actionPerformed(ActionEvent arg0) {
	addAlien();
	
}
}
