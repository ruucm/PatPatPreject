package ji.ruucm.android.vertical_view.fragment;

import java.util.ArrayList;

import ji.ruucm.android.chat.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Frag02 extends DialogFragment  {

	
	//for intent comunication
	final static int ACT_EDIT = 0;
	private static final int RESULT_OK = -1;
	
	//for custom dialog
	Dialog dialog = null;
	private static final int CUSTOM_DIALOG 	= 1;
	private static final int DEFAULT_DIALOG	= 2;

	
	
	// for changing callender
	ImageView i,i2;
	ArrayList a,a2;
	static int currentcal;

	// public static TextPref fbPref;

	static boolean init;
	public static boolean lv0_1;
	static boolean lv0_2;
	public static boolean lv1;
	public static boolean lv2;
	public static boolean lv3_1;

	static View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			final Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.frag02, container, false);

		a = new ArrayList();
		a2 = new ArrayList();

		a.add(R.drawable.month_august);
		a.add(R.drawable.month_september);
		a.add(R.drawable.month_october);
		a.add(R.drawable.month_november);
		a.add(R.drawable.month_december);
		
		
		a2.add(R.drawable.monthcontent_aug);
		a2.add(R.drawable.monthcontent_sep);
		a2.add(R.drawable.monthcontent_octo);
		a2.add(R.drawable.monthcontent_novem);
		a2.add(R.drawable.monthcontent_decem);
		
		

		i = (ImageView) v.findViewById(R.id.month);
		i2 = (ImageView) v.findViewById(R.id.monthcontent);

		currentcal = 1;
		i.setImageResource((Integer) a.get(currentcal));
		i2.setImageResource((Integer) a2.get(currentcal));

		// setListener
		
		ImageView arrow01 = (ImageView) v.findViewById(R.id.arrow01);

		arrow01.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (currentcal<4)
					currentcal++;
				
				i.setImageResource((Integer)a.get(currentcal));
				i2.setImageResource((Integer)a2.get(currentcal));
				Log.d("Frag02","currentcal++ "+currentcal);

			}
		});

		arrow01.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "arrow01...", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
		});
		
		
		ImageView arrow02 = (ImageView) v.findViewById(R.id.arrow02);

		arrow02.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (currentcal > 0)
					currentcal--;
				i.setImageResource((Integer)a.get(currentcal));
				i2.setImageResource((Integer)a2.get(currentcal));

			}
		});

		arrow02.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "arrow02...", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
		});
		
		
		ImageView add_button = (ImageView) v.findViewById(R.id.add_button);
		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				
				
				Intent a = new Intent(getActivity(),Activity_ScheduleAdd.class);
				startActivityForResult (a, ACT_EDIT);
				
				
//				getActivity().showDialog(CUSTOM_DIALOG);
//				getActivity().showDialog();
//				onCreateDialog(CUSTOM_DIALOG).show();
				
				
				/*Dialog_DatePicker a = new Dialog_DatePicker();
				a.show(getFragmentManager(), "datePicker");*/
				
				
				
				Log.d("Frag02","getActivity() "+getActivity());
				
				/*CustomDialog.Builder customBuilder = new
						CustomDialog.Builder(getActivity());
						customBuilder.setTitle("Custom title")
							.setMessage("Custom body")
							.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									getActivity()
									.dismissDialog(CUSTOM_DIALOG);
								}
							})
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							});
			            dialog = customBuilder.create();
				*/
			}
		});
		
		
	/*	
		ImageView add02 = (ImageView) v.findViewById(R.id.add_date);
		add_button02.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Dialog_DatePicker a = new Dialog_DatePicker();
				a.show(getFragmentManager(), "datePicker");
				
				
			}
		});*/
		
		

		return v;

	}
/*	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		
		Dialog dialog = null;
		Dialog_Custom.Builder customBuilder = new
				Dialog_Custom.Builder(getActivity());
				customBuilder.setTitle("Custom title")
					.setMessage("Custom body")
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							getActivity()
							.dismissDialog(CUSTOM_DIALOG);
						}
					})
					.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
	            dialog = customBuilder.create();
	    return dialog;
	}
	
	
	
    public Dialog onCreateDialog(int dialogId) {
    	Dialog dialog = null;
    	
    	Log.d("Frag02","onCreateDialog, getActivity() "+getActivity());
    	switch (dialogId) {
	    	case CUSTOM_DIALOG :
	    		
	    		Log.d("Frag02","CUSTOM_DIALOG getActivity() "+getActivity());
				Dialog_Custom.Builder customBuilder = new
				Dialog_Custom.Builder(getActivity());
				customBuilder.setTitle("Custom title")
					.setMessage("Custom body")
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							getActivity()
							.dismissDialog(CUSTOM_DIALOG);
						}
					})
					.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
	            dialog = customBuilder.create();
	    		break;
	    	case DEFAULT_DIALOG :
				AlertDialog.Builder alertBuilder = new
				AlertDialog.Builder(getActivity());
				alertBuilder.setTitle("Default title")
					.setMessage("Default body")
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							getActivity()
							.dismissDialog(DEFAULT_DIALOG);
						}
					});
	            dialog = alertBuilder.create();
	    		break;
    	}
    	return dialog;
    }*/
    
    
	public void onActivityResult (int requestCode, int resultCode, Intent data) {
		
		
		super.onActivityResult(requestCode, resultCode, data);
		
		
			if (resultCode == RESULT_OK) {
				
				Toast.makeText(getActivity(), "The \""+data.getStringExtra("schedulename")+"\" schedule was added", Toast.LENGTH_LONG).show();
			}
	}

	
	
    
    
    
    
}
