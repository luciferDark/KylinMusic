package com.kylin.ui.Test.LockView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kylin.R;
import com.kylin.iviews.lockview.KylinLockView;
import com.kylin.iviews.lockview.listeners.OnLockViewSelectStatedChanged;

import java.util.List;

/**
 * Created by kylin on 2018/5/7.
 */

public class TestActivity extends Activity {
    TextView result;
    EditText rEditText, cEditText;
    Button change;
    KylinLockView lockView;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kylin_ui_test_activity_layout);

        lockView = findViewById(R.id.kylinLockView);
        result = findViewById(R.id.result);
        rEditText = findViewById(R.id.rol);
        cEditText = findViewById(R.id.col);
        change = findViewById(R.id.change);


        lockView.setOnLockViewSelectStatedChanged(new OnLockViewSelectStatedChanged() {
            @Override
            public <T extends KylinLockView> void onStart(T t) {

            }

            @Override
            public <T extends KylinLockView> void onChange(T t, final List<Integer> selectedList) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("onChange: " + listToString(selectedList));
                    }
                });
            }

            @Override
            public <T extends KylinLockView> void onComplete(T t, final List<Integer> selectedList) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("onComplete: " + listToString(selectedList));
                    }
                });
            }

            @Override
            public <T extends KylinLockView> void onClear(T t) {

            }

        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rowString = rEditText.getText().toString();
                String colString = cEditText.getText().toString();

                int row = 3;
                int col = 3;
                if (!rowString.isEmpty()) {
                    row = Integer.parseInt(rowString);
                }
                if (!colString.isEmpty()) {
                    col = Integer.parseInt(colString);
                }

                lockView.setRowColNum(row, col);
            }
        });

    }

    public String listToString(List<Integer> list) {
        StringBuffer buffer = new StringBuffer();
        if (list.isEmpty()){
            return  "";
        }
        for (int i = 0; i < list.size(); i ++){
            buffer.append(list.get(i)).append("-->");

            if (i ==( list.size() - 1)){
                buffer.substring(0, buffer.length() - 3);
            }
        }

        return  buffer.toString();
    }
}
