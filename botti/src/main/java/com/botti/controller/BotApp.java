package com.botti.controller;

import java.awt.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.botti.model.Birthday;
import com.botti.repository.BirthdayRepo;

import com.slack.api.bolt.App;




import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.HeaderBlock;

import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.DatePickerElement;



@Configuration
public class BotApp {

	@Autowired
	BirthdayRepo birthRepo;
	
	@Bean
    public App initSlackApp() {

		// creo una app de slack
		App app = new App();

		// ctx es el contexto, req me da la info que viene de slack
		app.command("/hola", (req, ctx) -> {

			String commandArgText = req.getPayload().getText();
			String user = req.getPayload().getUserName();
			String channelId = req.getPayload().getChannelId();
			String channelName = req.getPayload().getChannelName();
			// respuesta


			List<LayoutBlock> message = new ArrayList();
			message.add(HeaderBlock.builder()
					.text(PlainTextObject.builder()
					.text("¡Hola!" + " " + user + " :smile:").build())
					.build());

			message.add(DividerBlock.builder().build());
			
			message.add(SectionBlock.builder()
					.text(MarkdownTextObject.builder()
					.text(":calendar: Estos son los proximos feriados :calendar:").build())
					.build());
			
			message.add(SectionBlock.builder()
					.text(MarkdownTextObject.builder()
					.text("`12/10/20` Día del Respeto a la Diversidad Cultural.*").build())
					.build());
			
			message.add(SectionBlock.builder()
					.text(MarkdownTextObject.builder()
					.text("`23/11/20` Día de la Soberanía Nacional (20/11)").build())
					.build());
			
			message.add(SectionBlock.builder()
					.text(MarkdownTextObject.builder()
					.text("`7/12/20` Feriado con Fines Turísticos.").build())
					.build());
			
			message.add(DividerBlock.builder().build());
			
			message.add(HeaderBlock.builder()
					.text(PlainTextObject.builder()
					.text("Mirá el calendario completo acá").build())
					.build());
			
			message.add(SectionBlock.builder()
					.text(MarkdownTextObject.builder()
					.text(":pushpin: <https://www.argentina.gob.ar/interior/feriados-nacionales-2020|Feriados en Argentina>").build())
					.build());

			
			message.add(DividerBlock.builder().build());


			// ack responde un http 200 al req
			return ctx.ack(message);

		});
		
		
		
		return app;
	}
	
	@Bean
	@Primary
	public App sayHappyBirthday() {
		App app = new App();
		app.command("/cumple", (req, ctx) -> {
			
			
			Date fechaActual = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			String strDate = dateFormat.format(fechaActual);
			
			
			List<LayoutBlock> message = new ArrayList();
			
			message.add(SectionBlock.builder()
					.text(MarkdownTextObject.builder()
					.text(":tada: Agregá tu cumpleaños a mi calendario :tada:").build())
					.build());
			
		//boton
//			message.add(ActionsBlock.builder().elements(Arrays.asList(ButtonElement.builder().text(PlainTextObject.builder()
//				.text("click me")
//				.build()).actionId("boton1")
//				.build())).build());
//		
			//datepicker	
		 
			message.add(ActionsBlock.builder()
				   .elements(Arrays.asList(DatePickerElement.builder()
				   .actionId("birthday")
				   .placeholder(PlainTextObject.builder().text("Seleccioná una fecha").build())
				   .initialDate(strDate).build()))
				   .build());
		
		    return ctx.ack(message);
		 
			
			
		});
		
		app.blockAction("birthday", (req,ctx)-> {
			
        
			String userIdStr = req.getPayload().getUser().getId();
		    String userBirth = req.getPayload().getActions().get(0).getSelectedDate();
			
			Birthday birth = new Birthday();
			
			birth.setIdUsuario(userIdStr);
			birth.setFechaCumple(userBirth);
			
			
			birthRepo.save(birth);	

				
			return ctx.ack();
			
		});
		
		return app;
}
	}
	
