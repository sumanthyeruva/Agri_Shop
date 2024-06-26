package com.example.agri_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.agri_shop.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    TextView quantity;
    int totalQuantity =1;
    int totalPrice=0;
    ImageView detailedImg;
    TextView price,description;
    ImageView addItem,removeItem;
    Toolbar toolbar;
    Button addToCart;

    ViewAllModel viewAllModel=null;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        firestore = FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel)object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg=findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price=findViewById(R.id.detailed_price);
        description=findViewById(R.id.detailed_dec);

        if(viewAllModel!=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            description.setText(""+viewAllModel.getDescription());
            price.setText("Price :$"+viewAllModel.getPrice()+"/kg");
             totalPrice = viewAllModel.getPrice()*totalQuantity;
            if(viewAllModel.getType().equals("egg")){
                price.setText("Price :$"+viewAllModel.getPrice()+"/dozen");
                totalPrice=viewAllModel.getPrice()*totalQuantity;
            }
        }

        addToCart=findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=viewAllModel.getPrice()*totalQuantity;
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=viewAllModel.getPrice()*totalQuantity;
                }
            }
        });

    }

    private void addedToCart(){
//        String saveCurrentDate,saveCurrentTime;
//        Calendar calForDate = Calendar.getInstance();
//
//        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd,yyyy");
//        saveCurrentDate = currentDate.format(calForDate.getTime());
//
//        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String ,Object> cartMap = new HashMap<>();

        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productPrice",price.getText().toString());
//        cartMap.put("currentDate",saveCurrentDate);
//        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).
                collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this,"Added to a cart",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}