package za.co.prescient.activity;

import za.co.prescient.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AdminFindGuest extends Activity {
	
	Button button;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_find_guest_page);
		addListenerOnButton();
		Toast.makeText(getApplicationContext(), "Find Guest -> Work in progress", Toast.LENGTH_SHORT).show();
	}
 
	public void addListenerOnButton() {
 
		final Context context = this;
		
		button = (Button) findViewById(R.id.button6);
		button.setEnabled(false);
		button = (Button) findViewById(R.id.button7);
		button.setEnabled(false);
		button = (Button) findViewById(R.id.button8);
		button.setEnabled(false);
 
//		button = (Button) findViewById(R.id.logout);
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, LoginPage.class);
                            startActivity(intent);   
 
			}
 
		});
		
	}

}
