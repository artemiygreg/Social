package ru.social.API;

import android.content.Context;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import ru.social.Interfaces.JsonResponse;
import ru.social.Interfaces.StringResponse;
import ru.social.Server.Server;
import ru.social.View.AlertDialog.MyAlertDialog;
import ru.social.View.AlertDialog.MyAlertDialogImpl;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Admin on 10.03.15.
 */
public class WebAPIImpl implements WebAPI {
    private static WebAPI instance = null;
    private MyAlertDialog myAlert;
    private Context context;
    private RequestQueue requestQueue;
    private static final int POST = Request.Method.POST;
    private static final int GET = Request.Method.GET;
    private static final int DELETE = Request.Method.DELETE;
    private static final String REGISTRATION = "/user/";
    private static final String AUTH = "/auth/";
    private static final String GET_LIST_ARTICLES  = "/articles/";
    private static final String GET_DETAIL_ARTICLE = "/articles/";
    private static final String GET_LIST_EVENTS = "/events/";
    private static final String GET_DETAIL_EVENT = "/events/";
    private static final String REGISTRATION_ON_EVENT = "/events/";
    private static final String GET_PROFILE = "/user/";
    private static final String EDIT_PROFILE = "/user/";

    public WebAPIImpl(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        myAlert = new MyAlertDialogImpl(context);
        instance = this;
    }
    public static WebAPI getInstance() {
        return instance;
    }

    @Override
    public void registration(String fio, String phone, String email, String password, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + REGISTRATION;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.FIO, fio);
        params.put(Vars.EMAIL, email);
        params.put(Vars.PHONE, phone);
        params.put(Vars.PASSWORD, password);

        baseRequestJson(url, POST, params, jsonResponse);
    }

    @Override
    public void auth(String email, String password, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + AUTH;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.EMAIL, email);
        params.put(Vars.PASSWORD, password);

        baseRequestJson(url, POST, params, jsonResponse);
    }

    @Override
    public void getListArticles(String token, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + GET_LIST_ARTICLES;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.TOKEN, token);

        baseRequestJson(url, GET, params, jsonResponse);
    }

    @Override
    public void getArticle(String token, int id, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + GET_DETAIL_ARTICLE + id + "?" + Vars.TOKEN + "=" + token;
        baseRequestJson(url, GET, null, jsonResponse);
    }

    @Override
    public void getListEvents(String token, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + GET_LIST_EVENTS;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.TOKEN, token);

        baseRequestJson(url, GET, params, jsonResponse);
    }

    @Override
    public void getEvent(String token, int id, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + GET_DETAIL_EVENT + id + "?" + Vars.TOKEN + "=" + token;
        baseRequestJson(url, GET, null, jsonResponse);
    }

    @Override
    public void registrationOnEvent(String token, int id, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + REGISTRATION_ON_EVENT + id;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.TOKEN, token);

        baseRequestJson(url, POST, params, jsonResponse);
    }

    @Override
    public void getMyProfile(String token, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + GET_PROFILE;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.TOKEN, token);

        baseRequestJson(url, POST, params, jsonResponse);
    }

    @Override
    public void editMyProfile(String token, String fio, String email, String phone, String avatar, JsonResponse jsonResponse) {
        String url = Server.PROTOCOL_HTTP + Server.HOST + EDIT_PROFILE;

        Map<String, String> params = new HashMap<String, String>();
        params.put(Vars.TOKEN, token);
        params.put(Vars.FIO, fio);
        params.put(Vars.EMAIL, email);
        params.put(Vars.PHONE, phone);
        params.put(Vars.AVATAR, avatar);

        baseRequestJson(url, POST, params, jsonResponse);
    }

    private void baseRequestString(String url, int method, final Map<String, String> params, final StringResponse stringResponse){
        myAlert.showProgressDialog();
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myAlert.dismissProgressDialog();
                stringResponse.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                myAlert.dismissProgressDialog();
                stringResponse.failed();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(request);
    }
    private void baseRequestJson(String url, int method, final Map<String, String> params, final JsonResponse jsonResponse){
        myAlert.showProgressDialog();
        JsonObjectRequest request = new JsonObjectRequest(method, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                myAlert.dismissProgressDialog();
                try {
                    Log.e("onResponse", jsonObject.toString());
                    if(jsonObject.getJSONObject(Vars.RESULT).getInt(Vars.CODE) == 200)
                        jsonResponse.success(jsonObject);
                    else
                        jsonResponse.failed();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("onResponse", volleyError.getMessage());
                myAlert.dismissProgressDialog();
                jsonResponse.failed();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(request);
    }
}
