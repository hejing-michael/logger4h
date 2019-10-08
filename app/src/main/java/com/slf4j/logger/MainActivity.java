package com.slf4j.logger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author hejing
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static List<Logger> loggerList = Arrays.asList(
            LoggerFactory.getLogger("aa"),
            LoggerFactory.getLogger("bb"),
            LoggerFactory.getLogger("cc"),
            LoggerFactory.getLogger("dd"),
            LoggerFactory.getLogger("ee"),
            LoggerFactory.getLogger("ff"),
            LoggerFactory.getLogger("gg"),
            LoggerFactory.getLogger("hh"),
            LoggerFactory.getLogger("ii"),
            LoggerFactory.getLogger("jj"),
            LoggerFactory.getLogger("kk")
    );
    EditText etContent;
    EditText etThread;
    Button btnWrite;
    Button btnTest;
    TextView tvTest;
    EditText etTimes;
    boolean testing = false;

    private void log(String message) {
        for (Logger logger : loggerList) {
            logger.info(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etThread = findViewById(R.id.et_thread);
        etContent = findViewById(R.id.et_content);
        btnWrite = findViewById(R.id.btn_write);
        btnTest = findViewById(R.id.btn_test);
        tvTest = findViewById(R.id.tv_test);
        etTimes = findViewById(R.id.et_times);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etContent.getText().toString();
                for (int i = 0; i < 1000; i++) {
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
                    log("log-->" + time + "-->" + i + " :" + content);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
