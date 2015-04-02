package ru.social.API;

import ru.social.Interfaces.JsonResponse;

public interface WebAPI {
    public void registration(String fio, String phone, String email, String password, JsonResponse jsonResponse);
    public void auth(String email, String password, JsonResponse jsonResponse);
    public void getListArticles(String token, JsonResponse jsonResponse);
    public void getArticle(String token, int id, JsonResponse jsonResponse);
    public void getListEvents(String token, JsonResponse jsonResponse);
    public void getEvent(String token, int id, JsonResponse jsonResponse);
    public void registrationOnEvent(String token, int id, JsonResponse jsonResponse);
    public void getMyProfile(String token, JsonResponse jsonResponse);
    public void editMyProfile(String token, String fio, String email, String phone, String avatar,JsonResponse jsonResponse);
}
