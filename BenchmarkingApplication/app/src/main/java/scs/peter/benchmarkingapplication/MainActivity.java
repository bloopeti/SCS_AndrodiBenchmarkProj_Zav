package scs.peter.benchmarkingapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import scs.peter.benchmarkingapplication.benchmarkTools.CPUBenchmark;
import scs.peter.benchmarkingapplication.mFragments.BuildDataFragment;

public class MainActivity extends AppCompatActivity {

   // private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_benchmark);
                    BuildDataFragment buildDataFragment = new BuildDataFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.list, buildDataFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();

                    CPUBenchmark cpuB = new CPUBenchmark(getString(R.string.string_to_hash));
                    cpuB.hashTestString();

                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.button_device_build);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)); //doesnt work from xml \; why?
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
