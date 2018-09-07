package hoanglong.framgia.com.fetchimagefromgallery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mListAllImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpData();
        setUpRecyclerView();
    }

    private void setUpView() {
        mRecyclerView = findViewById(R.id.rv_gallery);
    }

    private void setUpData() {
        mListAllImage = new ArrayList<>();
        new LoadImageAsyncTask().execute();
    }

    private void setUpRecyclerView() {
        //SetUp Adapter
        ImageGalleryAdapter mImageGalleryAdapter = new ImageGalleryAdapter(mListAllImage);
        //Setup RecyclerView
        GridLayoutManager manager = new GridLayoutManager(this, Constant.SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        //Set Adapter
        mRecyclerView.setAdapter(mImageGalleryAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    class LoadImageAsyncTask extends AsyncTask<Intent, Void, ArrayList<String>> {
        @SuppressLint("Recycle")
        @Override
        protected ArrayList<String> doInBackground(Intent... intents) {
            Uri mUri;
            Cursor mCursor;
            int mColumnIndexData;
            String absolutePathOfImage;
            mUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
            mCursor = getContentResolver().query(mUri, projection, null,
                    null, null);
            if (mCursor != null) {
                mColumnIndexData = mCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                while (mCursor.moveToNext()) {
                    absolutePathOfImage = mCursor.getString(mColumnIndexData);
                    mListAllImage.add(absolutePathOfImage);
                }
            }
            return mListAllImage;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            mListAllImage.addAll(s);
        }
    }
}



