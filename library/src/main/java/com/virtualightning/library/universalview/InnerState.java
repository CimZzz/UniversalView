package com.virtualightning.library.universalview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class InnerState implements Parcelable {
    /*View 显示状态*/
    public static final int VIEW_STATE_LOADING = 0;//显示状态-加载中
    public static final int VIEW_STATE_CONTENT = 1;//显示状态-内容
    public static final int VIEW_STATE_ERROR = 2;//显示状态-加载失败

    int viewState;




    InnerState() {

    }

    InnerState(Parcel in) {
    }

    public static final Creator<InnerState> CREATOR = new Creator<InnerState>() {
        @Override
        public InnerState createFromParcel(Parcel in) {
            return new InnerState(in);
        }

        @Override
        public InnerState[] newArray(int size) {
            return new InnerState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
