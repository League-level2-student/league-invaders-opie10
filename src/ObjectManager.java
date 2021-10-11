import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
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
	for (int i = 0; i < pew.size(); i++) {
		pew.get(i).draw(g);
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

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	addAlien();
}
}
