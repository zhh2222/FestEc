package com.example.zhh_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.example.zhh_core.R;
import com.example.zhh_core.delegates.ZhhDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author brett-zhu
 * created at 2019/3/2 11:51
 */
public abstract class ProxyActivity extends SupportActivity {
    public abstract ZhhDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.runFinalization();
        System.gc();
    }
}