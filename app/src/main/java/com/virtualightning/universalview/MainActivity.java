package com.virtualightning.universalview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.virtualightning.library.universalview.BaseItemViewCallback;
import com.virtualightning.library.universalview.UniversalBuilder;
import com.virtualightning.library.universalview.UniversalView;
import com.virtualightning.library.universalview.interfaces.IItemViewCallback;

import java.io.Serializable;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    UniversalView universalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        universalView = findViewById(R.id.universalView);
        universalView.setBackgroundColor(Color.BLACK);
        universalView.initUniversalView(new UniversalBuilder()
                .errorViewGenerator(new ErrorGeneratorImpl(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        universalView.refreshAll(true);
                    }
                }))
                .loadingViewGenerator(new LoadGeneratorImpl())
                .layoutManagerGenerator(new LayoutManagerGeneratorImpl())
                .preloadStrategyGenerator(new PreloadStrategyGeneratorImpl())
                .universalRequestCallback(new UniversalRequestCallbackImpl())
                .dataBundle(new ExtendDataBundle())
                .viewPicker(new ExtendViewPicker())
                .itemViewCallback(new ItemViewCallbackImpl())
        );

    }

    public void onAllowUpdateClick(View view) {
        Boolean isAllow = view.getTag() == null ? true : (Boolean) view.getTag();
        view.setTag(!isAllow);
        ((Button) view).setText(String.format(Locale.CHINA, "isAllowUpdateImmediately(%s)", String.valueOf(!isAllow)));
        universalView.setAllowUpdateImmediately(!isAllow);
    }

    public void onForceUpdate(View view) {
        universalView.refreshAll(true);
    }
}
