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

public class Settings extends BasePanel implements ActionListener, ItemListener {
	
	private boolean actionReady;
	
	private KPanel titlePN = new KPanel();
		private KLabel titleLB = new KLabel("SETTINGS");
	private KPanel settingsPN = new KPanel();
		private KPanel resetPN = new KPanel();
			private KButton resetBT = new KButton("  Reset all progress  ");
		private KPanel settingsPN_1 = new KPanel();
			private KLabel leftFillLB = new KLabel("...................");
			private KPanel switchesPN = new KPanel();
				private KLabel timerLB = new KLabel("Timer & Attempt Display       ");
				private KPanel timerPN = new KPanel();
					private KCheckBox timerCB = new KCheckBox(" ON   ");
				private KLabel coordLB = new KLabel("Debug Tool       ");
				private KPanel coordPN = new KPanel();
					private KCheckBox coordCB = new KCheckBox(" ON   ");
				private KLabel scaleLB = new KLabel("Screen Compatibility       ");
				private KPanel scalePN = new KPanel();
					private KRadioButton scaleCB = new KRadioButton(" ON   ");
	private KPanel confirmPN = new KPanel();
		private KLabel confirmLB = new KLabel("Are you sure you want to reset everything?");
		private KPanel confirmPN_1 = new KPanel();
			private KButton yesBT = new KButton(" I'm sure ");
			private KButton noBT = new KButton("  Cancel  ");
	private KPanel restartPN = new KPanel();
		private KLabel restartLB = new KLabel("Please restart to make changes");
		private KPanel restartPN_1 = new KPanel();
			private KButton restartBT = new KButton("    Exit    ");
			private KButton cancelBT = new KButton(" Cancel ");
	private KPanel menuPN = new KPanel();
		private KButton backBT = new KButton("                 Back                 ");
		
