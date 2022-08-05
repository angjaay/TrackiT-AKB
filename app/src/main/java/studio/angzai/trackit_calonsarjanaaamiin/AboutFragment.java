package studio.angzai.trackit_calonsarjanaaamiin;

/**
 *
 * Tanggal pengerjaan : 28 - 07 - 2022
 * Nama : Andika Putra & Primarazaq Noorsalih
 * NIM : 10119101 & 10119124
 * Kelas : IF-3
 *
 * **/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class AboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}