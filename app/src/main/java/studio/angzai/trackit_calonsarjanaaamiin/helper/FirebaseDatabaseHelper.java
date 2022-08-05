package studio.angzai.trackit_calonsarjanaaamiin.helper;
/**
 *
 * Tanggal pengerjaan : 02 - 08 - 2022
 * Nama : Reichan Muhammad Maulana
 * NIM : 10119120
 * Kelas : IF-3
 *
 * **/


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import studio.angzai.trackit_calonsarjanaaamiin.model.Place;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferencePlaces;
    private List<Place> places = new ArrayList<>();

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferencePlaces = mDatabase.getReference("place");
    }


    public interface DataStatus{
        void DataIsLoaded(List<Place> places, List<String> keys);
    }
    public void readPlaces(final DataStatus dataStatus){
        mReferencePlaces.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                places.clear();
                List<String> keys = new ArrayList<>();

                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Place place = keyNode.getValue(Place.class);
                    places.add(place);
                }
                dataStatus.DataIsLoaded(places, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
