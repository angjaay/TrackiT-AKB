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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_my_location, container, false);

        // Initialize location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        // check condition
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // When permission is granted
            // Call method
            getCurrentLocation();
        }
        else {
            // When permission is not granted
            // Call method
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 100);
        }

        //Return view
        return view;
    }
    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
        // Initialize map fragment
        SupportMapFragment mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_maps);

        // Initialize Location manager
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Check condition
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // When location service is enabled
            // Get last location
            client.getLastLocation().addOnCompleteListener(
                    task -> {
                        // Initialize location
                        Location location = task.getResult();
                        // Check condition
                        if (location != null) {
                            // When location result is not null set latitude and longitude
                            mapFragment.getMapAsync(googleMap -> {
                                LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
                                MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Saya");
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,17));
                                googleMap.addMarker(options);
                            });
                        }
                        else {
                            // When location result is null
                            // initialize location request
                            LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10000).setFastestInterval(1000).setNumUpdates(1);

                            // Initialize location call back
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

                            // Request location updates
                            client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }
                    });
        }
        else {
            // When location service is not enabled
            // open location setting
            startActivity(
                    new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
