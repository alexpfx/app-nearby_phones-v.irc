package br.com.alexpfx.irctest.app.mvp.model.domain.json;

import br.com.alexpfx.irctest.app.exceptions.JsonFromStringConvertException;

/**
 * Created by alexandre on 20/07/15.
 */
public interface WifiInfoJsonConverter {

    <T> String toJson(T object);

    <T> T fromJson(String json, Class<T> type) throws JsonFromStringConvertException;

}
