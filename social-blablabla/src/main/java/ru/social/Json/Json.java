package ru.social.Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import ru.social.API.Vars;
import ru.social.Model.Article;
import ru.social.Model.Event;
import ru.social.Model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12.03.15.
 */
public class Json {
    private Gson gson;

    public Json(){
        gson = new Gson();
    }
    public List<Event> makeListEventFromJson(JSONObject json){
        List<Event> list = new ArrayList<Event>();
        try {
            Type type = new TypeToken<List<Event>>(){}.getType();
            list = gson.fromJson(json.getJSONObject(Vars.RESULT).getString(Vars.EVENTS), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Article> makeListArticleFromJson(JSONObject json){
        List<Article> list = new ArrayList<Article>();
        try {
            Type type = new TypeToken<List<Article>>(){}.getType();
            list = gson.fromJson(json.getJSONObject(Vars.RESULT).getString(Vars.ARTICLES), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public User makeUserFromJson(JSONObject jsonObject){
        return gson.fromJson(jsonObject.toString(), User.class);
    }
}