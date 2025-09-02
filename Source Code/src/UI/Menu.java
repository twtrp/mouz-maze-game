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

public class Menu extends BasePanel implements ActionListener {

	private KPanel menuPN = new KPanel();
		private KButton playBT = new KButton("        Play        ");
		private KButton settingsBT = new KButton(" Settings ");
	
	Menu(){
		this.add(new KPanel(), BorderLayout.CENTER);
		this.add(menuPN, BorderLayout.SOUTH);
			menuPN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
			menuPN.add(playBT);
				playBT.addActionListener(this);
				playBT.setBackground(new Color(52, 118, 130));
			menuPN.add(settingsBT);
				settingsBT.addActionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(KColor.bg);
			g.fillRect(0, 0, 1600, 900);
		g.setColor(Color.white);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 200));
				g.drawString("mouz", 450+200, 285+60);
				g.drawString("maze", 450+200, 440+60);
			int[] x = {250+200, 250+200, 300+200, 325+200, 345+200, 320+200, 370+200};
			int[] y = {225+60, 400+60, 365+60, 420+60, 410+60, 355+60, 355+60};
			g.fillPolygon(x, y, y.length);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
				g.drawString("(c) 2022 ttewtor", 10, 820);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playBT) {
			super.scene(new Play());
		}
		if (e.getSource() == settingsBT) {
			super.scene(new Settings());
		}
	}
}
