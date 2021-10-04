import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	ObjectManager karen = new ObjectManager(rs);
	
	public GamePanel() {

		Timer frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();

	}

	// game state updates

	public void updateMenuState() {
	}

	public void updateGameState() {
		karen.update();
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
		g.drawString("Press Enter", 200, 400);
		g.setFont(menuFont);
		g.setColor(Color.YELLOW);
		g.drawString("Space for Instructions", 150, 650);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		karen.draw(g);
	
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 150, 200);
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
				currentState = MENU;
			} else {
				currentState++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {

				System.out.println("UP");
				if (rs.y - 10 > 0) {
					rs.up();
				} else {
					rs.y = 0;
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("DOWN");
				if (rs.y + rs.height + 10+20 < 800) {
					rs.down();
				} else {
					rs.y = 800 - rs.height-20;
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("LEFT");
				if (rs.x - 10 > 0) {
					rs.left();
				} else {
					rs.x = 0;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
				if (rs.x + rs.width + 10 < 500) {
					rs.right();
				} else {
					rs.x = 500 - rs.width;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
