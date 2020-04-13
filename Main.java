import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Main{
	public static void main(String[] args){

		JFrame frame = new JFrame("Main");
		frame.setPreferredSize(new Dimension(500,525));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final GameArea gameArea = new GameArea();
		final GameProper game = new GameProper(gameArea );

 		frame.setContentPane(gameArea); 
 		
 		frame.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent ke){
             	switch(ke.getKeyCode()){
             		case KeyEvent.VK_UP:
             			System.out.println("Key pressed: UP");
             			game.getKey("UP");
             			break;
             		case KeyEvent.VK_DOWN:
             			System.out.println("Key pressed: DOWN");             			
             			game.getKey("DOWN");
             			break;
             		case KeyEvent.VK_LEFT:
             			System.out.println("Key pressed: LEFT");
             			game.getKey("LEFT");
             			break;
             		
             		case KeyEvent.VK_RIGHT:
             			System.out.println("Key pressed: RIGHT");
             			game.getKey("RIGHT");
             			break;
             		  	
             	}
             	game.getGameArea().printPuzzle();	
			}

			public void keyTyped(KeyEvent ke){}
			public void keyReleased(KeyEvent ke){}
        });

        frame.setFocusable(true); 
		frame.pack();
		frame.setVisible(true);
	}
}