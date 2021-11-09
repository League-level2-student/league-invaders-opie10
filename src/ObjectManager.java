import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	ArrayList<Projectile> pew = new ArrayList<>();
	ArrayList<Alien> invasive = new ArrayList<>();
	Random rand = new Random();
	Rocketship r;
	int score = 0;
	Boolean isInvincible =false;

	ObjectManager(Rocketship Rr) {
		r = Rr;
	}

	public void addProjectile(Projectile pow) {
		pew.add(pow);
	}

	public void addAlien() {
		invasive.add(new Alien(rand.nextInt(LeagueInvaders.width), 0, 50, 50));
	}

	public void update() {

		for (int i = 0; i < pew.size(); i++) {
			pew.get(i).update();

			if (pew.get(i).y < LeagueInvaders.height) {
				pew.get(i).isActive = false;
			}
		}
		for (int i = 0; i < invasive.size(); i++) {
			invasive.get(i).update();

			if (invasive.get(i).y > LeagueInvaders.height) {
				invasive.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
		scoreTrack();
		r.update();
	}

	public void draw(Graphics g) {
		r.draw(g);
		for (int i = 0; i < invasive.size(); i++) {
			invasive.get(i).draw(g);
		}
		for (int i = 0; i < pew.size(); i++) {
			pew.get(i).draw(g);
		}
	}

	public void purgeObjects() {

		for (int i = 0; i < invasive.size(); i++) {
			invasive.get(i);
			if (invasive.get(i).isActive == false) {
				invasive.remove(i);
			}
		}

	}

	public void checkCollision() {
		
		for (int i = 0; i < invasive.size(); i++) {
			Rectangle alienc = invasive.get(i).collisionBox;
			Rectangle shipc = r.collisionBox;
			if (isInvincible==false) {
				if (alienc.intersects(shipc)) {
					r.isActive = false;
			}
			
			}
			for (int p = 0; p < pew.size(); p++) {
				Rectangle rt = pew.get(p).collisionBox;
				if (rt.intersects(alienc)) {
					pew.get(p).isActive = false;
					invasive.get(i).isActive = false;
					score+=1;
				}

			}
		}

	}

	public int scoreTrack() {

	
		return score;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
