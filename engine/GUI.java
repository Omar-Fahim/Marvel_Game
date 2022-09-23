package engine;
import java.awt.event.KeyEvent;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import exceptions.GameActionException;
import exceptions.*;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.effects.Effect;
import model.world.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
public class GUI extends JFrame implements ActionListener , GameListener {
	private Game Game;
	private JLabel f;
	private JLabel s;
	private JButton done;
	private JTextField m;
	private JTextField n;
	private GuiListener l;
	private JPanel dis;
	private JPanel btus;
	private JTextArea info;
	private JTextArea cname;
	private ArrayList<String> name ;
	private Player one ;
	private Player two;
	private JPanel north ;
	private JPanel east ;
	private JPanel south;
	private JPanel west ;
	private JPanel center;
	private JPanel[][] panelHolder = new JPanel[5][5];
	private boolean allow = true;
	private String curr = "";
	private ArrayList<String> abilityName  ;
	private Ability tmpABD = null;
	private Ability tmpABS = null;
	private int x = -1;
	private int y =-1;
	boolean canCast = true;
	private static Clip clip;
	
	//GraphicsDevice gg;
	public void setL(GuiListener l) {
		this.l = l;
		
	}
	public GuiListener getL() {
		return l;
	}
	public static void audio() {
        try {
            File file = new File("av.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.err.println("Put the music.wav file in the project !");
        }
    }
	public static void audio2() {
        try {
            File file = new File("vidwav.wav");
             clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.err.println("Put the music.wav file in the project !");
        }
    }

	public GUI () {
		this.audio2();
		/*try {
			Desktop.getDesktop().open(new File("vid.mp4"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.name = new ArrayList<String>();
		this.abilityName = new ArrayList<String>(); 
		this.setL(Game);
		setTitle("Marvel: Ultimate War");
		setVisible(true);
		//setLayout(null);
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setBounds(0, 0, 1540 , 830 );
	//	this.setBackground(Color.PINK);
		//ImageIcon coverI = new ImageIcon("m7.jpg");
		ImageIcon coverI = new ImageIcon("gg.gif");
		Image coverImage = coverI.getImage(); // transform it 
		Image coverImageResize = coverImage.getScaledInstance(1540,830,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
		ImageIcon coverF = new ImageIcon(coverImageResize);
		//JLabel i = new JLabel(coverI);
		JPanel i = new JPanel();
		i.setBackground(Color.BLACK);
		JLabel gm = new JLabel(coverI);
		i.add(gm);
		this.add(i,BorderLayout.CENTER);
		JPanel t = new JPanel();
		t.setLayout(new BorderLayout());
		this.add(t,BorderLayout.NORTH);
		
		JPanel o = new JPanel();
		o.setLayout(new FlowLayout());
		JPanel v = new JPanel();
		v.setLayout(new FlowLayout());
		f = new JLabel("First Player Name");
		f.setForeground(Color.ORANGE);
		f.setBounds((1540/3)+170, 5,100, 25);
		o.add(f);
		m = new JTextField();
		//m.setBounds((1540+120)/2, 5, 165, 25);
		m.setPreferredSize(new Dimension(150,25));
		o.add(m);
		o.setBackground(Color.BLACK);
		t.add(o,BorderLayout.NORTH);
		s = new JLabel("Second  Player Name");
		s.setForeground(Color.ORANGE);
		s.setBounds((1540/3)+170, 50,150, 25);
		v.add(s);
		n = new JTextField();
		n.setPreferredSize(new Dimension(150,25));
		//n.setBounds((1540+120)/2, 50, 165, 25);
		v.add(n);
		v.setBackground(Color.BLACK);
		t.add(v,BorderLayout.CENTER);
		
		JPanel l = new JPanel();
		l.setLayout(new FlowLayout());
		
	    done = new JButton("Done");
	    done.setBackground(Color.ORANGE);
		done.setBounds((1540/3)+250, 100, 100, 50);
		done.addActionListener(this);
		l.add(done);
		JButton Exit = new JButton("Exit");
		Exit.setBackground(Color.ORANGE);
		Exit.setBounds((1540/3)+360, 100, 100, 50);
		Exit.addActionListener(new CloseListener());
		l.add(Exit);
		l.setBackground(Color.BLACK);
		t.add(l,BorderLayout.SOUTH);
	//	t.setBackground(Color.BLUE);
		
		
		
		
		this.revalidate();
		this.repaint();
	}
	public static void main(String[]args) {
		new GUI();
		//attack
		//cast
		//move
	}
	/*public String turnDisplay() {
		String order ="Turn order: "+"\n";
		ArrayList<Comparable> x = new ArrayList<>();
		order+="\n"+ ((Champion)this.Game.getTurnOrder().peekMin()).getName()+ " <----- Current turn" + "\n";
		x.add(this.Game.getTurnOrder().remove());
		while(!this.Game.getTurnOrder().isEmpty()) {
//			if(this.Game.getTurnOrder().size() == 1)
//				order+= ((Champion)this.Game.getTurnOrder().peekMin()).getName();
//			else
				order+="\n"+ ((Champion)this.Game.getTurnOrder().peekMin()).getName()+"\n";
				x.add(this.Game.getTurnOrder().remove());
		}
		while(!x.isEmpty()) {
			this.Game.getTurnOrder().insert(x.remove(0));
		}
		return order;
		//System.out.println(order);
	}*/
	public String turnDisplay() {
		String order ="             "+"Turn order: "+"\n";
		ArrayList<Comparable> x = new ArrayList<>();
		order+="\n"+ "             "+((Champion)this.Game.getTurnOrder().peekMin()).getName()+ " <----- Current turn" + "\n";
		x.add(this.Game.getTurnOrder().remove());
		while(!this.Game.getTurnOrder().isEmpty()) {
//			if(this.Game.getTurnOrder().size() == 1)
//				order+= ((Champion)this.Game.getTurnOrder().peekMin()).getName();
//			else
				order+="\n"+ "             "+ ((Champion)this.Game.getTurnOrder().peekMin()).getName()+"\n";
				x.add(this.Game.getTurnOrder().remove());
		}
		while(!x.isEmpty()) {
			this.Game.getTurnOrder().insert(x.remove(0));
		}
		return order;
		//System.out.println(order);
	}
	public void actionPerformed(ActionEvent e) {
		
		try {
		JButton bt = (JButton) e.getSource();
		if(e.getActionCommand() == "Done") {
			if(this.m.getText().isEmpty()  || this.n.getText().isEmpty()) {
				
				JFrame frame = new JFrame("Error");
				JOptionPane.showMessageDialog(frame, "The field is empty","Error", JOptionPane.ERROR_MESSAGE);
				return;
				
			}
			clip.stop();
			this.audio();

			 one = new Player(this.m.getText());
			 two = new Player(this.n.getText());
			try {
				this.Game = new Game(one,two);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setContentPane(new Container());
		
	    Game.setL(this);
		this.displayAvilableChamps();
			
			
			
		}
		else if(  this.name.contains( bt.getActionCommand() ) && (this.Game.getFirstPlayer().getTeam().size() != 3 || this.Game.getSecondPlayer().getTeam().size() != 3)  ) {
				int in = this.name.indexOf( bt.getActionCommand());
				Champion tmp = this.Game.getAvailableChampions().get(in);
				this.setContentPane(new Container());
				this.setLayout(new BorderLayout());
				//this.revalidate();
				//this.repaint();
				cname = new JTextArea();
				this.cname.setText(tmp.getName());
				this.cname.setFont(this.cname.getFont().deriveFont(30f));
				this.cname.setForeground(Color.BLUE);
				cname.setEditable(false);
				cname.setBackground(Color.BLACK);

				this.add(cname,BorderLayout.NORTH);
				info = new JTextArea();
			    info.setPreferredSize(new Dimension(200, 830));
			    info.setEditable(false);
			    this.info.setText(tmp.toString());
				this.info.setFont(info.getFont().deriveFont(17f)); 
				this.info.setForeground(Color.ORANGE);
				info.setBackground(Color.BLACK);
			    this.add(info,BorderLayout.CENTER);
			    btus = new JPanel();this.add(btus,BorderLayout.SOUTH);
			    btus.setLayout(new GridLayout(1,3));
			    JButton b = new JButton("Back");
			    b.setBackground(Color.ORANGE);
			    b.addActionListener(this);
			    if(this.Game.getFirstPlayer().getTeam().size() != 3 ||  this.Game.getSecondPlayer().getTeam().size() != 3) {
			    btus.add(b);}
			    
			    JButton add1 = new JButton("Add to 1st player");
			    add1.addActionListener(this);
			    add1.setBackground(Color.ORANGE);
			    JButton add2 = new JButton("Add to 2nd player");
			   add2.addActionListener(this);
			   add2.setBackground(Color.ORANGE);
			   if(this.Game.getFirstPlayer().getTeam().size() != 3) {
				   btus.add(add1);
			   }
			   if( this.Game.getSecondPlayer().getTeam().size() != 3) {
				   btus.add(add2);
			   }
			    
			    this.revalidate();
			    this.repaint();
				
				
				
				
				
				
				
			}
			else if( bt.getActionCommand().equals("Back") ) {
					this.displayAvilableChamps();
				}
			else if(bt.getActionCommand().equals("Add to 1st player")){
				if(this.Game.getFirstPlayer().getTeam().size()<3) {
					Champion e1 = null ;
					for(Champion c : this.Game.getAvailableChampions()) {
						if(c.getName().equals(this.cname.getText()))
							e1 = c;
						}
					this.Game.getFirstPlayer().getTeam().add(e1);
					this.displayAvilableChamps();
					}
			      }
			else if(bt.getActionCommand().equals("Add to 2nd player")){
				if(this.Game.getSecondPlayer().getTeam().size()<3) {
					Champion e1 = null ;
					for(Champion c : this.Game.getAvailableChampions()) {
						if(c.getName().equals(this.cname.getText()))
										e1 = c;
								}
					this.Game.getSecondPlayer().getTeam().add(e1);
					this.displayAvilableChamps();
							}
					      }
			else if(  this.name.contains( bt.getActionCommand() ) && this.Game.getFirstPlayer().getTeam().size() == 3 && this.Game.getSecondPlayer().getTeam().size() == 3  ) {
				for(Champion c : this.Game.getFirstPlayer().getTeam()) {
					if(c.getName().equals( bt.getActionCommand()))
						this.Game.getFirstPlayer().setLeader(c);
								}
				for(Champion c : this.Game.getSecondPlayer().getTeam()) {
					if(c.getName().equals( bt.getActionCommand()))
						this.Game.getSecondPlayer().setLeader(c);
								}
				if(this.Game.getFirstPlayer().getLeader() != null && this.Game.getSecondPlayer().getLeader() != null)
					this.mainGame();
								
							}
			else {
				if(   (bt.getActionCommand().charAt(0) == '1' || bt.getActionCommand().charAt(0) == '2')  &&this.name.contains(bt.getActionCommand().substring(1))&&this.Game.getCurrentChampion().getName().equals(bt.getActionCommand().substring(1))&&this.allow)  {
									this.allow=false;
									this.curr = bt.getActionCommand().substring(1);
									this.disAB();
								}
				else if(bt.getActionCommand().equals("attack")) {
					this.disATTACK();
					}
				else if(bt.getActionCommand().equals("Attack UP")) {
					this.Game.attack(Direction.UP);
					this.updateBoard();
					this.revalidate();
					this.repaint();
				}
				else if(bt.getActionCommand().equals("Attack DOWN")) {
						this.Game.attack(Direction.DOWN);
						this.updateBoard();
						this.revalidate();
						this.repaint();
					}
				else if(bt.getActionCommand().equals("Attack RIGHT")) {
					this.Game.attack(Direction.RIGHT);
					this.updateBoard();
					this.revalidate();
					this.repaint();
				}
				else if(bt.getActionCommand().equals("Attack LEFT")) {
						this.Game.attack(Direction.LEFT);
						this.updateBoard();
						this.revalidate();
						this.repaint();
					}
				else if(bt.getActionCommand().equals("endTurn")) {
					this.Game.endTurn();
					/*useLeaderAbility*/
					this.allow=true;
					
				}
				else if(bt.getActionCommand().equals("useLeaderAbility")) {
						this.Game.useLeaderAbility();
						/*useLeaderAbility*/
					}
					else if(bt.getActionCommand().equals("move")) {
						
							this.disMOVE();
							
						}
					else if(bt.getActionCommand().equals("Move UP")) {
						this.Game.move(Direction.UP);
						this.revalidate();
						this.repaint();
						}
					else if(bt.getActionCommand().equals("Move DOWN")) {
						this.Game.move(Direction.DOWN);
						this.revalidate();
						this.repaint();
					}
					else if(bt.getActionCommand().equals("Move RIGHT")) {
							this.Game.move(Direction.RIGHT);
							this.revalidate();
							this.repaint();
						}
						else if(bt.getActionCommand().equals("Move LEFT")) {
								this.Game.move(Direction.LEFT);
								this.revalidate();
								this.repaint();
							}
						else if(bt.getActionCommand().equals("castAbility")) {
									this.disCAST();
								}
							else if(this.abilityName.contains(bt.getActionCommand()) ) {
								int jk = this.abilityName.indexOf(bt.getActionCommand());
								Ability lol = this.Game.getAvailableAbilities().get(jk);
								AreaOfEffect tp = lol.getCastArea();
								if(tp == AreaOfEffect.SELFTARGET || tp == AreaOfEffect.SURROUND || tp == AreaOfEffect.TEAMTARGET) {
									//int y = this.Game.getCurrentChampion().getAbilities().indexOf(lol);
									//int ans = this.Game.getCurrentChampion().getAbilities().size();
									for(int i = 0;i<3;i++) {
										if(this.Game.getCurrentChampion().getAbilities().get(i).getName().equals(bt.getActionCommand()))
											try {
												this.Game.castAbility(this.Game.getCurrentChampion().getAbilities().get(i));
											} catch (CloneNotSupportedException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										
										
									}
								}
							
								
								else if(tp == AreaOfEffect.DIRECTIONAL) {
									for(int i = 0;i<3;i++) {
										if(this.Game.getCurrentChampion().getAbilities().get(i).getName().equals(bt.getActionCommand()))
										this.tmpABD =	this.Game.getCurrentChampion().getAbilities().get(i);
										
									}
									this.disDIRECTIONAL();
								}
								else if(tp == AreaOfEffect.SINGLETARGET) {
										for(int i = 0;i<3;i++) {
											if(this.Game.getCurrentChampion().getAbilities().get(i).getName().equals(bt.getActionCommand()))
											this.tmpABS =	this.Game.getCurrentChampion().getAbilities().get(i);
											
										}
										this.disSINGT();
										this.revalidate();
										this.repaint();
								}
							}
								else if(bt.getActionCommand().equals("Cast UP")) {
									try {
										this.Game.castAbility(tmpABD, Direction.UP);
									} catch (CloneNotSupportedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								
							else if(bt.getActionCommand().equals("Cast DOWN")) {
									try {
										this.Game.castAbility(tmpABD, Direction.DOWN);
									} catch (CloneNotSupportedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								else if(bt.getActionCommand().equals("Cast RIGHT")) {
										try {
											this.Game.castAbility(tmpABD, Direction.RIGHT);
										} catch (CloneNotSupportedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									}
								else if(bt.getActionCommand().equals("Cast LEFT")) {
									try {
										this.Game.castAbility(tmpABD, Direction.LEFT);
									} catch (CloneNotSupportedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
											
										}
				
									else if(bt.getActionCommand().charAt(0) == '!') {
												if(bt.getActionCommand().charAt(1) == 'x') {
													/*Character.getNumericValue(bt.getActionCommand().charAt(2))*/                                                             
													this.x = Character.getNumericValue(bt.getActionCommand().charAt(2));
												}
												if(bt.getActionCommand().charAt(1) == 'y') {
													this.y = Character.getNumericValue(bt.getActionCommand().charAt(2));
												}
												if(this.x != -1 && this.y != -1) {
													try {
														this.Game.castAbility(this.tmpABS, this.x, this.y);
														this.x=-1;this.y=-1;
													} catch (CloneNotSupportedException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												
												
												
												
											}
//									else if(bt.getActionCommand().equals("MET")) {
//										try {
//											Desktop.getDesktop().browse(new URI("https://www.guc.edu.eg//en/research/met/introduction/index.aspx"));
//										} catch (IOException e1) {
//											// TODO Auto-generated catch block
//											e1.printStackTrace();
//										} catch (URISyntaxException e1) {
//											// TODO Auto-generated catch block
//											e1.printStackTrace();
//										}
//									}
									else if(bt.getActionCommand().equals("Punch")) {
										for(int i = 0;i<this.Game.getCurrentChampion().getAbilities().size();i++) {
											if(this.Game.getCurrentChampion().getAbilities().get(i).getName().equals(bt.getActionCommand()))
											this.tmpABS =	this.Game.getCurrentChampion().getAbilities().get(i);
											
										}
										this.disSINGT();
										this.revalidate();
										this.repaint();
									}
				
				
			
}

}



										
								
											
																
																								/*try {
																									this.Game.castAbility(this.Game.getCurrentChampion().getAbilities().get(y));
																								} catch (CloneNotSupportedException e1) {
																									// TODO Auto-generated catch block
																									e1.printStackTrace();
																								}*/
																								//System.out.println("JK = " + jk +"\n"+"Lol = "+lol+"\n"+"TP = "+tp+"\n"+"ANS = "+bt.getActionCommand());
																							
																							
		
		
		
		catch (NotEnoughResourcesException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"NotEnoughResourcesException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		catch (LeaderAbilityAlreadyUsedException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"LeaderAbilityAlreadyUsedException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		catch (UnallowedMovementException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"UnallowedMovementException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		catch (AbilityUseException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"AbilityUseException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		catch (LeaderNotCurrentException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"LeaderNotCurrentException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		catch (InvalidTargetException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"InvalidTargetException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		catch (ChampionDisarmedException e1) {
			JFrame frame = new JFrame(e1.getClass().getSimpleName());
			JOptionPane.showMessageDialog(frame, e1.getMessage(),"ChampionDisarmedException", JOptionPane.ERROR_MESSAGE);
			this.x=-1;this.y=-1;
			this.updateBoard();
			return;
		}
		
		
		
	
	}
	
	public void disSINGT() {
		this.remove(south);
		this.revalidate();
		this.repaint();
		south = new JPanel();
		this.add(south,BorderLayout.SOUTH);
		south.setLayout(new GridLayout(3,6));
		//JLabel x = new JLabel("Choose X coordinates : ");
		//JLabel y = new JLabel("Choose Y coordinates : ");
		JPanel x = new JPanel();
		JPanel y = new JPanel();
		south.add(x);
		for(int i =0;i<5;i++) {
			JButton cs = new JButton("X : "+Integer.toString(i));
			cs.setActionCommand("!x"+Integer.toString(i));
			cs.addActionListener(this);
			south.add(cs);
		}
		south.add(y);
		for(int i =0;i<5;i++) {
			JButton cs = new JButton("Y : "+Integer.toString(i));
			cs.setActionCommand("!y"+Integer.toString(i));
			cs.addActionListener(this);
			south.add(cs);
		}
		for(int i =0;i<6;i++) {
			JPanel cs = new JPanel();
			south.add(cs);
		}
		this.revalidate();
		this.repaint();
		
	}

	public void disDIRECTIONAL() {
		this.remove(south);
		this.revalidate();
		this.repaint();
		south = new JPanel();
		this.add(south,BorderLayout.SOUTH);
		south.setLayout(new FlowLayout());
		JButton up = new JButton("Cast UP");
		up.setActionCommand("Cast UP");
		up.addActionListener(this);
		JButton down = new JButton("Cast DOWN");
		down.setActionCommand("Cast DOWN");
		down.addActionListener(this);
		JButton right = new JButton("Cast RIGHT");
		right.setActionCommand("Cast RIGHT");
		right.addActionListener(this);
		JButton left = new JButton("Cast LEFT");
		left.setActionCommand("Cast LEFT");
		left.addActionListener(this);
		south.add(up);south.add(down);south.add(right);south.add(left);
		this.revalidate();
		this.repaint();
		
	}

	public void disCAST() {
		this.remove(north);
		north = new JPanel();
		this.add(north,BorderLayout.NORTH);
		north.setLayout(new FlowLayout());
		for(Ability a : this.Game.getCurrentChampion().getAbilities()) {
			JButton ability = new JButton(a.getName());
			ability.setActionCommand(a.getName());
			ability.addActionListener(this);
			north.add(ability);
		}
		this.revalidate();
		this.repaint();
		
	}

	public void disMOVE() {
		this.remove(south);
		south = new JPanel();
		this.add(south,BorderLayout.SOUTH);
		this.remove(north);
		north = new JPanel();
		this.add(north,BorderLayout.NORTH);
		north.setLayout(new FlowLayout());
		JButton up = new JButton("Move UP");
		up.setActionCommand("Move UP");
		up.addActionListener(this);
		JButton down = new JButton("Move DOWN");
		down.setActionCommand("Move DOWN");
		down.addActionListener(this);
		JButton right = new JButton("Move RIGHT");
		right.setActionCommand("Move RIGHT");
		right.addActionListener(this);
		JButton left = new JButton("Move LEFT");
		left.setActionCommand("Move LEFT");
		left.addActionListener(this);
		north.add(up);north.add(down);north.add(right);north.add(left);
		this.revalidate();
		this.repaint();
		
	}

	public void disATTACK() {
		this.remove(south);
		south = new JPanel();
		this.add(south,BorderLayout.SOUTH);
		this.remove(north);
		north = new JPanel();
		this.add(north,BorderLayout.NORTH);
		north.setLayout(new FlowLayout());
		JButton up = new JButton("Attack UP");
		up.setActionCommand("Attack UP");
		up.addActionListener(this);
		JButton down = new JButton("Attack DOWN");
		down.setActionCommand("Attack DOWN");
		down.addActionListener(this);
		JButton right = new JButton("Attack RIGHT");
		right.setActionCommand("Attack RIGHT");
		right.addActionListener(this);
		JButton left = new JButton("Attack LEFT");
		left.setActionCommand("Attack LEFT");
		left.addActionListener(this);
		north.add(up);north.add(down);north.add(right);north.add(left);
		this.revalidate();
		this.repaint();
		
		
	}

	public void disAB() {
		east.setLayout(new GridLayout(5,1));
		JButton attack = new JButton("attack");
		attack.addActionListener(this);
		east.add(attack);
		JButton castAbility = new JButton("castAbility");
		castAbility.addActionListener(this);
		east.add(castAbility);
		JButton move = new JButton("move");
		move.addActionListener(this);
		east.add(move);
		JButton useLeaderAbility = new JButton("useLeaderAbility");
		useLeaderAbility.addActionListener(this);
		east.add(useLeaderAbility);
		JButton endTurn = new JButton("endTurn");
		endTurn.addActionListener(this);
		east.add(endTurn);
		this.revalidate();
		this.repaint();
		
	}

	public void updateBoard() {
		if(this.Game.checkGameOver() != null) {
			this.over(this.Game.checkGameOver());
			return;
		}
		this.setContentPane(new Container() );
		this.setLayout(new BorderLayout());
		this.revalidate();
		this.repaint();
		north = new JPanel();
		 east = new JPanel();
		 south = new JPanel();
		 west = new JPanel();
		 center = new JPanel();
		center.setLayout(new GridLayout(5,5));
		center.setVisible(true);
		//center.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		this.add(north,BorderLayout.NORTH);
		this.add(east,BorderLayout.EAST);
		this.add(west,BorderLayout.WEST);
		this.add(south,BorderLayout.SOUTH);
		this.add(center,BorderLayout.CENTER);
		
		
		//int i = 5;
		//int j = 5;
		 //panelHolder = new JPanel[i][j];    

		for(int m = 0; m < 5; m++) {
		   for(int n = 0; n < 5; n++) {
		      panelHolder[4-m][n] = new JPanel();
		      panelHolder[4-m][n].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		      
		      center.add(panelHolder[4-m][n]);
		   }
		}
		//north.setLayout(new FlowLayout());
		//JLabel labe3 = new JLabel("Turn Order : ");
		//north.add(labe3);
		
		//JLabel label = new JLabel("First Player Name : "+ Game.getFirstPlayer().getName()+" , ");
	    // JLabel label2 = new JLabel("Second Player Name : "+Game.getSecondPlayer().getName());
		//north.add(label);
		//north.add(label2);
		//this.updateBoard();
		this.revalidate();
		this.repaint();
		
		
		
		
		Object[][] board = this.Game.getBoard();
		for(int i = 0;i<5;i++) {
			for(int j =0;j<5;j++) {
				if(board[i][j] instanceof Cover) {
					panelHolder[i][j].setLayout(new BorderLayout());
					
					Cover tmp = (Cover) board[i][j];
					ImageIcon coverI = new ImageIcon("2.jpg");
					Image coverImage = coverI.getImage(); // transform it 
					Image coverImageResize = coverImage.getScaledInstance(240,140,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
					ImageIcon coverF = new ImageIcon(coverImageResize);
					JLabel x = new JLabel( "Cover Hp : " +Integer.toString(tmp.getCurrentHP()) );
					
					JLabel y = new JLabel(coverF);
					
					x.setFont(new Font("Verdana", Font.BOLD, 12));
                    panelHolder[i][j].add(y,BorderLayout.CENTER);
					panelHolder[i][j].add(x,BorderLayout.NORTH);
					panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
					
				}
				if(board[i][j] instanceof Champion) {
					Champion tmp = (Champion)board[i][j];/*Dr Strange*//*Electro*//*Ghost Rider*/
					if(!tmp.getName().equals("Captain America")
							&&!tmp.getName().equals("Deadpool")
							&&!tmp.getName().equals("Dr Strange")
							&&!tmp.getName().equals("Electro")
							&&!tmp.getName().equals("Ghost Rider")
							&&!tmp.getName().equals("Hela")
							&&!tmp.getName().equals("Hulk")
							&&!tmp.getName().equals("Iceman")
							&&!tmp.getName().equals("Ironman")
							&&!tmp.getName().equals("Loki")
							&&!tmp.getName().equals("Quicksilver")
							&&!tmp.getName().equals("Spiderman")
							&&!tmp.getName().equals("Thor")
							&&!tmp.getName().equals("Venom")) {
					
					
						String p = "";
						String al = "";
						if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
							p = "P1 : ";
							al = "1"+tmp.getName();
						}
						else {
							p="P2 : ";
							al = "2"+tmp.getName();
						}
						/*if(p.equals("P2 : "))
							panelHolder[i][j].setBackground(Color.BLUE);
							if(p.equals("P1 : "))
								panelHolder[i][j].setBackground(Color.CYAN);*/
						panelHolder[i][j].setLayout(new GridLayout(1,0));

						panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
						ImageIcon water = new ImageIcon("yjj.jpg");
			    		
			    		Image coverImage = water.getImage(); // transform it 
						Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
						ImageIcon coverF = new ImageIcon(coverImageResize);
						JButton bc = new JButton(coverF);
						bc.setActionCommand(al);
						bc.setBackground(Color.LIGHT_GRAY);
						bc.addActionListener(this);
						panelHolder[i][j].add(bc);
					
					}
					else {
						
						if(tmp.getName().equals("Captain America")){
						
						String p = "";
						String al = "";
						if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
							p = "P1 : ";
							al = "1"+tmp.getName();
						}
						else {
							p="P2 : ";
							al = "2"+tmp.getName();
						}
						/*if(p.equals("P2 : "))
							panelHolder[i][j].setBackground(Color.BLUE);
							if(p.equals("P1 : "))
								panelHolder[i][j].setBackground(Color.CYAN);*/
						panelHolder[i][j].setLayout(new GridLayout(1,0));

						panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
						ImageIcon water = new ImageIcon("cad.png");
			    		
			    		Image coverImage = water.getImage(); // transform it 
						Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
						ImageIcon coverF = new ImageIcon(coverImageResize);
						JButton bc = new JButton(coverF);
						bc.setBackground(Color.LIGHT_GRAY);
						bc.setActionCommand(al);
						bc.addActionListener(this);
						panelHolder[i][j].add(bc);
						
					}
						else {
							if(tmp.getName().equals("Deadpool")) {
								String p = "";
								String al = "";
								if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
									p = "P1 : ";
									al = "1"+tmp.getName();
								}
								else {
									p="P2 : ";
									al = "2"+tmp.getName();
								}
								/*if(p.equals("P2 : "))
									panelHolder[i][j].setBackground(Color.BLUE);
									if(p.equals("P1 : "))
										panelHolder[i][j].setBackground(Color.CYAN);*/
								panelHolder[i][j].setLayout(new GridLayout(1,0));

								panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
								ImageIcon water = new ImageIcon("dead.jpg");
					    		
					    		Image coverImage = water.getImage(); // transform it 
								Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
								ImageIcon coverF = new ImageIcon(coverImageResize);
								JButton bc = new JButton(coverF);
								bc.setActionCommand(al);
								bc.setBackground(Color.LIGHT_GRAY);
								bc.addActionListener(this);
								panelHolder[i][j].add(bc);
							}
							else {
								if(tmp.getName().equals("Dr Strange")) {
									String p = "";
									String al = "";
									if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
										p = "P1 : ";
										al = "1"+tmp.getName();
									}
									else {
										p="P2 : ";
										al = "2"+tmp.getName();
									}
									/*if(p.equals("P2 : "))
										panelHolder[i][j].setBackground(Color.BLUE);
										if(p.equals("P1 : "))
											panelHolder[i][j].setBackground(Color.CYAN);*/
									panelHolder[i][j].setLayout(new GridLayout(1,0));

									panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
									ImageIcon water = new ImageIcon("strange.PNG");
						    		
						    		Image coverImage = water.getImage(); // transform it 
									Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
									ImageIcon coverF = new ImageIcon(coverImageResize);
									JButton bc = new JButton(coverF);
									bc.setActionCommand(al);
									bc.setBackground(Color.LIGHT_GRAY);
									bc.addActionListener(this);
									panelHolder[i][j].add(bc);
								}
								else {
									if(tmp.getName().equals("Electro")) {
										String p = "";
										String al = "";
										if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
											p = "P1 : ";
											al = "1"+tmp.getName();
										}
										else {
											p="P2 : ";
											al = "2"+tmp.getName();
										}
										/*if(p.equals("P2 : "))
											panelHolder[i][j].setBackground(Color.BLUE);
											if(p.equals("P1 : "))
												panelHolder[i][j].setBackground(Color.CYAN);*/
										panelHolder[i][j].setLayout(new GridLayout(1,0));

										panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
										ImageIcon water = new ImageIcon("ele.jpg");
							    		
							    		Image coverImage = water.getImage(); // transform it 
										Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
										ImageIcon coverF = new ImageIcon(coverImageResize);
										JButton bc = new JButton(coverF);
										bc.setActionCommand(al);
										bc.setBackground(Color.LIGHT_GRAY);
										bc.addActionListener(this);
										panelHolder[i][j].add(bc);
									}
									else {
										if(tmp.getName().equals("Ghost Rider")) {
											String p = "";
											String al = "";
											if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
												p = "P1 : ";
												al = "1"+tmp.getName();
											}
											else {
												p="P2 : ";
												al = "2"+tmp.getName();
											}
											/*if(p.equals("P2 : "))
												panelHolder[i][j].setBackground(Color.BLUE);
												if(p.equals("P1 : "))
													panelHolder[i][j].setBackground(Color.CYAN);*/
											panelHolder[i][j].setLayout(new GridLayout(1,0));

											panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
											ImageIcon water = new ImageIcon("gr.PNG");
								    		
								    		Image coverImage = water.getImage(); // transform it 
											Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
											ImageIcon coverF = new ImageIcon(coverImageResize);
											JButton bc = new JButton(coverF);
											bc.setActionCommand(al);
											bc.setBackground(Color.LIGHT_GRAY);
											bc.addActionListener(this);
											panelHolder[i][j].add(bc);
										}
										else {
											if(tmp.getName().equals("Hela")) {
												String p = "";
												String al = "";
												if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
													p = "P1 : ";
													al = "1"+tmp.getName();
												}
												else {
													p="P2 : ";
													al = "2"+tmp.getName();
												}
												/*if(p.equals("P2 : "))
													panelHolder[i][j].setBackground(Color.BLUE);
													if(p.equals("P1 : "))
														panelHolder[i][j].setBackground(Color.CYAN);*/
												panelHolder[i][j].setLayout(new GridLayout(1,0));

												panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
												ImageIcon water = new ImageIcon("hela.jpg");
									    		
									    		Image coverImage = water.getImage(); // transform it 
												Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
												ImageIcon coverF = new ImageIcon(coverImageResize);
												JButton bc = new JButton(coverF);
												bc.setActionCommand(al);
												bc.setBackground(Color.LIGHT_GRAY);
												bc.addActionListener(this);
												panelHolder[i][j].add(bc);
											}
											else {
												if(tmp.getName().equals("Hulk")) {
													String p = "";
													String al = "";
													if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
														p = "P1 : ";
														al = "1"+tmp.getName();
													}
													else {
														p="P2 : ";
														al = "2"+tmp.getName();
													}
													/*if(p.equals("P2 : "))
														panelHolder[i][j].setBackground(Color.BLUE);
														if(p.equals("P1 : "))
															panelHolder[i][j].setBackground(Color.CYAN);*/
													panelHolder[i][j].setLayout(new GridLayout(1,0));

													panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
													ImageIcon water = new ImageIcon("hulkk.jpg");
										    		
										    		Image coverImage = water.getImage(); // transform it 
													Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
													ImageIcon coverF = new ImageIcon(coverImageResize);
													JButton bc = new JButton(coverF);
													bc.setActionCommand(al);
													bc.setBackground(Color.LIGHT_GRAY);
													bc.addActionListener(this);
													panelHolder[i][j].add(bc);
												}
												else {
													if(tmp.getName().equals("Iceman")) {
														String p = "";
														String al = "";
														if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
															p = "P1 : ";
															al = "1"+tmp.getName();
														}
														else {
															p="P2 : ";
															al = "2"+tmp.getName();
														}
														/*if(p.equals("P2 : "))
															panelHolder[i][j].setBackground(Color.BLUE);
															if(p.equals("P1 : "))
																panelHolder[i][j].setBackground(Color.CYAN);*/
														panelHolder[i][j].setLayout(new GridLayout(1,0));

														panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
														ImageIcon water = new ImageIcon("ice.jpg");
											    		
											    		Image coverImage = water.getImage(); // transform it 
														Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
														ImageIcon coverF = new ImageIcon(coverImageResize);
														JButton bc = new JButton(coverF);
														bc.setActionCommand(al);
														bc.setBackground(Color.LIGHT_GRAY);
														bc.addActionListener(this);
														panelHolder[i][j].add(bc);
													}
													else {
														
														if(tmp.getName().equals("Ironman")) {
															String p = "";
															String al = "";
															if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
																p = "P1 : ";
																al = "1"+tmp.getName();
															}
															else {
																p="P2 : ";
																al = "2"+tmp.getName();
															}
															/*if(p.equals("P2 : "))
																panelHolder[i][j].setBackground(Color.BLUE);
																if(p.equals("P1 : "))
																	panelHolder[i][j].setBackground(Color.CYAN);*/
															panelHolder[i][j].setLayout(new GridLayout(1,0));

															panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
															ImageIcon water = new ImageIcon("iron.jpg");
												    		
												    		Image coverImage = water.getImage(); // transform it 
															Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
															ImageIcon coverF = new ImageIcon(coverImageResize);
															JButton bc = new JButton(coverF);
															bc.setActionCommand(al);
															bc.setBackground(Color.LIGHT_GRAY);
															bc.addActionListener(this);
															panelHolder[i][j].add(bc);
														}
														else {
															if(tmp.getName().equals("Loki")) {
																String p = "";
																String al = "";
																if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
																	p = "P1 : ";
																	al = "1"+tmp.getName();
																}
																else {
																	p="P2 : ";
																	al = "2"+tmp.getName();
																}
																/*if(p.equals("P2 : "))
																	panelHolder[i][j].setBackground(Color.BLUE);
																	if(p.equals("P1 : "))
																		panelHolder[i][j].setBackground(Color.CYAN);*/
																panelHolder[i][j].setLayout(new GridLayout(1,0));

																panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
																ImageIcon water = new ImageIcon("ko.jpeg");
													    		
													    		Image coverImage = water.getImage(); // transform it 
																Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
																ImageIcon coverF = new ImageIcon(coverImageResize);
																JButton bc = new JButton(coverF);
																bc.setActionCommand(al);
																bc.setBackground(Color.LIGHT_GRAY);
																bc.addActionListener(this);
																panelHolder[i][j].add(bc);
															}
															else {
																if(tmp.getName().equals("Quicksilver")) {
																	String p = "";
																	String al = "";
																	if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
																		p = "P1 : ";
																		al = "1"+tmp.getName();
																	}
																	else {
																		p="P2 : ";
																		al = "2"+tmp.getName();
																	}
																	/*if(p.equals("P2 : "))
																		panelHolder[i][j].setBackground(Color.BLUE);
																		if(p.equals("P1 : "))
																			panelHolder[i][j].setBackground(Color.CYAN);*/
																	panelHolder[i][j].setLayout(new GridLayout(1,0));

																	panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
																	ImageIcon water = new ImageIcon("qq.jpg");
														    		
														    		Image coverImage = water.getImage(); // transform it 
																	Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
																	ImageIcon coverF = new ImageIcon(coverImageResize);
																	JButton bc = new JButton(coverF);
																	bc.setActionCommand(al);
																	bc.setBackground(Color.LIGHT_GRAY);
																	bc.addActionListener(this);
																	panelHolder[i][j].add(bc);
																}
																else {
																	if(tmp.getName().equals("Spiderman")) {
																		String p = "";
																		String al = "";
																		if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
																			p = "P1 : ";
																			al = "1"+tmp.getName();
																		}
																		else {
																			p="P2 : ";
																			al = "2"+tmp.getName();
																		}
																		/*if(p.equals("P2 : "))
																			panelHolder[i][j].setBackground(Color.BLUE);
																			if(p.equals("P1 : "))
																				panelHolder[i][j].setBackground(Color.CYAN);*/
																		panelHolder[i][j].setLayout(new GridLayout(1,0));

																		panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
																		ImageIcon water = new ImageIcon("sp.jpeg");
															    		
															    		Image coverImage = water.getImage(); // transform it 
																		Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
																		ImageIcon coverF = new ImageIcon(coverImageResize);
																		JButton bc = new JButton(coverF);
																		bc.setActionCommand(al);
																		bc.setBackground(Color.LIGHT_GRAY);
																		bc.addActionListener(this);
																		panelHolder[i][j].add(bc);
																	}
																	else {
																		if(tmp.getName().equals("Thor")) {
																			String p = "";
																			String al = "";
																			if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
																				p = "P1 : ";
																				al = "1"+tmp.getName();
																			}
																			else {
																				p="P2 : ";
																				al = "2"+tmp.getName();
																			}
																			/*if(p.equals("P2 : "))
																				panelHolder[i][j].setBackground(Color.BLUE);
																				if(p.equals("P1 : "))
																					panelHolder[i][j].setBackground(Color.CYAN);*/
																			panelHolder[i][j].setLayout(new GridLayout(1,0));
																			panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
																			ImageIcon water = new ImageIcon("Thorr.jpg");
																    		
																    		Image coverImage = water.getImage(); // transform it 
																			Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
																			ImageIcon coverF = new ImageIcon(coverImageResize);
																			JButton bc = new JButton(coverF);
																			bc.setActionCommand(al);
																			bc.setBackground(Color.LIGHT_GRAY);
																			bc.addActionListener(this);
																			panelHolder[i][j].add(bc);
																		}
																		else {
																			if(tmp.getName().equals("Venom")) {
																				String p = "";
																				String al = "";
																				if(this.Game.getFirstPlayer().getTeam().contains(tmp)) {
																					p = "P1 : ";
																					al = "1"+tmp.getName();
																				}
																				else {
																					p="P2 : ";
																					al = "2"+tmp.getName();
																				}
																				/*if(p.equals("P2 : "))
																					panelHolder[i][j].setBackground(Color.BLUE);
																					if(p.equals("P1 : "))
																						panelHolder[i][j].setBackground(Color.CYAN);*/
																				panelHolder[i][j].setLayout(new GridLayout(1,0));
																				panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
																				ImageIcon water = new ImageIcon("vn.PNG");
																	    		
																	    		Image coverImage = water.getImage(); // transform it 
																				Image coverImageResize = coverImage.getScaledInstance(340,210,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
																				ImageIcon coverF = new ImageIcon(coverImageResize);
																				JButton bc = new JButton(coverF);
																				bc.setActionCommand(al);
																				bc.setBackground(Color.LIGHT_GRAY);
																				bc.addActionListener(this);
																				panelHolder[i][j].add(bc);
																			}
																		}
																	}
																}
															}
														}
														
													}
												}
												
											}
										}
									}
								}
							}
							
						}
						
						
						
					}
					
					
					
					
				}
				if(board[i][j] == null) {
					
					panelHolder[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
			//this.north.setLayout(new FlowLayout());
			
			//JLabel p = new JLabel( this.Game.getTurnOrder().toString());
			//this.north.add(p);
			//this.south.setLayout(new GridLayout(1,2));
			//JPanel a7a = new JPanel();
			//a7a.setLayout(new BorderLayout());
			//JPanel a7a2 = new JPanel();
			//a7a2.setLayout(new BorderLayout());
		   // this.east.add(a7a);this.west.add(a7a2);
		   // JLabel b = new JLabel("P1 Name : " + this.Game.getFirstPlayer().getName()  );
			//a7a.add(b,BorderLayout.CENTER);
			//JLabel bn = new JLabel("P2 Name : " + this.Game.getSecondPlayer().getName());
			//a7a2.add(bn,BorderLayout.CENTER);
			/*//this.north.setLayout(new GridLayout(2,1));
			JPanel up = new JPanel();
			//this.north.add(up);
			JLabel p = new JLabel( this.Game.getTurnOrder().toString());
			this.north.add(p);
			JLabel p1 = new JLabel( this.Game.getFirstPlayer().getName());
			//east.add(p1);
			up.setLayout(new BorderLayout());
			boolean fpla = this.Game.isFirstLeaderAbilityUsed();
			boolean spla = this.Game.isSecondLeaderAbilityUsed();
		    JLabel ula = new JLabel("Used Leader Ability");
			JLabel nula = new JLabel("Did Not Use Leader Ability");
			String mm = "Leader Ability : ";
			String nn = "Leader Ability : ";
			if(fpla) {
				nn +=ula;
			}
			else {
				nn += nula;
			}
           if(spla) {
        	   mm +=ula;
			}
			else {
				mm += nula;
			}
           /*"P2 Name : " + this.Game.getSecondPlayer().getName()+" "+mm*/
          // JLabel fpn = new JLabel("P1 Name : " + this.Game.getFirstPlayer().getName()+" "+nn);//up.add(fpn);
          // JLabel b = new JLabel("P1 Name : " + this.Game.getFirstPlayer().getName()  );
          // fpn.add(b);
          // JLabel spn = new JLabel("P2 Name : " + this.Game.getSecondPlayer().getName()+" "+mm);//up.add(spn);
           //JLabel bn = new JLabel("P2 Name : " + this.Game.getSecondPlayer().getName());
          // spn.add(bn);
			//up.add(b,BorderLayout.NORTH);
			/*up.add(bn,BorderLayout.SOUTH);
			this.south.add(up);*/
			this.disInfo();
			//this.cla();
			//south = new JPanel();
			this.allow=true;
			this.revalidate();
			this.repaint();
			//turn;
			//return;
			if(this.Game.checkGameOver() != null) {
				this.over(this.Game.checkGameOver());
				this.revalidate();
				this.repaint();
				return;
			}
			this.revalidate();
			this.repaint();
		}
	}

	public void disInfo() {
		west = new JPanel();
		this.add(west,BorderLayout.WEST);
		 String info = "             ";
		 info += "**************************"+"\n"+ "             "+"I N F O R M A T I O N"+"\n"+ "             "+"**************************"+"\n";
		 String y = "used their leader ability.";
			String f = "did not use their leader ability.";
			String b = "";
			String o ="";
			if(this.Game.isFirstLeaderAbilityUsed()) {
				b=y;
			}
			else {b=f;}
			if(this.Game.isSecondLeaderAbilityUsed() ) {
				o=y;
			}
			/*"             "*/
			else {o=f;}
			info += "\n"+"\n";
			info +=  "             "+"First player name : "+ Game.getFirstPlayer().getName()+"\n";
			info +=  "             "+"Second player name : "+Game.getSecondPlayer().getName()+"\n";
			
			info += "\n"+"\n";
			info += this.turnDisplay()+"\n"+"\n";
			
			
			
			info += "\n"+ "             " +this.Game.getFirstPlayer().getName()+" "+b;
			 info += "\n";
			info += "\n"+ "             " +this.Game.getSecondPlayer().getName()+" "+o;
		    info += "\n"+"\n";
		 
		 
		 info += "\n"+ "             "+"  - "+this.Game.getFirstPlayer().getName()+"'s team:"+"\n";
		 for(Champion c : this.Game.getFirstPlayer().getTeam() ) {
			 
			 String t = "";
			 if(c instanceof Villain) {
				 t = "Villain";
			 }
			 if(c instanceof Hero) {
				 t = "Hero";
			 }
			 if(c instanceof AntiHero) {
				 t = "AntiHero";
			 }
			 boolean k = false;
			 if(this.Game.getFirstPlayer().getLeader().equals(c) || this.Game.getSecondPlayer().getLeader().equals(c)) {
				 k = true;
			 }
			
			 if(c.getAbilities().size()==4)
			 {
				 info +=  "\n"+ "             "+"Champion Name: " + c.getName() +"\n" + "             "
						 +"Max HP: " + c.getMaxHP()+"\n" + "             "
									 + "Current HP: " + c.getCurrentHP() +"\n"+ "             "
						 + "Mana: " + c.getMana()+"\n"+ "             "
										+ "Max Action Points Per Turn: " + c.getMaxActionPointsPerTurn() +"\n"+ "             "
						 + "Current Action Points: " + c.getCurrentActionPoints()+"\n"+ "             "
										+ "Attack Range: " + c.getAttackRange()+"\n" + "             "
						 + "Attack Damage: " + c.getAttackDamage() +"\n"+ "             "
										+ "Speed: " + c.getSpeed()+"\n" + "\n"+ "             "
										+ "Abilities:"  +"\n"+ "             "+ " - Ability 1:"+"\n"+ "             "+c.getAbilities().get(0).toString()
										+"\n"+ "\n" + "             "+ " - Ability 2:"+"\n"+ "             "+c.getAbilities().get(1).toString()
										+"\n"+ "\n"+ "             " + " - Ability 3:"+"\n"+ "             "+c.getAbilities().get(2).toString()
										+"\n"+ "\n"+ "             " + " - Ability 4:"+"\n"+ "             "+c.getAbilities().get(3).toString()
										+"\n" + "\n"+ "             " + "Applied Effects: " + c.getAppliedEffects().toString()
										+"\n"+ "             "+ "Condition: " + c.getCondition()
										+"\n"+ "             " +"Type: " +t+"\n"+ "             "+"Is Leader: "+k+"\n"+"\n"+ "             " + "~~~~~~~~~~~~~~~" + "\n"  ;
						
			 }
			 else {
			 info +=  "\n"+ "             "+"Champion Name: " + c.getName() +"\n" + "             "
		 +"Max HP: " + c.getMaxHP()+"\n" + "             "
					 + "Current HP: " + c.getCurrentHP() +"\n"+ "             "
		 + "Mana: " + c.getMana()+"\n"+ "             "
						+ "Max Action Points Per Turn: " + c.getMaxActionPointsPerTurn() +"\n"+ "             "
		 + "Current Action Points: " + c.getCurrentActionPoints()+"\n"+ "             "
						+ "Attack Range: " + c.getAttackRange()+"\n" + "             "
		 + "Attack Damage: " + c.getAttackDamage() +"\n"+ "             "
						+ "Speed: " + c.getSpeed()+"\n" + "\n"+ "             "
						+ "Abilities:"  +"\n"+ "             "+ " - Ability 1:"+"\n"+ "             "+c.getAbilities().get(0).toString()
						+"\n"+ "\n" + "             "+ " - Ability 2:"+"\n"+ "             "+c.getAbilities().get(1).toString()
						+"\n"+ "\n"+ "             " + " - Ability 3:"+"\n"+ "             "+c.getAbilities().get(2).toString()
						+"\n" + "\n"+ "             " + "Applied Effects: " + c.getAppliedEffects().toString()
						+"\n"+ "             "+ "Condition: " + c.getCondition()
						+"\n"+ "             " +"Type: " +t+"\n"+ "             "+"Is Leader: "+k+"\n"+"\n"+ "             " + "~~~~~~~~~~~~~~~" + "\n"  ;
		 }
			 }
		 
		 //info += "\n";
		 info += "\n"+ "             "+"  - "+this.Game.getSecondPlayer().getName()+"'s Team :"+"\n";
		 for(Champion c : this.Game.getSecondPlayer().getTeam() ) {
			 String t = "";
			 if(c instanceof Villain) {
				 t = "Villain";
			 }
			 if(c instanceof Hero) {
				 t = "Hero";
			 }
			 if(c instanceof AntiHero) {
				 t = "AntiHero";
			 }
			 boolean k = false;
			 if(this.Game.getFirstPlayer().getLeader().equals(c) || this.Game.getSecondPlayer().getLeader().equals(c)) {
				 k = true;
			 }
			 
			 
//			 info +=  "\n"+" "+"Champion Name :" + c.getName() +"\n" 
//		 +" Max HP :" + c.getMaxHP()+"\n" 
//					 + "Current HP :" + c.getCurrentHP() +"\n"
//		 + "Mana :" + c.getMana()+"\n"
//						+ "Max Action Points Per Turn :" + c.getMaxActionPointsPerTurn() +"\n"
//		 + "Current Action Points :" + c.getCurrentActionPoints()+"\n"
//						+ "Attack Range :" + c.getAttackRange()+"\n" 
//		 + "Attack Damage :" + c.getAttackDamage() +"\n"
//						+ "Speed :" + c.getSpeed()+"\n"
//						+ "Abilities :"  +"\n"+ "Ability 1"+"\n"+c.getAbilities().get(0).toString()
//						+"\n"+ "Ability 2"+"\n"+c.getAbilities().get(1).toString()
//						+"\n"+ "Ability 3"+"\n"+c.getAbilities().get(2).toString()
//						+"\n" + "Applied Effects :" + c.getAppliedEffects().toString()
//						+"\n"+ "Condition " + c.getCondition()
//						+"\n" +"Type : " +t+"\n"+"Is Leader ? "+k+"\n"+"\n"  ;
			 
			 
//			 info +=   "             "+"\n"+ "             "+"Champion Name: " + c.getName() +"\n" + "             "
//					 +"Max HP: " + c.getMaxHP()+"\n" + "             "
//								 + "Current HP: " + c.getCurrentHP() +"\n"+ "             "
//					 + "Mana: " + c.getMana()+"\n"+ "             "
//									+ "Max Action Points Per Turn: " + c.getMaxActionPointsPerTurn() +"\n"+ "             "
//					 + "Current Action Points: " + c.getCurrentActionPoints()+"\n"+ "             "
//									+ "Attack Range: " + c.getAttackRange()+"\n" + "             "
//					 + "Attack Damage: " + c.getAttackDamage() +"\n"+ "             "
//									+ "Speed: " + c.getSpeed()+"\n" + "\n"+ "             "
//									+ "Abilities:"  +"\n"+ "             "+ " - Ability 1:"+"\n"+ "             "+c.getAbilities().get(0).toString()
//									+"\n"+ "\n" + "             "+ " - Ability 2:"+"\n"+ "             "+c.getAbilities().get(1).toString()
//									+"\n"+ "\n" + "             "+ " - Ability 3:"+"\n"+ "             "+c.getAbilities().get(2).toString()
//									+"\n" + "\n" + "             "+ "Applied Effects: " + c.getAppliedEffects().toString()
//									+"\n"+ "             "+ "Condition: " + c.getCondition()
//									+"\n"+ "             " +"Type: " +t+"\n"+ "             "+"Is Leader: "+k+"\n"+"\n"+ "             " + "~~~~~~~~~~~~~~~" + "\n"  ;
//			 
			 if(c.getAbilities().size()==4)
			 {
				 info +=  "\n"+ "             "+"Champion Name: " + c.getName() +"\n" + "             "
						 +"Max HP: " + c.getMaxHP()+"\n" + "             "
									 + "Current HP: " + c.getCurrentHP() +"\n"+ "             "
						 + "Mana: " + c.getMana()+"\n"+ "             "
										+ "Max Action Points Per Turn: " + c.getMaxActionPointsPerTurn() +"\n"+ "             "
						 + "Current Action Points: " + c.getCurrentActionPoints()+"\n"+ "             "
										+ "Attack Range: " + c.getAttackRange()+"\n" + "             "
						 + "Attack Damage: " + c.getAttackDamage() +"\n"+ "             "
										+ "Speed: " + c.getSpeed()+"\n" + "\n"+ "             "
										+ "Abilities:"  +"\n"+ "             "+ " - Ability 1:"+"\n"+ "             "+c.getAbilities().get(0).toString()
										+"\n"+ "\n" + "             "+ " - Ability 2:"+"\n"+ "             "+c.getAbilities().get(1).toString()
										+"\n"+ "\n"+ "             " + " - Ability 3:"+"\n"+ "             "+c.getAbilities().get(2).toString()
										+"\n"+ "\n"+ "             " + " - Ability 4:"+"\n"+ "             "+c.getAbilities().get(3).toString()
										+"\n" + "\n"+ "             " + "Applied Effects: " + c.getAppliedEffects().toString()
										+"\n"+ "             "+ "Condition: " + c.getCondition()
										+"\n"+ "             " +"Type: " +t+"\n"+ "             "+"Is Leader: "+k+"\n"+"\n"+ "             " + "~~~~~~~~~~~~~~~" + "\n"  ;
						
			 }
			 else {
			 info +=  "\n"+ "             "+"Champion Name: " + c.getName() +"\n" + "             "
		 +"Max HP: " + c.getMaxHP()+"\n" + "             "
					 + "Current HP: " + c.getCurrentHP() +"\n"+ "             "
		 + "Mana: " + c.getMana()+"\n"+ "             "
						+ "Max Action Points Per Turn: " + c.getMaxActionPointsPerTurn() +"\n"+ "             "
		 + "Current Action Points: " + c.getCurrentActionPoints()+"\n"+ "             "
						+ "Attack Range: " + c.getAttackRange()+"\n" + "             "
		 + "Attack Damage: " + c.getAttackDamage() +"\n"+ "             "
						+ "Speed: " + c.getSpeed()+"\n" + "\n"+ "             "
						+ "Abilities:"  +"\n"+ "             "+ " - Ability 1:"+"\n"+ "             "+c.getAbilities().get(0).toString()
						+"\n"+ "\n" + "             "+ " - Ability 2:"+"\n"+ "             "+c.getAbilities().get(1).toString()
						+"\n"+ "\n"+ "             " + " - Ability 3:"+"\n"+ "             "+c.getAbilities().get(2).toString()
						+"\n" + "\n"+ "             " + "Applied Effects: " + c.getAppliedEffects().toString()
						+"\n"+ "             "+ "Condition: " + c.getCondition()
						+"\n"+ "             " +"Type: " +t+"\n"+ "             "+"Is Leader: "+k+"\n"+"\n"+ "             " + "~~~~~~~~~~~~~~~" + "\n"  ;
		 }
			 
		 }
		// info += "\n" +"\n" + "\n" +"\n" +"\n" + "\n"+ "hhhh";
		 /*	@Override
	public String toString() {
		return "Champion [name=" + name + ", maxHP=" + maxHP + ", currentHP=" + currentHP + ", mana=" + mana
				+ ", maxActionPointsPerTurn=" + maxActionPointsPerTurn + ", currentActionPoints=" + currentActionPoints
				+ ", attackRange=" + attackRange + ", attackDamage=" + attackDamage + ", speed=" + speed
				+ ", abilities=" + abilities + ", appliedEffects=" + appliedEffects + ", condition=" + condition + "]";
	}*/
		 
		 
		 
		 
		 
		 
		 JTextArea a = new JTextArea(40,25);
		// a.setPreferredSize(new Dimension(30,50));
		 a.setText(info);
		 a.setEditable(false);
		// JScrollPane s = new JScrollPane(a,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 //s.setBorder(BorderFactory.createEmptyBorder());
		 JScrollPane scroll = new JScrollPane(a);
		 scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		 west.add(scroll);
		 a.setCaretPosition(0);
		 this.revalidate();
		 this.repaint();
	}

	/*public void cla() {
		south = new JPanel();
		this.add(south,BorderLayout.SOUTH);
		south.setLayout(new FlowLayout());
		//"Used Leader Ability"
		//"Did Not Use Leader Ability"
		String y = "Used Leader Ability";
		String f = "Did Not Use Leader Ability";
		String b = "";
		String o ="";
		if(this.Game.isFirstLeaderAbilityUsed()) {
			b=y;
		}
		else {b=f;}
		if(this.Game.isSecondLeaderAbilityUsed() ) {
			o=y;
		}
		else {o=f;}
		JLabel label = new JLabel(""+this.Game.getFirstPlayer().getName()+" "+b+"  ,  ");
	    JLabel label2 = new JLabel(""+this.Game.getSecondPlayer().getName()+" "+o);
		south.add(label);
		south.add(label2);
		this.revalidate();
		this.repaint();
	}*/

	public void mainGame() {
		/*this.setContentPane(new Container() );
		this.setLayout(new BorderLayout());
		this.revalidate();
		this.repaint();*/
		try {
			this.Game = new Game (one ,two);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.setL(Game);
		Game.setL(this);
		for(Ability a : this.Game.getAvailableAbilities()) {
			this.abilityName.add(a.getName());
		}
		 /*north = new JPanel();
		 east = new JPanel();
		 south = new JPanel();
		 west = new JPanel();
		 center = new JPanel();
		center.setLayout(new GridLayout(5,5));
		center.setVisible(true);
		//center.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		this.add(north,BorderLayout.NORTH);
		this.add(east,BorderLayout.EAST);
		this.add(west,BorderLayout.WEST);
		this.add(south,BorderLayout.SOUTH);
		this.add(center,BorderLayout.CENTER);
		
		
		//int i = 5;
		//int j = 5;
		 //panelHolder = new JPanel[i][j];    

		for(int m = 0; m < 5; m++) {
		   for(int n = 0; n < 5; n++) {
		      panelHolder[m][n] = new JPanel();
		      panelHolder[m][n].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		      
		      center.add(panelHolder[m][n]);
		   }
		}
		north.setLayout(new FlowLayout());
		//JLabel labe3 = new JLabel("Turn Order : ");
		//north.add(labe3);
		
		JLabel label = new JLabel("First Player Name : "+ Game.getFirstPlayer().getName()+" , ");
	     JLabel label2 = new JLabel("Second Player Name : "+Game.getSecondPlayer().getName());
		north.add(label);
		north.add(label2);*/
		this.updateBoard();
		this.revalidate();
		this.repaint();
		
		
	}

	public void displayAvilableChamps() {
		this.setContentPane(new Container() );
		this.setLayout(new BorderLayout());
		this.revalidate();
		this.repaint();
		dis = new JPanel();
		dis.setLayout(new GridLayout(5,3));
		if(this.Game.getFirstPlayer().getTeam().size() == 3 && this.Game.getSecondPlayer().getTeam().size() == 3) {
			this.chooseLeade();
			return;
		}
	    for(Champion c : this.Game.getAvailableChampions()) {
	    	if(!this.Game.getFirstPlayer().getTeam().contains(c) && !this.Game.getSecondPlayer().getTeam().contains(c)) {
	    	if(!c.getName().equals("Captain America")
	    			&&!c.getName().equals("Deadpool")
	    			&&!c.getName().equals("Dr Strange")
	    			&&!c.getName().equals("Electro")
	    			&&!c.getName().equals("Ghost Rider")
	    			&&!c.getName().equals("Hela")
	    			&&!c.getName().equals("Hulk")
	    			&&!c.getName().equals("Iceman")
	    			&&!c.getName().equals("Ironman")
	    			&&!c.getName().equals("Loki")
	    			&&!c.getName().equals("Quicksilver")
	    			&&!c.getName().equals("Spiderman")
	    			&&!c.getName().equals("Thor")
	    			&&!c.getName().equals("Venom")) {
	    	
	    		ImageIcon water = new ImageIcon("yj.jpg");
	    		
	    		Image coverImage = water.getImage(); // transform it 
				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
				ImageIcon coverF = new ImageIcon(coverImageResize);
	    		
	    		
	    		JButton tmp = new JButton(coverF);
	    		tmp.setActionCommand("Yellow Jacket");
	    		this.name.add(c.getName());
				tmp.addActionListener(this);
				dis.add(tmp);
	    	}
	    	else {
	    		
	    		if(c.getName().equals("Captain America")){
	    		
	    		ImageIcon water = new ImageIcon("m55.jpg");
	    		
	    		Image coverImage = water.getImage(); // transform it 
				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
				ImageIcon coverF = new ImageIcon(coverImageResize);
	    		
	    		
	    		JButton tmp = new JButton(coverF);
	    		tmp.setActionCommand("Captain America");
	    		this.name.add(c.getName());
				tmp.addActionListener(this);
				dis.add(tmp);
				
	    	}
	    		else {
	    			if(c.getName().equals("Deadpool")) {
	    				ImageIcon water = new ImageIcon("d2.png");
	    	    		
	    	    		Image coverImage = water.getImage(); // transform it 
	    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
	    				ImageIcon coverF = new ImageIcon(coverImageResize);
	    	    		
	    	    		
	    	    		JButton tmp = new JButton(coverF);
	    	    		tmp.setActionCommand("Deadpool");
	    	    		this.name.add(c.getName());
	    				tmp.addActionListener(this);
	    				dis.add(tmp);
	    			}
	    			else {
	    				if(c.getName().equals("Dr Strange")){
	    					ImageIcon water = new ImageIcon("s1.png");
		    	    		
		    	    		Image coverImage = water.getImage(); // transform it 
		    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
		    				ImageIcon coverF = new ImageIcon(coverImageResize);
		    	    		
		    	    		
		    	    		JButton tmp = new JButton(coverF);
		    	    		tmp.setActionCommand("Dr Strange");
		    	    		this.name.add(c.getName());
		    				tmp.addActionListener(this);
		    				dis.add(tmp);
	    					
	    				}
	    				else {
	    					if(c.getName().equals("Electro")) {
	    						ImageIcon water = new ImageIcon("el1.jpg");
			    	    		
			    	    		Image coverImage = water.getImage(); // transform it 
			    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
			    				ImageIcon coverF = new ImageIcon(coverImageResize);
			    	    		
			    	    		
			    	    		JButton tmp = new JButton(coverF);
			    	    		tmp.setActionCommand("Electro");
			    	    		this.name.add(c.getName());
			    				tmp.addActionListener(this);
			    				dis.add(tmp);
	    					}
	    					else {
	    						if(c.getName().equals("Ghost Rider")) {
		    						ImageIcon water = new ImageIcon("gr1.png");
				    	    		
				    	    		Image coverImage = water.getImage(); // transform it 
				    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
				    				ImageIcon coverF = new ImageIcon(coverImageResize);
				    	    		
				    	    		
				    	    		JButton tmp = new JButton(coverF);
				    	    		//tmp.setBackground(Color.LIGHT_GRAY);
				    	    		tmp.setActionCommand("Ghost Rider");
				    	    		this.name.add(c.getName());
				    				tmp.addActionListener(this);
				    				dis.add(tmp);
		    					}
	    						else {
	    							if(c.getName().equals("Hela")) {
			    						ImageIcon water = new ImageIcon("h1.jpg");
					    	    		
					    	    		Image coverImage = water.getImage(); // transform it 
					    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
					    				ImageIcon coverF = new ImageIcon(coverImageResize);
					    	    		
					    	    		
					    	    		JButton tmp = new JButton(coverF);
					    	    		tmp.setActionCommand("Hela");
					    	    		this.name.add(c.getName());
					    				tmp.addActionListener(this);
					    				dis.add(tmp);
			    					}
	    							else {
	    								if(c.getName().equals("Hulk")) {
				    						ImageIcon water = new ImageIcon("hulk.png");
						    	    		
						    	    		Image coverImage = water.getImage(); // transform it 
						    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
						    				ImageIcon coverF = new ImageIcon(coverImageResize);
						    	    		
						    	    		
						    	    		JButton tmp = new JButton(coverF);
						    	    		tmp.setActionCommand("Hulk");
						    	    		this.name.add(c.getName());
						    				tmp.addActionListener(this);
						    				dis.add(tmp);
				    					}
	    								else {
	    									if(c.getName().equals("Iceman")) {
					    						ImageIcon water = new ImageIcon("i1.jpg");
							    	    		
							    	    		Image coverImage = water.getImage(); // transform it 
							    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
							    				ImageIcon coverF = new ImageIcon(coverImageResize);
							    	    		
							    	    		
							    	    		JButton tmp = new JButton(coverF);
							    	    		tmp.setActionCommand("Iceman");
							    	    		this.name.add(c.getName());
							    				tmp.addActionListener(this);
							    				dis.add(tmp);
					    					}
	    									else {
	    										if(c.getName().equals("Ironman")) {
						    						ImageIcon water = new ImageIcon("im1.jpg");
								    	    		
								    	    		Image coverImage = water.getImage(); // transform it 
								    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
								    				ImageIcon coverF = new ImageIcon(coverImageResize);
								    	    		
								    	    		
								    	    		JButton tmp = new JButton(coverF);
								    	    		tmp.setActionCommand("Ironman");
								    	    		this.name.add(c.getName());
								    				tmp.addActionListener(this);
								    				dis.add(tmp);
						    					}
	    										else {
	    											if(c.getName().equals("Loki")) {
							    						ImageIcon water = new ImageIcon("loki.jpg");
									    	    		
									    	    		Image coverImage = water.getImage(); // transform it 
									    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
									    				ImageIcon coverF = new ImageIcon(coverImageResize);
									    	    		
									    	    		
									    	    		JButton tmp = new JButton(coverF);
									    	    		tmp.setActionCommand("Loki");
									    	    		this.name.add(c.getName());
									    				tmp.addActionListener(this);
									    				dis.add(tmp);
							    					}
	    											else {
	    												if(c.getName().equals("Quicksilver")) {
								    						ImageIcon water = new ImageIcon("q1.jpg");
										    	    		
										    	    		Image coverImage = water.getImage(); // transform it 
										    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
										    				ImageIcon coverF = new ImageIcon(coverImageResize);
										    	    		
										    	    		
										    	    		JButton tmp = new JButton(coverF);
										    	    		tmp.setActionCommand("Quicksilver");
										    	    		this.name.add(c.getName());
										    				tmp.addActionListener(this);
										    				dis.add(tmp);
								    					}
	    												else {
	    													if(c.getName().equals("Spiderman")) {
									    						ImageIcon water = new ImageIcon("sm.jpg");
											    	    		
											    	    		Image coverImage = water.getImage(); // transform it 
											    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
											    				ImageIcon coverF = new ImageIcon(coverImageResize);
											    	    		
											    	    		
											    	    		JButton tmp = new JButton(coverF);
											    	    		tmp.setActionCommand("Spiderman");
											    	    		this.name.add(c.getName());
											    				tmp.addActionListener(this);
											    				dis.add(tmp);
									    					}
	    													else {
	    														if(c.getName().equals("Thor")) {
										    						ImageIcon water = new ImageIcon("thor.jpg");
												    	    		
												    	    		Image coverImage = water.getImage(); // transform it 
												    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
												    				ImageIcon coverF = new ImageIcon(coverImageResize);
												    	    		
												    	    		
												    	    		JButton tmp = new JButton(coverF);
												    	    		tmp.setActionCommand("Thor");
												    	    		this.name.add(c.getName());
												    				tmp.addActionListener(this);
												    				dis.add(tmp);
										    					}
	    														else {
	    															if(c.getName().equals("Venom")) {
											    						ImageIcon water = new ImageIcon("venom.jpg");
													    	    		
													    	    		Image coverImage = water.getImage(); // transform it 
													    				Image coverImageResize = coverImage.getScaledInstance(500,150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
													    				ImageIcon coverF = new ImageIcon(coverImageResize);
													    	    		
													    	    		
													    	    		JButton tmp = new JButton(coverF);
													    	    		tmp.setActionCommand("Venom");
													    	    		this.name.add(c.getName());
													    				tmp.addActionListener(this);
													    				dis.add(tmp);
											    					}
	    															
	    														
	    														}
	    													}
	    												}
	    											}
	    											
	    										}
	    									}
	    								}
	    							}
	    						}
	    					}
	    				}
	    			}
	    			
	    			
	    		}
				
				
				
	    	}
			
		//	tmp.addActionListener(this);
			
	    	}
			
			
		}
		this.add(dis,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		
		
	}

	public void chooseLeade() {
		this.setContentPane(new Container() );
		this.setLayout(new GridLayout(2,1));
		this.revalidate();
		this.repaint();
		JPanel ocl = new JPanel();
		ocl.setLayout(new GridLayout(1,4));
		JLabel on = new JLabel("P1 choose Leader :");
		on.setFont(new Font("Serif", Font.BOLD, 25));
		on.setForeground(Color.ORANGE);
		ocl.setBackground(Color.BLACK);

		ocl.add(on);
		JPanel tcl = new JPanel();
		tcl.setLayout(new GridLayout(1,4));
		JLabel tn = new JLabel("P2 choose Leader :");
		tn.setFont(new Font("Serif", Font.BOLD, 25));
		tn.setForeground(Color.ORANGE);
		tcl.setBackground(Color.BLACK);
		tcl.add(tn);
		JButton b1 = new JButton(this.Game.getFirstPlayer().getTeam().get(0).getName());b1.addActionListener(this);
		JButton b2 = new JButton(this.Game.getFirstPlayer().getTeam().get(1).getName());b2.addActionListener(this);
		JButton b3 = new JButton(this.Game.getFirstPlayer().getTeam().get(2).getName());b3.addActionListener(this);
		b1.setFont(new Font("Serif", Font.BOLD, 25));
		b2.setFont(new Font("Serif", Font.BOLD, 25));
		b3.setFont(new Font("Serif", Font.BOLD, 25));

		b1.setForeground(Color.ORANGE);
		b2.setForeground(Color.ORANGE);
		b3.setForeground(Color.ORANGE);

		b1.setBackground(Color.BLACK);b2.setBackground(Color.BLACK);b3.setBackground(Color.BLACK);
		ocl.add(b1);ocl.add(b2);ocl.add(b3);
		JButton b4 = new JButton(this.Game.getSecondPlayer().getTeam().get(0).getName());b4.addActionListener(this);
		JButton b5 = new JButton(this.Game.getSecondPlayer().getTeam().get(1).getName());b5.addActionListener(this);
		JButton b6 = new JButton(this.Game.getSecondPlayer().getTeam().get(2).getName());b6.addActionListener(this);
		b4.setFont(new Font("Serif", Font.BOLD, 25));
		b5.setFont(new Font("Serif", Font.BOLD, 25));
		b6.setFont(new Font("Serif", Font.BOLD, 25));

		b4.setForeground(Color.ORANGE);
		b5.setForeground(Color.ORANGE);
		b6.setForeground(Color.ORANGE);
		b4.setBackground(Color.BLACK);b5.setBackground(Color.BLACK);b6.setBackground(Color.BLACK);
		tcl.add(b4);tcl.add(b5);tcl.add(b6);
		this.add(ocl);
		this.add(tcl);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void over(Player P) {
//		this.remove(south);
//		this.remove(north);
//		this.remove(west);
//		this.remove(east);
//		this.remove(center);
		//this.removeAll();
		//this.removeNotify();
		JPanel r = new JPanel();
		r.setLayout(new FlowLayout());
		JButton e = new JButton("Exit");
		e.addActionListener(new CloseListener());
		r.add(e);
			
		JTextArea area = new JTextArea();
		area.setText("                         Special Thanks to : \n                         Professor. Slim \n                         Dr.Menrit \n                         Dr.Nourhan \n                         Dr.Nada ");
		area.setPreferredSize(new Dimension(100,100));
		area.setFont(area.getFont().deriveFont(80f)); 
		area.setEditable(false);
	
//		JButton Met = new JButton("MET Website");
//		Met.addActionListener(this);
//		r.add(Met);
		/*JButton Exit = new JButton("Exit");
		Exit.setBackground(Color.ORANGE);
		Exit.setBounds((1540/3)+360, 100, 100, 50);
		Exit.addActionListener(new CloseListener());*/
		 
		
	    this.setContentPane(new Container() );
		this.setLayout(new BorderLayout());
		this.add(r,BorderLayout.NORTH);
		this.add(area,BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
		//this.removeAll();
		JFrame frame = new JFrame("Game Over");
		JOptionPane.showMessageDialog(frame, ""+P.getName()+" Won","Game Over", JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
	}

}
