package KComponent;
import javax.swing.*;
import java.awt.*;

public class KLabel extends JLabel {
	
	public KLabel(){
		this("");
	}
	public KLabel(String text){
		super(text);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(Color.white);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
	}
	
	public void checkBounds() {
		this.setOpaque(true);
		this.setBackground(Color.cyan);
	}
}