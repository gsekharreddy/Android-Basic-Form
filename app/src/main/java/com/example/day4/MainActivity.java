package com.example.day4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.day4.R;

public class MainActivity extends AppCompatActivity {

	TextView tv_1, tv_2, tv_3;
	RadioButton selectedRadio;
	RadioGroup radio;
	CheckBox cb_1, cb_2, cb_3;
	Button submit, clear;
	Spinner spinner;
	int selectedGenderId;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);

		// Initialize Views
		radio = findViewById(R.id.radio);
		cb_1 = findViewById(R.id.cb_1);
		cb_2 = findViewById(R.id.cb_2);
		cb_3 = findViewById(R.id.cb_3);
		submit = findViewById(R.id.submit);
		clear = findViewById(R.id.clear);
		spinner = findViewById(R.id.spinner);
		Intent intent = new Intent();
		url="https://google.com";
		// Spinner Data
		String[] courses = {
				"C", "Data structures",
				"Interview prep", "Algorithms",
				"DSA with java", "OS"
		};
		btn_intent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
			}
		});
		// Spinner Setup
		ArrayAdapter<String> ad = new ArrayAdapter<>(
				this,
				android.R.layout.simple_spinner_item,
				courses
		);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(ad);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// Submit Button Listener
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedGenderId = radio.getCheckedRadioButtonId();

				String gender;
				if (selectedGenderId != -1) {
					selectedRadio = findViewById(selectedGenderId);
					gender = selectedRadio.getText().toString();
				} else {
					gender = "None";
				}

				StringBuilder interests = new StringBuilder("Interests: ");
				if (cb_1.isChecked()) interests.append("Swimming ");
				if (cb_2.isChecked()) interests.append("Dancing ");
				if (cb_3.isChecked()) interests.append("Riding ");
				if (!cb_1.isChecked() && !cb_2.isChecked() && !cb_3.isChecked())
					interests.append("None");

				String result = "Gender: " + gender + "\n" + interests.toString() + temp;
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
			}
		});

		// Clear Button Listener
		clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				radio.clearCheck();
				cb_1.setChecked(false);
				cb_2.setChecked(false);
				cb_3.setChecked(false);
				spinner.setSelection(0); // Optional: reset spinner
			}
		});

		// Set Window Insets
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
	}
}
