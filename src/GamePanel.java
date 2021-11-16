import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Rocketship rs = new Rocketship(250, 700, 50, 50);
	Font titleFont = new Font("Arial", Font.BOLD, 29);
	Font menuFont = new Font("Arial", Font.ITALIC, 20);
	BufferedImage bg ;
	ObjectManager karen = new ObjectManager(rs);
	Timer alienSpawn ;
	int difficulty = 0 ;
	static final int easy = 1 ;
	static final int hard = 2 ;
	static final int expert = 3 ;
	static final int insane = 4 ;
	public GamePanel() {

		Timer frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		alienSpawn = new Timer(1000 , karen);



		
		
		try {
			bg = ImageIO.read(this.getClass().getResourceAsStream("space.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// game state updates

	public void updateMenuState() {
		
	}

	public void updateGameState() {
		
		karen.update();
		if (rs.isActive==false) {
			currentState = END;
		
		}
		
	}

	public void updateEndState() {
	}

	// game drawing
	public void drawMenuState(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("WELCOME TO LEAGUE INVASION", 10, 100);
		g.setFont(menuFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press Enter", 190, 400);
		g.setFont(menuFont);
		g.setColor(Color.YELLOW);
		g.drawString("Space for Instructions", 150, 650);
		g.setColor(Color.YELLOW);
		g.drawString("PRESS E FOR EASY MODE", 110, 250);
		g.drawString(" PRESS H FOR HARD MODE", 100, 270);
		g.drawString("PRESS X FOR EXPERT MODE", 100, 290);
		g.drawString("PRESS I FOR INSANE MODE", 100, 310);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(bg, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		g.setFont(menuFont);
		g.setColor(Color.WHITE);
		
		g.drawString(karen.score+"", 10, 20);
		
		karen.draw(g);
	
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 160, 200);
		g.setFont(menuFont);
		g.setColor(Color.BLACK);
		g.drawString("You got a score of "+karen.score+" before you died.", 90, 400);
		g.setFont(menuFont);
		g.setColor(Color.BLACK);
		g.drawString("Press Enter to try again.", 150, 600);

	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				 rs = new Rocketship(250, 700, 50, 50);
				 karen = new ObjectManager(rs);
				 alienSpawn = new Timer(1000 , karen);
					
				 karen.score = 0;
				currentState = MENU;
			} else {
				currentState++;
			}
		}
		
		if (currentState == MENU) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				
			JOptionPane.showMessageDialog(null, "Space to shoot\n" + "Arrow keys to move\n" + "Enter to restart\n","Instructions",JOptionPane.INFORMATION_MESSAGE);
		
			}
			
			if (e.getKeyCode() == KeyEvent.VK_E) {
			
			karen.changeSpeed(easy);
			JOptionPane.showMessageDialog(null, "You are in EASY mode","Difficulty",JOptionPane.INFORMATION_MESSAGE);
			difficulty = 1 ;
			alienSpawn = new Timer(1000 , karen);
			}
			
			
			if (e.getKeyCode() == KeyEvent.VK_H) {
				
				karen.changeSpeed(hard);
				JOptionPane.showMessageDialog(null, "You are in HARD mode","Difficulty",JOptionPane.INFORMATION_MESSAGE);
				difficulty = 2 ;
				alienSpawn = new Timer(700 , karen);
			}
			if (e.getKeyCode() == KeyEvent.VK_X) {
				
				karen.changeSpeed(expert);
				JOptionPane.showMessageDialog(null, "You are in EXPERT mode","Difficulty",JOptionPane.INFORMATION_MESSAGE);
				difficulty = 3;
				alienSpawn = new Timer(500 , karen);
			}
			if (e.getKeyCode() == KeyEvent.VK_I) {
				
				karen.changeSpeed(insane);
				JOptionPane.showMessageDialog(null, "You are in INSANE mode","Difficulty",JOptionPane.INFORMATION_MESSAGE);
				difficulty = 4 ;
				alienSpawn = new Timer(100 , karen);
			}
		}
		if (currentState == GAME) {
			startGame();
			rs.update();
			if (e.getKeyCode() == KeyEvent.VK_UP) {

				
				if (rs.y - 10 > 0) {
					rs.up();
				} else {
					rs.y = 0;
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				
				if (rs.y + rs.height + 10+20 < 800) {
					rs.down();
				} else {
					rs.y = 800 - rs.height-20;
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				if (rs.x - 10 > 0) {
					rs.left();
				} else {
					rs.x = 0;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				if (rs.x + rs.width + 10 < 500) {
					rs.right();
				} else {
					rs.x = 500 - rs.width;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
			karen.addProjectile(rs.getProjectile());
			
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH ) {
				karen.isInvincible=true;
				
				}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
if (e.getKeyCode() == KeyEvent.VK_RIGHT ||e.getKeyCode() == KeyEvent.VK_LEFT) {
	rs.xspeed=0;
}
if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN) {
	rs.yspeed=0;
}
	}
public void startGame() {
	
	
	alienSpawn.start();
}
}
