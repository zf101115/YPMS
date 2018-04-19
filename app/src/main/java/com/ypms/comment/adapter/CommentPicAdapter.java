package com.ypms.comment.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.comment.activity.CommentAddActivity;
import com.ypms.comment.bean.CommentPic;
import com.ypms.common.BaseListAdapter;
import com.ypms.common.ContextUtils;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.customWidget.CustomToast;
import com.ypms.customWidget.RoundRectImageView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/18.
 */

public class CommentPicAdapter extends BaseListAdapter {
    private int limitSize;
    private WeakReference<Activity> mWeak;
    private HashMap<String,Bitmap> map = new HashMap<>();
    public CommentPicAdapter(Context context,int limitsize) {
        super(context);
        this.limitSize = limitsize;
        mWeak = new WeakReference<>((Activity) mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder ;
        if(null==convertView){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sku_add_imgs_cell, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Log.e("===",parent.getChildCount()+"+"+position);
        if (position == items.size()){
            holder.iv.setVisibility(items.size() >=limitSize?View.GONE:View.VISIBLE);
            Picasso.with(mContext).load(R.drawable.add_sku_pic_add).into(holder.iv);
            holder.deleteView.setVisibility(View.GONE);
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CommentAddActivity)mWeak.get()).addPic();
                }
            });
        }else if (parent.getChildCount()==position){
            final CommentPic commentPic = (CommentPic) items.get(position);
            if (!TextUtils.isEmpty(commentPic.getPath())){
                if (map.containsKey(commentPic.getPath())){
                    holder.iv.setImageBitmap(map.get(commentPic.getPath()));
                }else {
                    new ImageLoadTask(holder.iv).execute(commentPic.getPath());
                }
            }
            holder.deleteView.setVisibility(View.VISIBLE);
            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView mImageView;

        public ImageLoadTask(ImageView imageView) {
            this.mImageView = imageView;
        }

        @Override
        // Actual download method, run in the task thread
        protected Bitmap doInBackground(String... params) {
            // params comes from the execute() call: params[0] is the url.
            Bitmap bitmap ;
            if(map.containsKey(params[0])){
                bitmap = map.get(params[0]);
            }else{
                bitmap = ContextUtils.compressImage(params[0], 200f);
                if(null == bitmap){
                    CustomToast.showToastAtTop(mContext.getApplicationContext(),"获取图片失败，请重试",R.drawable.custom_toast_fail,3000);
                    return null;
                }
                map.put(params[0],bitmap);
            }
            return bitmap;
        }

        @Override
        // Once the image is downloaded, associates it to the imageView
        protected void onPostExecute(Bitmap bitmap) {
            if (null != bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }

        @Override
        protected void onPreExecute() {
            mImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pictures_no));
        }
    }

    @Override
    public int getCount() {
        return items.size()+1;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        RoundRectImageView iv;
        @BindView(R.id.delete)
        ImageView deleteView;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
    }
}
