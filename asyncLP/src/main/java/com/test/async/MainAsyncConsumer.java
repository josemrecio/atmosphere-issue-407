package com.test.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;

public class MainAsyncConsumer {
	
	public static void main(String[] args) {
		final AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
		final ExecutorService asyncExec = Executors.newCachedThreadPool();
		builder.setExecutorService(asyncExec);
		final ScheduledExecutorService reaperExec = Executors.newSingleThreadScheduledExecutor();
		builder.setScheduledExecutorService(reaperExec);
		final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(builder.build());

		final ExecutorService launcherExec = Executors.newSingleThreadExecutor();
		//final String username = args[0];
		final String username = "hola";
		System.out.println("long polling username: " + username);
		launcherExec.submit(new AsyncCometConsumer(asyncHttpClient, username));
	}
}
