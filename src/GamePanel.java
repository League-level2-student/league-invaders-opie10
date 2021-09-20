import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;

    Font titleFont = new Font("Arial", Font.BOLD, 29);
    Font menuFont = new Font("Arial", Font.ITALIC, 20);
  public GamePanel() {
	  
	  Timer frameDraw = new Timer(1000/60,this);
	    frameDraw.start();

  }

	
	

    
    //game state updates
    
    public void updateMenuState() {  }
    public void updateGameState() {  }
    public void  updateEndState()  {  }
   //game drawing
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
    }
    public void drawEndState(Graphics g)  {
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
    	g.setFont(titleFont);
    	g.setColor(Color.BLACK);
    	g.drawString("GAME OVER", 250, 200);
    	g.setFont(menuFont);
    	g.setColor(Color.BLACK);
    	g.drawString("Press Enter to try again.", 10, 600);
    
  
    }
    
    
    
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
System.out.println("action");
repaint();
	}

}
