package poker;

import java.util.ArrayList;

public class Poker {

	public static final int anzahlWerte = 13;
	public static final int anzahlFarben = 4;
	public static final int anzahlKartenZug = 5;
	public static int anzahlVersuche = 100000;
	
	public static int hoechsteKarte = 0;
	public static int einPaar = 0;
	public static int zweiPaare = 0;
	public static int drilling = 0;
	public static int strasse = 0;
	public static int flush = 0;
	public static int fullHouse = 0;
	public static int vierling = 0;
	public static int straightFlush = 0;
	public static int royalFlush = 0;
	
	public static int[] kartenzug(int[] karten)	{
		int[] zug = new int[anzahlKartenZug];
		for(int i = 0; i < anzahlKartenZug; i++)	{
			int index = (int)(Math.random()*(anzahlWerte * anzahlFarben - i));
			zug[i] = karten[index];
			int x = karten[index];
			karten[index] = karten[karten.length - 1 - i];
			karten[karten.length - 1 - i] = x;
		}
		return zug;
	}
	
	public static void countCombination(int[] zug)	{
		boolean istStrasse = strasse(zug);
		boolean hatGleicheFarbe = gleicheFarbe(zug);
		int paare = 0;
		boolean drillingExistiert = false;
		boolean paar = false;
		for (int i = 0; i < zug.length; i++) {
			for (int j = 0; j < zug.length; j++) {
				if(zug[i] % anzahlWerte == zug[j] % anzahlWerte)	{
					if(!paar)	{
						paare++;
						paar = true;
					} else if(!drillingExistiert)	{
						drillingExistiert = true;
						drilling++;
					} else if(drillingExistiert)	{
						vierling++;
					}
				}
				paar = false;
			}
		}
		
		if(paare == 0 && !strasse(zug))	{
			hoechsteKarte++;
		} else {
			einPaar += paare;
			zweiPaare += paare/2;
			if(paare == 2 && drillingExistiert)	{
				fullHouse++;
			}
			if(hatGleicheFarbe)	{
				flush++;
			}
			if(istStrasse)	{
				strasse++;
				if(hatGleicheFarbe)	{
					straightFlush++;
					if(zug[zug.length - 1] % anzahlWerte == anzahlWerte)	{
						royalFlush++;
					}
				}
			}
		}
	}
	
	public static boolean strasse(int[] zug)	{
		int summeWerte = 0;
		int summeStellen = 0;
		for (int i = 0; i < zug.length; i++) {
			summeWerte += (zug[i] % anzahlWerte) - (zug[0] % anzahlWerte);
		}
		for (int i = 0; i < zug.length; i++) {
			summeStellen += i;
		}
		return summeWerte == summeStellen;
	}
	
	public static boolean gleicheFarbe(int[] zug)	{
		int summeFarben = 0;
		for (int i = 0; i < zug.length; i++) {
			summeFarben += zug[i] / anzahlWerte;
		}
		return summeFarben == (zug[0] / anzahlWerte) * zug.length;
	}
	
	public static void printArray(int[] arr)	{
		for(int i = 0; i < arr.length - 1; i++)	{
			System.out.print(arr[i] + ", ");
		}
		System.out.print(arr[arr.length - 1]);
		System.out.println();
	}
	
	public static void printCombination()	{
		System.out.println("Höchste Karte: " + hoechsteKarte);
		System.out.println("Ein Paar: " + einPaar);
		System.out.println("Zwei Paare: " + zweiPaare);
		System.out.println("Drilling: " + drilling);
		System.out.println("Straße: " + strasse);
		System.out.println("Flush: " + flush);
		System.out.println("Full House: " + fullHouse);
		System.out.println("Vierling" + vierling);
		System.out.println("Straight Flush: " +straightFlush);
		System.out.println("Royal Flush: " + royalFlush);
	}
	
	public static void main(String[] args) {
		int[] karten = new int[anzahlWerte * anzahlFarben];
		for(int i = 0; i < karten.length; i++)	{
			karten[i] = i;
		}
//		for (int i = 0; i < anzahlVersuche; i++) {
//			int[] karten2 = karten.clone();
//			countCombination(kartenzug(karten2));
//		}
		int[] zug = kartenzug(karten);
		printArray(zug);
		countCombination(zug);
		printCombination();
	}

}
