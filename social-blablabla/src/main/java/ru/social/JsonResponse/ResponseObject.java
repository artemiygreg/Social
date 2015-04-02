package ru.social.JsonResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.social.API.Vars;

/**
 * Created by Admin on 26.03.15.
 */
public final class ResponseObject {

    public static JSONObject registration(){
        /**
        *  {
        *    "result": {
        *       "code": 200,
        *        "token": "fcac1a3b62cb51374123de565dc12e16"
        *    }
        *  }
        */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            result.put(Vars.TOKEN, "fcac1a3b62cb51374123de565dc12e16");
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject auth(){
        /**
         *  {
         *    "result": {
         *       "code": 200,
         *        "token": "fcac1a3b62cb51374123de565dc12e16"
         *    }
         *  }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            result.put(Vars.TOKEN, "fcac1a3b62cb51374123de565dc12e16");
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject articles(){
        /**{
         *    "result": {
         *    "code": 200,
         *        "articles": [
         *            {
         *                "id": 1,
         *                "title": "Заголовок статьи 1",
         *                "body": "<p>Текст статьи в формате HTML</p>",
         *                "image": "http://blablabla.com/images/article1.jpg"
         *            },
         *            {
         *                "id": 2,
         *                "title": "Заголовок статьи 2",
         *                "body": "<p>Текст статьи в формате HTML</p>",
         *                "image": "http://blablabla.com/images/article2.jpg"
         *            }
         *        ]
         *    }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            JSONArray articles = new JSONArray();
            for(int i = 1; i <= 15; i++){
                JSONObject json = new JSONObject();
                json.put(Vars.ID, i);
                json.put(Vars.TITLE, "Заголовок статьи "+i);
                json.put(Vars.BODY, "<p>Текст статьи в формате HTML "+i+"</p>");
                json.put(Vars.IMAGE, "");
                articles.put(json);
            }
            result.put(Vars.ARTICLES, articles);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject articleDetail(int id){
        /**{
         *    "result": {
         *    "code": 200,
         *    "article":
         *        {
         *            "id": 1,
         *            "title": "Заголовок статьи 1",
         *            "body": "<p>Текст статьи в формате HTML</p>",
         *            "date": "2013-03-01T23:59:59",
         *            "image": "http://blablabla.com/images/article1.jpg"
         *        },
         *    }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            JSONObject json = new JSONObject();
            json.put(Vars.ID, id);
            json.put(Vars.TITLE, "Заголовок статьи "+id);
            json.put(Vars.BODY, "<p>Текст статьи в формате HTML "+id+"</p>");
            json.put(Vars.DATE, "2013-03-01T23:59:59");
            json.put(Vars.IMAGE, "");
            result.put(Vars.ARTICLE, json);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject events(){
        /**{
         *    "result": {
         *    "code": 200,
         *        "events": [
         *            {
         *                "id": 1,
         *                "title": "Название мероприятия 1",
         *                "body": "<p>Описание мероприятия в формате HTML</p>",
         *                "date": "2013-03-01T23:59:59",
         *                "image": "http://blablabla.com/images/event1.jpg"
         *            },
         *            {
         *                "id": 2,
         *                "title": "Название мероприятия 2",
         *                "body": "<p>Описание мероприятия в формате HTML</p>",
         *                "date": "2013-03-01T23:59:59",
         *                "image": "http://blablabla.com/images/event1.jpg"
         *            }
         *        ]
         *    }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            JSONArray events = new JSONArray();
            for(int i = 1; i <= 15; i++){
                JSONObject json = new JSONObject();
                json.put(Vars.ID, i);
                json.put(Vars.TITLE, "Название мероприятия "+i);
                json.put(Vars.BODY, "<p>Описание мероприятия в формате HTML "+i+"</p>");
                json.put(Vars.IMAGE, "");
                events.put(json);
            }
            result.put(Vars.EVENTS, events);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject eventDetail(int id){
        /**{
         *    "result": {
         *    "code": 200,
         *    "event":
         *        {
         *            "id": 1,
         *            "title": "Название мероприятия 1",
         *            "body": "<p>Описание мероприятия в формате HTML</p>",
         *            "date": "2013-03-01T23:59:59",
         *            "image": "http://blablabla.com/images/event1.jpg"
         *        },
         *    }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        String body = "<p><u><i><a href=\"\">Описание мероприятия в формате HTML "+id+"</a></i></u></p><p>" +
                "Описание мероприятияОписание мероприятияОписание мероприятияОписание мероприятияОписание мероприятия" +
                "Описание мероприятияОписание мероприятияОписание мероприятия Описание мероприятия</p>";
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            JSONObject json = new JSONObject();
            json.put(Vars.ID, id);
            json.put(Vars.TITLE, "Название мероприятия "+id);
            json.put(Vars.BODY, body);
            json.put(Vars.DATE, "2013-03-01T23:59:59");
            json.put(Vars.IMAGE, "");
            result.put(Vars.EVENT, json);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject registrationOnEvent(){
        /**
         *  {
         *    "result": {
         *       "code": 200,
         *    }
         *  }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject getUser(String fio, String email, String phone, String avatar){
        /**{
         *    "result": {
         *    "code": 200,
         *    "user":
         *        {
         *            "fio": "Пупкин Василий Петрович",
         *            "email": "pupkin@blablabla.com",
         *            "phone": "+79269101010",
         *            "avatar": "http://balbalbla.com/avatars/1.jpg"
         *        },
         *    }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            JSONObject user = new JSONObject();
            user.put(Vars.FIO, fio);
            user.put(Vars.EMAIL, email);
            user.put(Vars.PHONE, phone);
            user.put(Vars.AVATAR, avatar);
            result.put(Vars.USER, user);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject editUser(String fio, String email, String phone, String avatar){
        /**{
         *    "result": {
         *    "code": 200,
         *    "user":
         *        {
         *            "fio": "Пупкин Василий Петрович",
         *            "email": "pupkin@blablabla.com",
         *            "phone": "+79269101010",
         *            "avatar": "http://balbalbla.com/avatars/1.jpg"
         *        },
         *    }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject result = new JSONObject();
            result.put(Vars.CODE, 200);
            JSONObject user = new JSONObject();
            user.put(Vars.FIO, fio);
            user.put(Vars.EMAIL, email);
            user.put(Vars.PHONE, phone);
            user.put(Vars.AVATAR, avatar);
            result.put(Vars.USER, user);
            jsonObject.put(Vars.RESULT, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
