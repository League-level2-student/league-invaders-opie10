import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int xspeed ;
	int yspeed ;
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		if (needImage) {
		    loadImage ("plane.png");
		}
	}
void draw(Graphics g)
{
	
     if (gotImage) {
    		g.drawImage(image, x, y, width, height, null);
    	} else {
    		g.setColor(Color.BLUE);
    		g.fillRect(x, y, width, height);
    	}


}
public void right() {
    xspeed=5;
    super.update();
}
public void left() {
    xspeed=-5;
    super.update();
}
public void up() {
    yspeed=-5;
    super.update();
}
public void down() {
   yspeed=5;
   super.update();
}
public void update () {
	if (x + xspeed > 0 && x+width+xspeed<LeagueInvaders.width) {
		x += xspeed ;
	}
	if (y+yspeed> 0 && y+height+yspeed<LeagueInvaders.height) {
		y += yspeed ;
	}
	
	super.update();
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
public Projectile getProjectile() {
    return new Projectile(x+width/2, y, 10, 10);
} 

}
