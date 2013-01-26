package fr.aimaf;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button = (Button) findViewById(R.id.sign_in);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
				startActivity(intent);

				Toast toast = Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_LONG);
				toast.show();
				
				overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
