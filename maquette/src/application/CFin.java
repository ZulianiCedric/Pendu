package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CFin {
	private GestionOption opt;
	private GestionJeu jeu;
	private Stage fen;

	@FXML
	private Label TFin;
	@FXML
	private ImageView IFin;
	@FXML
	private ImageView IText;
	public void Controlleur3(Stage primaryStage, GestionOption option, GestionJeu jeu2) {
		this.jeu=jeu2;
		this.opt=option;
		this.fen=primaryStage;
	}
	
	public void Affichage(boolean b) {
		if(b) {
			TFin.setText(opt.TFin(jeu.getNbErreurs(), jeu.getMotMystere(), jeu.getGagner()));
			Image ifin=new Image("file:Theme/Victoire.png");
			IFin.setImage(ifin);
			Image itext=new Image("file:Theme/felicitation.jpg");
			IText.setImage(itext);
		}
		else {
			TFin.setText(opt.TFin(jeu.getNbErreurs(), jeu.getMotMystere(), jeu.getGagner()));
			Image ifin=new Image("file:Theme/Defaites.png");
			IFin.setImage(ifin);
			Image itext=new Image("file:Theme/Dommage!.png");
			IText.setImage(itext);
		}
	}
}
