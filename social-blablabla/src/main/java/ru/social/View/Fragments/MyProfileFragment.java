package ru.social.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;
import ru.social.API.Vars;
import ru.social.API.WebAPI;
import ru.social.API.WebAPIImpl;
import ru.social.Interfaces.Callback;
import ru.social.Interfaces.JsonResponse;
import ru.social.Json.Json;
import ru.social.JsonResponse.ResponseObject;
import ru.social.Model.User;
import ru.social.R;
import ru.social.Service.Image.ImageService;
import ru.social.Service.Preferences.MyPreferences;
import ru.social.Service.Preferences.MyPreferencesImpl;

/**
 * Created by Admin on 26.03.15.
 */
public class MyProfileFragment extends Fragment {
    private MyPreferences myPfres;
    private Json json = new Json();
    private WebAPI webAPI;
    private boolean editEnable = false;
    private ImageButton edit;
    private ImageView imageViewAvatar;
    private EditText editTextFio, editTextPhone, editTextEmail;
    private static final int REQUEST_AVATAR = 1;
    private String avatar;

    public MyProfileFragment(){
        super();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myPfres = new MyPreferencesImpl(getActivity());
        webAPI = new WebAPIImpl(getActivity());
        View view = inflater.inflate(R.layout.my_profile_fragment, container, false);
        editTextFio = (EditText)view.findViewById(R.id.editTextFio);
        editTextPhone = (EditText)view.findViewById(R.id.editTextPhone);
        editTextEmail = (EditText)view.findViewById(R.id.editTextEmail);
        edit = (ImageButton)view.findViewById(R.id.imageButton);
        imageViewAvatar = (ImageView)view.findViewById(R.id.imageViewAvatar);

        imageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAvatar();
            }
        });

        webAPI.getMyProfile("", new JsonResponse() {
            @Override
            public void success(JSONObject jsonResponse) {

            }

            @Override
            public void failed() {
                try {
                    JSONObject jsonObject = ResponseObject.getUser(myPfres.getFio(), myPfres.getEmail(), myPfres.getPhone(), "" ).getJSONObject(Vars.RESULT);
                    if(jsonObject.getInt(Vars.CODE) == Vars.OK){
                        User user = json.makeUserFromJson(jsonObject.getJSONObject(Vars.USER));
                        showDataFromResponse(user);
                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEditing(editTextFio, editTextPhone, editTextEmail);
            }
        });

        return view;
    }
    private void activateEditing(EditText... allFields){
        edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_save));
        for(EditText editText : allFields){
            editText.setEnabled(true);
        }
        allFields[0].setFocusable(true);
    }
    private void disactivateEditing(EditText... allFields){
        edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
        for(EditText editText : allFields){
            editText.setEnabled(false);
        }
    }
    private void onClickEditing(EditText... allFields){
        if(!editEnable){
            activateEditing(allFields);
            editEnable = true;
        }
        else {
            disactivateEditing(allFields);
            String fio = allFields[0].getText().toString();
            String phone = allFields[1].getText().toString();
            String email = allFields[2].getText().toString();
            sendEditedData("", fio, phone, email);
            editEnable = false;
        }
    }
    private void sendEditedData(final String avatar, final String fio, final String phone, final String email){
        webAPI.editMyProfile("", fio, email, phone, avatar, new JsonResponse() {
            @Override
            public void success(JSONObject jsonResponse) {

            }

            @Override
            public void failed() {
                try {
                    JSONObject jsonObject = ResponseObject.editUser(fio, email, phone, avatar).getJSONObject(Vars.RESULT);
                    if(jsonObject.getInt(Vars.CODE) == Vars.OK){
                        User user = json.makeUserFromJson(jsonObject.getJSONObject(Vars.USER));
                        showDataFromResponse(user);
                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDataFromResponse(User user){
        editTextFio.setText(user.getFio());
        editTextEmail.setText(user.getEmail());
        editTextPhone.setText(user.getPhone());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_AVATAR){
            if(resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                Bitmap bitmap = ImageService.makeBitmapFromUri(getActivity(), uri);
                imageViewAvatar.setImageBitmap(bitmap);
                ImageService.encodeBitmapToBase64(bitmap, new Callback() {
                    @Override
                    public void call(String encode) {
                        avatar = encode;
                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    private void selectAvatar(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_AVATAR);
    }
}
