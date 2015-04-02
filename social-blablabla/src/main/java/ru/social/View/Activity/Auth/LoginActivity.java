package ru.social.View.Activity.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
import ru.social.View.Activity.MainActivity;


public class LoginActivity extends BaseActivity {
    private static final int REGISTRATION_CODE = 200;
    private EditText editTextLogin, editTextPassword;
    private Button btnLogin;
    private WebAPI webAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webAPI = new WebAPIImpl(this);
        setContentView(R.layout.login_activity);
        editTextLogin = (EditText)findViewById(R.id.editTextLogin);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar(toolbar, getString(R.string.title_activity_login), false, false);
    }
    public void onClickLogin(View view){
        if(allFieldsFilled()){
            String login = editTextLogin.getText().toString();
            String password = editTextPassword.getText().toString();
            webAPI.auth(login, password, new JsonResponse() {
                @Override
                public void success(JSONObject jsonResponse) {

                }

                @Override
                public void failed() {
                    try {
                        JSONObject response = ResponseObject.auth().getJSONObject(Vars.RESULT);
                        if(response.getInt(Vars.CODE) == 200){
                            saveTokenAndGoToMainActivity(response.getString(Vars.TOKEN));
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    public void onClickRegistration(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivityForResult(intent, REGISTRATION_CODE);
    }
    private void saveTokenAndGoToMainActivity(String token){
        MyPreferences myPrefs = new MyPreferencesImpl(this);
        myPrefs.setToken(token);

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }
    private boolean allFieldsFilled(){
        if(editTextLogin.getText().length() > 0 && editTextPassword.length() > 0)
            return true;
        else if (editTextLogin.getText().length() == 0)
            showToast("");
        else if (editTextPassword.length() == 0)
            showToast("");
        return false;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTRATION_CODE) {
            if (resultCode == RESULT_OK){
                showToast("Регистрация прошла успешно, теперь вы можете авторизоваться!!!");
            }
        }
    }
}
