package com.softgridtech.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity {

    EditText mNameEt, mEmailEt, mPasswordEt, mConfirmPasswordEt, mPhoneEt;
    Switch mOnOffSwtc;
    Button mSubmitBtn;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String ConfirmPassword = "confirmPasswordKey";
    public static final boolean OnOffSwitch = Boolean.parseBoolean("onOffKey");


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSubmitBtn = (Button) findViewById(R.id.btnSubmit);

        /*
        Step 1
        To use android shared preference we have to call method  getSharedPreferences() that returns
         a SharedPreference instance poiting to the file that contains the values of preferences.
         The first parameter is the key and the second parameter is the MODE as shown below,
         */
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        mNameEt = (EditText) findViewById(R.id.nameEt);
        mEmailEt = (EditText) findViewById(R.id.emailEt);
        mPasswordEt = (EditText) findViewById(R.id.passwordEt);
        mConfirmPasswordEt = (EditText) findViewById(R.id.confirmPasswordEt);
        mPhoneEt = (EditText) findViewById(R.id.phoneEt);
        mOnOffSwtc = (Switch) findViewById(R.id.onOffSwtch);

      

        /*
        Step 3 : Get data from sharedpreference
          sharedpreferences.getString() method should be used to get the values for corresponding keys as shown below
         */

        if (sharedpreferences.contains(Name)) {
            mNameEt.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            mEmailEt.setText(sharedpreferences.getString(Email, ""));
        }

        if (sharedpreferences.contains(Phone)) {
            mPhoneEt.setText(sharedpreferences.getString(Phone, ""));
        }
        if (sharedpreferences.contains(Password)) {
            mPasswordEt.setText(sharedpreferences.getString(Password, ""));
        }

        if (sharedpreferences.contains(ConfirmPassword)) {
            mConfirmPasswordEt.setText(sharedpreferences.getString(ConfirmPassword, ""));
        }
        if (sharedpreferences.contains(String.valueOf(OnOffSwitch))) {
            mOnOffSwtc.setChecked(sharedpreferences.getBoolean("", OnOffSwitch));
        }


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = mNameEt.getText().toString();
                String ph = mPhoneEt.getText().toString();
                String e = mEmailEt.getText().toString();
                String pw = mPasswordEt.getText().toString();
                String cpw = mConfirmPasswordEt.getText().toString();




                /*
                Step 2 : Save object to sharedpreferences
         We can save data to sharedpreferences using SharedPreferences.Editor class, for which we need to use edit method of the shared preference.
                 */
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.putString(Password, pw);
                boolean tgpref = sharedpreferences.getBoolean("tgpref", false);  //default is true

                mOnOffSwtc.setChecked(tgpref);
                editor.putString(ConfirmPassword, cpw);
                editor.apply();

                Toast.makeText(MainActivity.this, "Thanks", Toast.LENGTH_LONG).show();

            }
        });
    }


}







