package br.com.alexpfx.irctest.app.mvp.model.domain.json;

/**
 * Created by alexandre on 20/07/15.
 */
public interface Json {

    String toJson(Object o);

    Object fromJson(String json);

}
