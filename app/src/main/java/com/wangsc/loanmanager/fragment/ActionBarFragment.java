package com.wangsc.loanmanager.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.wangsc.loanmanager.R;
import com.wangsc.loanmanager.helper._Helper;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class ActionBarFragment extends Fragment {

    //   视图变量
    View containerView;
    ImageView imageView_back, imageView_setting;
    TextSwitcher switcherTitle;

    //   类变量
    private OnActionFragmentBackListener backListener;
    private OnActionFragmentSettingListener settingListener;

    //   值变量
    public static final int TO_LIST = 0;
    private Timer timer;
    private int index;
    private Handler uiThreadHandler;

    // 要显示的文本
    String[] strs = new String[]
            {
                    "南无阿弥陀佛","南无阿弥陀佛"
//                    "一生都是修来的，求什么?",
//                    "今日不知明日事，愁什么?",
//                    "不礼爹娘礼世尊，敬什么?",
//                    "兄弟姐妹皆同气，争什么?",
//                    "儿孙自有儿孙福，忧什么?",
//                    "岂有人无得运时，急什么?",
//                    "人世难逢开口笑，苦什么?",
//                    "补破遮寒暖即休，摆什么?",
//                    "食过三寸成何物，馋什么?",
//                    "死后一文带不去，吝什么?",
//                    "前人田地后人收，占什么?",
//                    "得便宜处失便宜，贪什么?",
//                    "举头三尺有神明，欺什么?",
//                    "荣华富贵眼前花，傲什么?",
//                    "他家富贵前生定，妒什么?",
//                    "前世不修今受苦，怨什么?",
//                    "赌博之人无下梢，耍什么?",
//                    "治家勤俭胜求人，奢什么?",
//                    "冤冤相报几时休，结什么?",
//                    "世事如同棋一局，算什么?",
//                    "聪明反被聪明误，巧什么?",
//                    "虚言折尽平生福，谎什么?",
//                    "是非到底见分明，辨什么?",
//                    "谁能保得常无事，诮什么?",
//                    "穴在人心不在山，谋什么?",
//                    "欺人是祸饶人福，卜什么?",
//                    "一旦无常万事休，忙什么?"
            };

    private int curStr;

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
        uiThreadHandler = new Handler();
        try {

            //  2016/10/19 初始化视图


            switcherTitle = (TextSwitcher) containerView.findViewById(R.id.switcher_title);
            switcherTitle.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    TextView tv = new TextView(getActivity());
                    tv.setTextSize(18);
                    tv.setTextColor(Color.WHITE);
                    return tv;
                }
            });


            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    uiThreadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            switcherTitle.setText(strs[index]);
                            index++;
                            if (index >= strs.length)
                                index = 0;
                        }
                    });
                }
            }, 0, 5000);


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


        } catch (Exception e) {
            _Helper.printException(getContext(), e);
        }
        return containerView;
    }


    // 事件处理函数，控制显示下一个字符串
    public void next(View source) {
        switcherTitle.setText(strs[curStr++ % strs.length]);
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
     * 如果要使用设置按钮，需实现此接口
     */
    public interface OnActionFragmentSettingListener {
        void onSettingButtonClickListener();

        void onSettingButtonLongClickListener();
    }
}
