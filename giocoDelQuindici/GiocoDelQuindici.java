package giocoDelQuindici;
import java.util.Random;
import java.util.Scanner;


public class GiocoDelQuindici {
	
	private Tessera[][] campo = new Tessera[4][4];
	
	public GiocoDelQuindici() {
		
		int counter = 0;
				
		for(int i=0;i<4;i++)
			for(int j=0; j<4; j++) {
				campo[i][j] = new Tessera(counter+1, j, i);
				counter++;
			}
		
		this.mischia();
	}
	
	private void mischia() {
		
		Random rand = new Random();
		
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				
				int randI = rand.nextInt(4);
				int randJ = rand.nextInt(4);
				
				Tessera temp = campo[i][j];
				temp.setPosX(randJ);
				temp.setPosY(randI);
				
				
				campo[i][j] = campo[randI][randJ];
				
				campo[i][j].setPosX(j);
				campo[i][j].setPosY(i);
				
				campo[randI][randJ] = temp;
				
			}
		
	}
	
	public void disegna() {
		System.out.println(" ________________");
		System.out.println("|                |");
		for(int i=0; i<4;i++) {
			System.out.print("|");
			for(int j=0; j<4; j++) {
				System.out.print(campo[i][j].stampaTessera());
			}
			System.out.println("|");
		}
		System.out.println("|________________|\n");
		
	}
	
	public void scorri(String numeroTessera) {
		
		Tessera tesseraScelta = null;
		Tessera tesseraVuota = null;
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++) {
				Tessera tesseraCampo = campo[i][j];
				
				if(tesseraCampo.getValore() == Integer.parseInt(numeroTessera))
					tesseraScelta = tesseraCampo;
				if(tesseraCampo.getValore() == 16)
					tesseraVuota = tesseraCampo;	
			}
		
		if((tesseraScelta.getPosX() - tesseraVuota.getPosX() == 1 | 
			tesseraScelta.getPosX() - tesseraVuota.getPosX() == -1) ^
				(tesseraScelta.getPosY() - tesseraVuota.getPosY() == 1 | 
				tesseraScelta.getPosY() - tesseraVuota.getPosY() == -1)) {
			
			int sceltaX = tesseraScelta.getPosX();
			int sceltaY = tesseraScelta.getPosY();
			
			int vuotaX = tesseraVuota.getPosX();
			int vuotaY = tesseraVuota.getPosY();
			
			tesseraScelta.setPosX(vuotaX);
			tesseraScelta.setPosY(vuotaY);
			campo[vuotaY][vuotaX] = tesseraScelta;
			
			tesseraVuota.setPosX(sceltaX);
			tesseraVuota.setPosY(sceltaY);
			campo[sceltaY][sceltaX] = tesseraVuota;
			
			this.disegna();
			
		} 
		else {
			System.out.println("Hai scelto una tessera bagliata");
			this.disegna();
		}
		
		
	}
	
	public boolean vinto() {
		int counter = 1;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++) {
				if (campo[i][j].getValore() == counter)
					counter++;
			}
		if (counter == 16)
			return true;
		
		return false;
	}
	
	
	public static void main(String[] args) {
		
		GiocoDelQuindici main = new GiocoDelQuindici();
		Scanner scan = new Scanner(System.in);
		
		main.disegna();
		
		boolean game = true;
		
		// Inizio del game loop
		while(game) {
			
			System.out.print("Chi muovi? ('basta' per terminare): ");
			String risposta = scan.nextLine();
			
			if(risposta.equals("basta")) {
				game = false;
				break;				
			}
			
			main.scorri(risposta);
			if(main.vinto()) {
				System.out.println("bravo hai vinto");
				game = false;
			}
		}
		
		// Fine del game loop
		System.out.println("Gioco finito");

	}
}
