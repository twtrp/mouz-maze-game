package UI;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import KComponent.*;
import System.*;
import Levels.*;

public class BasePanel extends KPanel {

	public static Config con = new Config();
		public static boolean timerBool = con.getConfig("timerBool");
		public static boolean coordBool = con.getConfig("coordBool");
		public static boolean scaleBool = con.getConfig("scaleBool");
	
	protected void scene(BasePanel scene) { //Implicit Casting
		this.removeAll();
		this.revalidate();
		this.add(scene);
	}
	protected Color pixelColor() throws AWTException {
		PointerInfo pointer = MouseInfo.getPointerInfo();
		Point point = pointer.getLocation();
		Robot robot = new Robot();
	    return robot.getPixelColor((int)point.getX(), (int)point.getY()); //Explicit casting
	}
	protected void print(String text) {
		System.out.println(text);
	}
}