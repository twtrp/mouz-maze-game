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

public class Fan {
	
	int angleStart = 0;
	int arcLengthStart = 32;
	int dStart = 250;
	int shiftStart = 2;
	int xStart;
	int yStart;
	
	int angle = 0;
	int arcLength = 32;
	int d = 250;
	int shift = 2;
	int x;
	int y;
	
	Fan(int x, int y){
		this.x = x;
		this.y = y;
		xStart = x;
		yStart = y;
	}
	Fan(int x, int y, int shift){
		this(x, y);
		this.shift = shift;
		shiftStart = shift;
	}
	Fan(int x, int y, int shift, int diameter){
		this(x, y, shift);
		this.d = diameter;
		dStart = d;
	}
	Fan(int x, int y, int shift, int diameter, int angle){
		this(x, y, shift, diameter);
		this.angle = angle;
		angleStart = angle;
	}
	Fan(int x, int y, int shift, int diameter, int angle, int arcLength){
		this(x, y, shift, diameter, angle);
		arcLengthStart = arcLength;
	}
	
	
	void reset() {
		angle = angleStart;
		d = dStart;
		shift = shiftStart;
		x = xStart;
		y = yStart;
	}
}