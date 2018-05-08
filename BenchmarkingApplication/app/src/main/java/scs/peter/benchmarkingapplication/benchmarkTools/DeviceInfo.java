package scs.peter.benchmarkingapplication.benchmarkTools;

import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class DeviceInfo
{
    public String getManufacturer()
    {
        return "Manufacturer: " + Build.MANUFACTURER + "\n";
    }
    public String getModel()
    {
        return "Model: " + Build.MODEL + "\n";
    }
    public String getBrand()
    {
        return "Brand: " + Build.BRAND + "\n";
    }
    public String getBoard()
    {
        return "Board: " + Build.BOARD + "\n";
    }
    public String getDisplay()
    {
        return "Display: " + Build.DISPLAY + "\n";
    }
    public String getDevice()
    {
        return "Device: " + Build.DEVICE + "\n";
    }

    public String getFingerprint()
    {
        return "Fingerprint: " + Build.FINGERPRINT + "\n";
    }
    public String getHardware()
    {
        return "Hardware: " + Build.HARDWARE + "\n";
    }

    public int getNumCores()
    {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter
        {
            @Override
            public boolean accept(File pathname)
            {
                //Check if filename is "cpu", followed by a single digit number
                if(Pattern.matches("cpu[0-9]+", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try
        {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            Log.d(TAG, "CPU Count: "+files.length);
            //Return the number of cores (virtual CPU devices)
            return files.length;
        }
        catch(Exception e)
        {
            //Print exception
            Log.d(TAG, "CPU Count: Failed.");
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }
    /*
    public static int[] getCPUFrequencyCurrent() throws Exception {
        int[] output = new int[getNumCores()];
        for(int i=0;i<getNumCores();i++) {
            output[i] = readSystemFileAsInt("/sys/devices/system/cpu/cpu"+String.valueOf(i)+"/cpufreq/scaling_cur_freq");
        }
        return output;
    }*/
}
