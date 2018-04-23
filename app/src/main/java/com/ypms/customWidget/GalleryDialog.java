package com.ypms.customWidget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ypms.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/23.
 */

public class GalleryDialog extends Dialog {

    private List<String> pics = new ArrayList<>();
    private List<RatioImageView> imageViewList = new ArrayList<>();


    private Context mContext;
    @BindView(R.id.viewpage)
    ViewPager viewPager;
    @BindView(R.id.tv_page)
    TextView tvPage;
    public GalleryDialog(@NonNull Context context,List<String> pics) {
        super(context);
        this.mContext =context;
        this.pics = pics;
    }

    public void showAtPositioin(int position){
        show();
        viewPager.setCurrentItem(position);
        tvPage.setText(position+1+"/"+imageViewList.size());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gallery_dialog_layout);
        ButterKnife.bind(this);
        initAttrs();
        initData();
    }

    private void initData() {
    for (String url:pics){
            RatioImageView imageView = new RatioImageView(mContext);
            Picasso.with(mContext).load(url).into(imageView);
            imageViewList.add(imageView);
        }
        viewPager.setAdapter(new PhotoPagerAdapter());
        viewPager.setCurrentItem(8);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            tvPage.setText(position+1+"/"+imageViewList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initAttrs() {
        //设置宽度铺满屏幕
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setBackgroundDrawableResource(android.R.drawable.screen_background_dark_transparent);
        setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
    }


    class PhotoPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_gallery_layout,null);
            RatioImageView iv = view.findViewById(R.id.iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            Picasso.with(mContext).load(pics.get(position)).into(iv);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
