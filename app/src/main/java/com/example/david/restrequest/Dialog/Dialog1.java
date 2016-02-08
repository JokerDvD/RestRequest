package com.example.david.restrequest.Dialog;

import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.restrequest.R;

/**
 * Created by David on 05.02.2016.
 */

public class Dialog1 extends DialogFragment implements OnClickListener {

    final String LOG_TAG = "myLogs";
    TextView tvTitle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

       // TextView

        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog1, null);


        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvTitle.setText(getArguments().getString("key"));
        v.findViewById(R.id.btOk).setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        dismiss();
    }

    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog)
    {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}