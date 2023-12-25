package KComponent;
import javax.swing.*;
import java.awt.*;

public class KRadioButton extends JRadioButton {

	public KRadioButton(String text){
		super(text);
		this.setFocusPainted(false);
		this.setOpaque(false);
		this.setVerticalAlignment(JCheckBox.CENTER);
		this.setHorizontalAlignment(JCheckBox.CENTER);
		this.setForeground(Color.white);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
	}
	
	public void checkBounds() {
		this.setOpaque(true);
		this.setBackground(Color.cyan);
	}
}
