package ru.social.View.Activity.Auth;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;
import ru.social.API.Vars;
import ru.social.API.WebAPI;
import ru.social.API.WebAPIImpl;
import ru.social.Interfaces.JsonResponse;
import ru.social.JsonResponse.ResponseObject;
import ru.social.R;
import ru.social.Service.Preferences.MyPreferences;
import ru.social.Service.Preferences.MyPreferencesImpl;
import ru.social.View.Activity.BaseActivity;


public class RegistrationActivity extends BaseActivity {
    private WebAPI webAPI;
    private EditText editTextFio, editTextEmail, editTextPhone, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        webAPI = new WebAPIImpl(this);
        editTextFio = (EditText)findViewById(R.id.editTextFio);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar(toolbar, getString(R.string.title_activity_registration), true, true);
    }
    public void onClickRegistration(View view){
        final String fio = editTextFio.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String phone = editTextPhone.getText().toString();
        String password = editTextPassword.getText().toString();
        webAPI.registration(fio, phone, email, password, new JsonResponse() {
            @Override
            public void success(JSONObject jsonResponse) {

            }

            @Override
            public void failed() {
                try {
                JSONObject response = ResponseObject.registration().getJSONObject(Vars.RESULT);
                    if(response.getInt(Vars.CODE) == 200){
                        saveDataToPref(fio, email, phone, response.getString(Vars.TOKEN));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void saveDataToPref(String fio, String email, String phone, String token){
        MyPreferences myPref = new MyPreferencesImpl(this);
        myPref.setFio(fio);
        myPref.setEmail(email);
        myPref.setPhone(phone);
        myPref.setToken(token);

        setResult(RESULT_OK);
        finish();
    }
}
