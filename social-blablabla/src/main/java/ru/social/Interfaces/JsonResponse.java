package ru.social.Interfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 12.03.15.
 */
public interface JsonResponse {
    public void success(JSONObject jsonResponse);
    public void failed();
}
