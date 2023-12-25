package KComponent;
import javax.swing.*;
import java.awt.*;

public class KPanel extends JPanel {
	
	public KPanel(){
		super();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
	}
	
	public void checkBounds() {
		this.checkBounds(Color.cyan);
	}
	public void checkBounds(Color color) {
		this.setOpaque(true);
		this.setBackground(color);
	}
	
}