package scs.peter.benchmarkingapplication.benchmarkTools;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CPUBenchmark {
    private String testString;
    private String HashValue;
    private long startTime;
    private Long finishTimeSHA1;
    private String output;
    private boolean SHA1complete;
    private Handler handlerSHA1;

    @SuppressLint("HandlerLeak")
    public CPUBenchmark(String testString) {
        this.testString = testString;
        this.HashValue = null;
        this.output = "";
        this.handlerSHA1 = new Handler() {
            void handleSHA1Message(Message msg) {
                final Long finishTimeSHA1 = (Long) msg.obj;
                output = "SHA-1 hash: " + HashValue + "\n Time Taken: " + finishTimeSHA1.toString() + "\n";
                SHA1complete = true;
            }
        };
    }

    public void hashTestXTimes(final int x)
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                long finishTime = 0;
                for (Integer i = 0; i < x; i++) {
                    finishTime += hashTestString();
                }
                Message msg = Message.obtain();
                msg.obj = finishTime;
                msg.setTarget(handlerSHA1);
                msg.sendToTarget();
            }
        };
        Thread t = new Thread(r);
        t.start();
        //return (long) handlerSHA1.obtainMessage().obj;
    }

    public long hashTestString () {
        Long finishTime;
        startTime = System.nanoTime();
        HashValue = getSHA1Value(testString);
        finishTime = System.nanoTime() - startTime;
        output = "SHA-1 hash: " + " " + HashValue + "\nTime Taken: " + finishTime.toString() + "\n";
        return finishTime;
    }

    private String getSHA1Value(String string)
    {
        String sha1val = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(string.getBytes("UTF-8"));
            byte[] result = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            sha1val = sb.toString();
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1val;
    }

    public String getOutput() {
        return output;
    }
}
