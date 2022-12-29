package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	private GestionOption Option;
	private GestionJeu Jeu;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			FXMLLoader loader=FXMLLoader.load(getClass().getResource("Scene Intro.fxml"));
			GridPane root = loader.load();
			Scene scene = new Scene(root);
			C1 Controlleur1 = new C1(scene,Jeu,Option);
			loader.setController(Controlleur1);*/
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Scene Intro.fxml"));
			GridPane root = loader.load();
			Scene scene = new Scene(root);
			C1 Acceuil = loader.getController();
			Acceuil.Controlleur1(primaryStage,Option,Jeu);
			scene.getStylesheets().add(getClass().getResource(Option.getCSS()).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init()
	{
		Option = new GestionOption();
		try {
			Jeu = new GestionJeu("Dico/"+Option.getDico());
		} catch (IOException e) {
			System.out.print("Erreur fichier");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
