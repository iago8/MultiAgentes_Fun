package Agents;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class Baby extends Agent{

	private static final long serialVersionUID = 11L;
	
protected void setup(){
		
		cry();
		receiveMessage();
		searchmom();
		
	}



private void cry() {
	// TODO Auto-generated method stub
	addBehaviour(new OneShotBehaviour(this){
		
		private static final long serialVersionUID = 1L;

		public void action(){
			
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.addReceiver(new AID("mom",AID.ISLOCALNAME));
			message.setLanguage("English");
			message.setOntology("Emergency");
			message.setContent("cry");
			myAgent.send(message);
		}
	});
	
	
}

private void receiveMessage() {
	// TODO Auto-generated method stub
	addBehaviour(new CyclicBehaviour(this) {
		
		private static final long serialVersionUID = 1L;

	
		public void action() {
			ACLMessage message = myAgent.receive();
			if(message != null){
				String content = message.getContent();
				System.out.println("-->" + message.getSender().getName() + " : " + content);
			}else{
				block();
			}
		}
	});
}
	
protected void searchmom() {
	DFAgentDescription searchServices = new DFAgentDescription();
	
	ServiceDescription service = new ServiceDescription();
	service.setType("mom");
	
	searchServices.addServices(service);
	
	try {
		
		DFAgentDescription[] firefight = DFService.search(this, searchServices);
		printServices(firefight);
		
	}catch(FIPAException e){
		e.printStackTrace();
	}
}

private void printServices(DFAgentDescription[] mom) {
	
	for(int i = 0; i < mom.length; i++){
		
		String out = mom[i].getName().getLocalName() + "  : ";
		Iterator services = mom[i].getAllServices();
		
		while(services.hasNext()){
			ServiceDescription serviceDescription = (ServiceDescription) services.next();
			out += " " + serviceDescription.getName();
		}
		
		System.out.println(out);
	}
}
	
}
