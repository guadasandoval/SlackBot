package com.botti.controller;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

@WebServlet("/slack/events")
public class BotAppController extends SlackAppServlet{

	@Autowired
	private App app;
	
	
	public BotAppController(App app) {
		super(app);
		
	}
	
}
