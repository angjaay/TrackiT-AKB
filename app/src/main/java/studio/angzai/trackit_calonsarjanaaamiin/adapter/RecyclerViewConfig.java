package studio.angzai.trackit_calonsarjanaaamiin.adapter;
/**
 *
 * Tanggal pengerjaan : 02 - 08 - 2022
 * Nama : Reichan Muhammad Maulana
 * NIM : 10119120
 * Kelas : IF-3
 *
 * **/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import studio.angzai.trackit_calonsarjanaaamiin.DetailFragment;
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
        //holder.txtdescription.setText(model.getDescription());
        //holder.latitudetempat.setText(String.valueOf(model.getLatitude()));
        //holder.longitudetempat.setText(String.valueOf(model.getLongitude()));
        //holder.txtopentime.setText(String.valueOf(model.getOpen_time()));
        holder.txtscore.setText(String.valueOf(model.getScore()));
        holder.txtaddress.setText(model.getAddress());
        Glide.with(holder.images.getContext()).load(model.getImage()).into(holder.images);

            holder.images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity =(AppCompatActivity)view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new DetailFragment(model.getName(),
                                                                                                                     model.getImage(),
                                                                                                                     model.getAddress(),
                                                                                                                     model.getLatitude(),
                                                                                                                     model.getLongitude(),
                                                                                                                     model.getDescription(),
                                                                                                                     model.getScore(),
                                                                                                                     model.getOpen_time())).addToBackStack(null).commit();
                }
            });
    }

    @NonNull
    @Override
    public PlaceAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_list_item, parent, false);

        return new PlaceAdapter(view);
    }

    public static class PlaceAdapter extends RecyclerView.ViewHolder
    {
        TextView txtname, txtaddress, txtdescription,latitudetempat, longitudetempat, txtopentime, txtscore;
        ImageView images;

        public PlaceAdapter(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            images = itemView.findViewById(R.id.txtimage);
            //txtdescription = itemView.findViewById(R.id.textDescription);
            //latitudetempat = itemView.findViewById(R.id.latitudeTempat);
            //longitudetempat = itemView.findViewById(R.id.longitudeTempat);
            //txtopentime = itemView.findViewById(R.id.textOpenTime);
            txtscore = itemView.findViewById(R.id.txtrating);

        }


    }
}
