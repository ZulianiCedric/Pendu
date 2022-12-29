package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class C1{

	private GestionOption opt;
	private GestionJeu jeu;
	private Stage fen;
	public int Sc=1;
	
	public void Controlleur1(Stage primaryStage, GestionOption option, GestionJeu jeu2) {
		this.jeu=jeu2;
		this.opt=option;
		this.fen=primaryStage;
	}
	
	public void Jeu(ActionEvent e) throws IOException {
		ChoiceDialog dialog = new ChoiceDialog("Annuler","Dico.txt","Pays.txt","Fruit.txt","Animaux.txt","Annuler");
		dialog.setTitle(opt.SelecDicoT());
		dialog.setHeaderText(opt.SelecDicoH());
		dialog .setGraphic(null);
		dialog.getDialogPane().getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
		dialog.showAndWait();
		if(dialog.getSelectedItem() != "Annuler") {
			String c="Dico/"+dialog.getSelectedItem();
			jeu.ChangerDico(c);
			FXMLLoader loader1=new FXMLLoader(getClass().getResource("jeu.fxml"));
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("barreOptions.fxml"));
			String aux[] = opt.getReso().split(" ");
			GridPane root = new GridPane();
			AnchorPane Jeu=loader1.load();
			HBox BarreOp=loader2.load();
			root.add(BarreOp, 0, 0);
			root.add(Jeu, 0, 1);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
			C2 ControlleurJeu=loader1.getController();
			ControlleurJeu.Controlleur2(fen, opt, jeu);
			C1 ControlleurOpt=loader2.getController();
			ControlleurOpt.Controlleur1(fen, opt, jeu);
			ControlleurOpt.Sc=2;
			fen.setScene(scene);	
		}
	}
	
	public void Option(ActionEvent e) throws IOException {
		String LangueActuel = opt.CLangue();
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Les options");
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Option.fxml"));	
		GridPane Option = loader.load();
		Option.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
		((Labeled) Option.getChildren().get(0)).setText(opt.Langue());
		((Labeled) Option.getChildren().get(2)).setText(opt.Theme());
		((Labeled) Option.getChildren().get(4)).setText(opt.Resolution());
		((Labeled) Option.getChildren().get(6)).setText(opt.Police());

		((Slider)Option.getChildren().get(7)).setValue(opt.getPolice());
		((Slider)Option.getChildren().get(3)).setValue(opt.getTheme());
		((ChoiceBox<String>)Option.getChildren().get(1)).setValue(LangueActuel);
		((ChoiceBox<String>)Option.getChildren().get(5)).setValue(opt.getReso());		
		Option.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());

		dialog.getDialogPane().setContent(Option);
		String[] aux=opt.bouttonLang2();
		ButtonType BoutonTypeOUI = new ButtonType(aux[0], ButtonData.OK_DONE);
		ButtonType BoutonTypeNON = new ButtonType(aux[1], ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(BoutonTypeOUI,BoutonTypeNON);
		dialog.setResultConverter(new Callback<ButtonType, String>(){
			@Override
			public String call(ButtonType b) {					
				if(b==BoutonTypeOUI) {
					String lang = (String) ((ChoiceBox)Option.getChildren().get(1)).getValue();
					String reso=(String)((ChoiceBox)Option.getChildren().get(5)).getValue();
					
					opt.setLang(opt.ChoixLangue(lang));
					opt.setPolice((int)((Slider)Option.getChildren().get(7)).getValue());
					opt.setReso(reso);
					opt.setTheme((int)((Slider)Option.getChildren().get(3)).getValue());	
					opt.setCSS();
					opt.setPendu();
				}
				return null;
			}
		});
		Optional<String> selection = dialog.showAndWait();
		if(Sc==1) {		
			GridPane root =(GridPane) (((Scene)fen.getScene()).getRoot());
			((Labeled)root.getChildren().get(0)).setText(opt.Titre());	
			
			HBox Hb1=(HBox) root.getChildren().get(2);
			((Labeled)Hb1.getChildren().get(1)).setText(opt.NPartie());
			
			HBox Hb3=(HBox) root.getChildren().get(3);
			((Labeled)Hb3.getChildren().get(1)).setText(opt.BRegles());
			
			HBox Hb4=(HBox) root.getChildren().get(5);
			((Labeled)Hb4.getChildren().get(1)).setText(opt.PNousC());
			
			HBox Hb5=(HBox) root.getChildren().get(6);
			((Labeled)Hb5.getChildren().get(1)).setText(opt.BQuitter());
		}
		
		if(Sc==2) {
			GridPane root =(GridPane) (((Scene)fen.getScene()).getRoot());
			AnchorPane root2= (AnchorPane) root.getChildren().get(1);
			((Labeled) root2.getChildren().get(27)).setText(opt.CommentJ());
			((Labeled) root2.getChildren().get(28)).setText(opt.PMot());
			((Labeled) root2.getChildren().get(29)).setText(opt.Fautes());
		}
		if(Sc==3) {
			GridPane root =(GridPane) (((Scene)fen.getScene()).getRoot());
			VBox root2= (VBox) ((GridPane) root.getChildren().get(1)).getChildren().get(1);
			((Labeled) root2.getChildren().get(1)).setText(opt.TFin(jeu.getNbErreurs(), jeu.getMotMystere(), jeu.getGagner()));
		}
		String aux1[]=opt.getReso().split(" ");
		fen.setHeight((Double.parseDouble(aux1[0]))+38);
		fen.setWidth((Double.parseDouble(aux1[1]))+14);
		fen.getScene().getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
		
	}
	
	public void OuvrirAide(ActionEvent e) throws IOException {
		Dialog<Image> dialog = new Dialog<>();
		dialog.setTitle("Les r�gles");
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Regles.fxml"));
		GridPane Regles = loader.load();
		Regles.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
		((Labeled) Regles.getChildren().get(0)).setText(opt.Regles(10));
		dialog.getDialogPane().setContent(Regles);
		ButtonType BoutonTypeOk = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(BoutonTypeOk);
		Optional<Image> selection=dialog.showAndWait();
	}
	
	public void NousContactez(ActionEvent e) throws IOException {
		Dialog<Image> dialog = new Dialog<>();
		dialog.setTitle("Les r�gles");
		FXMLLoader loader=new FXMLLoader(getClass().getResource("NousContactez.fxml"));
		VBox Contactez = loader.load();
		Contactez.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
		((Labeled) Contactez.getChildren().get(0)).setText(opt.NousC());
		dialog.getDialogPane().setContent(Contactez);
		ButtonType BoutonTypeOk = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(BoutonTypeOk);
		Optional<Image> selection=dialog.showAndWait();
	}
	
	public void Quitter(ActionEvent e) throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Quitter.fxml"));
		VBox quitter = loader.load();
		quitter.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
		((Labeled) quitter.getChildren().get(0)).setText(opt.PQuitter());
		dialog.getDialogPane().setContent(quitter);
		String[] aux=opt.bouttonLang();
		ButtonType BoutonTypeOUI = new ButtonType(aux[0], ButtonData.OK_DONE);
		ButtonType BoutonTypeNON = new ButtonType(aux[1], ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(BoutonTypeOUI,BoutonTypeNON);

		Optional<ButtonType> selection=dialog.showAndWait();
		if(selection.get()==BoutonTypeOUI)
			Platform.exit();
	}

//
}