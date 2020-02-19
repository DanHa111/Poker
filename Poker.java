package poker;

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
			int index = (int)(Math.random()*(anzahlWerte * anzahlFarben - 1 - i));
			zug[i] = karten[index];
			int x = karten[index];
			karten[index] = karten[karten.length - 1];
			karten[karten.length - 1] = x;
		}
		return zug;
	}
	
	public static void printArray(int[] arr)	{
		for(int i = 0; i < arr.length - 1; i++)	{
			System.out.print(arr[i] + ", ");
		}
		System.out.print(arr[arr.length - 1]);
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] karten = new int[anzahlWerte * anzahlFarben];
		for(int i = 0; i < karten.length; i++)	{
			karten[i] = i;
		}
		printArray(kartenzug(karten));
	}

}
