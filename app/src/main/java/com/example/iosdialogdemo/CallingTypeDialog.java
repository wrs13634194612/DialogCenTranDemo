package com.example.iosdialogdemo;



        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.text.TextUtils;
        import android.util.DisplayMetrics;
        import android.view.Gravity;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.view.WindowManager;

        import androidx.appcompat.widget.AppCompatImageView;
        import androidx.appcompat.widget.LinearLayoutCompat;

   /*     import com.ivxiaoyuan.wmpf1.R;
        import com.ivxiaoyuan.wmpf1.base.BaseDialogFragment;
        import com.ivxiaoyuan.wmpf1.base.SettingParams;
        import com.ivxiaoyuan.wmpf1.bean.StudentInfo;
        import com.ivxiaoyuan.wmpf1.utils.Utils;*/

public class CallingTypeDialog extends BaseDialogFragment implements View.OnClickListener {

    private LinearLayoutCompat wx_call_ll;
    private LinearLayoutCompat te_call_ll;
    private LinearLayoutCompat wx_not_call_ll;
    private AppCompatImageView close_type_iv;
    private Integer orderMethod = 1;
    private int videoDuration = 0;
    private int voiceDuration = 0;
    private int singleDuration = 0;
    private String balance;
    private int duration;
    private String authStatus;
    private String openid;


    @Override
    protected int initLayout() {
        return R.layout.dialog_calling_type;
    }

    @Override
    protected void initView(View root) {
        wx_call_ll = root.findViewById(R.id.wx_call_ll);
        te_call_ll = root.findViewById(R.id.te_call_ll);
        wx_not_call_ll = root.findViewById(R.id.wx_not_call_ll);
        close_type_iv = root.findViewById(R.id.close_type_iv);

        boolean notClick = false;

        wx_call_ll.setVisibility(notClick ? View.GONE : View.VISIBLE);
        wx_not_call_ll.setVisibility(notClick ? View.VISIBLE : View.GONE);
        initListener();

    }

    private void initListener() {
        wx_call_ll.setOnClickListener(this);
        te_call_ll.setOnClickListener(this);
        wx_not_call_ll.setOnClickListener(this);
        close_type_iv.setOnClickListener(this);
    }

    @Override
    protected void initData(Context context) {

    }

    /*@Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }*/

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();  // 设置宽度为屏宽, 靠近屏幕底部。
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 一定要设置Background，如果不设置，window属性设置无效
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.LEFT;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(params);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wx_call_ll:

                clickBack(1);
                break;
            case R.id.te_call_ll:
                clickBack(2);
                break;
            case R.id.wx_not_call_ll:
            //    notClickTip();
                break;
            case R.id.close_type_iv:
                dismiss();
                break;
        }
    }


    private void clickBack(int position) {
        if (clickBack != null) {
            clickBack.clickBack(position);
        }
    }


    private ClickBack clickBack;

    public void setClickBack(ClickBack clickBack) {
        this.clickBack = clickBack;
    }

    public interface ClickBack {
        void clickBack(int position);
    }
}
