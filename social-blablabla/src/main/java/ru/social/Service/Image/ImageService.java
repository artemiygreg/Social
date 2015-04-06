package ru.social.Service.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import ru.social.Interfaces.Callback;
import ru.social.View.BitmapAdapter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

/**
 * Created by Admin on 12.03.15.
 */
public class ImageService {
    private ImageLoader imageLoader = null;
    private Context context;

    public ImageService(Context context){
        if(imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
        }
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.context = context;
    }
    public void setImageByLink(ImageView imageView, String link, final BitmapAdapter bitmapAdapter, final int position){
        imageLoader.displayImage(link, imageView, getOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if(bitmapAdapter != null){
                    bitmapAdapter.cache(position, bitmap);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
    public void setImageByLinkWithRounded(ImageView imageView, String link, final BitmapAdapter bitmapAdapter, final int position){
        imageLoader.displayImage(link, imageView, getOptionsRounded(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if(bitmapAdapter != null){
                    bitmapAdapter.cache(position, bitmap);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
    private DisplayImageOptions getOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(false)
//                .showImageOnLoading(context.getResources().getDrawable(R.drawable.bg_image_post))
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        return options;
    }
    private DisplayImageOptions getOptionsRounded(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(false)
//                .showImageOnLoading(context.getResources().getDrawable(R.drawable.bg_image_profile))
                .displayer(new RoundedBitmapDisplayer(50))
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        return options;
    }
    public static Bitmap makeBitmapFromUri(Context context, Uri uri){
        if(uri == null){
            return null;
        }
        else {
//            return BitmapFactory.decodeFile(uri.toString());
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
    public static void encodeBitmapToBase64(final Bitmap bitmap, final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                final byte[] byteArray = os.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                callback.call(encoded);
            }
        }).start();
    }
}
