package part01;

import java.util.Scanner;

public class Biometrics implements IBiometrics {

	@Override
	public boolean bioCheck() {
		boolean correct = true;
		do {
			correct = false;
			System.out.println("Are you allowed to progress?");
			String pass = input.next();
			if (pass.equals("y")) {
				correct = true;
				System.out.println("Please continue with your transaction \n");
				return correct;
			} else {
				System.out.println("Please try again \n");
				correct = false;
			}
		} while (!correct);
		return correct;
	}

	public boolean bioChooser(int i) {
		switch (i) {
		case 1:
			bio = new Password();
			break;
		case 2:
			bio = new FaceScanner();
			break;
		default:
			bio = new Biometrics();
			break;
		}
			return bio.bioCheck();
		}
	

	public static class Password extends Biometrics implements IBiometrics {
		public boolean bioCheck() {

			boolean correct = true;
			do {
				correct = false;
				System.out.println("Please enter the password");
				String pass = input.next();
				if (pass.equals("1234")) {
					correct = true;
					System.out.println("Password verified! \n");
					return correct;
				} else {
					System.out.println("Sorry incorrect password. Please try again \n");
					correct = false;
				}
			} while (!correct);
			return correct;
		}
	}

	public static class FaceScanner extends Biometrics implements IBiometrics {
		public boolean bioCheck() {

			boolean correct = true;
			do {
				correct = false;
				System.out.println("Please present your face to the scanner");
				String pass = input.next();
				if (pass.equals("y")) {
					correct = true;
					System.out.println("Face recognised! \n");
					return correct;
				} else {
					System.out.println("Sorry face not recognised \n");
					correct = false;
				}
			} while (!correct);
			return correct;
		}
	}

	private static Biometrics bio;
	private static Scanner input = new Scanner(System.in);
}
