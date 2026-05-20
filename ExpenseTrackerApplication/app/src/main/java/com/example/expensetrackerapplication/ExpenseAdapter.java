package com.example.expensetrackerapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    Context context;
    ArrayList<Expense> list;

    public ExpenseAdapter(Context context,ArrayList<Expense> list){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.expense_item,parent,false);
        return new ViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.amount.setText("₹ " + list.get(position).getAmount());
        holder.Date.setText(list.get(position).getDate());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Expense")
                        .setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Expense expense = list.get(position);
                                DatabaseHelper dbHelper = new DatabaseHelper(context);
                                dbHelper.deleteExpense(expense.getId());
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                                Toast.makeText(context, "Expense Deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

@Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,amount,Date;

        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tvTitle);
            amount=itemView.findViewById(R.id.tvAmount);
            Date=itemView.findViewById(R.id.tvDate);

        }
    }
}