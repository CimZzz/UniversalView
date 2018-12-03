package com.virtualightning.library.universalview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class InnerDataBundle {
    List<Serializable> headerList;
    List<Serializable> contentList;

    InnerDataBundle() {
        headerList = new ArrayList<>();
        contentList = new ArrayList<>();
    }
}
