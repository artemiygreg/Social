package ru.social.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import ru.social.API.Vars;
import ru.social.API.WebAPI;
import ru.social.API.WebAPIImpl;
import ru.social.Interfaces.JsonResponse;
import ru.social.JsonResponse.ResponseObject;
import ru.social.R;

public class DetailEventActivity extends BaseActivity {
    private WebAPI webAPI = WebAPIImpl.getInstance();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event_activity);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        final int id = getIntent().getIntExtra(Vars.ID, 0);
        String token = getIntent().getStringExtra(Vars.TOKEN);
        webAPI.getArticle(token, id, new JsonResponse() {
            @Override
            public void success(JSONObject jsonResponse) {

            }

            @Override
            public void failed() {
                try {
                    JSONObject response = ResponseObject.eventDetail(id).getJSONObject(Vars.RESULT);
                    if(response.getInt(Vars.CODE) == Vars.OK){
                        setDataFromJsonResponse(response.getJSONObject(Vars.EVENT));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void setDataFromJsonResponse(JSONObject jsonObject){
        TextView title = (TextView)findViewById(R.id.title);
        TextView date = (TextView)findViewById(R.id.date);
        TextView body = (TextView)findViewById(R.id.body);
        ImageView imageArticle = (ImageView)findViewById(R.id.imageArticle);

        try {
            title.setText(jsonObject.getString(Vars.TITLE));
            date.setText(jsonObject.getString(Vars.DATE));
            Spanned spannedBody = Html.fromHtml(jsonObject.getString(Vars.BODY));
            body.setText(spannedBody);
            initToolbar(toolbar, jsonObject.getString(Vars.TITLE), true, true);
//            imageArticle.setImageURI(Uri.parse(jsonObject.getString(Vars.IMAGE)));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
