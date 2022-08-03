package studio.angzai.trackit_calonsarjanaaamiin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyLocationFragment extends Fragment {

    // Initialize variables
    FusedLocationProviderClient client;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_my_location, container, false);

        //Insialisasi map Fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //Inisialisasi client
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        //Singkron Map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //dimana map berhasil dijalankan maka
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
