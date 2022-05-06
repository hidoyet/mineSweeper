package mineSweeper;

import java.util.Scanner;

public class MineSweeper {
	public void run() {
		Scanner scanner=new Scanner(System.in);
		System.out.print("Mayýn tarlasýnýn enini giriniz: ");
		int col=scanner.nextInt();
		System.out.print("Mayýn tarlasýnýn boyunu giriniz: ");
		int row=scanner.nextInt();
		String[][] land=new String[row][col];
		String[][] landMap=new String[row][col];
		int mineNumber=(col*row)/4,counter=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				land[i][j]=" - ";
			}
		}				
		generateMineMap(landMap,col,row,mineNumber,counter);//Mayýn tarlasýný oluþturuyoruz	
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
			System.out.print("Satýr numarasýna giriniz: ");
			int landRow=scanner.nextInt();
			System.out.print("Sütun numarasýna giriniz: ");
			int landCol=scanner.nextInt();
			if(landRow<0||landRow>=row||landCol<0||landCol>=col) {
				System.out.println("Girilen satýr veya sütun 0'dan küçük veya mayýn tarlasý sýnýrlarýndan büyük olamaz");
				System.out.println("Yeni bir konum giriniz ");
			continue;
			}
			if(isPressed(land,landMap,landRow,landCol)==false) {
			System.out.println("Mayýna bastýnýz,geçmiþ olsun.");
			break;
			}else
			checkSurroundings(land,landMap,landRow,landCol);
			showMap(land);
			counter++;
			if(counter==((col*row)-mineNumber)) {
			System.out.println("kazandýnýz");
			break;	
		}			
		}	
		scanner.close();	
	}
	public void checkSurroundings(String[][] land, String[][] landMap, int landRow, int landCol) {
		int numberOfMines=0;
		if(landRow-1<0&&landCol-1<0) { //sol üst köþeyi tarýyoruz
			if(landMap[landRow][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol+1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landRow+1==land.length&&landCol-1<0) { //sol alt köþeyi tarýyoruz
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
		if(landCol==0&&landRow>0&&landRow<land.length-1) { //sol orta kýsýmlarý tarýyoruz
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
		if(landRow==0&&landCol>0&&landCol<land[0].length-1) { //üstü tarýyoruz
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
		if(landRow-1<0&&landCol+1>=land[0].length) { //sað üst köþeyi tarýyoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow+1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landCol==land[0].length-1&&landRow>0&&landRow<land.length-1) { //sað tarafý tarýyoruz
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
		if(landCol==land[0].length-1&&landRow==land.length-1){ //sað alt köþeyi tarýyoruz
			if(landMap[landRow][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol-1]==" * ")
				numberOfMines++;
			if(landMap[landRow-1][landCol]==" * ")
				numberOfMines++;
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";
		}
		if(landRow==land.length-1&&landCol>0&&landCol<land[0].length-1) { //alt tarafý tarýyoruz
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
		if((landRow>0)&&(landRow<land.length-1)&&(landCol>0)&&(landCol<land[0].length-1)) { //orta noktalarýn etraflarýný tarýyoruz
			if(landMap[landRow-1][landCol]==" * ")  //noktanýn üstü
				numberOfMines++;			
			if(landMap[landRow-1][landCol-1]==" * ")  //noktanýn sol üstü
				numberOfMines++;			
			if(landMap[landRow-1][landCol+1]==" * ")  //noktanýn sað üstü
				numberOfMines++;			
			if(landMap[landRow+1][landCol]==" * ") //noktanýn altý
				numberOfMines++;			
			if(landMap[landRow+1][landCol+1]==" * ")  //noktanýn sað altý
				numberOfMines++;			
			if(landMap[landRow+1][landCol-1]==" * ") // noktanýn sol altý
				numberOfMines++;			
			if(landMap[landRow][landCol+1]==" * ")  //noktanýn saðý
				numberOfMines++;			
			if(landMap[landRow][landCol-1]==" * ")  //noktanýn solu
				numberOfMines++;			
			land[landRow][landCol]=" "+Integer.toString(numberOfMines)+" ";			
		}		
	}		
	public void generateMineMap(String[][] landMap,int col,int row,int mineNumber,int counter) {
		for(int i=0;i<landMap.length;i++) { //Mayýn haritasýnýn sütun ve satýrlarýna varsayýlan olarak "-" deðerini atýyoruz
			for(int j=0;j<landMap[0].length;j++) {
				landMap[i][j]=" - ";					
			}
		}			
		while(counter!=mineNumber) { //Mayýn haritasýna rastgele mayýn yerleþtiriyoruz
			int rowRandom=(int)(Math.random()*row);
			int colRandom=(int)(Math.random()*col);
			if(landMap[rowRandom][colRandom]!=" * ") {
					landMap[rowRandom][colRandom]=" * ";
				counter++;
			}
		}
			showMap(landMap);
			//oyunun baþýnda kontrol amaçlý mayýnlý matrisi yazdýrýyoruz
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
