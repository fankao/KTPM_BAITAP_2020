package sa.app;

import sa.app.entity.BacSi;
import sa.app.gui.DoctorGUI;
import sa.app.gui.PatientGUI;

public class App {
	public static void main(String[] args) {
	
		try {
			showPatientGUI("Lễ tân");
			showDoctorGUI(new BacSi("BS001", "An"));
			showDoctorGUI(new BacSi("BS002", "Kim"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void showPatientGUI(String title) {
		PatientGUI gui = new PatientGUI(title);
		gui.setSize(643,463);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
	private static void showDoctorGUI(BacSi bs) throws Exception {
		DoctorGUI gui = new DoctorGUI(bs);
		gui.setSize(1000,600);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
}
