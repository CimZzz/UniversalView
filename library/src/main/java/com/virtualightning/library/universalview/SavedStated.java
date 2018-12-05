package com.virtualightning.library.universalview;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
class SavedStated implements Parcelable {
    Parcelable originalStated;
    int viewState;
    List<Serializable> headerList;
    List<Serializable> contentList;
    Map<String, Object> objectMap;

    SavedStated() {

    }

    SavedStated(Parcel in) {
        originalStated = in.readParcelable(getClass().getClassLoader());
        viewState = in.readInt();
        headerList = in.readArrayList(getClass().getClassLoader());
        contentList = in.readArrayList(getClass().getClassLoader());
        objectMap = in.readHashMap(getClass().getClassLoader());
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
        dest.writeInt(viewState);
        dest.writeList(headerList);
        dest.writeList(contentList);
        dest.writeMap(objectMap);
    }
}
