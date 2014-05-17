package dateihandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Main.Benutzerinterface;

/**
 * @author TolleN
 * 
 */
public class Datei {
	public static final String KOMMENTARMARKER = "##";
	public static final char MERKMAL_BEGINN = '[';
	public static final char MERKMAL_ENDE = ']';
	public static final char BEZEICHNER_WERT_TRENNER = '|';

	/**
	 * Methode die eine gegebene Datei Zeilenweise ausliest und ein Array von
	 * Strings aller Zeilen zur�ckgibt. Datei muss von UI gepr�ft worden sein
	 * auf lesbarkeit und isFile().
	 * 
	 * @param neueDatei
	 * @return ArrayList<String> pro Index eine Zeile. Ohne Leerzeilen.
	 */
	public static ArrayList<String> leseDatei(File aktuelleDatei) {
		ArrayList<String> zeilen = new ArrayList<String>();
		ArrayList<String> ohneLeerzeilen = new ArrayList<String>();
		int aktuelleZeile = 0;
		try {
			FileReader fileReader = new FileReader(aktuelleDatei);
			BufferedReader reader = new BufferedReader(fileReader);
			while (reader.ready()) {
				zeilen.add(aktuelleZeile, reader.readLine().trim());
				aktuelleZeile += 1;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Datei nicht gefunden");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fehler in Datei");
		}
		for (String zeile : zeilen) {
			if (!zeile.equals("")) {
				int i = 0;
				ohneLeerzeilen.add(zeilen.get(i));
				i += 1;
			}
		}
		return ohneLeerzeilen;
	}

	/**
	 * bekommt Dateinamen[inklusive Suffix!] und OutputStream als String
	 * 
	 * @param zuSchreibenderText
	 *            ArrayList von Strings mit allen Zeilen.
	 * @param neuerDateiname
	 *            String der Namen+Suffix der Datei(kein ".txt") enth�lt.
	 * @author Nils
	 */
	public static void schreibeDatei(ArrayList<String> zuSchreibenderText,
			String neuerDateiname) {
		File neueDatei = new File(Benutzerinterface.standardpfad
				+ neuerDateiname + ".txt");
		try {
			PrintStream writer = new PrintStream(neueDatei);
			for (String zeile : zuSchreibenderText) {
				writer.println(zeile);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Datei nicht gefunden");
			return;
		}

	}

}