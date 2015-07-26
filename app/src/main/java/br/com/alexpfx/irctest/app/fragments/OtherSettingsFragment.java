package br.com.alexpfx.irctest.app.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.alexpfx.irctest.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherSettingsFragment extends PreferenceFragment {

    public OtherSettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_other_settings, container, false);
    }

}
