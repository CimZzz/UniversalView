package com.virtualightning.library.universalview.bases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class SavedState implements Parcelable {
    protected SavedState() {

    }

    protected SavedState(Parcel in) {
    }

    public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
        @Override
        public SavedState createFromParcel(Parcel in) {
            return new SavedState(in);
        }

        @Override
        public SavedState[] newArray(int size) {
            return new SavedState[size];
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
