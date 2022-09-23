package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	/*@Override
	public String toString() {
		return "HealingAbility [healAmount=" + healAmount + "]";
	}*/

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() + healAmount);

	}
	public String toString() {
		// TODO Auto-generated method stub
		 return  "Type: HealingAbility"+"\n"+"             Name: " + this.getName()+"\n"+ "             " + " ManaCost: " + this.getManaCost() +"\n"+ "             "+ " Base Cooldown: " + this.getBaseCooldown()+"\n"+ "             "+" Current Cooldown: "+ this.getCurrentCooldown() +"\n"+ "             " + " CastRange: "
				+ this.getCastRange() +"\n"+ "             "+ " CastArea: " + this.getCastArea()+"\n"+ "             " + " RequiredActionPoints: " + this.getRequiredActionPoints() +"\n"+ "             "+ " Healing Amount: "+this.getHealAmount();
	}

	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}*/
	

}
