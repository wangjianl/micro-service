package com.soft.scheduler.task;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 创建计划任务容器
 *
 * @Author ptmind
 * @Create 2018-03-12
 */

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * 以fixedRate来固定执行定时任务,而不关心任务执行情况
     */
    @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() {
        logger.info("Current Thread : {} , Fixed Rate Task :: Execution Time - {}",Thread.currentThread().getName(),dateTimeFormatter.format(LocalDateTime.now()));

        Request request = new Request.Builder()
                //.url("http://www.dianping.com/search/keyword/2/0_%E8%8B%B1%E8%AF%AD/p4?aid=19159101%2C5169808%2C6045948%2C32648220%2C91957147&cpt=19159101%2C5169808%2C6045948%2C32648220%2C91957147&tc=2")
                .url("http://www.dianping.com/search/keyword/2/0_%E8%8B%B1%E8%AF%AD/p3")
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("User_Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .header("Cookie","__utma=1.1755122501.1499487996.1499487996.1499487996.1; _lxsdk_cuid=15d2073d5efc8-06ac7070087a52-3064750a-1fa400-15d2073d5efc8; _lxsdk=15d2073d5efc8-06ac7070087a52-3064750a-1fa400-15d2073d5efc8; s_ViewType=10; aburl=1; __mta=243570618.1499488077272.1501933581790.1525148019427.3; cy=2; cye=beijing; _lx_utm=utm_source%3Dgoogle%26utm_medium%3Dorganic; _hc.v=9aaba327-8a97-a073-d445-43fac8752c93.1535889871; _lxsdk_s=1659a2b8e3a-a7d-05a-abd%7C%7C578")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println("response:"+response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(50*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 以fixedDelay间隔来执行定时任务，而且会等待任务执行后间隔fixedDelay
     */
    //@Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 初始执行间隔initialDelay时间后，按照间隔fixedRate来执行任务，而不关心任务执行情况
     */
    //@Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    /**
     * 自定义表达式执行
     * 每分钟执行
     */
    //@Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

}
