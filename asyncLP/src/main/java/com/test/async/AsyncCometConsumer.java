package com.test.async;

import java.io.IOException;
import java.util.Random;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class AsyncCometConsumer implements Runnable {
	
	private final String username;
	//private int idCounter;
	private final AsyncHttpClient asyncHttpClient;
	private Random random = new Random();
	
	public AsyncCometConsumer(final AsyncHttpClient asyncHttpClient, final String username) {
		this.username = username;
		//idCounter = 1;
		this.asyncHttpClient = asyncHttpClient;
	}

	public void run() {
		//final String cometUrl = "http://192.168.0.51:8080/basic-comet/BasicCometHandler?username=" + username + "&id=" + idCounter;
		final String cometUrl = "http://127.0.0.1:8080/atmolp/LP?username=" + username + "&id=" + random.nextInt();
		//final String cometUrl = "http://192.168.0.51:8080/rcsbox-standalonecomet/NotificationsCometHandler?username=" + username + "&id=" + random.nextInt();
		try {
			asyncHttpClient.prepareGet(cometUrl).execute(new AsyncCompletionHandler<Response>() {
				@Override
				public Response onCompleted(Response response) throws Exception {
					//System.out.println(response.getStatusCode() + " " + response.getStatusText());
					//Thread.sleep(100);
					System.out.print(".");
					asyncHttpClient.prepareGet(cometUrl).execute(this);
					return response;
				}

				@Override
				public void onThrowable(Throwable t){
					System.out.println("throwable: " + t);
				}		
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
