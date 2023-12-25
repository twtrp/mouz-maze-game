import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import KComponent.*;
import UI.*;

public class RunMouzMaze {
	
	public static void main(String[] args) {
		if (BasePanel.scaleBool == true) {
			System.setProperty("sun.java2d.uiScale", "1");
		}
		new GameFrame();
	}
}