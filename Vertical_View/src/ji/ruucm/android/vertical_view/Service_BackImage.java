package ji.ruucm.android.vertical_view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;



public class Service_BackImage extends Service {

	
	static Service_BackImage instance;
	
	
	public static Service_BackImage getIntance() {

		return instance;
    }
	
	
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
    public void onCreate() {
		instance = this;
        Toast.makeText(this, "The Service_BackImage was Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStart(Intent intent, int startId) {
    	// For time consuming an long tasks you can launch a new thread here...
    	
    	
    	/*//Threadfor send Auto message
    	AsyncTask02 sendAutomessage = new AsyncTask02();
        sendAutomessage.execute("");
    	
    	 // Thread for change background img
 		AsyncTask01 changebackground = new AsyncTask01();
 		changebackground.execute("");
        Toast.makeText(this, " Service_BackImage Started", Toast.LENGTH_LONG).show();
*/
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service_BackImage Destroyed", Toast.LENGTH_LONG).show();

    }
	
	
	
	
	
	  
	
};