/*Code challenge: Joe/Peter - robots do a linear battle...
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class RobotBattle {

	public static void main(String[] args) {
		RobotBattle ga = new RobotBattle();

		ga.runMenu();	
	}

	private void runMenu() {
		boolean menuRunning = true;
		do{

			int menuChoice = 0;
			Scanner scan = new Scanner(System.in);
			System.out.println("");
			System.out.println("*** Battle Robots ***");
			System.out.println("1) Play Last Man Standing" + "\n2) Play 1 V 1 Deathmatch" + "\n3) Quit");

			try {
				menuChoice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Pleas pick a valid choice");
			}
			//main menu


			switch(menuChoice){
			case 1 : runLastManStanding();break;
			case 2 : run1v1();break;
			case 3 : menuRunning=false;break;
			}
		}while(menuRunning);

	}

	private void runLastManStanding() {
		Weapons armoury = new Weapons();
		int humanWinNum = 0;
		int humanCasualties = 0;
		int robotWinNum = 0;
		int robotsCrushed = 0;
		int draws = 0;
		int totalHumanLife = 0;
		int totalRobotPower = 0;
		int fightCounter = 0;

		//create boolean robot is dead array
		boolean[] robotIsDead = new boolean[500];
		//create boolean human is dead array
		boolean[] humanIsDead = new boolean[500];

		System.out.println("hello world");

		//create 100 human life array
		int[] humanLife = new int[500];
		Random humanLifeRand = new Random();
		//randomly insert life between 70 - 100
		for(int i = 0;i <= 499 ;++i){
			humanLife[i] = humanLifeRand.nextInt(100);
			int addLife = humanLife[i];
			totalHumanLife += addLife;
		}

		//create human name array
		int humanConcatNameCounter = 1;
		String[] humanNames = new String[500];
		for(int i = 0; i <=499;++i){
			humanNames[i] = "Human#" + humanConcatNameCounter;
			humanConcatNameCounter++;	
		}

		//create 100 robot life array
		int[] robotLife = new int[500];
		Random robotLifeRand = new Random();
		//randomly insert life between 70 - 100
		for(int i = 0;i <= 499;++i){
			robotLife[i] = robotLifeRand.nextInt(100);
			int addLife = robotLife[i];
			totalRobotPower += addLife;
		}

		//create robot name array
		int robotConcatNameCounter = 1;
		String robotType = "TypeA";
		Random rand2 = new Random();

		String[] robotNames = new String[500];
		for(int i = 0; i <=499;++i){

			//randomly create TypeA & TypeB robots
			int tmp = rand2.nextInt(100);
			if(tmp%2 ==0){
				robotNames[i] = "TypeB" + robotConcatNameCounter;
				++robotConcatNameCounter;
			}else{
				robotNames[i] = "TypeA" + robotConcatNameCounter;
				++robotConcatNameCounter;
			}	
		}
		
		//create human and robot veteran count array
		int[] humanVets = new int[500];
		int[] robotVets = new int[500];


		//~~~~~~~~~~~~~~~do battle loop~~~~~~~~~~~~~~~~~~~
		for(int i = 0;humanCasualties+draws<500 && robotsCrushed+draws<500;i++){

			Random battleRand = new Random();

			int battleRandHuman = battleRand.nextInt(500);
			int battleRandRobot = battleRand.nextInt(500);

			//check if human is dead
			if(humanIsDead[battleRandHuman] == true){
				continue;
			}
			//check if robot is dead
			if(robotIsDead[battleRandRobot] == true){
				continue;
			}
			
			
			//[Humans] randomly hand out weapons and armour to humans
			boolean HumanHasWeapon;
			int humanWeaponType = 0;
			int humanArmourType = 0;
			Random HumanHasWeaponRand = new Random();
			int tmp = HumanHasWeaponRand.nextInt(100);
			if(tmp%2 ==0){
				HumanHasWeapon=true;
			}
			if(HumanHasWeapon=true){
				Random humanWeaponRand = new Random();
				Random humanArmourRand = new Random();
					
				//give the human a random weapon and armour from class (simply adds to their life/power)
				humanWeaponType = humanWeaponRand.nextInt(4);
				humanArmourType = humanArmourRand.nextInt(4);
				humanLife[battleRandHuman]+=humanWeaponType;
				humanLife[battleRandHuman]+=humanArmourType;	
			}
			
			
			//[Robots] randomly hand out weapons and armour to robots
			boolean robotHasWeapon;
			int robotWeaponType = 0;
			int robotArmourType = 0;
			Random robotHasWeaponRand = new Random();
			int tmp1 = robotHasWeaponRand.nextInt(100);
			if(tmp1%2 ==0){
				robotHasWeapon=true;
			}
			if(robotHasWeapon=true){
				Random robotWeaponRand = new Random();
				Random robotArmourRand = new Random();
				
				//give the robot a random weapon and armour from class (simply adds to their life/power)
				robotWeaponType = robotWeaponRand.nextInt(3);
				robotArmourType = robotArmourRand.nextInt(4);
				robotLife[battleRandRobot]+=robotWeaponType;
				robotLife[battleRandRobot]+=robotArmourType;		
			}


			//check for draw & kick both
			if(humanLife[battleRandHuman] == robotLife[battleRandRobot]){
				System.out.println("Draw, both human and robot lose");
				draws++;

				//if DRAW - kill both human and robot in the array
				humanIsDead[battleRandHuman] = true;
				robotIsDead[battleRandRobot] = true;
				fightCounter++;
			}
			
			//begin the battle status printout
			System.out.println("                       ~~~~~~~~~~~~");
			System.out.println("                       B A T T L E");
			System.out.println("                       ~~~~~~~~~~~~");
			System.out.println(humanNames[battleRandHuman] + " @ lvl. " 
					+ humanLife[battleRandHuman] 
							+ " --V-- " 
							+ robotNames[battleRandRobot] + " @ lvl. "
							+ robotLife[battleRandRobot]
									+ " | This is fight number: "+ fightCounter);
			
			//show weapons and armour info of the battle
			if(HumanHasWeapon=true){
			System.out.println(humanNames[battleRandHuman] + " was armed with a " + armoury.guns[humanWeaponType] 
														   + " and had armour of " + armoury.armour[humanArmourType] );
			System.out.println(robotNames[battleRandRobot] + " was armed with a " + armoury.lasers[robotWeaponType] 
														   + " and had a shield level of " + armoury.shields[robotArmourType]);
			}
			
			
			//human win
			if(humanLife[battleRandHuman] > robotLife[battleRandRobot]){
				System.out.println("Human Wins - he had a total Battle Killcount of: " + humanVets[battleRandHuman] + " kills!");
				System.out.println("");
				humanWinNum++;
				robotsCrushed++;
				robotIsDead[battleRandRobot] = true;//kill robot in isDead array
				humanLife[battleRandHuman] -= 2;//remove some life after battle
				fightCounter++;
				humanVets[battleRandHuman]++;
			}else{//robot win
				System.out.println("Robot Wins - he had a total Battle Killcount of: " + robotVets[battleRandRobot] + " kills!");
				System.out.println("");
				robotWinNum++;
				humanCasualties++;
				humanIsDead[battleRandHuman] = true;//kill human in the isDead array
				robotLife[battleRandRobot] -= 2;//remove some power after battle
				fightCounter++;
				robotVets[battleRandRobot]++;
			}


		}//end battle loop

		//print battle results
		
		int humansLeftStanding = 500 - (humanCasualties + draws);
		int robotsLeftStanding = 500 - (robotsCrushed + draws);
		
		System.out.println("                * * * BATTLE RESULTS * * *");
		System.out.println("");
		if(humanWinNum>robotWinNum){
			System.out.println("                 ~~~~~ HUMANS WIN ~~~~~");
		}else{
			System.out.println("                 ~~~~~ ROBOTS WIN ~~~~~");
			System.out.println("                    after " + fightCounter + " Rounds:");
		}
		
		System.out.println("___________________________________________________________");
		System.out.println("| HUMANS | DEATHS | DRAWS | TOTAL POWER % | LEFT STANDING  |");
		System.out.println("-----------------------------------------------------------");
		System.out.println("|   500  |   " + humanCasualties + "  |  " + draws + "   |    " + totalHumanLife + "      |        " + humansLeftStanding +"       |");
		
		System.out.println("___________________________________________________________");
		System.out.println("| ROBOTS | DEATHS | DRAWS | TOTAL POWER % | LEFT STANDING  |");
		System.out.println("-----------------------------------------------------------");
		System.out.println("|   500  |   " + robotsCrushed + "  |  " + draws + "   |    " + totalRobotPower + "      |        " + robotsLeftStanding +"       |");
		System.out.println("");
		System.out.println("Medals of Honour:");
		//robot medals
		System.out.println("[* * * ROBOTS * * *]");
		for(int i = 0;i <=499;++i){
			if(robotVets[i] >= 10 && robotVets[i]<=14){
				System.out.println(robotNames[i] + " : " + robotVets[i] + " kills");
			}else{
				if(robotVets[i] >= 15){
					System.out.println("Noteworthy bot: " + robotNames[i] + " killed " + robotVets[i] + " humans...what a bot!");
				}
			}
		}
		
		//human medals
		System.out.println("");
		System.out.println("[* * * HUMANS * * *]");
		for(int i = 0;i <=499;++i){
			if(humanVets[i] >= 10 && humanVets[i]<=14){
				System.out.println(humanNames[i] + " : " + humanVets[i] + " kills");
			}else{
				if(humanVets[i] >= 15){
					System.out.println("Noteworthy fighter: " + humanNames[i] + " killed " + humanVets[i] + " bots...what a guy!");
				}
			}
		}
		
	}

	private void run1v1(){

		int humanWinNum = 0;
		int humanCasualties = 0;
		int robotWinNum = 0;
		int robotsCrushed = 0;
		int draws = 0;
		int totalHumanLife = 0;
		int totalRobotPower = 0;

		System.out.println("hello world");

		//create 100 human life array
		int[] humanLife = new int[500];
		Random humanLifeRand = new Random();
		//randomly insert life between 70 - 100
		for(int i = 0;i <= 499 ;++i){
			humanLife[i] = humanLifeRand.nextInt(100);
			int addLife = humanLife[i];
			totalHumanLife += addLife;
		}

		//create human name array
		int humanConcatNameCounter = 1;
		String[] humanNames = new String[500];
		for(int i = 0; i <=499;++i){
			humanNames[i] = "Human#" + humanConcatNameCounter;
			humanConcatNameCounter++;	
		}

		//##########

		//create 100 robot life array
		int[] robotLife = new int[500];
		Random robotLifeRand = new Random();
		//randomly insert life between 70 - 100
		for(int i = 0;i <= 499;++i){
			robotLife[i] = robotLifeRand.nextInt(100);
			int addLife = robotLife[i];
			totalRobotPower += addLife;
		}

		//create robot name array
		int robotConcatNameCounter = 1;
		String robotType = "TypeA";
		Random rand2 = new Random();

		String[] robotNames = new String[500];
		for(int i = 0; i <=499;++i){
			int tmp = rand2.nextInt(100);
			if(tmp%2 ==0){
				robotNames[i] = "TypeB" + robotConcatNameCounter;
				++robotConcatNameCounter;
			}else{
				robotNames[i] = "TypeA" + robotConcatNameCounter;
				++robotConcatNameCounter;
			}	
		}


		//do battle loop
		for(int i = 0;i <=499;i++){
			Random battleRand = new Random();
			int battleRand1 = battleRand.nextInt(500);
			int battleRand2 = battleRand.nextInt(500);
			System.out.println("~~~~~~~~~~~~");
			System.out.println("B A T T L E");
			System.out.println("~~~~~~~~~~~~");
			System.out.println("*** " + humanNames[battleRand1] + " -- V -- " + robotNames[battleRand2] + " ***");
			if(humanLife.equals(robotLife)){
				System.out.println("Draw");
				draws++;
			}

			if(humanLife[i] > robotLife[i]){
				System.out.println("Human Wins");
				System.out.println("");
				humanWinNum++;
				robotsCrushed++;
			}else{
				System.out.println("Robot Wins");
				System.out.println("");
				robotWinNum++;
				humanCasualties++;
			}

		}//end battle loop


		System.out.println("*** BATTLE RESULTS ***");
		if(humanWinNum>robotWinNum){
			System.out.println("Humans Win !!!");
		}else{
			System.out.println("Robots Win !!!");
		}

		System.out.println("Human Casualties: " + humanCasualties);
		System.out.println("Total Human Power: " +totalHumanLife);
		System.out.println("Robots Crushed: " + robotsCrushed);
		System.out.println("Total Robot Power: " +totalRobotPower);
		System.out.println("Draws: " + draws);

	}
	

}

