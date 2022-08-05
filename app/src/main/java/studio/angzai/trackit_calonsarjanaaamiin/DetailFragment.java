package studio.angzai.trackit_calonsarjanaaamiin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

public class DetailFragment extends Fragment {

    String name,address,image,description,time;
    float latitude,longitude,score;


    public DetailFragment() {
        // Required empty public constructor
    }

    public DetailFragment(String name, String image, String address, float latitude, float longitude, String description, float score, String time) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.score = score;
        this.time = time;
    }

    public float lat_d(){
        return latitude;
    }

    public float long_d(){
        return longitude;
    }

    public String name_d(){
        return name;
    }

    public String address_d(){
        return address;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView imageholder = view.findViewById(R.id.img_tempat);
        TextView nameholder = view.findViewById(R.id.name_tempat);
        TextView addressholder = view.findViewById(R.id.address_tempat);
        TextView descholder = view.findViewById(R.id.desc_tempat);
        TextView timeholder = view.findViewById(R.id.time_tempat);

        nameholder.setText(name);
        addressholder.setText(address);
        descholder.setText(description);
        timeholder.setText(time);
        Glide.with(getContext()).load(image).into(imageholder);

        addressholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bikin bundle biar bisa ngirim data ke fragment maps
                Bundle bundle = new Bundle();
                // "latitude" teh key nya
                bundle.putFloat("latitude", lat_d());
                bundle.putFloat("longitude", long_d());
                bundle.putString("name", name_d());

                Fragment fragment = new MapsFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.wrapperdetail, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity =(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new HomeFragment()).addToBackStack(null).commit();
    }
}