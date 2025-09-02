package UI;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import KComponent.*;
import Levels.*;
import System.*;

public class GameFrame extends BasePanel implements MouseListener, MouseMotionListener {
	
	boolean startup = true;
	public static int moveX;
	public static int moveY;

	public static KFrame frame = new KFrame("MOUZ MAZE");
	static JFrame cFrame = new JFrame();
		public static JLabel moveLB = new JLabel("   Live (x,y): N/A,N/A");
		public static JLabel colorLB = new JLabel("   Live (Color): N/A,N/A,N/A");
		public static JLabel clickLB = new JLabel("   Click (x,y): N/A,N/A");
		
	public GameFrame(){
		frame.setSize(1600, 875);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.setLocation(new Point(frame.getX()-10, frame.getY()));
		cFrame.setDefaultCloseOperation(KFrame.DO_NOTHING_ON_CLOSE);
		cFrame.setFocusableWindowState(false);
		cFrame.setAlwaysOnTop(true);
		cFrame.setResizable(false);
		cFrame.setSize(175, 125);
		cFrame.setLocation(-10, 0);
		cFrame.setLayout(new GridLayout(3,1));
		cFrame.add(moveLB);
			moveLB.setHorizontalAlignment(JLabel.LEFT);
		cFrame.add(colorLB);
			colorLB.setHorizontalAlignment(JLabel.LEFT);
		cFrame.add(clickLB);
			clickLB.setHorizontalAlignment(JLabel.LEFT);
		if (coordBool == true) {
			cFrame.setVisible(true);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(KColor.bg);
			g.fillRect(0, 0, 1600, 900);
		g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
				g.drawString("Made by", 710, 300);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 120));
				g.drawString("ttewtor", 600, 420);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
				g.drawString("with JComponent", 675, 500);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 45));
				g.drawString("< Click to continue >", 320+200, 650+100);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (startup) {
			super.scene(new Menu());
			startup = false;
		}
		if (coordBool) {
			int clickX = e.getX()-9;
			int clickY = e.getY()-38;
			clickLB.setText("   Click (x,y): "+clickX+","+clickY);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if (coordBool) {
			moveX = e.getX()-9;
			moveY = e.getY()-38;
			moveLB.setText("   Live (x,y): "+moveX+","+moveY);
			try {
				colorLB.setText("   Live (Color): "+super.pixelColor().getRed()+","+super.pixelColor().getGreen()+","+super.pixelColor().getBlue());
			}
			catch(AWTException ex) {
			}
		}
	}
}