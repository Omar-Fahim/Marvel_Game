package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
	public String toString() {
		// TODO Auto-generated method stub
		 return "Type: DamagingAbility"+"\n"+ "             Name: " + this.getName() +"\n"+ "             "+ " ManaCost: " + this.getManaCost() +"\n"+ "             "+ " Base Cooldown: " + this.getBaseCooldown()+"\n"+ "             "+" Current Cooldown: "+ this.getCurrentCooldown() +"\n" + "             "+ " CastRange: "
				+ this.getCastRange() +"\n"+ "             "+ " CastArea: " + this.getCastArea()+"\n" + "             "+ " RequiredActionPoints: " + this.getRequiredActionPoints() +"\n"+ "             "+ " Damageing Amount: "+this.getDamageAmount();
	}

	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}*/
}
