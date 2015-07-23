package br.com.alexpfx.irctest.app.mvp.model.domain.json.impl;

import br.com.alexpfx.irctest.app.exceptions.JsonFromStringConvertException;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.WifiInfoJsonConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by alexandre on 20/07/15.
 */
public class GsonWifiInfoJsonConverterImpl implements WifiInfoJsonConverter {
    private Gson gson = new GsonBuilder().create();

    @Override
    public <T> String toJson(T object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T fromJson(String json, Class<T> type) throws JsonFromStringConvertException {
        try {
            return gson.fromJson(json, type);
        } catch (RuntimeException e) {
            throw new JsonFromStringConvertException();
        }
    }

}
