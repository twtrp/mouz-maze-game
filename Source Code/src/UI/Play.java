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

public class Play extends BasePanel implements ActionListener {
		
	private KPanel titlePN = new KPanel();
		private KLabel titleLB = new KLabel("PLAY");
	private KPanel playPN = new KPanel();
		private KPanel playPN_1 = new KPanel();
			private PlayBT lvl1BT = new PlayBT("l1", "Ⅰ", "First Steps");
			private PlayBT lvl2BT = new PlayBT("l2", "Ⅱ", "Onion");
			private PlayBT lvl3BT = new PlayBT("l3", "Ⅲ", "Locks & Levers");
			private PlayBT lvl4BT = new PlayBT("l4", "Ⅳ", "Claustrophobia");
			private PlayBT lvl5BT = new PlayBT("l5", "Ⅴ", "Alphabets");
			private PlayBT lvl6BT = new PlayBT("l6", "Ⅵ", "Factory");
	private KPanel menuPN = new KPanel();
		private KButton backBT = new KButton("                 Back                 ");
	
	public Play(){
		this.add(titlePN, BorderLayout.NORTH);
			titlePN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
			titlePN.add(titleLB); 
				titleLB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
				titleLB.setForeground(new Color(152, 221, 235));
		this.add(playPN, BorderLayout.CENTER);
			playPN.setLayout(new BorderLayout(60, 40));
			playPN.add(new KPanel(), BorderLayout.WEST);
			playPN.add(new KPanel(), BorderLayout.EAST);
			playPN.add(new KPanel(), BorderLayout.NORTH);
			playPN.add(new KPanel(), BorderLayout.SOUTH);
			playPN.add(playPN_1, BorderLayout.CENTER);
				playPN_1.setLayout(new GridLayout(3, 2));
				playPN_1.add(lvl1BT);
					lvl1BT.addActionListener(this);
				playPN_1.add(lvl4BT);
					lvl4BT.addActionListener(this);
				playPN_1.add(lvl2BT);
					lvl2BT.addActionListener(this);
				playPN_1.add(lvl5BT);
					lvl5BT.addActionListener(this);
				playPN_1.add(lvl3BT);
					lvl3BT.addActionListener(this);
				playPN_1.add(lvl6BT);
					lvl6BT.addActionListener(this);
		this.add(menuPN, BorderLayout.SOUTH);
			menuPN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
			menuPN.add(backBT);
				backBT.addActionListener(this);
		if (con.saveSize() <= 2) {
			lvl6BT.lock();
			if (con.saveSize() <= 1) {
				lvl4BT.lock();
				lvl5BT.lock();
				if (con.saveSize() == 0) {
					lvl2BT.lock();
					lvl3BT.lock();
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(KColor.bg);
			g.fillRect(0, 0, 1600, 900);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBT) {
			super.scene(new Menu());
		}
		if (e.getSource() == lvl1BT) {
			super.scene(new Level1());
		}
		if (e.getSource() == lvl2BT) {
			super.scene(new Level2());
		}
		if (e.getSource() == lvl3BT) {
			super.scene(new Level3());
		}
		if (e.getSource() == lvl4BT) {
			super.scene(new Level4());
		}
		if (e.getSource() == lvl5BT) {
			super.scene(new Level5());
		}
		if (e.getSource() == lvl6BT) {
			super.scene(new Level6());
		}
	}
}

class PlayBT extends KButton {
	
	private String lvl;
	private Config con = new Config();
	
	PlayBT(String lvlCode, String lvl, String text){
		super();
		int starsAmt = 0;
		if (lvlCode == "l1") {
			starsAmt = Level1.getStars();
		}
		if (lvlCode == "l2") {
			starsAmt = Level2.getStars();
		}
		if (lvlCode == "l3") {
			starsAmt = Level3.getStars();
		}
		if (lvlCode == "l4") {
			starsAmt = Level4.getStars();
		}
		if (lvlCode == "l5") {
			starsAmt = Level5.getStars();
		}
		if (lvlCode == "l6") {
			starsAmt = Level6.getStars();
		}
		if (starsAmt == 0) {
			this.setText("⠀⠀⠀⠀ "+lvl+"  "+text);
		}
		else if (starsAmt == 1) {
			this.setText("★⠀⠀⠀"+lvl+"  "+text+" :  "+con.getTime(lvlCode)+" secs");
		}
		else if (starsAmt == 2) {
			this.setText("★★⠀  "+lvl+"  "+text+" :  "+con.getTime(lvlCode)+" secs");
		}
		else if (starsAmt == 3) {
			this.setText("★★★ "+lvl+"  "+text+" :  "+con.getTime(lvlCode)+" secs");
		}
		this.lvl = lvl;
		this.setBackground(new Color(85, 85, 85));
		this.setHorizontalAlignment(KButton.LEFT);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
	}
	
	public void lock() {
		this.setEnabled(false);
		this.setText("⠀⠀⠀⠀ "+lvl+"  Locked");
		this.setBackground(new Color(30, 30, 30));
	}
}