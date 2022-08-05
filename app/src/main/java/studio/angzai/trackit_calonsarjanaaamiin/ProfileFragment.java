package studio.angzai.trackit_calonsarjanaaamiin;

/**
 *
 * Tanggal pengerjaan : 02 - 08 - 2022
 * Nama : Rizky Septiana Abdulrazak, Angga Cahya Abadi & Primarazaq Noorsalih
 * NIM : 10119118, 10119123 & 10119124
 * Kelas : IF-3
 *
 * **/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}