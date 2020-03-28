package controller;

import app.Main;
import view.Toast;

public class ToastThread extends Thread {
	
	private String toastmsg;
	
	public ToastThread(String message) {
		this.toastmsg = message;
	}
	
	public void run() {	
		
		
		try {

			showToast(this.toastmsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	private void showToast(String toastMsg) {
		int toastMsgTime = 5000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
}
