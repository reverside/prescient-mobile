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

public class WelcomePage extends Activity {

	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);
		addListenerOnButton();
		Toast.makeText(getApplicationContext(), R.string.welcome_to_prescient,
				Toast.LENGTH_SHORT).show();
	}

	public void addListenerOnButton() {
		final Context context = this;
		((Button) findViewById(R.id.start_btn))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(context, LoginPage.class);
						startActivity(intent);
					}
				});
	}
}
