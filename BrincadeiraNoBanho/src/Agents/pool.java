package Agents;

import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;



public class pool extends Agent{

	private static final long serialVersionUID = 1L;

	public static final int Baby = 5;
	public static final int rubberDuck = 7;
	public static final int MOM = 3;
	private static final int SIZE = 4;
	//public static final int Duck = 1;
	public static AgentController babyAgent;
	public static AgentController momAgent;
	private int pool[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },{ 0, 0, 0 } };

	public void setup() {
		super.setup();

		PlatformController container = getContainerController();

		createAgents(container);

		addBehaviour(new Location(this));

	}
	
	private void createAgents(PlatformController container) {
		try {

			this.momAgent = container.createNewAgent("mom",
					"agents.mom", null);
			this.babyAgent = container.createNewAgent("baby",
					"agents.Baby", null);

			System.out.println("Agent born: "
					+ this.momAgent.getName());
			System.out.println("Agent born: " + this.babyAgent.getName());

			this.momAgent.start();
			this.babyAgent.start();

		} catch (ControllerException e) {
			System.out.println("the Agent don't exist");
			e.printStackTrace();
		}
	}
	
	
	private class Location extends OneShotBehaviour {

		private static final long serialVersionUID = 1L;

		public Location(Agent agent) {
			super(agent);

		}

		
		public void action() {
			setbabyLocation();
			setrubberDuckPosition();

		}

		private void setrubberDuckPosition() {

			boolean babyCaught = setmomLocation();

			for (int countLine = 0; countLine < pool.length; countLine++) {
				for (int countColumn = 0; countColumn < pool[countLine].length; countColumn++) {
					
					System.out.println("Duck position:  vector position " + pool[countLine][countColumn]);
					//System.out.println("position of the baby " + Baby);
					

					if (pool[countLine][countColumn] == Baby) {
						System.out.println("Duck position:  ( X = " + countLine + "  Y= " + countLine + ")");
						System.out.println("Rubber Duck hit the baby");
					} else {
						pool[countLine][countColumn] = rubberDuck;
					}
				}
			}

		}
	
	}
		private void setbabyLocation() {

			Random random = new Random();

			int x = 0;
			int y = 0;

			x = random.nextInt(SIZE);
			y = random.nextInt(SIZE);

			System.out.println("baby's position: (X= " + x + "  Y= " + y + ")");
			pool[x][y] = Baby;

		}	
		
		
		private boolean setmomLocation() {

			Random random = new Random();

			boolean isSave = false;
			int line = 0;
			int column = 0;

			line = random.nextInt(SIZE);       	System.out.println("mom line X: " +  line);
			column = random.nextInt(SIZE);         System.out.println("mom column Y: " + column);

			for (int countLine = line; countLine < pool.length; countLine++) {
				for (int countColumn = column; countColumn < pool[countLine].length; countColumn++) {

					System.out.println("mom position ( X = " + countLine + "  Y= " + countLine + ")");
					
					
					if (pool[countLine][countColumn] == Baby) {
						System.out.println("Position of the mom:  ( X = " + countLine + "  Y= " + countLine + ")");
						System.out.println("Baby Safe");
						isSave = true;
					} else {
						pool[countLine][countColumn] = MOM;
					} 
					
					
					
				}
			}

			return isSave;
		}

	
		/*private class duck extends OneShotBehaviour {

			private static final long serialVersionUID = 41L;

			public Duck(Agent agent) {
				super(agent);

			}
		
		
		public void action() {
			setquack();
			setduckLocation();
			
		}
		private void setduckLocation() {

			Random random = new Random();

			boolean inHunt = false;
			int x = 0;
			int y = 0;

			x = random.nextInt(SIZE);		System.out.println("Duck line X: " +  line);
			y = random.nextInt(SIZE);		System.out.println("Duck column Y: " + column);
			
			for (int countLine = line; countLine < pool.length; countLine++) {
				for (int countColumn = column; countColumn < pool[countLine].length; countColumn++) {

					System.out.println("Duck position ( X = " + countLine + "  Y= " + countLine + ")");
					System.out.println("Quack")
				}
			}
			
			return inHunt;

		}	
		
	}
	
	
*/
		
		
}
	
	
	
