package ru.social.View.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import ru.social.R;
import ru.social.Service.Preferences.MyPreferences;
import ru.social.Service.Preferences.MyPreferencesImpl;

/**
 * Created by Admin on 26.03.15.
 */
public class MyProfileFragment extends Fragment {
    private MyPreferences myPfres;
    private boolean editEnable = false;

    public MyProfileFragment(){
        super();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myPfres = new MyPreferencesImpl(getActivity());
        View view = inflater.inflate(R.layout.my_profile_fragment, container, false);
        EditText editTextFio = (EditText)view.findViewById(R.id.editTextFio);
        EditText editTextPhone = (EditText)view.findViewById(R.id.editTextPhone);
        EditText editTextEmail = (EditText)view.findViewById(R.id.editTextEmail);
        editTextFio.setText(myPfres.getFio());
        editTextEmail.setText(myPfres.getEmail());
        editTextPhone.setText(myPfres.getPhone());

        return view;
    }
    private void activateEditing(EditText... allFields){
        for(EditText editText : allFields){
            editText.setEnabled(true);
        }
    }
    private void disactivateEditing(EditText... allFields){
        for(EditText editText : allFields){
            editText.setEnabled(false);
        }
    }
}
