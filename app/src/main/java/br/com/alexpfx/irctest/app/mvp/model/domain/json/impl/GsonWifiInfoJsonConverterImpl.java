package br.com.alexpfx.irctest.app.mvp.model.domain.json.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.json.WifiInfoJsonConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by alexandre on 20/07/15.
 */
public class GsonWifiInfoJsonConverterImpl implements WifiInfoJsonConverter {
    private Gson gson = new GsonBuilder().create();

    @Override
    public String toJson(List list) {
        return gson.toJson(list);
    }

    @Override
    public List fromJson(String json) {
        return null;
    }
}
