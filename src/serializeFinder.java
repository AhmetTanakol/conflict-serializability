import java.util.ArrayList;
import java.util.Arrays;

public class serializeFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String history = "w 1 x r 2 x w 2 y r 3 y w 3 z r 1 z";
		history = history.replaceAll("\\s","").toLowerCase();
		
		//split the history string into transactions
		String[] transactionsList = history.split("(?<=\\G...)");
		
		//create an array to keep transactions as array elements
		//each array element has another array element represents an operation
		ArrayList<String[]> historyArray = new ArrayList<String[]>();
		
		//use this arraylist to keep edges of the graph 
		//to find if history is serializable or not by checking the graph
		ArrayList<String[]> linkedTransactions = new ArrayList<String[]>();
		
		//create operations array for each transaction
		//operation array consists of transaction number, operation and data 
		//e.g. w 1 x = [1, w, x]
		for(int i = 0; i < transactionsList.length; i++) {
			String[] transactionInfo = new String[3]; 
			transactionInfo[0] = String.valueOf(transactionsList[i].charAt(1));
			transactionInfo[1] = String.valueOf(transactionsList[i].charAt(0)); 
			transactionInfo[2] = String.valueOf(transactionsList[i].charAt(2));
			historyArray.add(transactionInfo);
		}
		
		for (int i = 0; i < historyArray.size(); i++) {
			String transactionNo = historyArray.get(i)[0];
			String operation = historyArray.get(i)[1];
			String data = historyArray.get(i)[2];
			
			//read the rest of the transaction operations
			innerloop:
			for (int j = i + 1; j < historyArray.size(); j++) {
				//current transaction number and compared transaction number have to be different
				if (!transactionNo.equals(historyArray.get(j)[0])) {
					//both transaction operations data have to be same
					if (data.equals(historyArray.get(j)[2])) {
						String[] link = new String[2];
						//if current operation is write, then both writing and reading are acceptable 
						if (operation.equals("w")) {
							link[0] = transactionNo; 
							link[1] = historyArray.get(j)[0];
							linkedTransactions.add(link);
							//we fond a link, for simplicity we do not continue for the rest of the operations of transactions
							break innerloop;
						}
						//if current operation is read, then only writing is acceptable 
						if (operation.equals("r")) {
							if (historyArray.get(j)[1].equals("w")) {
								link[0] = transactionNo; 
								link[1] = historyArray.get(j)[0];
								linkedTransactions.add(link);
								//we found a link, for simplicity we do not continue for the rest of the operations of transactions
								break innerloop;	
							}
						}
					}
				}
			}
		}
		/*
		//print out edges between nodes e.i for T1 to T2 [1,2] 
		for (int z = 0; z < linkedTransactions.size(); z++) {
			System.out.println(Arrays.toString(linkedTransactions.get(z)));
		}*/
		
		//control the graph (acyclic check), check if the starting point of the graph is same with the ending point
		//if yes so it is not serializable otherwise it is serializable
		int sizeOfLinks = linkedTransactions.size();
		if (sizeOfLinks > 0) {
			if (sizeOfLinks == 1) {
				System.out.println("true");
			} else {
				if (linkedTransactions.get(0)[0].equals(linkedTransactions.get(sizeOfLinks - 1)[1])) {
					System.out.println("false");
				} else {
					System.out.println("true");
				}
			}
		} else {
			System.out.println("false");
		}
	}

}
