import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NfaTest {

	static BufferedReader br;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String st;
		int i,n,k; //number of NFA transitions
		i=k=n=0;
		List <String> fStates = new ArrayList<String>();
		List <String> tmp_states = new ArrayList<String>();
		List <String> tmp = new ArrayList<String>();
		List <String> merge = new ArrayList<String>();
		List <String> inputλ = new ArrayList<String>();
		List <String> startStates = new ArrayList<String>();
		List <String> start = new ArrayList<String>();
		List <String> finals = new ArrayList<String>();
		List <String> listWithoutDuplicates = new ArrayList<String>();


		/*String[] states;
		char[] alphabet;
		String[] Fstates;
		String startState;
	    String TstartState;
		   char input;
		String newState;*/
		Transition[] t = new Transition[8];
		Transition[] nfa_transitions = new Transition[8];
		Scanner inp = new Scanner(System.in);
		File file = new File("D:\\documents\\java.exercises\\NFA\\src\\NFA_Input_2.txt"); 
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String[][] l = new String[12][];
		
		for(i=0;i<12;i++)
			try {
				l[i] = br.readLine().split(" ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		start.addAll(Arrays.asList(l[2]));
		finals.addAll(Arrays.asList(l[3]));
		System.out.println(finals+ "jjjjjjjjjjjjjjjjj");
		String l1[] = new String[3];
		String l3[] = new String[3];
		for(i=0;i<3;i++) {
			
			l1[i] = l[1][i];
			System.out.println("L1 : " + l1[i]);
		}
		AuxiliaryStates[] as = new AuxiliaryStates[l[1].length];
		System.out.println();
		l3[0] = l[3][0]; 
		
		int j = 4;
		for(i=0;i<8;i++) {
			
			t[i] = new Transition(l[j][0], l[j][1].charAt(0), l[j][2]);
			j++;
			System.out.println(t[i].startState + t[i].input + t[i].newState);
		}
		
		for(i=0;i<l1.length;i++) {

			as[i] = new AuxiliaryStates();
			for(j=0;j<t.length;j++) {
				
				if(t[j].startState.equals(l1[i])) {
					
					if(t[j].input== '0'){
						as[i].statesOfstate0.add(t[j].newState);
						as[i].state = t[j].startState;
					}
					if(t[j].input== '1'){
						as[i].statesOfstate1.add(t[j].newState);
						as[i].state = t[j].startState;
					}
					if(t[j].input== 'λ'){
						inputλ.add(t[j].startState);
						inputλ.add(t[j].newState);
						as[i].statesOfstateλ.add(t[j].newState);
						as[i].state = t[j].startState;
					}
				}
			}
			System.out.printf("State %d : %s , %s , %s\n",i,as[i].statesOfstate0.toString(),as[i].statesOfstate1.toString(),inputλ.toString());
			if(!isInList(tmp_states, inputλ) && !inputλ.isEmpty()){
				tmp_states.addAll(inputλ);
				tmp_states.add(",");
				inputλ.clear();
			}
		}
		//System.out.println("tmp_states :"+ tmp_states);
		find_FinalStartStates(tmp_states, start, startStates);
		System.out.println("startStates :"+ startStates);
		//System.out.println("Asssssssssssss :"+as[0].state + as[0].statesOfstateλ);
		if(startStates.isEmpty())
			startStates.addAll(start);
		tmp_states.clear();
		tmp_states.addAll(startStates);
		//tmp_states.add(",");
		System.out.println("tmp_states befor tracing  : "+ tmp_states.toString());
		for(i=0;i<tmp_states.size();i++){

			if(k==tmp_states.size()) break;
			for (i=k;i<tmp_states.size();i++) {
				k++;
				if (tmp_states.get(i).equals(",")) break;
				tmp.add(tmp_states.get(i));
			}
			System.out.println("Temp Temp :" + tmp);
			for (j=0;j<as.length;j++){
				if(tmp.contains(as[j].state)){
					merge.addAll(as[j].statesOfstate0);
				}
			}
			for (j=0;j<as.length;j++){
				if(merge.contains(as[j].state) && !as[j].statesOfstateλ.isEmpty()){
					merge.addAll(as[j].statesOfstateλ);
				}
			}
			listWithoutDuplicates = merge.stream().distinct().collect(Collectors.toList());
			System.out.println("listWithoutDuplicates For 0 "+ listWithoutDuplicates.toString());
			nfa_transitions[n] = new Transition(tmp.toString(), '0', listWithoutDuplicates.toString());
			n++;

			if(!isInList(tmp_states, listWithoutDuplicates)){

				tmp_states.addAll(listWithoutDuplicates);
				tmp_states.add(",");
			}
			merge.clear();
			for (j=0;j<as.length;j++){
				if(tmp.contains(as[j].state)){
					merge.addAll(as[j].statesOfstate1);
				}
			}
			System.out.println("Merge : "+ merge.toString());
			for (j=0;j<as.length;j++){
				if(merge.contains(as[j].state) && !as[j].statesOfstateλ.isEmpty()){
					merge.addAll(as[j].statesOfstateλ);
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&");
				}
			}
			listWithoutDuplicates = merge.stream().distinct().collect(Collectors.toList());
			System.out.println("listWithoutDuplicates For 1 "+ listWithoutDuplicates.toString());
			nfa_transitions[n] = new Transition(tmp.toString(), '1', listWithoutDuplicates.toString());
			n++;
			tmp.clear();
			//merge.clear();
			if(!isInList(tmp_states, listWithoutDuplicates)){

				tmp_states.addAll(listWithoutDuplicates);
				tmp_states.add(",");
			}
			merge.clear();
			System.out.println("tmp_states : "+ tmp_states.toString());

		}

		find_FinalStartStates(tmp_states, finals, fStates);
		startStates.clear();
		find_FinalStartStates(tmp_states, start, startStates);
		System.out.println(startStates);
		System.out.println("New NFA States :");
		for(i=0;i<nfa_transitions.length-2;i++){
			System.out.println(nfa_transitions[i].startState + nfa_transitions[i].input + nfa_transitions[i].newState);
		}

		try {
			File myObj = new File("DFA_Output_2.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter("DFA_Output_2.txt");
			myWriter.write("New DFA for imported NFA : "+ "\n");
			myWriter.write(Arrays.toString(l[0]) + "\n");
			myWriter.write(Arrays.toString(l[1]) + "\n");
			myWriter.write(startStates.toString() + "\n");
			myWriter.write(fStates.toString() + "\n");
			for(i=0;i<nfa_transitions.length-2;i++){
				myWriter.write(nfa_transitions[i].startState + nfa_transitions[i].input + nfa_transitions[i].newState + "\n");
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static <T> boolean listEqualsIgnoreOrder(List<String> list1, List<T> list2) {
		return new HashSet<>(list1).equals(new HashSet<>(list2));
	}

	public static boolean isInList(List<String> list1, List<String> list2){

		List <String> tmp = new ArrayList<String>();
		int i,j=0;
		boolean result = false;
		//System.out.println("list :"+ list1);
		//System.out.println(list1.get(6));
		for(i=0;i<list1.size();i++){
			//System.out.println(list1.get(i));
			if(!list1.get(i).equals(","))
				tmp.add(list1.get(i));
			else{
				//System.out.println("temp : " + tmp);
				if(listEqualsIgnoreOrder(tmp, list2))
					j++;
				tmp.clear();
			}
		}
		if(j!=0)
			return true;
		else
			return false;
	}

	public static void find_FinalStartStates(List<String> list1, List<String> list2, List<String> list3){
		int i,j=0;
		List <String> tmp = new ArrayList<String>();
		System.out.println("list** :"+ list1);
		//System.out.println(list1.get(6));
		for(i=0;i<list1.size();i++){
			//System.out.println(list1.get(i));
			if(!list1.get(i).equals(","))
				tmp.add(list1.get(i));
			else if(!Collections.disjoint(tmp, list2)){
				//System.out.println(tmp);
					list3.addAll(tmp);
					list3.add(",");
					tmp.clear();
					//j++;
			}
		}
		//System.out.println(j);

	}

	/*public static void find_finalStates(List<String> list1, List<String> list2, List<String> list3){
		int i,j=0;
		List <String> tmp = new ArrayList<String>();
		System.out.println("list** :"+ list1);
		//System.out.println(list1.get(6));
		for(i=0;i<list1.size();i++){
			//System.out.println(list1.get(i));
			if(!list1.get(i).equals(","))
				tmp.add(list1.get(i));
			else if(!Collections.disjoint(tmp, list2)){
				list3.addAll(tmp);
				list3.add(",");
				tmp.clear();
			}
		}

	}*/



}
