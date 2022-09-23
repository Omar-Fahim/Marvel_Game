package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}
/*public String toString() {
		return "name=" + name + ", manaCost=" + manaCost + ", baseCooldown=" + baseCooldown + ", castRange="
				+ castRange + ", castArea=" + castArea + ", requiredActionPoints=" + requiredActionPoints + "]";
	}*/
		@Override
	public String toString() {
		// TODO Auto-generated method stub
		 return "Type: CrowdControlAbility"+"\n"+"             Name: " + this.getName() +"\n"+ "             "+ " ManaCost: " + this.getManaCost() +"\n"+ "             "+ " Base Cooldown: " + this.getBaseCooldown() +"\n"+ "             "+" Current Cooldown: "+ this.getCurrentCooldown() +"\n"+ "             "+ " CastRange: "
				+ this.getCastRange()+"\n" + "             "+ " CastArea: " + this.getCastArea() +"\n"+ "             "+ " RequiredActionPoints: " + this.getRequiredActionPoints() +"\n"+ "             "+ " Effect: "+this.getEffect().getName()+"\n"+"             "+" Duration: "+this.getEffect().getDuration() ;
	}
		public Effect getEffect() {
		return effect;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
	}


}
