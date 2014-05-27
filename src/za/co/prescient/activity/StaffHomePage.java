package za.co.prescient.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import za.co.prescient.R;
import za.co.prescient.activity.model.TouchPoint;

import java.util.ArrayList;
import java.util.List;

public class StaffHomePage extends Activity implements  OnClickListener{

    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home_page);


        TextView tv=(TextView)findViewById(R.id.prescientName1);
        tv.setText(Html.fromHtml(getString(R.string.prescient_name)));

        //check session here
        session = new SessionManager(getApplicationContext());
        Log.i("check", "checking the session");
        session.checkSession();

        String userName = this.getIntent().getStringExtra("name");
        ((TextView) findViewById(R.id.userName)).setText(userName);
        Log.i("name : ", userName);
        Toast.makeText(getApplicationContext(), "Welcome " + userName.toUpperCase(), Toast.LENGTH_SHORT).show();


        Button button=(Button)findViewById(R.id.find_guest);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        Log.i("FIND A GUEST","is clicked");

        Context context = getApplicationContext();

        try {
            String data = ServiceInvoker.getAllAssignedTouchPoint(session.getToken());
            // Toast.makeText(getApplicationContext(), "DATA:: " + data, Toast.LENGTH_SHORT).show();
            Log.i("JSON DATA::",data);


            List<TouchPoint> touchPointList=new ArrayList<TouchPoint>();
            JSONArray touchPointArray = new JSONArray(data);

            for (int i = 0; i < touchPointArray.length(); i++) {
                JSONObject obj = (JSONObject) touchPointArray.get(i);
                TouchPoint touchPoint=new TouchPoint();
                touchPoint.setId(obj.getLong("id"));
                touchPoint.setName(obj.getString("name"));

                touchPointList.add(touchPoint);
            }
            Log.i("ALL TP OBJECTS::",touchPointList.toString());
            //set the touchpointList in Application data
            ApplicationData.touchPointList=touchPointList;

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            Log.i("exception", e.getMessage());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.opt1:
                logOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logOut()
    {
        session.logOut();
        Log.i(" staff Login status after logout::", Boolean.toString(session.isLoggedIn()));

        Intent intent = new Intent(getApplicationContext(), LoginPage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }


}
