package com.example.expensetrackerapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class AddFragment extends Fragment {
    Button btn;
    EditText title, amount, category, date;
    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        title = view.findViewById(R.id.etTitle);
        amount = view.findViewById(R.id.etAmount);
        category = view.findViewById(R.id.etCategory);
        date = view.findViewById(R.id.etDate);
        btn = view.findViewById(R.id.save);
        db = new DatabaseHelper(getContext());

        date.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate =
                                selectedDay + "/" +
                                        (selectedMonth + 1) + "/" +
                                        selectedYear;
                        date.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertExpenese(
                        title.getText().toString(),
                        amount.getText().toString(),
                        category.getText().toString(),
                        date.getText().toString()
                );
                Toast.makeText(getContext(),
                        "Data Saved",
                        Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}