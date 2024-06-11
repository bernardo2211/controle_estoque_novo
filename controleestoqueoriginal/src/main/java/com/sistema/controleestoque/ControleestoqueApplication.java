package com.sistema.controleestoque;

import org.hibernate.id.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class ControleestoqueApplication extends Application {

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		launch(args);
		SpringApplication.run(ControleestoqueApplication.class, args);
		
	}

@Override
public void start(Stage stage) throws Exception {
	context = SpringApplication.run(ControleestoqueApplication.class);
	FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/java/fx/main.fxml"));
		fxml.setControllerFactory(context::getBean);

		Scene scene = new Scene(fxml.load());
		stage.setTitle("Cadastro de produtos");
		stage.setScene(scene);
		stage.show();
} 
}