package br.com.alexpfx.irctest.app.mvp.model.domain.json;

/**
 * Created by alexandre on 20/07/15.
 */
public interface WifiInfoJsonConverter {

    String toJson(Object object);

    Object fromJson(String json);

}
