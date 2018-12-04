package com.virtualightning.library.universalview.bases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class BaseDataBundle {
    public List<Serializable> headerList;
    public List<Serializable> contentList;

    public BaseDataBundle() {
        headerList = new ArrayList<>();
        contentList = new ArrayList<>();
    }
}
