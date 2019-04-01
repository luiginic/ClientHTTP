package ApiManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


public class ApiManager {
    private static ApiManager apiManagerInstance = null;
    private Context context;
    private ImageLoader imageLoader;
    private RequestQueue queue;


    private ApiManager(Context context){
        this.context=context;
        queue = getRequestQueue();

        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> cache = new LruCache<>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

    }

    public static synchronized ApiManager getInstance(Context context){
        if(apiManagerInstance == null)
            apiManagerInstance= new ApiManager(context);
        return apiManagerInstance;
    }

    public RequestQueue getRequestQueue(){
        if(this.queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
