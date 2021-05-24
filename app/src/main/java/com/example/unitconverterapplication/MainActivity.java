package com.example.unitconverterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText value;
    private TextView result1;
    private TextView result2;
    private TextView result3;
    private TextView resultUnit1;
    private TextView resultUnit2;
    private TextView resultUnit3;
    private Button convertBtn;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radioButtons);
        value = (EditText)findViewById(R.id.editTextNumber);
        result1 = (TextView)findViewById(R.id.result1);
        result2 = (TextView)findViewById(R.id.result2);
        result3 = (TextView)findViewById(R.id.result3);
        resultUnit1 = (TextView)findViewById(R.id.resultUnit1);
        resultUnit2 = (TextView)findViewById(R.id.resultUnit2);
        resultUnit3 = (TextView)findViewById(R.id.resultUnit3);
        convertBtn = (Button)findViewById(R.id.convertBtn);

        TriggerUnits();
        convertBtn.setOnClickListener(v -> converter());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"Nothing is selected",Toast.LENGTH_SHORT).show();
    }

    public void converter() {
        DecimalFormat df = new DecimalFormat("#.###");
        Double realValue = Double.parseDouble(value.getText().toString());
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb1:
                if(spinner.getSelectedItem().toString().equals("Meter")) {
                    Double centimeter = realValue*100;
                    Double foot = realValue*3.28084;
                    Double inch = realValue*39.3701;
                    result1.setText(""+df.format(centimeter));
                    result2.setText(""+df.format(foot));
                    result3.setText(""+df.format(inch));
                    resultUnit1.setText("Centimeter");
                    resultUnit2.setText("Foot");
                    resultUnit3.setText("Inch");
                } else {
                    Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                }
                
                break;
            case R.id.rb2:
                if(spinner.getSelectedItem().toString().equals("Celsius")) {
                    Double farenheit = (realValue * 9/5) + 32;
                    Double kelvin = realValue + 273.15;
                    result1.setText(""+df.format(farenheit));
                    result2.setText(""+df.format(kelvin));
                    resultUnit1.setText("Farenheit");
                    resultUnit2.setText("Kelvin");
                } else {
                    Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rb3:
                if(spinner.getSelectedItem().toString().equals("Kilograms")) {
                    Double gram = realValue*1000;
                    Double ounce = realValue*35.274;
                    Double pound = realValue*2.20462;
                    result1.setText(""+df.format(gram));
                    result2.setText(""+df.format(ounce));
                    result3.setText(""+df.format(pound));
                    resultUnit1.setText("Gram");
                    resultUnit2.setText("Ounce(Oz)");
                    resultUnit3.setText("Pound(Lb)");
                } else {
                    Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void TriggerUnits() {
        spinner = findViewById(R.id.units);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void onRadioButtonClicked(View v)
    {
        RadioButton rb1 = (RadioButton) findViewById(R.id.rb1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.rb2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.rb3);

        boolean  checked = ((RadioButton) v).isChecked();

        switch(v.getId()){

            case R.id.rb1:
                if(checked)
                    rb1.setTypeface(null, Typeface.BOLD_ITALIC);
                rb2.setTypeface(null, Typeface.NORMAL);
                rb3.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.rb2:
                if(checked)
                    rb2.setTypeface(null, Typeface.BOLD_ITALIC);
                rb1.setTypeface(null, Typeface.NORMAL);
                rb3.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.rb3:
                if(checked)
                    rb3.setTypeface(null, Typeface.BOLD_ITALIC);
                rb1.setTypeface(null, Typeface.NORMAL);
                rb2.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }
}