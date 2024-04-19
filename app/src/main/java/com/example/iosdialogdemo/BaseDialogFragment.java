package com.example.iosdialogdemo;



        import android.app.Dialog;
        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.DialogFragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;


        import java.lang.reflect.Field;

public abstract class BaseDialogFragment extends DialogFragment {

    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//1.必须设置dialog的window背景为透明颜色，不然圆角无效或者是系统默认的颜色
        View view=inflater.inflate(initLayout(),container,false);
        initView(view);
        initData(context);
        return view;
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        NoLeakDialog noLeakDialog = new NoLeakDialog(getActivity(), getTheme());
        noLeakDialog.setHostFragmentReference(this);
        return noLeakDialog;
    }

    /**
     * 初始化布局
     */
    protected abstract  int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView(View root);


    /**
     * 初始化，绑定数据
     * @param context 上下文
     */
    protected abstract void initData(Context context);


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 处理dialogFragment 内存泄漏
        try {
            Class<?> aClass = Class.forName("android.app.Dialog");
            Field declaredField = aClass.getDeclaredField("mListenersHandler");
            //boolean accessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            Class<?> type = declaredField.getType();
            Handler handler= (Handler) type.newInstance();
            Log.i("allen","type::"+type+",,,handler::"+handler);
            if (handler!=null){
                handler.removeCallbacksAndMessages(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllowingStateLoss(FragmentManager manager, String tag){
        try {
            Field dismissed = DialogFragment.class.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(this, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Field shown = DialogFragment.class.getDeclaredField("mShownByMe");
            shown.setAccessible(true);
            shown.set(this, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}
