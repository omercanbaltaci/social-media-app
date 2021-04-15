package com.example.socialmediaapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialmediaapp.AdaptersClasses.TextStatusAdapterClass;
import com.example.socialmediaapp.ModelClasses.Model_TextStatus;
import com.example.socialmediaapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Statuses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Statuses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Statuses() {

    }

    // XML
    private RecyclerView objectRecylerView;

    // Class
    private View parent;
    private TextStatusAdapterClass objectTextStatusAdapterClass;

    //Firebase
    FirebaseFirestore objectFirebaseFirestore;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Statuses.
     */
    // TODO: Rename and change types and number of parameters
    public static Statuses newInstance(String param1, String param2) {
        Statuses fragment = new Statuses();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent = inflater.inflate(R.layout.fragment_statuses, container, false);
        objectRecylerView = parent.findViewById(R.id.textStatus_RV);
        objectFirebaseFirestore = FirebaseFirestore.getInstance();
        addStatusToRV();
        return parent;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            objectTextStatusAdapterClass.startListening();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addStatusToRV() {
        try {
            Query objectQuery = objectFirebaseFirestore.collection("TextStatus")
                    .orderBy("currentdatetime", Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<Model_TextStatus> options = new FirestoreRecyclerOptions.Builder<Model_TextStatus>()
                    .setQuery(objectQuery, Model_TextStatus.class).build();
            objectTextStatusAdapterClass = new TextStatusAdapterClass(options);
            objectRecylerView.setAdapter(objectTextStatusAdapterClass);
            objectRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}