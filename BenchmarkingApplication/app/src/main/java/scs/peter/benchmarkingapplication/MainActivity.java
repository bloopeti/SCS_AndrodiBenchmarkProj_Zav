package scs.peter.benchmarkingapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import scs.peter.benchmarkingapplication.benchmarkTools.CPUBenchmark;
import scs.peter.benchmarkingapplication.benchmarkTools.DeviceInfo;

public class MainActivity extends AppCompatActivity {

    private TextView mLogger; // = (TextView) findViewById(R.id.logger);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mLogger = (TextView) findViewById(R.id.logger);
            switch (item.getItemId()) {
                case R.id.navigation_benchmark:

                    CPUBenchmark cpuB = new CPUBenchmark(getString(R.string.string_to_hash));
                    String s1 = "SHA1 hash time (ns) = " + Long.toString(cpuB.hashTestString());
                    s1 = s1 + "\nFloating point test (FLOPs) = " + cpuB.flops();
                    s1 = s1 + "\nInteger test (int ops / s)= " + cpuB.intops();
                    mLogger.setText(s1);

                    return true;
                case R.id.navigation_device_build:
                    //mLogger.setText(R.string.button_device_build);
                    DeviceInfo deviceInfo = new DeviceInfo();
                    String s2 = deviceInfo.getManufacturer() +
                            deviceInfo.getModel() +
                            deviceInfo.getBrand() +
                            deviceInfo.getBoard() +
                            deviceInfo.getDisplay() +
                            deviceInfo.getDevice() +
                            deviceInfo.getFingerprint() +
                            deviceInfo.getHardware() +
                            "Cores: " + deviceInfo.getNumCores()
                            ;
                    mLogger.setText(s2); //"Yes.\n");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogger = (TextView) findViewById(R.id.logger);
        mLogger.setMovementMethod(new ScrollingMovementMethod());
        mLogger.setFocusable(false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)); //doesnt work from xml \; why?
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
