package dcll.parser;

import dcll.interfaces.Xmltojava;
import dcll.quiz.Quiz;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.filter.*;
import java.util.List;
import java.util.Iterator;

public class Xmltojavaparser implements Xmltojava {

	static org.jdom.Document document;
	static Element racine;

	public Quiz parse(String filename) {

		// Instance of SAXBuilder' creation
		SAXBuilder sxb = new SAXBuilder();
		try {
			// New JDOM documents with the filename of the xml
			document = sxb.build(new File(filename));
		} catch (Exception e) {
		}

		// Initialisation of a new element root with the root element of the
		// documents
		racine = document.getRootElement();

		return parseAll();
	}

	public Quiz parseAll() {

		// On crée une List contenant tous les noeuds "etudiant" de l'Element
		// racine
		List listEtudiants = racine.getChildren("etudiant");

		// On crée un Iterator sur notre liste
		Iterator i = listEtudiants.iterator();
		while (i.hasNext()) {
			// On recrée l'Element courant à chaque tour de boucle afin de
			// pouvoir utiliser les méthodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();
			// On affiche le nom de l'element courant
			System.out.println(courant.getChild("nom").getText());
		}

		return new Quiz();
	}
}
