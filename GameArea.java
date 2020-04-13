import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.*;



public class GameArea extends JPanel{
		private final String I_WALL ="image/wall.png";
		private final String I_KEEPER ="image/keeper.png";
		private final String I_BOX ="image/box.png";
		private final String I_BOS ="image/boxonstorage.png";
		private final String I_STORAGELOCATION ="image/storagelocation.png";
		private final String I_NONE ="image/none.png";
		private final String I_FLOOR = "image/floor.png";

		private Image img;
		private int initKeeperXpos;
		private int initKeeperYpos;
		private char layout[][] = new char[10][10];
	
		private int i = 0;
		private int j = 0;
	
	public GameArea(){
		getPuzzle();
		printPuzzle();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		for (i = 0; i <10; i ++){
			for (j=0; j<10; j++){
				img = getImage(layout[i][j]);
				Graphics2D g2d = (Graphics2D)g;
				g.drawImage(img, j*50,i*50,50,50,null);
			}
		}
	}
	
	public void win(){
				JOptionPane.showMessageDialog(new JFrame(), "YOU WIN!");
	}

	private void getPuzzle(){
		i = 0;
		j = 0;
		try{
			FileReader input = new FileReader("puzzle.in");
			int charRead;
			while((charRead=input.read()) != -1) {
				if (charRead == ' ' || charRead == '\n')
					continue;
				if (charRead == GameProper.KEEPER || charRead == GameProper.KOS){
					initKeeperXpos = i;
					initKeeperYpos = j;

				}
				layout[i][j]=(char)charRead;
				j++;
				if (j == 10){
					i++;
					j = 0;
				}
			}
			input.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
	}

	public void printPuzzle(){
		for (i=0; i <10; i++){
			for (j=0; j<10; j++){
				System.out.print(layout[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Xpos: " + this.initKeeperXpos + " Ypos: " + this.initKeeperYpos);
	}

	private Image getImage(char tile){
		try {
			switch(tile){
				case 'b':
					return ImageIO.read(new File(I_BOX));
				case 'B':
					return ImageIO.read(new File(I_BOS));
				case 'e':
					return ImageIO.read(new File(I_FLOOR));
				case 'k':
					return ImageIO.read(new File(I_KEEPER));
				case 'K':
					return ImageIO.read(new File(I_KEEPER));
				case 's':
					return ImageIO.read(new File(I_STORAGELOCATION));
				case 'w':
					return ImageIO.read(new File(I_WALL));
				case 'x':
					return ImageIO.read(new File(I_NONE));										
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int getKeeperXpos(){
		return this.initKeeperXpos;
	}
	public int getKeeperYpos(){
		return this.initKeeperYpos;
	}
	public char[][] getlayout(){
		return this.layout;
	}
}
