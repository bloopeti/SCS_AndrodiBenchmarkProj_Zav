package scs.peter.benchmarkingapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import scs.peter.benchmarkingapplication.benchmarkTools.CPUBenchmark;

public class MainActivityCopy extends AppCompatActivity {

    private TextView mLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogger = findViewById(R.id.Logger);
        mLogger.setMovementMethod(new ScrollingMovementMethod());
        mLogger.setFocusable(false);

        Button mButtonStart = findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CPUBenchmark cpuB = new CPUBenchmark(getString(R.string.string_to_hash));
                cpuB.hashTestString();

                mLogger.append(cpuB.getOutput());
            }});

        Button mButtonCheckFunction = findViewById(R.id.button_check_function);
        mButtonCheckFunction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mLogger.append(Build.MANUFACTURER + "\n" + Build.MODEL + "\n" + Build.BOARD + "\n" + Build.DISPLAY + "\n" + Build.DEVICE + "\n" + Build.FINGERPRINT + "\n" + Build.BRAND + "\n" + Build.HARDWARE + "\n"); //"Yes.\n");
            }});
    }

}
