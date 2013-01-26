package fr.aimaf;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService{

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@SuppressLint("NewApi")
	@Override
	protected void onMessage(Context context, Intent intent) {
		String message = intent.getStringExtra("message"); // data.message contient le texte de la notification
		int iconId = R.drawable.ic_launcher;
		final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		final Intent launchNotifiactionIntent = new Intent(context, GCMIntentService.class);
		final PendingIntent pendingIntent = PendingIntent.getActivity(context,
				0, launchNotifiactionIntent,
				PendingIntent.FLAG_ONE_SHOT);

		Notification.Builder builder = new Notification.Builder(context)
		.setWhen(System.currentTimeMillis())
		.setTicker("AIMAF NOTIFICATION")
		.setSmallIcon(iconId)
		.setContentTitle("TITLE HERE")
		.setContentText(message)
		.setContentIntent(pendingIntent);

		mNotification.notify(1, builder.build());
	}

	@Override
	protected void onRegistered(Context context, String registrationID) {
		Log.i(" ********* RegistrationID",registrationID);
		Toast toast = Toast.makeText(getApplicationContext(), registrationID, Toast.LENGTH_LONG);
		toast.show();

	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
