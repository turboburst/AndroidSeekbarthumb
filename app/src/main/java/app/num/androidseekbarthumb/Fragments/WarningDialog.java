package app.num.androidseekbarthumb.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import app.num.androidseekbarthumb.R;

/**
 * Created by turbo on 2016/7/21.
 */
public class WarningDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = super.onCreateView(inflater, container, savedInstanceState);
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.dialogfragmentlayout, container, false);
        }
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return mView;
    }
}
