
public class Weapons {
	
	//robot weapons use element number for life multiplier, they are randomly called/assigned to user
	String[] lasers = new String[3];{
		lasers[0] = "Photon Laser";
		lasers[1] = "Gravity Discharge Blaster";
		lasers[2] = "Blaster Cannon";
	}
	
	//human weapons use element number for life multiplier, they are randomly called/assigned to user
	String[] guns = new String[4];{
	guns[0] = "Pitch Fork";
	guns[1] = "Shotgun";
	guns[2] = "M16";
	guns[3] = "RPG";
	}
	
	//Armour (also uses element num as multiplier)
	//robot shields
	String[] shields = new String[4];{
		shields[0] = "0%";
		shields[1] = "20%";
		shields[2] = "30%";
		shields[3] = "45%";
	}
	
	//human armour
	String[] armour = new String[4];{
		armour[0] = "0%";
		armour[1] = "20%";
		armour[2] = "30%";
		armour[3] = "45%";
	}
	
	
}
