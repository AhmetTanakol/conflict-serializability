import java.util.Stack;
 
public class TopoCycle
{
    private Stack<Integer> stack;
 
    public TopoCycle() 
    {
        stack = new Stack<Integer>();
    }
 
    public boolean checkCycle(int adjacency_matrix[][], int source)
    {
        int number_of_nodes = adjacency_matrix[source].length - 1;
        int[] topological_sort = new int [number_of_nodes + 1];
        int pos = 1;
        int j;
        boolean cycle = false;
 
 
        int visited[] = new int[number_of_nodes + 1];
        int element = source;
        int i = source;
        visited[source] = 1;
        stack.push(source);
 
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            while (i <= number_of_nodes)
            {
                if (adjacency_matrix[element][i] == 1 && visited[i] == 1)
                {
                    if (stack.contains(i))
                    {
                        System.out.println("false");
                        cycle = true; 
                        return cycle;
                    }
                }
                if (adjacency_matrix[element][i] == 1 && visited[i] == 0)
                {
                    stack.push(i);
                    visited[i] = 1;
                    element = i;
                    i = 1;
                    continue;
                }
                i++;
            }
            j = stack.pop();	
            topological_sort[pos++] = j;
            i = ++j;
        }
        System.out.println("true");
        return cycle;
    }
}