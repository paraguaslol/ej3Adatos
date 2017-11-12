package com.dmb.Ej3a;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageButton imb;
    private Calendar cal;
    private EditText etDate,et1,et2;
    private RadioButton rb1,rb2;
    private DatePickerDialog date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cal = Calendar.getInstance();
        imb = (ImageButton)findViewById(R.id.datePicker);
        etDate = (EditText) findViewById(R.id.date);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);

        date = new DatePickerDialog(this);
        DatePicker dp = date.getDatePicker();
        dp.setMaxDate(cal.getTimeInMillis());
        date.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {


              @Override
              public void onDateSet(DatePicker view, int year, int monthOfYear,
                                    int dayOfMonth) {
                  // TODO Auto-generated method stub
                  cal.set(Calendar.YEAR, year);
                  cal.set(Calendar.MONTH, monthOfYear);
                  cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                  updateLabel();
              }
          }

        );

    }

    public void datePicker(View v){
        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        etDate.setText(sdf.format(cal.getTime()));
    }

    public void resetFields(View v){
        et1.setText("");
        et2.setText("");
        etDate.setText("");
        rb1.setChecked(false);
        rb2.setChecked(false);

        et1.setEnabled(true);
        et2.setEnabled(true);
        etDate.setEnabled(true);
        imb.setEnabled(true);
        rb1.setEnabled(true);
        rb2.setEnabled(true);
    }

    public void nextActivity(View v){
        SharedPreferences preferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("name",et1.getText().toString());
        edit.putString("dni",et2.getText().toString());
        edit.putString("birthday",etDate.getText().toString());
        if (rb1.isChecked()) {
            edit.putString("gender",rb1.getText().toString());
        }else if(rb2.isChecked()){
            edit.putString("gender",rb2.getText().toString());
        }else{
            edit.putString("gender",null);
        }
        edit.apply();

        et1.setEnabled(false);
        et2.setEnabled(false);
        etDate.setEnabled(false);
        imb.setEnabled(false);
        rb1.setEnabled(false);
        rb2.setEnabled(false);

        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
