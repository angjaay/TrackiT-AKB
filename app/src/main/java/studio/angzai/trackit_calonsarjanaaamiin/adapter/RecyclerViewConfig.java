package studio.angzai.trackit_calonsarjanaaamiin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import studio.angzai.trackit_calonsarjanaaamiin.R;
import studio.angzai.trackit_calonsarjanaaamiin.model.Place;

public class RecyclerViewConfig extends FirebaseRecyclerAdapter<Place, RecyclerViewConfig.PlaceAdapter>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerViewConfig(@NonNull FirebaseRecyclerOptions<Place> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PlaceAdapter holder, int position, @NonNull Place model) {
        holder.txtname.setText(model.getName());
        holder.imageTempat.setText(model.getImage());
        holder.txtdescription.setText(model.getDescription());
        holder.latitudetempat.setText(String.valueOf(model.getLatitude()));
        holder.longitudetempat.setText(String.valueOf(model.getLongitude()));
        holder.txtopentime.setText(String.valueOf(model.getOpen_time()));
        holder.txtscore.setText(String.valueOf(model.getScore()));
        holder.txtaddress.setText(model.getAddress());
    }

    @NonNull
    @Override
    public PlaceAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new PlaceAdapter(view);
    }

    public static class PlaceAdapter extends RecyclerView.ViewHolder
    {
        TextView txtname, txtaddress, txtdescription, imageTempat,latitudetempat, longitudetempat, txtopentime, txtscore;

        public PlaceAdapter(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.textName);
            txtaddress = itemView.findViewById(R.id.textAddress);
            txtdescription = itemView.findViewById(R.id.textDescription);
            imageTempat = itemView.findViewById(R.id.imageTempat);
            latitudetempat = itemView.findViewById(R.id.latitudeTempat);
            longitudetempat = itemView.findViewById(R.id.longitudeTempat);
            txtopentime = itemView.findViewById(R.id.textOpenTime);
            txtscore = itemView.findViewById(R.id.textScore);
        }
    }
}
