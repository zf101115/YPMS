package com.ypms.photopick.imageloader;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ypms.R;
import com.ypms.customWidget.CustomToast;
import com.ypms.photopick.utils.CommonAdapter;
import com.ypms.photopick.utils.ViewHolder;

import java.util.LinkedList;
import java.util.List;

public class MyAdapter extends CommonAdapter<String>
{

	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	public static List<String> mSelectedImage = new LinkedList<String>();

	/**
	 * 文件夹路径
	 */
	private String mDirPath;
	private PhotoPickActivity mActivity;
	private int pics;

	public MyAdapter(Context context, List<String> mDatas, int itemLayoutId, String dirPath, PhotoPickActivity activity, int pics) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		this.mActivity = activity;
		this.pics = pics;
	}

	@Override
	public void convert(final ViewHolder helper, final String item, final int position) {
		//设置no_pic
		helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
		//设置no_selected
		helper.setImageResource(R.id.id_item_select, R.drawable.picture_unselected);
		//设置图片
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);
		
		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);
		
		mImageView.setColorFilter(null);
		//设置ImageView的点击事件放大预览
		mImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mActivity.bigPic(mDirPath + "/" + item);
			}
		});
		mSelect.setOnClickListener(new OnClickListener() {
			//选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v)
			{
				// 已经选择过该图片
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.picture_unselected);
					mImageView.setColorFilter(null);
					mActivity.change(mSelectedImage);
				} else{// 未选择该图片
					if (mSelectedImage.size()>=pics){
						CustomToast.showToastAtTop(mContext.getApplicationContext(),"最多选择"+pics+"张照片",R.drawable.toast_notice,3000);
						//Toast.makeText(mContext,"最多选择"+pics+"张照片",Toast.LENGTH_SHORT).show();
						return;
					}
					mSelectedImage.add(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.pictures_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
					mActivity.change(mSelectedImage);
				}
			}
		});
		
		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item))
		{
			mSelect.setImageResource(R.drawable.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
}
