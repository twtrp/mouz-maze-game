package KComponent;
import javax.swing.*;
import java.awt.*;

public class KButton extends JButton {
	
	public KButton(){
		this("");
	}
	public KButton(String text){
		super(text);
		this.setFocusPainted(false);
		this.setBackground(new Color(70, 70, 70));
		this.setForeground(Color.white);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
	}
}
