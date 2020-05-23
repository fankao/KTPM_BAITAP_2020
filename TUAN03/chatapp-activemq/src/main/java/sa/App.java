package sa;

import java.awt.Color;

import sa.gui.ChatGUI;

public class App {
	public static void main(String[] args) {
		new ChatGUI("Chiến", Color.BLACK).setVisible(true);
		new ChatGUI("Hùng", Color.blue).setVisible(true);
		new ChatGUI("Nhân", Color.GRAY).setVisible(true);
	}

}
