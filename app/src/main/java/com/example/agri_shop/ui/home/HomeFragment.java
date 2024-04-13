package com.example.agri_shop.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agri_shop.R;
import com.example.agri_shop.adapters.PopularAdapter;
import com.example.agri_shop.databinding.FragmentHomeBinding;
import com.example.agri_shop.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec;
    FirebaseFirestore db;
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        popularRec=root.findViewById(R.id.pop_rec);

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList=new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapter);
        db.collection("users")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot document : task.getResult())
                            {

                                Log.d(" ",document.getId()+"=>"+document.getData());


                            }
                        }else{
                            Log.w("Error getting documents",task.getException());
                        }
                    }
                });
        return root;
    }


}