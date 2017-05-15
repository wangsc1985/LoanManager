package com.wangsc.loanmanager.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangsc.loanmanager.R;
import com.wangsc.loanmanager.helper._Helper;

import java.util.Calendar;

public class ActionBarFragment extends Fragment {

    //   视图变量
    View containerView;
    ImageView imageView_back, imageView_setting;
    TextView textViewHave, textViewLeave;
    ProgressBar progressBar;
    LinearLayout layoutReligious;

    //   类变量
    private OnActionFragmentBackListener backListener;
    private OnActionFragmentSettingListener settingListener;
    private OnActionFragmentProgressListener progressListener;

    //   值变量
    public static final int TO_LIST = 0;


    public static ActionBarFragment newInstance() {
        ActionBarFragment fragment = new ActionBarFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //  2016/10/19 初始化类变量和值变量
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        containerView = inflater.inflate(R.layout.fragment_action_bar, container, false);
        try {

            //  2016/10/19 初始化视图
            imageView_setting = (ImageView) containerView.findViewById(R.id.imageView_setting);
            imageView_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (settingListener != null)
                        settingListener.onSettingButtonClickListener();
                }
            });
            imageView_setting.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (settingListener != null) {
                        settingListener.onSettingButtonLongClickListener();
                    }
                    return true;
                }
            });


            imageView_back = (ImageView) containerView.findViewById(R.id.imageView_back);
            imageView_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (backListener != null)
                        backListener.onBackButtonClickListener();
                }
            });

            if (settingListener == null) {
                imageView_setting.setVisibility(View.INVISIBLE);
            }
            if (backListener == null) {
                imageView_back.setVisibility(View.INVISIBLE);
            }
//            if (progressListener == null) {
//                progressBar.setVisibility(View.INVISIBLE);
//            }

        } catch (Exception e) {
            _Helper.printException(getContext(), e);
        }
        return containerView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof OnActionFragmentBackListener) {
                backListener = (OnActionFragmentBackListener) context;
            }
            if (context instanceof OnActionFragmentSettingListener) {
                settingListener = (OnActionFragmentSettingListener) context;
            }
            if (context instanceof OnActionFragmentProgressListener) {
                progressListener = (OnActionFragmentProgressListener) context;
            }
        } catch (Exception e) {
            _Helper.printException(getContext(), e);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 如果要使用返回按钮，需实现此接口
     */
    public interface OnActionFragmentBackListener {
        //  更新参数的类型和名字
        void onBackButtonClickListener();
    }

    /**
     * 如果要显示progressbar，需实现此接口
     */
    public interface OnActionFragmentProgressListener {
    }

    /**
     * 如果要使用设置按钮，需实现此接口
     */
    public interface OnActionFragmentSettingListener {
        void onSettingButtonClickListener();

        void onSettingButtonLongClickListener();
    }
}
