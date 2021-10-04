import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<Projectile> pew= new ArrayList<>();
	ArrayList<Alien> invasive= new ArrayList<>();
	Random rand = new Random();
	Rocketship r ;
ObjectManager(Rocketship Rr) {
	r = Rr ;
}

public void addProjectile(Projectile pow) {
	pew.add(pow);
}
public void addAlien() {
	invasive.add(new Alien(rand.nextInt(LeagueInvaders.width),0,50,50));
}
public void update() {
for (int i = 0; i < invasive.size(); i++) {
	invasive.get(i).update();
	
	
	if (invasive.get(i).y > LeagueInvaders.height) {
		invasive.get(i).isActive = false ;
	}
}
}
public void draw(Graphics g) {
	r.draw( g);
	for (int i = 0; i < invasive.size(); i++) {
		invasive.get(i).draw(g);
	}
}
public void purgeObjects(){
	
	for (int i = 0; i < invasive.size(); i++) {
		invasive.get(i);
		if (invasive.get(i).isActive == false) {
			invasive.remove(i);
		}
	}
	
}
}
