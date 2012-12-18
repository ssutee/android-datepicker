package tw.travelingtwins.datepicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import tw.travelingtwins.datepicker.widgets.MonthView;
import tw.travelingtwins.datepicker.widgets.MonthViewDialog;
import tw.travelingtwins.datepicker.widgets.MonthViewDialog.OnDateSetLintener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DialogDemo extends Activity implements OnDateSetLintener {

	private MonthViewDialog mDialog;
	private Calendar mDate = Calendar.getInstance();
	private SimpleDateFormat mFormat = new SimpleDateFormat("dd MMMM yyyy",
			Locale.US);
	private TextView txtDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_demo);
		txtDate = (TextView) findViewById(R.id.txtDate);
	}

	public void showDialog(View view) {
		mDialog = new MonthViewDialog(this, this, mDate.get(Calendar.YEAR),
				mDate.get(Calendar.MONTH), mDate.get(Calendar.DAY_OF_MONTH));
		mDialog.show();
	}

	@Override
	public void onDateSet(MonthView view, int year, int month, int dayOfMonth) {
		mDate.set(Calendar.YEAR, year);
		mDate.set(Calendar.MONTH, month);
		mDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		txtDate.setText(mFormat.format(mDate.getTime()));
	}

}
