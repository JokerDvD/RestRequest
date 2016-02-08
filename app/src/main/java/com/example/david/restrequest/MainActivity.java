package com.example.david.restrequest;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.david.restrequest.Dialog.Dialog1;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DialogFragment dlg1;
    Dialog mDialog;
    Button btRequest;
    TextView tvParser, tbOk;
    String url = "http://esapa.meta4.kz/rest/cim/KZ-430301-0000000000";
    ProgressBar progressBar;
    private ProgressDialog dialog;
    //Context mContext = getActivity();
    private Context mContext;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //  progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btRequest = (Button) findViewById(R.id.btRequest);
        tvParser = (TextView) findViewById(R.id.tvParser);

        btRequest.setOnClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    //кнопка


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


      /*  dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Title");
        dialog.setMessage("Progress..");
        dialog.setCancelable(true);

        //  dialog.setIndeterminate(false);
        dialog.show();*/
        //dialog.dismiss();


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject GTIN = response.getJSONObject("gtin");

                         /* mDialog = new Dialog(MainActivity.this);

                          // Установите заголовок
                          mDialog.setTitle("Result");
                          // Передайте ссылку на разметку
                          mDialog.setContentView(R.layout.dialog1);
                          // Найдите элемент TextView внутри вашей разметки
                          // и установите ему соответствующий текст
                          TextView text = (TextView) mDialog.findViewById(R.id.tvTitle);
                          text.setText("Response:= " + response.getString("owner") + "\n" + GTIN.getString("subbrand"));
                          mDialog.show();*/
                            Fragment fragment = new Fragment();
                            Bundle bundle = new Bundle();

                            bundle.putString("key", "Response: = \n" + response.getString("owner") + "\n" + GTIN.getString("subbrand"));


                            FragmentManager fm = getSupportFragmentManager();
                            Dialog1 editNameDialog = new Dialog1();
                            editNameDialog.setArguments(bundle);
                            editNameDialog.show(fm, "");



                             /*dialog = new ProgressDialog(MainActivity.this);
                             dialog.setTitle("Title");
                             dialog.setMessage("Response: = \n" + response.getString("owner") + "\n" + GTIN.getString("subbrand"));

                             dialog.setCancelable(true);
                             dialog.setMax(1);
                             dialog.setIndeterminate(true);
        //  dialog.setIndeterminate(false);
                             dialog.show();*/
                            //tvParser.setText("Response:= " + response.getString("owner") + "\n" + GTIN.getString("subbrand"));


                        } catch (Exception e) {
                            tvParser.setText("Error");
                        }

                        // tvParser.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsObjRequest);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.david.restrequest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.david.restrequest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        Dialog1 editNameDialog = new Dialog1();
        editNameDialog.getView().findViewById(R.id.tvTitle);
        editNameDialog.show(fm, "fragment_edit_name");
    }
}
