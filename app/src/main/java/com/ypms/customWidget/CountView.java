package com.ypms.customWidget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypms.R;


/**
 * Created by Hero on 2018/3/31.
 */

public class CountView extends LinearLayout implements TextWatcher,View.OnClickListener {
    private EditText etAmount;
    private TextView tvDecrease;
    private TextView tvIncrease;
    private Context mContext;
    private int amount = 1;
    private int MaxCount;
    private OnAmountChangeListener mListener;
    public CountView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_count_view, this);
        etAmount = findViewById(R.id.et);
        tvDecrease = findViewById(R.id.tv_decrease);
        tvIncrease = findViewById(R.id.tv_increase);
        tvDecrease.setOnClickListener(this);
        tvIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setMaxCount(int max){
        this.MaxCount = max;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()){
            etAmount.setText("1");
            return;
        }
        amount = Integer.valueOf(s.toString());
        if (amount > MaxCount) {
            etAmount.setText(MaxCount + "");
            return;
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void onClick(View v) {
        int view = v.getId();
        switch (view){
            case R.id.tv_increase:
                if (amount > 1) {
                    amount--;
                    etAmount.setText(amount + "");
                }
                break;
            case R.id.tv_decrease:
                if (amount < MaxCount) {
                    amount++;
                    etAmount.setText(amount + "");
                }
                break;
            default:break;
        }
        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }

    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}
