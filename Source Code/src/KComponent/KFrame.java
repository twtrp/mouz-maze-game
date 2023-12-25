package KComponent;
import javax.swing.*;
import java.awt.*;

public class KFrame extends JFrame {
	
	public KFrame(){
		this("");
	}
	public KFrame(String text){
		super(text);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
}