package poker;

import java.util.Scanner;

public class PokerMehrereKombinationen {
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
		return selectionSort(zug);
	}
	
	public static void countCombination(int[] zug)	{
		boolean istStrasse = strasse(zug);
		boolean hatGleicheFarbe = gleicheFarbe(zug);
		int paare = 0;
		boolean drillingExistiert = false;
		int zaehler;
		for (int i = 0; i < zug.length - 1; i++) {
			zaehler = 1;
			while(i < zug.length - 1 && zug[i] % anzahlWerte == zug[i + 1] % anzahlWerte)	{
				zaehler++;
				i++;
			}
			paare += zaehler / 2;
			vierling += zaehler / 4;
			if(zaehler / 3 >= 1)	{
				drillingExistiert = true;
				drilling += zaehler / 3;
			}
		}
		
		if(paare == 0 && !istStrasse && !hatGleicheFarbe)	{
			hoechsteKarte++;
		} else {
			einPaar += paare;
			zweiPaare += paare/2;
			if(paare == 1 && drillingExistiert)	{
				fullHouse++;
			}
			if(hatGleicheFarbe)	{
				flush++;
			}
			if(istStrasse)	{
				strasse++;
				if(hatGleicheFarbe)	{
					straightFlush++;
					if(zug[zug.length - 1] % anzahlWerte == anzahlWerte - 1)	{
						royalFlush++;
					}
				}
			}
		}
	}
	
	private static boolean strasse(int[] zug)	{
		int zaehler = 0;
		for (int i = 0; i < zug.length - 1; i++) {
			if((zug[i] % anzahlWerte) + 1 == zug[i + 1] % anzahlWerte)	{
				zaehler++;
			}
		}
		return zaehler == zug.length - 1;
	}
	
	private static boolean gleicheFarbe(int[] zug)	{
		int zaehler = 0;
		for (int i = 0; i < zug.length - 1; i++) {
			if((zug[i] / 13) == zug[i + 1] / anzahlWerte)	{
				zaehler++;
			}
		}
		return zaehler == zug.length - 1;
	}
	
	private static int[] selectionSort(int[] List)	{
		int[] newList = new int[List.length];
		int biggest = groesstes(List);
		for(int i = 0; i < List.length; i++)	{
			int smallest = biggest;
			for(int j = 0; j < List.length; j++)	{
				if(List[j] % anzahlWerte <= smallest % anzahlWerte && !in(List[j], newList))	{
					smallest = List[j];
				}
			}
			newList[i] = smallest;
		}
		return newList;
	}
	
	private static boolean in(int i, int[] numbers)	{
		for (int j = 0; j < numbers.length; j++) {
			if(i == numbers[j])	{
				return true;
			}
		}
		return false;
	}
	
	private static int groesstes(int[] zug)	{
		int groesstes = zug[0];
		for(int i = 0; i < zug.length; i++)	{
			if(zug[i] % anzahlWerte > groesstes % anzahlWerte)	{
				groesstes = zug[i];
			}
		}
		return groesstes;
	}
	
	public static void printArray(int[] arr)	{
		for(int i = 0; i < arr.length - 1; i++)	{
			System.out.print(arr[i] + ", ");
		}
		System.out.print(arr[arr.length - 1]);
		System.out.println();
	}
	
	public static void printValArray(int[] arr)	{
		for(int i = 0; i < arr.length - 1; i++)	{
			System.out.print(arr[i] % anzahlWerte + ", ");
		}
		System.out.print(arr[arr.length - 1] % anzahlWerte);
		System.out.println();
	}
	
	public static void printCombination()	{
		System.out.println("Höchste Karte:		" + hoechsteKarte);
		System.out.println("Ein Paar:		" + einPaar);
		System.out.println("Zwei Paare:		" + zweiPaare);
		System.out.println("Drilling:		" + drilling);
		System.out.println("Straße:			" + strasse);
		System.out.println("Flush:			" + flush);
		System.out.println("Full House:		" + fullHouse);
		System.out.println("Vierling:		" + vierling);
		System.out.println("Straight Flush:		" + straightFlush);
		System.out.println("Royal Flush:		" + royalFlush);
	}
	
	public static void printPercentage()	{
		System.out.println("Prozentuales Ergebnis bei " + anzahlVersuche + " Versuchen:");
		System.out.println();
		System.out.println("Höchste Karte:		" + ((double)(hoechsteKarte)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Ein Paar:		" + ((double)(einPaar)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Zwei Paare:		" + ((double)(zweiPaare)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Drilling:		" + ((double)(drilling)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Straße:			" + ((double)(strasse)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Flush:			" + ((double)(flush)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Full House:		" + ((double)(fullHouse)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Vierling:		" + ((double)(vierling)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Straight Flush:		" + ((double)(straightFlush)/(double)(anzahlVersuche)) * 100 + "%");
		System.out.println("Royal Flush:		" + ((double)(royalFlush)/(double)(anzahlVersuche)) * 100 + "%");
	}
	
	public static void main(String[] args) {
		int[] karten = new int[anzahlWerte * anzahlFarben];
		for(int i = 0; i < karten.length; i++)	{
			karten[i] = i;
		}
		
		System.out.print("Geben Sie die Anzahl der durchzuführenden Versuche ein: ");
		Scanner s = new Scanner(System.in);
		anzahlVersuche = s.nextInt();
		s.close();
		
		for (int i = 0; i < anzahlVersuche; i++) {
			int[] karten2 = karten.clone();
			countCombination(kartenzug(karten2));
		}
		printPercentage();
		
		
//		int[] zug = kartenzug(karten);
//		printArray(zug);
//		printValArray(zug);
//		countCombination(zug);
//		printCombination();
	}
}
