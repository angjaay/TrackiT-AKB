package studio.angzai.trackit_calonsarjanaaamiin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import studio.angzai.trackit_calonsarjanaaamiin.adapter.RecyclerViewConfig;
import studio.angzai.trackit_calonsarjanaaamiin.model.Place;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PercobaanData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PercobaanData extends Fragment {

    private RecyclerView mRecyclerView;
    RecyclerViewConfig adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PercobaanData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PercobaanData.
     */
    // TODO: Rename and change types and number of parameters
    public static PercobaanData newInstance(String param1, String param2) {
        PercobaanData fragment = new PercobaanData();
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

    // Ini percobaan ygy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_percobaan_data, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_places);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Place> options =
                new FirebaseRecyclerOptions.Builder<Place>()
                        .setQuery(
                                FirebaseDatabase.getInstance().getReference().child("place"),
                                Place.class
                        )
                        .build();
        adapter = new RecyclerViewConfig(options);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}