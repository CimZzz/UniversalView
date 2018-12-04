package com.virtualightning.library.universalview.tools;

import android.graphics.Point;
import android.support.annotation.CallSuper;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.view.ViewGroup;

import com.virtualightning.library.universalview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class LazyLoadToolkit {
    private final ViewGroup hostView;

    private ArrayList<Point> controllerPositionList;
    private LinkedList<ViewController> visibleController;

    private Point indexPoint;

    private int verticalRange;

    public LazyLoadToolkit(ViewGroup hostView) {
        this.hostView = hostView;
        this.controllerPositionList = new ArrayList<>();
        this.visibleController = new LinkedList<>();
    }

    public void removeDataAt(int position) {
        int count = hostView.getChildCount();
        if(count <= position)
            return;

        View child = hostView.getChildAt(position);
        ViewController controller = (ViewController) child.getTag(R.id.viewController);
        controller.onDropped(this.hostView);

        hostView.removeViewAt(position);

        count --;

        for(int i = 0 ; i < count ; i ++) {
            child = hostView.getChildAt(i);
            controller = (ViewController) child.getTag(R.id.viewController);
            controller.setPosition(i);
        }
    }

    public void setAdapter(Adapter adapter) {
        SparseArrayCompat<LinkedList<ViewController<?>>> shortControllerCacheMap = null;
        HashMap<String, List<View>> shortViewCacheMap = null;
        int count = hostView.getChildCount();
        if(count != 0) {
            shortControllerCacheMap = new SparseArrayCompat<>();
            shortViewCacheMap = new HashMap<>();
            for(int i  = 0 ; i < count ; i ++) {
                View child = hostView.getChildAt(i);
                ViewController controller = (ViewController) child.getTag(R.id.viewController);
                controller.onCacheView(shortViewCacheMap);
                controller.onDropped(hostView);

                LinkedList<ViewController<?>> controllerList = shortControllerCacheMap.get(controller.dataType);
                if(controllerList == null) {
                    controllerList = new LinkedList<>();
                    shortControllerCacheMap.put(controller.dataType, controllerList);
                }
                controllerList.add(controller);
            }
            hostView.removeAllViews();
            this.controllerPositionList.clear();
            this.visibleController.clear();
        }
        if(adapter == null)
            return;

        adapter.toolkit = this;
        if((count = adapter.getItemCount()) == 0)
            return;

        if(indexPoint == null)
            indexPoint = new Point();

        indexPoint.x = -1;
        indexPoint.y = -1;

        for(int i = 0 ; i < count ; i ++) {
            Object data = adapter.getData(i);
            int dataType = adapter.getItemDataType(data, i);
            ViewController<?> viewController = null;
            if(shortControllerCacheMap != null) {
                LinkedList<ViewController<?>> controllerList = shortControllerCacheMap.get(dataType);
                if(controllerList != null) {
                    viewController = controllerList.pollFirst();
                    if(controllerList.size() <= 0)
                        shortControllerCacheMap.remove(dataType);
                }
            }

            if(viewController == null)
                viewController = adapter.createViewController(hostView, i, dataType);


            viewController.setData(data);
            viewController.setPosition(i);
            viewController.setDataType(dataType);
            viewController.initView(hostView, shortViewCacheMap);
            hostView.addView(viewController.itemView);
        }

        if(shortControllerCacheMap != null)
            shortControllerCacheMap.clear();
    }

    public void onLayoutChanged() {
        controllerPositionList.clear();
        int count = hostView.getChildCount();
        for(int i = 0 ; i < count ; i ++) {
            View child = hostView.getChildAt(i);
            controllerPositionList.add(getChildRect(child));
        }

        int height = hostView.getMeasuredHeight();

        int parentHeight = hostView.getParent() != null ? ((View)hostView.getParent()).getMeasuredHeight() : 0;
        verticalRange = height > parentHeight ? parentHeight : height;
    }


    public void onVerticalOffsetChanged(int offset) {
        int minOffsetRange = offset - 300;
        if(minOffsetRange < 0)
            minOffsetRange = 0;


        int maxOffsetRange = offset + verticalRange + 300;


        if(findVisibleRange(minOffsetRange, maxOffsetRange)) {
            for(ViewController controller : visibleController) {
                int position = controller.getPosition();
                if(position >= indexPoint.x && position <= indexPoint.y)
                    continue;

                controller.onInvisible(hostView);
            }
            visibleController.clear();
            for(int i = indexPoint.x ; i <= indexPoint.y ; i ++) {
                View child = hostView.getChildAt(i);
                ViewController controller = (ViewController) child.getTag(R.id.viewController);
                visibleController.add(controller);
                if(!controller.isVisible)
                    controller.onVisible(hostView);

            }
        }
    }

    private boolean findVisibleRange(int minOffsetRange, int maxOffsetRange) {
        int startIndex = -1, endIndex = -1;
        int size = controllerPositionList.size();

        int low = 0;
        int high = size - 1;

        if(size == 0)
            return false;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Point point = controllerPositionList.get(mid);
            if(point.y >= minOffsetRange && point.x <= maxOffsetRange)
                high = mid - 1;
            else {
                if(point.y < minOffsetRange) {
                    //check next point
                    int nextIdx = mid + 1;
                    if(nextIdx >= size)
                        break;

                    Point nextPoint = controllerPositionList.get(nextIdx);
                    if (nextPoint.y >= minOffsetRange && nextPoint.x <= maxOffsetRange) {
                        startIndex = nextIdx;
                        break;
                    } else low = nextIdx;
                }
                else high = mid - 1;
            }
        }

        if(startIndex == -1)
            startIndex = 0;

        low = startIndex;
        high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Point point = controllerPositionList.get(mid);
            if(point.y >= minOffsetRange && point.x <= maxOffsetRange)
                low = mid + 1;
            else {
                if(point.x > maxOffsetRange) {
                    int nextIdx = mid - 1;
                    if(nextIdx < 0)
                        break;
                    //check next point
                    Point nextPoint = controllerPositionList.get(nextIdx);
                    if (nextPoint.y >= minOffsetRange && nextPoint.x <= maxOffsetRange) {
                        endIndex = nextIdx;
                        break;
                    } else high = nextIdx;
                }
                else low = mid + 1;
            }
        }

        if(endIndex == -1)
            endIndex = size - 1;

        if(startIndex == indexPoint.x && endIndex == indexPoint.y)
            return false;

        indexPoint.set(startIndex, endIndex);
        return true;
    }

    private Point getChildRect(View child) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        return new Point(child.getTop() - layoutParams.topMargin, child.getBottom() + layoutParams.bottomMargin);
    }



    public static abstract class Adapter {
        private LazyLoadToolkit toolkit;

        public abstract Object getData(int position);
        public abstract int getItemCount();
        public abstract int getItemDataType(Object data, int position);
        public abstract ViewController createViewController(ViewGroup parent, int position, int dataType);

        public final void notifyDataSetChanged() {
            toolkit.setAdapter(this);
        }

        public final ViewController<?> getViewController(int position) {
            if(position >= toolkit.hostView.getChildCount())
                return null;

            View view = toolkit.hostView.getChildAt(position);
            return (ViewController<?>) view.getTag(R.id.viewController);
        }

        public final void notifyDataChangedAt(int position) {
            View view = toolkit.hostView.getChildAt(position);
            ViewController<?> controller = (ViewController<?>) view.getTag(R.id.viewController);
            if(controller.isVisible)
                controller.onVisible(toolkit.hostView);
        }

        public final void notifyDataChangedAt(ViewController<?> viewController) {
            if(viewController != null && viewController.isVisible)
                viewController.onVisible(toolkit.hostView);
        }

        public final void notifyRemoveAt(int position) {
            toolkit.removeDataAt(position);
        }
    }


    public static abstract class ViewController<T> {
        private T data;
        private int dataType;
        private int position;
        private boolean isVisible;
        protected final View itemView;

        protected ViewController(ViewGroup parent) {
            itemView = generateRootView(parent);
            itemView.setTag(R.id.viewController, this);
        }

        private void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        private void setData(Object data) {
            this.data = (T) data;
        }

        public T getData() {
            return data;
        }

        private void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public int getDataType() {
            return dataType;
        }

        protected abstract View generateRootView(ViewGroup parent);

        protected abstract void initView(ViewGroup parent, HashMap<String, List<View>> shortViewCacheMap);

        @CallSuper
        protected void onVisible(ViewGroup parent) {
            isVisible = true;
        }

        @CallSuper
        protected void onInvisible(ViewGroup parent) {
            isVisible = false;

        }

        @CallSuper
        protected void onCacheView(HashMap<String, List<View>> viewCacheMap) {
        }

        @CallSuper
        protected void onDropped(ViewGroup parent) {
            isVisible = false;
        }

        public final <E extends View> E findViewById(int id) {
            return itemView.findViewById(id);
        }
    }
}
