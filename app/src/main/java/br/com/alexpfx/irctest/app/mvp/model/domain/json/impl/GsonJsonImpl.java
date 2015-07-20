package br.com.alexpfx.irctest.app.mvp.model.domain.json.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.json.Json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by alexandre on 20/07/15.
 */
public class GsonJsonImpl implements Json {
    @Override
    public String toJson(Object o) {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(o);
    }

    @Override
    public Object fromJson(String json) {
        return null;
    }
}
