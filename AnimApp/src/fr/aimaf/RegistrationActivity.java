package fr.aimaf;

import java.util.ArrayList;

import com.google.android.gcm.GCMRegistrar;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity{

	protected static final String TAG = "RegistrationActivity";
	protected static final String SENDER_ID = "860212904673";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_layout);
		
		final Button button = (Button) findViewById(R.id.button_save);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				GCMRegistrar.checkDevice(RegistrationActivity.this);
				GCMRegistrar.checkManifest(RegistrationActivity.this);
				final String regId = GCMRegistrar.getRegistrationId(RegistrationActivity.this);
				if (regId.equals("")) {
				  GCMRegistrar.register(RegistrationActivity.this, SENDER_ID);
				} else {
				  Log.v(TAG, "Already registered");
				}
			}
		});
		
		// Display accounts
        String accounts[] = getGoogleAccounts();
        if (accounts.length == 0) {
			Toast toast = Toast.makeText(getApplicationContext(), "Aucun compte enregistré", Toast.LENGTH_LONG);
			toast.show();
        } else {
            EditText mEditEmail = (EditText)findViewById(R.id.email_text);
            mEditEmail.setText(accounts[0]);
            
            EditText mEditPseudo = (EditText)findViewById(R.id.pseudo_text);
            mEditPseudo.setText(accounts[0].subSequence(0, accounts[0].indexOf("@")));
        }

	}

	private String[] getGoogleAccounts() {
        ArrayList<String> accountNames = new ArrayList<String>();
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (account.type.equals("com.google")) {
                accountNames.add(account.name);
            }
        }

        String[] result = new String[accountNames.size()];
        accountNames.toArray(result);
        return result;
    }
}
