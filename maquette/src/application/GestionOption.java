package application;

public class GestionOption {
	private int Theme;
	private int police;
	private String dico;
	private int lang;
	private String Reso;
	
	private String Pendu[];
	
	private String PActuel;

	
	public GestionOption() {
		this.Theme=0;
		this.police = 12;
		this.dico = "Dico.txt";
		this.lang = 0;
		this.Reso = "450 400";
		setPendu();
		setCSS();

	}
	public GestionOption(int theme, int police, int lang, String reso) {
		setTheme(theme);
		setPolice(police);
		this.dico = "Dico.txt";
		setLang(lang);
		setReso(reso);
		setPendu();
		setCSS();
	}
	
	public int getTheme() {
		return Theme;
	}
	public void setTheme(int theme) {
		this.Theme = theme;
	}
	public int getPolice() {
		return police;
	}
	public void setPolice(int police) {
		this.police = police;
	}
	public String getDico() {
		return dico;
	}
	public void setDico(String dico) {
		this.dico = dico;
	}
	public int getLang() {
		return lang;
	}
	public void setLang(int lang) {
		this.lang = lang;
	}
	public String getReso() {
		return Reso;
	}
	public void setReso(String reso) {
		this.Reso = reso;
	}
	
	public String Titre() {
		if(lang==0)
			return "LE JEU DU PENDU";
		else
			return "HANGING MAN";
	}
	
	public int ChoixLangue(String s) {
		if(s.equals("Francais"))
			return 0;
		else return 1;
	}
	
	public String Langue() {
		if(lang==0) {
			return "LANGUE";
		}
		return "LANGUAGE";
	}
	
	public String Theme() {
		if(lang==0) 
			return "CHOIX DU THEME";
		else
			return "CHOOSE A THEME";
	}
	
	public String Resolution() {
		if(lang==0)
			return "RESOLUTION";
		else
			return "RESOLUTION";
	}
	
	public String Police() {
		if(lang==0)
			return "POLICE";
		else
			return "TYPEFACE";
	}
	
	public String Regles(int nbErreur) {
		if(lang==0) {
			return "Les règles : le but du jeu est de deviner un mot. Vous avez "+nbErreur+
					" chances avant de perdre la partie. Le dictionnaire choisi ne peut être"
					+ " modifié en cours de partie";
		}
		else
			return "The rules: the goal of the game is to guess a word. You have "+nbErreur+
					" chances before losing the game. The chosen dictionary cannot be"
					+" modified during the party";
	}
	
	public String NousC() {
		if(lang==0)
			return "Nous Contactez :";
		else
			return "Contact us";
		
	}
	
	public String[] bouttonLang() {
		
		if(lang==0) {
			String[] button = {"OUI","NON"};
			return button;
		}
		else {
			String[] button = {"YES","NO"};
			return button;
		}
	}
public String[] bouttonLang2() {
		if(lang==0) {
			String[] button = {"VALIDER","ANNULER"};
			return button;
		}
		else {
			String[] button = {"CONFIRM","CANCEL"};
			return button;
		}
	}
	public String PQuitter() {
		if(lang==0) {
			return "Voulez vous réellement quittez ?";
		}
		else 
			return "Do you really want to leave";
	}
	public String NPartie() {
		if(lang==0) {
			return "NOUVELLE PARTIE";
		}
		else 
			return "NEW GAME";
	}
	public String BRegles() {
		if(lang==0) {
			return "REGLES";
		}
		else 
			return "RULES";
	}
	public String BQuitter() {
		if(lang==0) {
			return "QUITTER";
		}
		else 
			return "LEAVE";
	}
	public String PNousC() {
		if(lang==0)
			return "NOUS CONTACTEZ";
		else
			return "CONTACT US";
	}
	public String CLangue() {
		if(lang==0)
			return "Francais";
		else
			return "English";
	}
	public String SelecDicoT() {
		if(lang==0)
			return "CHOIX DU DICTIONNAIRE";
		else return "CHOOSE OF A DICTIONNARY";
	}
	public String SelecDicoH() {
		if(lang==0)
			return "Choisissez quelle dictionnaire vous voulez utilisez :";
		else return "Select the dictionnary that you want to use";
	}
	public String CommentJ() {
		if(lang==0)
			return "Donne nous une lettre en effectuant un clique gauche de la souris sur la lettre de votre choix qui "
					+ "n'est pas barré d'une croix";
		else return "Give us a letter by left-clicking on the letter of your choice that is not cross-crossed";
	}
	public String PMot() {
		if(lang==0)
			return "Votre mot :";
		else return "Your word :";
	}
	public String Fautes() {
		if(lang==0)
			return "Nombre de fautes restantes :";
		else return "Number of errors remaining :";
	}
	
	public void setPendu() {
		if(Theme == 0) {
			String TPendu[]={"","file:Theme/Arbre4.jpg","file:Theme/Arbre5.jpg",
					"file:Theme/Arbre9.jpg","file:Theme/Arbre10.jpg"};
			Pendu=TPendu;

		}
		else {
			String TPendu[] = {"","file:Theme/Potence2.jpg","file:Theme/Potence3.jpg"
					,"file:Theme/Potence9.jpg","file:Theme/Potence10.jpg"};
			Pendu=TPendu;

		}
	}
	public String getPendu(int i) {
		return Pendu[i];
	}
	public String TFin(int nbErreurs, String mot, boolean b) {
		if(lang==0) {
			if(b)
				return "BIEN JOUE !!!!!\nTU AS TROUVE LE MOT QUI ETAIT "+mot+" AVEC "+nbErreurs+" FAUTES RESTANTES, "
				+ "FAITES UNE NOUVELLE PARTIE POUR FAIRE MIEUX";
			else
				return "DOMMAGE\n LE MOT ETAIT "+mot+" TU PEUX RELANCER UNE PARTIE OU QUITTER LE JEUX";
		}
		else {
			if(b)
				return "WELL PLAY !!!!!\nYOU FOUND THE WORD WHO WAS "+mot+" WITH "+nbErreurs+" REMAINING FAULTS, "
				+"DO A NEW PART TO DO BETTER";
			else
				return "TOO BAD THE WORD WAS "+mot+" YOU CAN RESTART A GAME OR QUIT THE GAMES";
		}
	}
	public String getCSS() {
		return PActuel;
	}
	
	public void setCSS() {
		String PMin = "10px.css";
		String Ponze = "11px.css";
		String PMid = "12px.css";
		String Ptreize = "13px.css";
		String PMax = "14px.css";
		int n=getPolice();
		if(n==10)
			this.PActuel=PMin;
		if(n==11)
			this.PActuel=Ponze;
		if(n==12)
			this.PActuel=PMid;
		if(n==13)
			this.PActuel=Ptreize;
		if(n==14)
			this.PActuel=PMax;
	}
}
