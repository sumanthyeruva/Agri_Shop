package com.example.agri_shop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.bumptech.glide.Glide;
import com.example.agri_shop.R;
//import com.example.agri_shop.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

//import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        auth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//
//
//       DrawerLayout drawer = findViewById(R.id.drawer_layout);
//       NavigationView navigationView = findViewById(R.id.nav_view);
//        //Passing each menu ID as a set of Ids because each
//        //menu should be considered as top level destinations.
//       mAppBarConfiguration = new AppBarConfiguration.Builder(
//               R.id.nav_home, R.id.nav_category, R.id.nav_profile)
//               .setDrawerLayout(drawer)
//               .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//       View headerView = navigationView.getHeaderView(0);
//       TextView headerName = headerView.findViewById(R.id.nav_header_name);
//       TextView headerEmail = headerView.findViewById(R.id.nav_header_email);
//       CircleImageView headerImg = headerView.findViewById(R.id.nav_header_img);
//
//       database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//               .addListenerForSingleValueEvent(new ValueEventListener() {
//                   @Override
//                   public void onDataChange(@NonNull DataSnapshot snapshot) {
//                       UserModel userModel = snapshot.getValue(UserModel.class);
//
//                      headerName.setText(userModel.getName());
//                       headerEmail.setText(userModel.getEmail());
//                       Glide.with(MainActivity.this).load(userModel.getProfileImg()).into(headerImg);
//                   }
//
//                   @Override
//                   public void onCancelled(@NonNull DatabaseError error) {
//
//                   }
//               });
//   }
//
//   @Override
//   public boolean onCreateOptionsMenu(Menu menu) {
//       // Inflate the menu; this adds items to the action bar if it is present.
//       getMenuInflater().inflate(R.menu.main, menu);
//       return true;
//   }
//
//
//   @Override
//   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//       int id = item.getItemId();
//
//       if (id == R.id.logout) {
//           auth.signOut();
//           startActivity(new Intent(MainActivity.this,HomeActivity.class));
//           finish();
//       }
//       return true;
//   }
//
//   @Override
//   public boolean onSupportNavigateUp() {
//       NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//       return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//               || super.onSupportNavigateUp();
//   }
   }
    public void login(View view)
    {
        startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
    }
    public void registration(View view)
    {
        startActivity(new Intent(MainActivity.this, UserRegistrationActivity.class));
    }
}