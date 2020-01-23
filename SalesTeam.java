import java.util.Iterator;
import java.util.LinkedList;

public class SalesTeam {
	protected LinkedList<String> salesTeam;
	
	public SalesTeam() {
		LinkedList<String> newTeam = new LinkedList<String>();
		newTeam.add("Agent001");
		newTeam.add("Agent002");
		newTeam.add("Agent003");
		newTeam.add("Agent004");
		newTeam.add("Agent005");
		newTeam.add("Agent006");
		newTeam.add("Agent007");
		newTeam.add("Agent008");
		
		salesTeam = newTeam;
	}
	public String getSalesPerson() {
		Iterator iterator = salesTeam.iterator();
		int randomEmployee = (int)(Math.random()*salesTeam.size());
		int index = 0;
		String randomEmployeeName = "";
		
		while (iterator.hasNext()) {
			String nextEmployee = (String) iterator.next();
			if(index == randomEmployee) {
				randomEmployeeName = nextEmployee;
			}
			index = index+1;
		}
		return randomEmployeeName;
		
	}
	
	public String getAllEmployees() {
		Iterator iterator = salesTeam.iterator();
		String allEmployees = "";
		int randomEmployee = (int)(Math.random()*salesTeam.size());
		int index = 0;
		
		while (iterator.hasNext()) {
			String nextEmployee = (String) iterator.next();
			allEmployees = allEmployees + nextEmployee + " ";
			index = index+1;
		}
		return allEmployees;
			
			
		}
	}

