package Agents;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class mom extends Agent{
	private static final long serialVersionUID = 1L;
	
	protected void setup (){
		
		ServiceDescription service = new ServiceDescription();
		service.setType("mom");
		service.setName("search the baby");
		
		register(service);
		getMessage();
	}

	private void getMessage() {
		// TODO Auto-generated method stub
		addBehaviour(new CyclicBehaviour (this){
			private static final long serialVersionUID = 10L;

			public void action() {
				// TODO Auto-generated method stub
				ACLMessage message = myAgent.receive();
				if (message != null){
					ACLMessage  reply = message.createReply();
					String content = message.getContent();
					
					if(content.equalsIgnoreCase("cry")){
						reply.setPerformative(ACLMessage.INFORM);
						reply.setContent("oh no! the baby is crying");
						myAgent.send(reply);
						System.out.println("i going to help");			
						
					}else
						block();
				}// End action
				
			}
		});//End Behavior
		
	}

	private void register(ServiceDescription service) {
		// TODO Auto-generated method stub
		DFAgentDescription dfAgentDescription = new DFAgentDescription();
		dfAgentDescription.setName(getAID());
		dfAgentDescription.addServices(service);
		
		// Registering agent in DF
		try{
			DFService.register(this,dfAgentDescription);
		}catch(FIPAException fipaException){
			fipaException.printStackTrace();
		}
	}
	
	protected void takeDown() {
		try{
			DFService.deregister(this);
		}catch(FIPAException fipaE){
			fipaE.printStackTrace();
		}
	} 
	
	
}
