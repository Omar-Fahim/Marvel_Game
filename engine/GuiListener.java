package engine;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.world.Direction;

public interface GuiListener {
	public void move(Direction d)throws NotEnoughResourcesException, UnallowedMovementException;
	public void attack(Direction d)throws NotEnoughResourcesException, ChampionDisarmedException, InvalidTargetException;
	public void castAbility(Ability a)throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	public void castAbility(Ability a, Direction d)throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	public void castAbility(Ability a, int x, int y)throws NotEnoughResourcesException, AbilityUseException,
	InvalidTargetException, CloneNotSupportedException;
	public void useLeaderAbility()throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException ;
	public void endTurn() ;
	public Player checkGameOver();
	

}