	Settings(){
		this.add(titlePN, BorderLayout.NORTH);
			titlePN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
			titlePN.add(titleLB);
				titleLB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
		this.add(settingsPN, BorderLayout.CENTER);
			settingsPN.setLayout(new BorderLayout(60, 10));
			settingsPN.add(resetPN, BorderLayout.SOUTH);
				resetPN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
				resetPN.add(resetBT);
					resetBT.addActionListener(this);
					resetBT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			settingsPN.add(settingsPN_1);
				settingsPN_1.add(leftFillLB, BorderLayout.WEST);
					leftFillLB.setForeground(new Color(30, 30, 30));
				settingsPN_1.add(switchesPN, BorderLayout.CENTER);
					switchesPN.setLayout(new GridLayout(3, 2));
					switchesPN.add(timerLB);
						timerLB.setHorizontalAlignment(KLabel.RIGHT);
					switchesPN.add(timerPN);
						timerPN.add(timerCB, BorderLayout.WEST);
							timerCB.addItemListener(this);
					switchesPN.add(coordLB);
						coordLB.setHorizontalAlignment(KLabel.RIGHT);
					switchesPN.add(coordPN);
						coordPN.add(coordCB, BorderLayout.WEST);
							coordCB.addItemListener(this);
					switchesPN.add(scaleLB);
						scaleLB.setHorizontalAlignment(KLabel.RIGHT);
					switchesPN.add(scalePN);
						scalePN.add(scaleCB, BorderLayout.WEST);
							scaleCB.addItemListener(this);
			confirmPN.setLayout(new GridLayout(3, 1));
			confirmPN.add(confirmLB);
				confirmLB.setVerticalAlignment(KLabel.BOTTOM);
			confirmPN.add(confirmPN_1);
				confirmPN_1.setLayout(new FlowLayout(FlowLayout.CENTER,5,50));
				confirmPN_1.add(yesBT);
					yesBT.addActionListener(this);
					yesBT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
				confirmPN_1.add(noBT);
					noBT.addActionListener(this);
					noBT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			confirmPN.add(new KPanel());
			restartPN.setLayout(new GridLayout(3, 1));
			restartPN.add(restartLB);
				restartLB.setVerticalAlignment(KLabel.BOTTOM);
			restartPN.add(restartPN_1);
				restartPN_1.setLayout(new FlowLayout(FlowLayout.CENTER,5,50));
				restartPN_1.add(restartBT);
					restartBT.addActionListener(this);
					restartBT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
				restartPN_1.add(cancelBT);
					cancelBT.addActionListener(this);
					cancelBT.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		this.add(menuPN, BorderLayout.SOUTH);
			menuPN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
			menuPN.add(backBT);
				backBT.addActionListener(this);
		if (timerBool) {
			timerCB.setSelected(true);
			timerCB.setForeground(KColor.yellow);
		}
		else {
			timerCB.setText(" OFF ");
		}
		if (scaleBool) {
			scaleCB.setSelected(true);
			scaleCB.setForeground(KColor.yellow);
		}
		else {
			scaleCB.setText(" OFF ");
		}
		if (coordBool) {
			coordCB.setSelected(true);
			coordCB.setForeground(KColor.yellow);
		}
		else {
			coordCB.setText(" OFF ");
			GameFrame.cFrame.setVisible(false);
		}
		actionReady = true;
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
		if (e.getSource() == resetBT) {
			backBT.setVisible(false);
			this.remove(settingsPN);
			this.add(confirmPN);
			this.revalidate();
			this.repaint();
		}
		if (e.getSource() == yesBT) {
			timerBool = true;
			coordBool = false;
			con.write("timerBool", "true");
			con.write("coordBool", "false");
			con.del("l1");
			con.del("l2");
			con.del("l3");
			con.del("l4");
			con.del("l5");
			con.del("l6");
			con.del("l7");
			con.del("l8");
			con.del("l9");
			con.del("l10");
			Level1.attempt = 1;
			Level2.attempt = 1;
			Level3.attempt = 1;
			Level4.attempt = 1;
			Level5.attempt = 1;
			Level6.attempt = 1;
			super.scene(new Settings());
			super.scene(new GameFrame());
		}
		if (e.getSource() == noBT) {
			this.remove(confirmPN);
			this.add(settingsPN);
			backBT.setVisible(true);
			super.scene(new Settings());
		}
		if (e.getSource() == restartBT) {
			System.exit(0);
		}
		if (e.getSource() == cancelBT) {
			if (scaleBool) {
				scaleBool = false;
			}
			else {
				scaleBool = true;
			}
			this.remove(restartPN);
			this.add(settingsPN);
			backBT.setVisible(true);
			super.scene(new Settings());
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == timerCB && e.getStateChange() == 1) {
			timerBool = true;
			con.write("timerBool","true");
			timerCB.setForeground(KColor.yellow);
			timerCB.setText(" ON   ");
		}
		if (e.getSource() == timerCB && e.getStateChange() == 2) {
			timerBool = false;
			con.write("timerBool","false");
			timerCB.setForeground(Color.white);
			timerCB.setText(" OFF ");
		}
		if (e.getSource() == scaleCB && e.getStateChange() == 1) {
			scaleBool = true;
			con.write("scaleBool","true");
			scaleCB.setForeground(KColor.yellow);
			scaleCB.setText(" ON   ");
			if (actionReady) {
				backBT.setVisible(false);
				this.remove(settingsPN);
				this.add(restartPN);
				this.revalidate();
				this.repaint();
			}
		}
		if (e.getSource() == scaleCB && e.getStateChange() == 2) {
			scaleBool = false;
			con.write("scaleBool","false");
			scaleCB.setForeground(Color.white);
			scaleCB.setText(" OFF ");
			if (actionReady) {
				backBT.setVisible(false);
				this.remove(settingsPN);
				this.add(restartPN);
				this.revalidate();
				this.repaint();
			}
		}
		if (e.getSource() == coordCB && e.getStateChange() == 1) {
			coordBool = true;
			con.write("coordBool","true");
			coordCB.setForeground(KColor.yellow);
			coordCB.setText(" ON   ");
			GameFrame.cFrame.setVisible(true);
		}
		if (e.getSource() == coordCB && e.getStateChange() == 2) {
			coordBool = false;
			con.write("coordBool","false");
			coordCB.setForeground(Color.white);
			coordCB.setText(" OFF ");
			GameFrame.cFrame.setVisible(false);
		}
	}
}