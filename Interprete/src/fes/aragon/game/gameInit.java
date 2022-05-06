package fes.aragon.game;

import fes.aragon.compilador.Principal;
import fes.aragon.inicio.Main;

public class gameInit {

	public static void main(String[] args) {
		Principal analizadores = new Principal();
		analizadores.main(null);
		Main juego = new Main();
		juego.main(null);
	}

}
