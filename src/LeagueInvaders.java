import javax.swing.JFrame;

public class LeagueInvaders {
int var ;
JFrame jf ;
public static final int width = 500  ;
public static final int height = 800  ;
GamePanel GP = new GamePanel();
public static void main(String[] args) {
 LeagueInvaders l = new LeagueInvaders();

	l.setup();
	
	
	
	
	
	
	
	
	
	
	
}
LeagueInvaders(){
	
	jf = new JFrame();
}
void setup() {
	jf.add(GP);
	jf.setSize(width, height);
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
jf.addKeyListener(GP);


	
	
	
}
}

