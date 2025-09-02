package Levels;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import KComponent.*;
import System.*;
import UI.*;

public class Level3 extends BasePanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	
	//******************************************************
	static String lvlCode = "l3";
	public static String lvlNum = "Ⅲ";
	public static String lvlName = "Locks & Levers";
	static int star2 = 12;
	static int star3 = 8;
	private int xOff = -50;
	private int yOff = -150;
	private GameButton motion = new GameButton('m');
	private GameButton button = new GameButton('b');
	private boolean motion_on;
	private boolean button_on;
	//******************************************************
	static Config save = new Config();
	static int sec;
	boolean running;
	boolean ended;
	boolean newBest;
	Timer timer = new Timer(1000, this);
	Timer checkTimer = new Timer(10, this);
	String starStr;
	public static int attempt = 1;
	
	KPanel lvlPN = new KPanel();
		KPanel gamePN = new KPanel();
			KButton backBT = new KButton("  Back  ");
			KLabel titleLB = new KLabel("Level "+lvlNum+"  "+lvlName);
			GameButton start = new GameButton('s');
			GameButton end = new GameButton('e');
		KPanel bottomPN = new KPanel();
			KPanel attemptPN = new KPanel();
				KLabel attemptLB = new KLabel(" Attempt "+attempt);
			KPanel timerPN = new KPanel();
				KLabel timerLB = new KLabel(""+sec);
	KPanel endPN = new KPanel();
		KLabel completeLB = new KLabel("Level "+lvlNum+" Complete!");
		KLabel star2LB = new KLabel(""+star2);
		KLabel star3LB = new KLabel(""+star3);
		KLabel timeLB = new KLabel("Time: "+sec+" secs");
		KLabel newBestLB = new KLabel("New Best!");
		KLabel bestLB = new KLabel("Best: "+save.getTime(lvlCode)+" secs");
		KPanel buttonPN = new KPanel();
			KButton retryBT = new KButton("   Retry   ");
			KButton exitBT = new KButton("    Exit    ");
		
	public Level3(){
		GameFrame.frame.addKeyListener(this);
		GameFrame.frame.setFocusable(true);
			this.add(lvlPN);
			lvlPN.add(gamePN, BorderLayout.CENTER);
				gamePN.addMouseListener(this);
				gamePN.addMouseMotionListener(this);
				gamePN.setLayout(null);
				gamePN.add(backBT);
					backBT.addActionListener(this);
					backBT.setBounds(50, 36, 227, 79);
				gamePN.add(titleLB);
					titleLB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 65));
					titleLB.setHorizontalAlignment(KLabel.LEFT);
					titleLB.setVerticalAlignment(KLabel.TOP);
					titleLB.setBounds(327, 34, 1000, 117);
			lvlPN.add(bottomPN, BorderLayout.SOUTH);
				bottomPN.setLayout(new GridLayout(1, 2));	
				bottomPN.add(attemptPN);
					attemptPN.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));
					attemptPN.add(attemptLB);
						attemptLB.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
				bottomPN.add(timerPN);
					timerPN.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 30));
					timerPN.add(timerLB);
						timerLB.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
				if (!timerBool) {
					timerLB.setVisible(false);
					attemptLB.setVisible(false);
				}
			endPN.setLayout(null);
			endPN.add(completeLB);
				completeLB.setBounds(0, 100, 1575, 100);
				completeLB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
			endPN.add(star2LB);
				star2LB.setBounds(0, 370, 1575, 100);
				star2LB.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
			endPN.add(star3LB);
				star3LB.setBounds(208, 370, 1575, 100);
				star3LB.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
			endPN.add(timeLB);
				timeLB.setBounds(0, 465, 1575, 100);
				timeLB.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
			endPN.add(buttonPN);
				buttonPN.setBounds(0, 675, 1575, 100);
				buttonPN.setLayout(new FlowLayout());
				buttonPN.add(retryBT);
					retryBT.addActionListener(this);
				buttonPN.add(exitBT);
					exitBT.addActionListener(this);
			endPN.add(newBestLB);
				newBestLB.setBounds(0, 550, 1575, 100);
				newBestLB.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
				newBestLB.setForeground(KColor.yellow);
			endPN.add(bestLB);
				bestLB.setBounds(0, 550, 1575, 100);
				bestLB.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
			//******************************************************
			gamePN.add(start);
				start.addMouseListener(this);
				start.setBounds(625+xOff, 725+yOff, 50, 50);
			gamePN.add(end);
				end.addMouseListener(this);
				end.setBounds(1025+xOff, 400+yOff, 50, 25);
			gamePN.add(motion);
				motion.addMouseListener(this);
				motion.setBounds(1225+xOff, 650+yOff, 50, 50);
			gamePN.add(button);
				button.addMouseListener(this);
				button.setBounds(500+xOff, 775+yOff, 50, 50);
			//******************************************************
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(KColor.bg);
		g.fillRect(0, 0, 1600, 900);
		if (ended) {
			g.setColor(KColor.yellow);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 250));
			g.drawString(starStr, 475, 400);
		}
		else {
			//******************************************************
			if (running) {
				g.setColor(KColor.gray);
			}
			else {
				g.setColor(KColor.gray_off);
			}
			int[] x = { 625, 925, 925,1025,1025,1275,1275,1075,1075, 925, 925,1075,1075, 875, 875, 675, 675, 425, 425, 500, 500, 550, 550, 475, 475, 625};
			int[] y = { 775, 775, 600, 600, 700, 700, 650, 650, 550, 550, 425, 425, 400, 400, 725, 725, 450, 450, 650, 650, 825, 825, 600, 600, 500, 500};
			for (int i = 0; i < x.length;  i++) {
				x[i] = x[i] + xOff;
			}
			for (int i = 0; i < y.length;  i++) {
				y[i] = y[i] + yOff;
			}
			g.fillPolygon(x, y, y.length);
			if (!motion_on) {
				if (running) {
					g.setColor(KColor.darkgray);
				}
				else {
					g.setColor(KColor.darkgray_off);
				}
				g.fillRect(575, 350, 50, 25);
			}
			if (!button_on) {
				if (running) {
					g.setColor(KColor.darkgray);
				}
				else {
					g.setColor(KColor.darkgray_off);
				}
				g.fillRect(825, 275, 50, 25);
			}
			if (running) {
				g.setColor(Color.white);
					g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
						g.drawString("Hover here!", 1240, 535);
						g.drawString("Click here!", 240, 660);
			}
			//******************************************************
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//******************************************************
		if (e.getSource() == retryBT) {
			super.scene(new Level3());
		}
		//******************************************************
		if (e.getSource() == backBT) {
			super.scene(new Play());
		}
		if (e.getSource() == timer) {
			sec++;
			timerLB.setText(""+sec);
		}
		if (e.getSource() == checkTimer) {
			try {
				Color cursor = super.pixelColor();
				if (cursor.getRed() == KColor.bg.getRed() || cursor.getRed() == KColor.darkgray.getRed()) {
					this.gameOver();
				}
			}
			catch (AWTException ex) {
			}
		}
		if (e.getSource() == exitBT) {
			super.scene(new Play());
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == start) {
			this.gameStart();
		}
		if (e.getSource() == end && running) {
			if (Math.min(sec, save.getTime(lvlCode)) == sec && (sec != save.getTime(lvlCode))) { //Math method
				newBest = true;
			}
			this.gameEnd();
		}
		//******************************************************
		if (e.getSource() == button && running) {
			if (button_on) {
				button_on = false;
			}
			else {
				button_on = true;
			}
			repaint();
		}
		//******************************************************
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//******************************************************
		if (e.getSource() == motion && running) {
			motion_on = true;
			motion.setVisible(false);
			repaint();
		}
		//******************************************************
	}
	void gameStart() {
		running = true;
		ended = false;
		timer.restart();
		backBT.setVisible(false);
		titleLB.setVisible(false);
		start.setVisible(false);
		end.setBackground(KColor.teal);
		end.setEnabled(true);
		attemptLB.setVisible(false);
		checkTimer.restart();
		//******************************************************
		motion.setBackground(KColor.orange);
		motion.setEnabled(true);
		button.setBackground(KColor.red);
		button.setEnabled(true);
		//******************************************************
		this.repaint();
	}
	void gameOver() {
		running = false;
		timer.stop();
		sec = 0;
		timerLB.setText(""+sec);
		backBT.setVisible(true);
		titleLB.setVisible(true);
		start.setVisible(true);
		end.setBackground(KColor.teal_off);
		end.setEnabled(false);
		attempt++;
		attemptLB.setText(" Attempt "+attempt);
		checkTimer.stop();
		if (timerBool) {
			attemptLB.setVisible(true);
		}
		//******************************************************
		motion.setBackground(KColor.orange_off);
		motion.setEnabled(false);
		motion.setVisible(true);
		button.setBackground(KColor.red_off);
		button.setEnabled(false);
		motion_on = false;
		button_on = false;
		//******************************************************
		this.repaint();
	}
	void gameEnd() {
		running = false;
		ended = true;
		timer.stop();
		timeLB.setText("Time: "+sec+" secs");
		timerLB.setText(""+sec);
		attempt = 1;
		checkTimer.stop();
		if (newBest) {
			con.write(lvlCode, ""+sec);
			newBestLB.setVisible(true);
			bestLB.setVisible(false);
		}
		else {
			newBestLB.setVisible(false);
			bestLB.setVisible(true);
		}
		if (sec > star2) {
			starStr = "★☆☆";
		}
		else if (sec > star3) {
			starStr = "★★☆";
		}
		else if (sec <= star3) {
			starStr = "★★★";
		}
		sec = 0;
		this.remove(lvlPN);
		this.revalidate();
		this.repaint();
		this.add(endPN);
	}
	public static int getStars() {
		int getStars = 0;
		if (save.isCompleted(lvlCode)) {
			if (con.getTime(lvlCode) > star2) {
				getStars = 1;
			}
			else if (con.getTime(lvlCode) > star3) {
				getStars = 2;
			}
			else if (con.getTime(lvlCode) <= star3) {
				getStars = 3;
			}
		}
		return getStars;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			Color cursor = super.pixelColor();
			if (running) {
				if (cursor.getRed() == KColor.bg.getRed() || cursor.getRed() == KColor.darkgray.getRed()) {
					this.gameOver();
				}
			}
			if (coordBool) {
				GameFrame.moveX = e.getX();
				GameFrame.moveY = e.getY();
				GameFrame.moveLB.setText("   Live (x,y): "+GameFrame.moveX+","+GameFrame.moveY);
				GameFrame.colorLB.setText("   Live (Color): "+cursor.getRed()+","+cursor.getGreen()+","+cursor.getBlue());
			}
		}
		catch (AWTException ex) {
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (coordBool) {
			int clickX = e.getX();
			int clickY = e.getY();
			GameFrame.clickLB.setText("   Click (x,y): "+clickX+","+clickY);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (running) {
			this.gameOver();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}