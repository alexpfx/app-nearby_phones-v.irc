package br.com.alexpfx.irctest.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.alexpfx.irctest.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkSettingsFragment extends Fragment {

    public NetworkSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_network_settings, container, false);
    }

}
