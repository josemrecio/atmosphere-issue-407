package com.test;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atmosphere.cpr.AtmosphereHandler;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;

public class Basic implements AtmosphereHandler {
	
	private static final Log log = LogFactory.getLog(Basic.class);

	public Basic(){
		super();
	}

	@Override
	public void onRequest(AtmosphereResource resource) throws IOException {
		HttpServletRequest req = resource.getRequest();
		if ("GET".equals(req.getMethod())) {
			log.info("onRequest(): GET");
			resource.suspend(2000, false);
		}
	}

	@Override
	public void onStateChange(AtmosphereResourceEvent event) throws IOException {
		if (event.isResumedOnTimeout()) {
			log.info("isResumedOnTimeout()");
			event.getResource().getRequest().getSession().invalidate();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
