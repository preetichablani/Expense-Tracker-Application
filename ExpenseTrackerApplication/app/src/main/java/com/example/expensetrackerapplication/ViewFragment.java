package com.example.expensetrackerapplication;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.expensetrackerapplication.R;

import java.util.ArrayList;

public class ViewFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Expense> list;
    ExpenseAdapter adapter;
    DatabaseHelper db;
    TextView total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_view, container, false);
        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        total=view.findViewById(R.id.total);
        db=new DatabaseHelper(getContext());

        Cursor cursor = db.getAllExpense();
        list=new ArrayList<>();
        while(cursor.moveToNext()){
            Log.d("text","list");
            list.add(new Expense(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            ));
        }
        Cursor totalAmountCursor = db.getTotalAmount();
        if(totalAmountCursor!=null){
            if (totalAmountCursor.moveToFirst()){
                String amount = totalAmountCursor.getString(0);
                total.setText("Total: "+amount);
            }
        }
        adapter=new ExpenseAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}