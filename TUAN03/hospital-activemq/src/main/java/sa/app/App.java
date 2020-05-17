package sa.app;

import sa.app.gui.DoctorGUI;
import sa.app.gui.PatientGUI;

public class App {
	public static void main(String[] args) {
	
		try {
			showPatientGUI("Lễ tân");
			showDoctorGUI("Bác sĩ khám bệnh");
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
	private static void showDoctorGUI(String title) throws Exception {
		DoctorGUI gui = new DoctorGUI(title);
		gui.setSize(1000,600);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
}
