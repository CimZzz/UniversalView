package com.virtualightning.library.universalview;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
class SavedStated implements Parcelable {
    Parcelable originalStated;
    SparseArray<Parcelable> modeStated;
    int viewState;
    List<Serializable> headerList;
    List<Serializable> contentList;
    List<Object> objectList;

    SavedStated() {

    }

    SavedStated(Parcel in) {

    }

    public static final Creator<SavedStated> CREATOR = new Creator<SavedStated>() {
        @Override
        public SavedStated createFromParcel(Parcel in) {
            return new SavedStated(in);
        }

        @Override
        public SavedStated[] newArray(int size) {
            return new SavedStated[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(originalStated, 0);
        dest.writeParcelable(modeStated, 0);
        dest.writeInt(viewState);
        dest.writeMap(objectHashMap);
    }
}
