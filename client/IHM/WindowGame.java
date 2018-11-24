package client.IHM;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Music;

import client.ConnectionClient;
import client.Game;
import client.Gestionnaire.GestionnaireAdversaire;
import client.Gestionnaire.GestionnaireBonusClient;
import client.Gestionnaire.GestionnaireMissile;
import client.Gestionnaire.GestionnairePartie;
import client.Model.Bonus;
import client.Model.Joueur;
import client.Model.Missile;

public class WindowGame extends BasicGameState {

	private GameContainer container;
	private TiledMap map;

	public static Image ship;
	public static Image shipJoueur;
	public static Image missileJoueur;
	public static Image missileEnnemies;
	public static Image bonus1;
	public static Image bonus2;
	public static Image bonus3;
	public static Image bonus4;
	
	public static Image flag1;
	public static Image flag2;
	public static Image baseFlag1;
	public static Image baseFlag2;

	public static boolean loop = true;

	public WindowGame(int state) {
	}

	public void init(GameContainer container, StateBasedGame sgb) throws SlickException {
		this.container = container;
		container.setAlwaysRender(true);
		this.map = new TiledMap("ressources/map/SpaceBattle.tmx");

		try {
			ship = new Image("ressources/sprites/sprite2.png");
			shipJoueur = new Image("ressources/sprites/sprite2.png");
			missileJoueur = new Image("ressources/sprites/missile.png");
			missileEnnemies = new Image("ressources/sprites/missile2.png");
			bonus1 = new Image("ressources/sprites/PW/bolt_gold.png");
			bonus2 = new Image("ressources/sprites/PW/powerupGreen_star.png");
			bonus4 = new Image("ressources/sprites/PW/shield_gold.png");
			bonus3 = new Image("ressources/sprites/PW/star_gold.png");
			flag1 = new Image("ressources/sprites/flag1.png");
			flag2 = new Image("ressources/sprites/flag2.png");
			baseFlag1 = new Image("ressources/sprites/baseFlag1.png");
			baseFlag2 = new Image("ressources/sprites/baseFlag2.png");

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Game.gestionnairePartie.joueur.loadImage();
		Game.connexionClient.addData(Game.gestionnairePartie);
	}

	public void render(GameContainer container, StateBasedGame sgb, Graphics g) throws SlickException {
		g.pushTransform();
		g.translate(container.getWidth() / 2 - (int) Game.gestionnairePartie.joueur.getX(),
				container.getHeight() / 2 - (int)  Game.gestionnairePartie.joueur.getY());

		// Background
		this.map.render(0, 0);

		Game.gestionnairePartie.renderAll(g);
		
		g.popTransform();
		g.drawString("Id partie : " + Game.gestionnairePartie.getIdPartie(), container.getWidth()-200,10);

	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Game.gestionnairePartie.update(container, delta, map);
		
		if(!loop) {
			loop = true;
			sbg.enterState(0, new EmptyTransition(), new FadeInTransition(Color.black));
			
			System.out.println("end");
		}

		// gestionnaireMissile.addMissileClient();

	}

	public void keyReleased(int key, char c) {

		if (key == Input.KEY_UP || key == Input.KEY_W)
			Game.gestionnairePartie.joueur.keys_pressed[0] = false;
		if (key == Input.KEY_LEFT || key == Input.KEY_A)
			Game.gestionnairePartie.joueur.keys_pressed[1] = false;
		if (key == Input.KEY_RIGHT || key == Input.KEY_D)
			Game.gestionnairePartie.joueur.keys_pressed[2] = false;
		if (key == Input.KEY_ESCAPE)
			container.exit();
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_UP || key == Input.KEY_W)
			Game.gestionnairePartie.joueur.keys_pressed[0] = true;
		if (key == Input.KEY_LEFT || key == Input.KEY_A)
			Game.gestionnairePartie.joueur.keys_pressed[1] = true;
		if (key == Input.KEY_RIGHT || key == Input.KEY_D)
			Game.gestionnairePartie.joueur.keys_pressed[2] = true;
		if (key == Input.KEY_SPACE)
			Game.gestionnairePartie.gestionnaireMissile.addMissileClient();
	}

	@Override
	public int getID() {
		return 1;
	}
}
