package com.example.agri_shop.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agri_shop.R;
import com.example.agri_shop.adapters.HomeAdapter;
import com.example.agri_shop.adapters.PopularAdapter;
import com.example.agri_shop.models.HomeCategory;
import com.example.agri_shop.models.PopularModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec,homeCatRec;
    FirebaseFirestore db;
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db=FirebaseFirestore.getInstance();
        popularRec=root.findViewById(R.id.pop_rec);
        homeCatRec=root.findViewById(R.id.explore_rec);

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList=new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapter);
        db.collection("PopularProducts")
                .get().addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        for(QueryDocumentSnapshot document : task.getResult())
                        {

                            PopularModel popularModel=document.toObject(PopularModel.class);
                            popularModelList.add(popularModel);
                            popularAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity()," "+task.getException(), Toast.LENGTH_SHORT).show();


                        }
                    }else{
                        Toast.makeText(getActivity()," "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList=new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);
        db.collection("HomeCategory")
                .get().addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        for(QueryDocumentSnapshot document : task.getResult())
                        {

                            HomeCategory homeCategory=document.toObject(HomeCategory.class);
                            categoryList.add(homeCategory);
                            homeAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity()," "+task.getException(), Toast.LENGTH_SHORT).show();


                        }
                    }else{
                        Toast.makeText(getActivity()," "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
        return root;
    }


}