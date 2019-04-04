package redisandkfka.demo.util.okhttp;

import lombok.extern.java.Log;
import okhttp3.Call;
import okhttp3.EventListener;

import java.net.InetAddress;
import java.util.List;
@Log
public class PrintingEventListener extends EventListener {
    private long callStartNanos;

    private void printEvent(String name) {
        long nowNanos = System.nanoTime();
        if (name.equals("callStart")) {
            callStartNanos = nowNanos;
        }
        long elapsedNanos = nowNanos - callStartNanos;
        System.out.printf("%.3f %s%n", elapsedNanos / 1000000000d, name);
        log.info("sd");
    }

    @Override public void callStart(Call call) {
        printEvent("callStart");
    }

    @Override public void callEnd(Call call) {
        printEvent("callEnd");
    }

    @Override public void dnsStart(Call call, String domainName) {
        printEvent("dnsStart");
    }

    @Override public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        printEvent("dnsEnd");
    }


}
