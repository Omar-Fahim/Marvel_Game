package engine;

import java.awt.event.KeyEvent;

public interface GameListener {
	public void updateBoard();
	public void over(Player P);
}
