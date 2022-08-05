package studio.angzai.trackit_calonsarjanaaamiin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MyLocationFragment extends Fragment {

    // Initialize variables
    FusedLocationProviderClient client;
    private GoogleMap map;
    String name_d,address_d;
    float lat_d, long_d;

    public MyLocationFragment() {
    }

    public MyLocationFragment(String name_d, String address_d, float lat_d, float long_d) {
        this.name_d = name_d;
        this.address_d = address_d;
        this.lat_d = lat_d;
        this.long_d = long_d;
    }

    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();

    //LatLng Marker1 = new LatLng(lat_d,long_d);
    //LatLng Marker1 = new LatLng(-7.150165234234893, 107.40101007509645 );
    //LatLng Marker2 = new LatLng(-6.7592326685330315, 107.60965194714275);
    //LatLng Market3 = new LatLng(-6.833865879675382, 107.66359362754234);
    //LatLng Marker4 = new LatLng(-6.789437701621277, 107.57917342712021);
    //LatLng Marker5 = new LatLng(-6.85633409657664, 107.63245922809203);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_my_location, container, false);

        //Insialisasi map Fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);



        //Inisialisasi client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        //arrayList.add(Marker1);
        //arrayList.add(Marker2);
        //arrayList.add(Market3);
        //arrayList.add(Marker4);
        //arrayList.add(Marker5);

        //Singkron Map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //dimana map berhasil dijalankan maka
                map = googleMap;
                //masih statis chan

                //map.addMarker(new MarkerOptions().position(Marker1).title(name_d));
                //map.addMarker(new MarkerOptions().position(Marker2).title("Gunung Tangkuban Perahu"));
                //map.addMarker(new MarkerOptions().position(Market3).title("Tebing Keraton"));
                //map.addMarker(new MarkerOptions().position(Marker4).title("Dusun Bambu"));
                //map.addMarker(new MarkerOptions().position(Marker5).title("Taman Hutan Raya"));
                for (int i=0;i<arrayList.size();i++){
                    map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                    map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                }
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));

                    }
                });

                // cek kondisi
                //jika lokasi diizinkan
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // memanggil method
                    getCurrentLocation();
                }
                else {
                    // jika lokasi tidak diizinkan
                    // memanggil metod
                    requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 100);
                }
            }
            @SuppressLint("MissingPermission")
            private void getCurrentLocation()
            {
                // inisialisasi map fragment
                SupportMapFragment mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

                // inisialisasi lokasi manager
                LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

                // cek kondisi
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    client.getLastLocation().addOnCompleteListener(
                            task -> {
                                // Menginisialisasi lokasi
                                Location location = task.getResult();
                                // Cek Kondisi
                                if (location != null) {
                                    // When location result is not null set latitude and longitude
                                    mapFragment.getMapAsync(googleMap -> {
                                        LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
                                        MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Anda");
                                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,17));
                                        googleMap.addMarker(options);
                                    });
                                }
                                else {
                                    LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10000).setFastestInterval(1000).setNumUpdates(1);
                                    LocationCallback locationCallback = new LocationCallback() {
                                        @Override
                                        public void
                                        onLocationResult(@NonNull LocationResult locationResult)
                                        {
                                            mapFragment.getMapAsync(googleMap -> {
                                                LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
                                                MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Sekarang");
                                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,17));
                                                googleMap.addMarker(options);
                                            });
                                        }
                                    };
                                    // mengupdate lokasi
                                    client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                                }
                            });
                }
                else {
                    startActivity(
                            new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });
        //Return view
        return view;
    }
}
