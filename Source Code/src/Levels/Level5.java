package Levels;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import KComponent.*;
import System.*;
import UI.*;

public class Level5 extends BasePanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	
	//******************************************************
	static String lvlCode = "l5";
	public static String lvlNum = "Ⅴ";
	public static String lvlName = "Alphabets";
	static int star2 = 13;
	static int star3 = 9;
	private int xOff = 0;
	private int yOff = -50;
	private GameButton motion_a = new GameButton('m');
	private GameButton button_a = new GameButton('b');
	private GameButton button_b = new GameButton('B');
	private GameButton button_c = new GameButton('B');
	private GameButton button_d = new GameButton('B');
	private GameButton motion_e1 = new GameButton('M');
	private GameButton motion_e2 = new GameButton('M');
	private char alphabet = 'a';
	private boolean motion_a_on;
	private boolean motion_e1_on;
	private boolean motion_e2_on;
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
		
	public Level5(){
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
				start.setBounds(545+xOff, 585+yOff, 40, 40);
			gamePN.add(end);
				end.addMouseListener(this);
				end.setBounds(1015+xOff, 343+yOff, 40, 40);
				end.setVisible(false);
			gamePN.add(motion_a);
				motion_a.addMouseListener(this);
				motion_a.setBounds(750+xOff, 585+yOff, 40, 40);
			gamePN.add(button_a);
				button_a.addMouseListener(this);
				button_a.setBounds(645+xOff, 516+yOff, 40, 40);
			gamePN.add(button_b);
				button_b.addMouseListener(this);
				button_b.setBounds(750+xOff, 510+yOff, 40, 40);
			gamePN.add(button_c);
				button_c.addMouseListener(this);
				button_c.setBounds(748+xOff, 355+yOff, 40, 40);
			gamePN.add(button_d);
				button_d.addMouseListener(this);
				button_d.setBounds(858+xOff, 347+yOff, 40, 40);
			gamePN.add(motion_e1);
				motion_e1.addMouseListener(this);
				motion_e1.setBounds(1020+xOff, 583+yOff, 40, 40);
			gamePN.add(motion_e2);
				motion_e2.addMouseListener(this);
				motion_e2.setBounds(1002+xOff, 453+yOff, 40, 40);
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
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 400));
			if (alphabet == 'a') {
				if (running) {
					g.setColor(KColor.gray);
					g.drawString("A", 525+xOff, 625+yOff);
					g.setColor(KColor.darkgray);
				}
				else {
					g.setColor(KColor.gray_off);
					g.drawString("A", 525+xOff, 625+yOff);
					g.setColor(KColor.darkgray_off);
				}
				if (!motion_a_on) {
					int[] x1 = { 610, 627, 706, 724};
					int[] y1 = { 560, 511, 511, 560};
					for (int i = 0; i < x1.length;  i++) {
						x1[i] = x1[i] + xOff;
					}
					for (int i = 0; i < y1.length;  i++) {
						y1[i] = y1[i] + yOff;
					}
					g.fillPolygon(x1, y1, y1.length);
				}
			}
			if (alphabet == 'b') {
				g.setColor(KColor.gray);
				g.drawString("B", 605+xOff, 555+yOff);
				g.setColor(KColor.bg);
				g.fillRect(693+xOff, 350+yOff, 50, 500);
			}
			if (alphabet == 'c') {
				g.setColor(KColor.gray);
				g.drawString("C", 540+xOff, 600+yOff);
			}
			if (alphabet == 'd') {
				g.setColor(KColor.gray);
				g.drawString("D", 715+xOff, 625+yOff);
				g.setColor(KColor.bg);
				g.fillRect(802+xOff, 310+yOff, 50, 200);
			}
			if (alphabet == 'e') {
				g.setColor(KColor.gray);
				g.drawString("E", 820+xOff, 625+yOff);
				g.setColor(KColor.darkgray);
				if (!motion_e1_on) {
					g.fillRect(907+xOff, 450+yOff, 144, 50);
				}
				if (!motion_e2_on) {
					g.fillRect(907+xOff, 338+yOff, 155, 50);
				}
			}
			//******************************************************
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//******************************************************
		if (e.getSource() == retryBT) {
			super.scene(new Level5());
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
		if (e.getSource() == button_a && running) {
			alphabet = 'b';
			button_a.setVisible(false);
			button_b.setVisible(true);
			repaint();
		}
		if (e.getSource() == button_b) {
			alphabet = 'c';
			button_b.setVisible(false);
			button_c.setVisible(true);
			repaint();
		}
		if (e.getSource() == button_c) {
			alphabet = 'd';
			button_c.setVisible(false);
			button_d.setVisible(true);
			repaint();
		}
		if (e.getSource() == button_d) {
			alphabet = 'e';
			button_d.setVisible(false);
			end.setVisible(true);
			motion_e1.setVisible(true);
			motion_e2.setVisible(true);
			repaint();
		}
		//******************************************************
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//******************************************************
		if (e.getSource() == motion_a && running) {
			motion_a_on = true;
			motion_a.setVisible(false);
			repaint();
		}
		if (e.getSource() == motion_e1 && running) {
			motion_e1_on = true;
			motion_e1.setVisible(false);
			repaint();
		}
		if (e.getSource() == motion_e2 && running) {
			motion_e2_on = true;
			motion_e2.setVisible(false);
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
		motion_a.setBackground(KColor.orange);
		motion_a.setEnabled(true);
		button_a.setBackground(KColor.red);
		button_a.setEnabled(true);
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
		motion_a.setBackground(KColor.orange_off);
		motion_a.setEnabled(false);
		motion_a.setVisible(true);
		button_a.setBackground(KColor.red_off);
		button_a.setEnabled(false);
		alphabet = 'a';
		motion_a_on = false;
		button_a.setVisible(true);
		button_b.setVisible(false);
		button_c.setVisible(false);
		button_d.setVisible(false);
		motion_e1.setVisible(false);
		motion_e1_on = false;
		motion_e2.setVisible(false);
		motion_e2_on = false;
		end.setVisible(false);
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