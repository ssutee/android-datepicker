package tw.travelingtwins.datepicker.widgets;

import java.util.Calendar;

import tw.travelingtwins.datepicker.R;
import tw.travelingtwins.datepicker.widgets.MonthView.OnDateSelectedListener;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class MonthViewDialog implements OnDateSelectedListener {

	private AlertDialog mDialog;
	private MonthView mMonthView;
	private OnDateSetListener onDateSetListener;
	private Calendar mDate = Calendar.getInstance();
	private FrameLayout mFrame;
	private Context mContext;

	public MonthViewDialog(Context context, OnDateSetListener callBack,
			int year, int month, int dayOfMonth) {
		mContext = context;
		View view = (View) LayoutInflater.from(context).inflate(
				R.layout.dialog, null);

		mFrame = (FrameLayout) view.findViewById(R.id.frame);

		mDialog = new AlertDialog.Builder(context).create();
		mDialog.setView(view, 0, 0, 0, 0);
		onDateSetListener = callBack;
		mDate.set(Calendar.YEAR, year);
		mDate.set(Calendar.MONTH, month);
		mDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	}

	public void show() {
		if (mDialog != null && !mDialog.isShowing()) {
			mMonthView = new MonthView(mContext, mDate);
			mMonthView.setOnDateSelectedListener(this);
			mMonthView.setLayoutParams(new FrameLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			mFrame.removeAllViews();
			mFrame.addView(mMonthView);
			mDialog.show();
		}
	}

	public boolean isShowing() {
		return mDialog != null && mDialog.isShowing();
	}

	public void dismiss() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
		mDialog = null;
	}

	public interface OnDateSetListener {
		public void onDateSet(MonthView view, int year, int month,
				int dayOfMonth);
	}

	@Override
	public void onDateSelected(Calendar date) {
		if (onDateSetListener != null) {
			onDateSetListener.onDateSet(mMonthView, date.get(Calendar.YEAR),
					date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
		}
		dismiss();
	}

}
