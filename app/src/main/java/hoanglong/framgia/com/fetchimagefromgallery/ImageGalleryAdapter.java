package hoanglong.framgia.com.fetchimagefromgallery;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder> {

    private ArrayList<String> mImageList;

    ImageGalleryAdapter(ArrayList<String> mImageList) {
        this.mImageList = mImageList;
    }

    @NonNull
    @Override
    public ImageGalleryAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryAdapter.ImageViewHolder holder, int position) {
        String imageUri = mImageList.get(position);
        holder.mImageGallery.setImageURI(Uri.parse(imageUri));
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageGallery;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageGallery = itemView.findViewById(R.id.iv_gallery);
        }
    }
}
