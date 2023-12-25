package Levels;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import KComponent.*;
import System.*;
import UI.*;

public class Level6 extends BasePanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	
	//******************************************************
	static String lvlCode = "l6";
	public static String lvlNum = "Ⅵ";
	public static String lvlName = "Factory";
	static int star2 = 18;
	static int star3 = 10;
	private int xOff = 0;
	private int yOff = -50;
	private Fan f1 = new Fan(1050, 200, -2, 250, -30);
	private Fan f2 = new Fan(743, 200, 0, 250, -16);
	private Fan f3 = new Fan(496, 200, 0, 250, 20);
	private Fan f4 = new Fan(225, 200, 0, 275, -16);
	private Fan f5 = new Fan(225, 472, 0, 275, 20);
	private Fan f6 = new Fan(496, 497, 0, 250, -16);
	private Fan f7 = new Fan(743, 497, 0, 250, 20);
	private GameButton button = new GameButton('b');
	private int speed = 0;
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
		
	public Level6(){
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
				start.setBounds(1325+xOff, 300+yOff, 50, 50);
			gamePN.add(end);
				end.addMouseListener(this);
				end.setBounds(1150+xOff, 596+yOff, 50, 50);
			gamePN.add(button);
				button.addMouseListener(this);
				button.setBounds(1150+xOff, 475+yOff, 50, 50);
				button.setEnabled(false);
			//******************************************************
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(KColor.bg);
		g.fillRect(0, 0, 1600, 900);
		if (ended) {
			g.setColor(KColor.bg);
			g.fillRect(0, 0, 1600, 900);
			g.setColor(KColor.yellow);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 250));
			g.drawString(starStr, 475, 400);
		}
		else {
			//******************************************************
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
			if (running) {
				g.setColor(KColor.red);
				g.drawString(""+speed, 1210+xOff, 512+yOff);
				g.setColor(KColor.gray);
			}
			else {
				g.setColor(KColor.red_off);
				g.drawString("0", 1210+xOff, 512+yOff);
				g.setColor(KColor.gray_off);
			}
			g.fillRect(1275+xOff, 300+yOff, 100, 50);
			g.fillRect(1150+xOff, 425+yOff, 50, 100);
			g.fillRect(985+xOff, 300+yOff, 70, 50);
			g.fillOval(f1.x+xOff, f1.y+yOff, f1.d, f1.d);
			g.fillOval(f2.x+xOff, f2.y+yOff, f2.d, f2.d);
			g.fillOval(f3.x+xOff, f3.y+yOff, f3.d, f3.d);
			g.fillOval(f4.x+xOff, f4.y+yOff, f4.d, f4.d);
			g.fillOval(f5.x+xOff, f5.y+yOff, f5.d, f5.d);
			g.fillOval(f6.x+xOff, f6.y+yOff, f6.d, f6.d);
			g.fillOval(f7.x+xOff, f7.y+yOff, f7.d, f7.d);
			g.fillRect(975+xOff, 596+yOff, 225, 50);
			g.fillRect(320+xOff, 418+yOff, 85, 100);
			if (running) {
				g.setColor(KColor.darkgray);
			}
			else {
				g.setColor(KColor.darkgray_off);
			}
			g.fillArc(f1.x+xOff, f1.y+yOff, f1.d, f1.d, f1.angle+0, f1.arcLength);
			g.fillArc(f1.x+xOff, f1.y+yOff, f1.d, f1.d, f1.angle+72, f1.arcLength);
			g.fillArc(f1.x+xOff, f1.y+yOff, f1.d, f1.d, f1.angle+144, f1.arcLength);
			g.fillArc(f1.x+xOff, f1.y+yOff, f1.d, f1.d, f1.angle+216, f1.arcLength);
			g.fillArc(f1.x+xOff, f1.y+yOff, f1.d, f1.d, f1.angle+288, f1.arcLength);
			g.fillArc(f2.x+xOff, f2.y+yOff, f2.d, f2.d, f2.angle+0, f2.arcLength);
			g.fillArc(f2.x+xOff, f2.y+yOff, f2.d, f2.d, f2.angle+72, f2.arcLength);
			g.fillArc(f2.x+xOff, f2.y+yOff, f2.d, f2.d, f2.angle+144, f2.arcLength);
			g.fillArc(f2.x+xOff, f2.y+yOff, f2.d, f2.d, f2.angle+216, f2.arcLength);
			g.fillArc(f2.x+xOff, f2.y+yOff, f2.d, f2.d, f2.angle+288, f2.arcLength);
			g.fillArc(f3.x+xOff, f3.y+yOff, f3.d, f3.d, f3.angle+0, f3.arcLength);
			g.fillArc(f3.x+xOff, f3.y+yOff, f3.d, f3.d, f3.angle+72, f3.arcLength);
			g.fillArc(f3.x+xOff, f3.y+yOff, f3.d, f3.d, f3.angle+144, f3.arcLength);
			g.fillArc(f3.x+xOff, f3.y+yOff, f3.d, f3.d, f3.angle+216, f3.arcLength);
			g.fillArc(f3.x+xOff, f3.y+yOff, f3.d, f3.d, f3.angle+288, f3.arcLength);
			g.fillArc(f4.x+xOff, f4.y+yOff, f4.d, f4.d, f4.angle+0, f4.arcLength);
			g.fillArc(f4.x+xOff, f4.y+yOff, f4.d, f4.d, f4.angle+72, f4.arcLength);
			g.fillArc(f4.x+xOff, f4.y+yOff, f4.d, f4.d, f4.angle+144, f4.arcLength);
			g.fillArc(f4.x+xOff, f4.y+yOff, f4.d, f4.d, f4.angle+216, f4.arcLength);
			g.fillArc(f4.x+xOff, f4.y+yOff, f4.d, f4.d, f4.angle+288, f4.arcLength);
			g.fillArc(f5.x+xOff, f5.y+yOff, f5.d, f5.d, f5.angle+0, f5.arcLength);
			g.fillArc(f5.x+xOff, f5.y+yOff, f5.d, f5.d, f5.angle+72, f5.arcLength);
			g.fillArc(f5.x+xOff, f5.y+yOff, f5.d, f5.d, f5.angle+144, f5.arcLength);
			g.fillArc(f5.x+xOff, f5.y+yOff, f5.d, f5.d, f5.angle+216, f5.arcLength);
			g.fillArc(f5.x+xOff, f5.y+yOff, f5.d, f5.d, f5.angle+288, f5.arcLength);
			g.fillArc(f6.x+xOff, f6.y+yOff, f6.d, f6.d, f6.angle+0, f6.arcLength);
			g.fillArc(f6.x+xOff, f6.y+yOff, f6.d, f6.d, f6.angle+72, f6.arcLength);
			g.fillArc(f6.x+xOff, f6.y+yOff, f6.d, f6.d, f6.angle+144, f6.arcLength);
			g.fillArc(f6.x+xOff, f6.y+yOff, f6.d, f6.d, f6.angle+216, f6.arcLength);
			g.fillArc(f6.x+xOff, f6.y+yOff, f6.d, f6.d, f6.angle+288, f6.arcLength);
			g.fillArc(f7.x+xOff, f7.y+yOff, f7.d, f7.d, f7.angle+0, f7.arcLength);
			g.fillArc(f7.x+xOff, f7.y+yOff, f7.d, f7.d, f7.angle+72, f7.arcLength);
			g.fillArc(f7.x+xOff, f7.y+yOff, f7.d, f7.d, f7.angle+144, f7.arcLength);
			g.fillArc(f7.x+xOff, f7.y+yOff, f7.d, f7.d, f7.angle+216, f7.arcLength);
			g.fillArc(f7.x+xOff, f7.y+yOff, f7.d, f7.d, f7.angle+288, f7.arcLength);
			//******************************************************
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//******************************************************
		if (e.getSource() == retryBT) {
			super.scene(new Level6());
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
			//******************************************************
			f1.angle = f1.angle + f1.shift;
			f2.angle = f2.angle + f2.shift;
			f3.angle = f3.angle + f3.shift;
			f4.angle = f4.angle + f4.shift;
			f5.angle = f5.angle + f5.shift;
			f6.angle = f6.angle + f6.shift;
			f7.angle = f7.angle + f7.shift;
			repaint();
			//******************************************************
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
		if (e.getSource() == button && running) {
			speed++;
			if (speed == 4) {
				speed = 0;
			}
			f2.shift = speed;
			f3.shift = -speed;
			f4.shift = speed;
			f5.shift = speed;
			f6.shift = -speed;
			f7.shift = speed;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//******************************************************
		//******************************************************
	}
	void gameStart() {
		running = true;
		ended = false;
		timer.restart();
		checkTimer.restart();
		backBT.setVisible(false);
		titleLB.setVisible(false);
		start.setVisible(false);
		end.setBackground(KColor.teal);
		end.setEnabled(true);
		attemptLB.setVisible(false);
		//******************************************************
		button.setBackground(KColor.red);
		speed = 0;
		button.setEnabled(true);
		//******************************************************
		this.repaint();
	}
	void gameOver() {
		running = false;
		timer.stop();
		checkTimer.stop();
		sec = 0;
		timerLB.setText(""+sec);
		backBT.setVisible(true);
		titleLB.setVisible(true);
		start.setVisible(true);
		end.setBackground(KColor.teal_off);
		end.setEnabled(false);
		attempt++;
		attemptLB.setText(" Attempt "+attempt);
		if (timerBool) {
			attemptLB.setVisible(true);
		}
		//******************************************************
		f1.reset();
		f2.reset();
		f3.reset();
		f4.reset();
		f5.reset();
		f6.reset();
		f7.reset();
		button.setBackground(KColor.red_off);
		button.setEnabled(false);
		//******************************************************
		this.repaint();
	}
	void gameEnd() {
		running = false;
		ended = true;
		timer.stop();
		checkTimer.stop();
		timeLB.setText("Time: "+sec+" secs");
		timerLB.setText(""+sec);
		attempt = 1;
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
		//******************************************************
		f1.reset();
		f2.reset();
		f3.reset();
		f4.reset();
		f5.reset();
		f6.reset();
		f7.reset();
		button.setBackground(KColor.red_off);
		button.setEnabled(false);
		//******************************************************
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