package application;

import java.io.IOException;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class C2 {
	
	private Image pendu;
	@FXML
	private ImageView Pendu;
	@FXML
	private Label Mot;
	@FXML
	private Label Error;
	@FXML
	private Button A;
	@FXML
	private Button B;
	@FXML
	private Button C;
	@FXML
	private Button D;
	@FXML
	private Button E;
	@FXML
	private Button F;
	@FXML
	private Button G;
	@FXML
	private Button H;
	@FXML
	private Button I;
	@FXML
	private Button J;
	@FXML
	private Button K;
	@FXML
	private Button L;
	@FXML
	private Button M;
	@FXML
	private Button N;
	@FXML
	private Button O;
	@FXML
	private Button P;
	@FXML
	private Button Q;
	@FXML
	private Button R;
	@FXML
	private Button S;
	@FXML
	private Button T;
	@FXML
	private Button U;
	@FXML
	private Button V;
	@FXML
	private Button W;
	@FXML
	private Button X;
	@FXML
	private Button Y;
	@FXML
	private Button Z;
	
	
	
	private GestionOption opt;
	private GestionJeu jeu;
	private Stage fen;
	char[] TmpMot = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
	
	public void Controlleur2(Stage primaryStage, GestionOption option, GestionJeu jeu2) {
		this.jeu=jeu2;
		this.opt=option;
		this.fen=primaryStage;
		jeu.InitialiserPartie();
		Error.setText(jeu.getNbErreurs()+"/"+jeu.getNbMaxErreurs());
		CreerLabel(Mot);
		CreerPendu(0);
		jeu.setGagner(true);
	}
	
	private void CreerPendu(int i) {
		if(i!=0&& i!=jeu.getNbMaxErreurs()+1) {
			pendu=new Image(opt.getPendu(i));
			Pendu.setImage(pendu);
		}		
	}

	private void CreerLabel(Label Solution) {
		int i = 0;
		char[] Char = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
		String Sol = new String (" ");
		int NElemnt = jeu.getMotMystere().length();
			while(NElemnt != 0)
			{
				Char[i] = '_';
				i++;NElemnt--;
			}
		NElemnt = jeu.getMotMystere().length();
		i= 0;
		while(NElemnt != 0)
		{
			if(this.TmpMot[i]!= ' ')
			{
				Char[i]= this.TmpMot[i];
			}
			Sol = Sol + new String (Char[i]+" ");
			i++;NElemnt--;
		}
		Solution.setText(Sol);		
	}

	public void Lettre(ActionEvent e) throws IOException {
		Vector<Integer> pos=new Vector<Integer>();
        boolean finJeu = false;
        char reponse;
        
        Button lettre = (Button) e.getSource();
        lettre.setDisable(true);
        
        reponse = lettre.getId().charAt(0);
        finJeu = jeu.VerificationFinDeJeu(reponse,pos);
        
        int NbErr = jeu.getNbMaxErreurs();
        Error.setText(jeu.getNbErreurs()+ "/"+ NbErr);
        CreerPendu(jeu.getNbErreurs());
        
        int a = pos.size();
           int i = 0;
           while(a != 0)
           {
               this.TmpMot[pos.get(i)]= reponse;
               System.out.print(this.TmpMot[i]);
               i++;a--;
           }
           
        CreerLabel(Mot);
        
        if(finJeu) {
            i= jeu.getMotMystere().length();
            while(i > 0)
            {
                i--;
             System.out.print(" yo "+i+" "+this.TmpMot[i]);
             
                if(Character.compare(this.TmpMot[i],' ')==0)
                {
                	System.out.print(" ah "+i);
                	jeu.setGagner(false);
                }
                
            }
            FXMLLoader loader1=new FXMLLoader(getClass().getResource("fin.fxml"));
    		FXMLLoader loader2=new FXMLLoader(getClass().getResource("barreOptions.fxml"));
    		GridPane root=new GridPane();
   			HBox BarreOp = loader2.load();
   			GridPane fin=loader1.load();
   			root.add(BarreOp, 0, 0);
   			root.add(fin, 0, 1);
   			Scene scene = new Scene(root);
   			CFin ControlleurFin=loader1.getController();
   			ControlleurFin.Controlleur3(fen, opt, jeu);
   			System.out.print(jeu.getGagner());
   			ControlleurFin.Affichage(jeu.getGagner());
   			C1 ControlleurOpt=loader2.getController();
    		ControlleurOpt.Controlleur1(fen, opt, jeu);
    		ControlleurOpt.Sc=3;
    		scene.getStylesheets().add(getClass().getResource(opt.getCSS()).toExternalForm());
    		fen.setScene(scene);
        }
        
	}
}
