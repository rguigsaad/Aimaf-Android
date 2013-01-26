package fr.aimaf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class NotificationSender {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String myApiKey="AIzaSyAkwaEWB2vrhYdotzHtMi_owUiHVG4mFag";
		Sender sender = new Sender(myApiKey);
		Message message = new Message.Builder().addData("message", "First Notification ! ").build();
		ArrayList<String> devices = new ArrayList<String>();
		devices.add("APA91bE_mimMvBEunE880Xal-RIlEH7sBFjx4XkKOVL5a9nenJpd76NaQRDUqGyrr2eIOkz0PxpWSzy2stkgM1SAG7MkNENRE08S8vkxOwi2Q-yMJigEQDOczH0zk5Gbj803bmOHLuloYBz29Hzi41gvChxvrmetPw");

		MulticastResult resultMulti = sender.send(message, devices, 5);
		List<Result> results = resultMulti.getResults();

		// Parsing des resultats
		for (int i = 0; i < devices.size(); i++) {
			Result result = results.get(i);

			if (result.getMessageId() != null) {
				System.out.println(" --> Succesfully sent message to device #" + i);

				String canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null) {
					devices.set(i, canonicalRegId);
					// < MISE A JOUR DU TOKEN EN BASE >
				}
			} else {
				String error = result.getErrorCodeName();

				if (Constants.ERROR_NOT_REGISTERED.equals(error)) {
					// < DESACTIVATION DU TOKEN EN BASE >
				} else {
					// < SYSTEME DE REJET >
				}
			}
		}

	}
}
