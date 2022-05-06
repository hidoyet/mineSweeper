package mineSweeper;

import java.util.Scanner;

public class MineSweeper {
	public void run() {
		Scanner scanner=new Scanner(System.in);
		System.out.print("May�n tarlas�n�n enini giriniz: ");
		int col=scanner.nextInt();
		System.out.print("May�n tarlas�n�n boyunu giriniz: ");
		int row=scanner.nextInt();
		String[][] land=new String[row][col];
		String[][] landMap=new String[row][col];
		int mineNumber=(col*row)/4,counter=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				land[i][j]=" - ";
			}
		}				
		generateMineMap(landMap,col,row,mineNumber,counter);//May�n tarlas�n� olu�turuyoruz	
		readInput(land,landMap,col,row,mineNumber,counter);		
		scanner.close();	
	}
	public void showMap(String[][] land) {
		for(int i=0;i<land.length;i++) {
			for(int j=0;j<land[0].length;j++) {					
					System.out.print(land[i][j]);
			}
			System.out.println();
		}			
	}
	private void readInput(String[][] land,String[][] landMap,int col, int row, int mineNumber, int counter) {
		Scanner scanner=new Scanner(System.in);
		while(counter<=((col*row)-mineNumber)) {
			System.out.print("Sat�r numaras�na giriniz: ");
			int landRow=scanner.nextInt();
			System.out.print("S�tun numaras�na giriniz: ");
			int landCol=scanner.nextInt();
			if(landRow<0||landRow>=row||landCol<0||landCol>=col) {
				System.out.println("Girilen sat�r veya s�tun 0'dan k���k veya may�n tarlas� s�n�rlar�ndan b�y�k olamaz");
				System.out.println("Yeni bir konum giriniz ");
			continue;
			}
			if(isPressed(land,landMap,landRow,landCol)==false) {
			System.out.println("May�na bast�n�z,ge�mi� olsun.");
			break;
			}else
			checkSurroundings(land,landMap,landRow,landCol);
			showMap(land);
			counter++;
			if(counter==((col*row)-mineNumber)) {
			System.out.println("kazand�n�z");
			break;	
		}			
		}	
		scanner.close();	
	}
	public void checkSurroundings(String[][] land, String[][] landMap, int landRow, int landCol) {
		int numberOfMines=0;
		if(landRow-1<0&&landCol-1<0) { //sol �st k��eyi tar�yoruz
			if(landMap[landRow][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landRow+1==land.length&&landCol-1<0) { //sol alt k��eyi tar�yoruz
			if(landMap[landRow][landCol+1]==" * ") {
				numberOfMines++;
			}
			if(landMap[landRow-1][landCol]==" * ") {
				numberOfMines++;
			}
			if(landMap[landRow-1][landCol+1]==" * ") {
				numberOfMines++;
			}
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landCol==0&&landRow>0&&landRow<land.length-1) { //sol orta k�s�mlar� tar�yoruz
			if(landMap[landRow][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol+1]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landRow==0&&landCol>0&&landCol<land[0].length-1) { //�st� tar�yoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landRow-1<0&&landCol+1>=land[0].length) { //sa� �st k��eyi tar�yoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landCol==land[0].length-1&&landRow>0&&landRow<land.length-1) { //sa� taraf� tar�yoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol-1]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landCol==land[0].length-1&&landRow==land.length-1){ //sa� alt k��eyi tar�yoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landRow==land.length-1&&landCol>0&&landCol<land[0].length-1) { //alt taraf� tar�yoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol+1]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if((landRow>0)&&(landRow<land.length-1)&&(landCol>0)&&(landCol<land[0].length-1)) { //orta noktalar�n etraflar�n� tar�yoruz
			if(landMap[landRow-1][landCol]==" * ")  //noktan�n �st�
				numberOfMines++;			
			if(landMap[landRow-1][landCol-1]==" * ")  //noktan�n sol �st�
				numberOfMines++;			
			if(landMap[landRow-1][landCol+1]==" * ")  //noktan�n sa� �st�
				numberOfMines++;			
			if(landMap[landRow+1][landCol]==" * ") //noktan�n alt�
				numberOfMines++;			
			if(landMap[landRow+1][landCol+1]==" * ")  //noktan�n sa� alt�
				numberOfMines++;			
			if(landMap[landRow+1][landCol-1]==" * ") // noktan�n sol alt�
				numberOfMines++;			
			if(landMap[landRow][landCol+1]==" * ")  //noktan�n sa��
				numberOfMines++;			
			if(landMap[landRow][landCol-1]==" * ")  //noktan�n solu
				numberOfMines++;			
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";			
		}		
	}		
	public void generateMineMap(String[][] landMap,int col,int row,int mineNumber,int counter) {
		for(int i=0;i<landMap.length;i++) { //May�n haritas�n�n s�tun ve sat�rlar�na varsay�lan olarak "-" de�erini at�yoruz
			for(int j=0;j<landMap[0].length;j++) {
				landMap[i][j]=" - ";					
			}
		}			
		while(counter!=mineNumber) { //May�n haritas�na rastgele may�n yerle�tiriyoruz
			int rowRandom=(int)(Math.random()*row);
			int colRandom=(int)(Math.random()*col);
			if(landMap[rowRandom][colRandom]!=" * ") {
					landMap[rowRandom][colRandom]=" * ";
				counter++;
			}
		}
			showMap(landMap);
			//oyunun ba��nda kontrol ama�l� may�nl� matrisi yazd�r�yoruz
		}
	public boolean isPressed(String[][] land, String[][] landMap, int landRow, int landCol) {
		if(landMap[landRow][landCol]==" - ") {
			System.out.println(landMap[landRow][landCol]);
			System.out.println(land[landRow][landCol]);
			return true;
		}else			
		return false;
	}
}
