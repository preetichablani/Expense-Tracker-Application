package com.example.expensetrackerapplication;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loadFragment(new AddFragment());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
           bottomNavigationView=findViewById(R.id.bottomNav);
           bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   if(menuItem.getItemId()==R.id.add){
                       loadFragment(new AddFragment());
                   }
                   if(menuItem.getItemId()==R.id.view){
                       loadFragment(new ViewFragment());
                   }
                   return true;
               }
           });

            return insets;
        });
    }
    private void loadFragment(Fragment Fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,Fragment)
                .commit();
    }
}