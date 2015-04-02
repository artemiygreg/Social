package ru.social.View.Fragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import org.json.JSONObject;
import ru.social.API.Vars;
import ru.social.Interfaces.JsonResponse;
import ru.social.JsonResponse.ResponseObject;
import ru.social.Model.Article;
import ru.social.View.Activity.DetailArticleActivity;
import ru.social.View.Adapter.ArticleAdapter;
import ru.social.View.BaseListFragment;

import java.util.List;

/**
 * Created by Admin on 26.03.15.
 */
public class ArticlesFragment extends BaseListFragment {
    private ArticleAdapter adapter;

    public ArticlesFragment() {
        super();
    }
    @Override
    protected void setAdapter(final RecyclerView recyclerView) {
        webAPI.getListArticles(token, new JsonResponse() {
            @Override
            public void success(JSONObject jsonResponse) {

            }

            @Override
            public void failed() {
                final List<Article> list = json.makeListArticleFromJson(ResponseObject.articles());
                adapter = new ArticleAdapter(list, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e("position", ""+position);
                        Intent intent = new Intent(getActivity(), DetailArticleActivity.class);
                        intent.putExtra(Vars.ID, (int)list.get(position).getId());
                        intent.putExtra(Vars.TOKEN, myPreferences.getToken());
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
