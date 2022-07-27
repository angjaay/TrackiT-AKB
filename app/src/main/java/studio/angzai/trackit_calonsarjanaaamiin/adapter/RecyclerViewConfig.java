package studio.angzai.trackit_calonsarjanaaamiin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import studio.angzai.trackit_calonsarjanaaamiin.R;
import studio.angzai.trackit_calonsarjanaaamiin.model.Place;

public class RecyclerViewConfig {
    private Context mContext;
    private PlacesAdapter mPlaceAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Place> places, List<String> keys){
        mContext = context;
        mPlaceAdapter = new PlacesAdapter(places, keys);
        recyclerView.setAdapter(mPlaceAdapter);
    }


    class PlaceItemView extends RecyclerView.ViewHolder{
        private TextView txtName, txtAddress, txtScore, txtImage, txtLongitude, txtLatitude, txtOpenTime;
        private String key;

        public PlaceItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_place, parent, false));

            txtName = (TextView) itemView.findViewById(R.id.textName);
            txtAddress =  (TextView) itemView.findViewById(R.id.textAddress);
            txtImage =  (TextView) itemView.findViewById(R.id.imageTempat);
            txtLongitude =  (TextView) itemView.findViewById(R.id.latitudeTempat);
            txtLatitude =  (TextView) itemView.findViewById(R.id.longitudeTempat);
            txtOpenTime =  (TextView) itemView.findViewById(R.id.textOpenTime);
            txtScore =  (TextView) itemView.findViewById(R.id.textScore);
        }

        public void bind(Place place, String key){
            txtName.setText(place.getName());
            txtAddress.setText(place.getAddress());
            txtImage.setText(place.getImage());
            txtLongitude.setText(String.valueOf(place.getLongitude()));
            txtLatitude.setText(String.valueOf(place.getLatitude()));
            txtOpenTime.setText(place.getOpen_time());
            txtScore.setText(String.valueOf(place.getScore()));

            this.key = key;
        }
    }

    class PlacesAdapter extends RecyclerView.Adapter<PlaceItemView>{
        private List<Place> mPlaceList;
        private List<String> mKeys;

        public PlacesAdapter(List<Place> mPlaceList, List<String> mKeys) {
            this.mPlaceList = mPlaceList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public PlaceItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PlaceItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PlaceItemView holder, int position) {
            holder.bind(mPlaceList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mPlaceList.size();
        }
    }
}
