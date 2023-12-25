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

public class GameButton extends KButton {
	
	public GameButton(char type){
		if (type == 's') {
			this.setBackground(Color.white);
		}
		if (type == 'e') {
			this.setBackground(KColor.teal_off);
			this.setEnabled(false);
		}
		if (type == 'm') {
			this.setBackground(KColor.orange_off);
			this.setEnabled(false);
		}
		if (type == 'M') {
			this.setBackground(KColor.orange);
			this.setEnabled(true);
			this.setVisible(false);
		}
		if (type == 'b') {
			this.setBackground(KColor.red_off);
			this.setEnabled(false);
		}
		if (type == 'B') {
			this.setBackground(KColor.red);
			this.setEnabled(true);
			this.setVisible(false);
		}
	}
}
