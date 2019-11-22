package com.wshttp.base;


import android.view.Menu;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * 需要集成此Activity
 */
public abstract class WSActivity extends RxAppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
        
    }
}
