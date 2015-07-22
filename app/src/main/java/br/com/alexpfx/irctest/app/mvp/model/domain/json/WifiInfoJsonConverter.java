package br.com.alexpfx.irctest.app.mvp.model.domain.json;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiList;

import java.util.List;

/**
 * Created by alexandre on 20/07/15.
 */
public interface WifiInfoJsonConverter {

    String toJson(List list);

    List fromJson(String json);

}
