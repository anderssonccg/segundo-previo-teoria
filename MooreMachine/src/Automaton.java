
import java.util.Stack;


/**
 *
 * @author Andersson
 */
public class Automaton {
    
    private String result;
    private boolean isValid;
    
    public Automaton(){
        
    }
    
    private void validate(String input){
        if(input == null || input.equals("")) throw new RuntimeException("Error. Debe digitar una cadena");
    }
    
    public String solveFirstProblem(String input){
        this.validate(input);
        this.solveMooreMachine(input);
        return this.result;
    }
    
    public boolean solveSecondProblem(String input){
        this.validate(input);
        this.solveFirstAPD(input);
        return this.isValid;
    }
    
    public boolean solveThirdProblem(String input){
        this.validate(input);
        this.solveSecondAPD(input);
        return this.isValid;
    }
    
    public boolean solveFourthProblem(String input){
        this.validate(input);
        this.solveThirdAPD(input);
        return this.isValid;
    }
    
    private void solveFirstAPD(String input){
        this.isValid = true;
        Stack<Character> st = new Stack();
        int flag = 0, visitOne = 0;
        for(int i = 0; i < input.length() && this.isValid; i++){
            if(input.charAt(i) == '0'){
                if(visitOne == 1) this.isValid = false;
                st.push('0');
            }else{
                visitOne = 1;
                flag++;
                if(!st.isEmpty()){
                    if(flag == 2){
                        flag = 0;
                        st.pop();
                    }
                }else{
                    this.isValid = false;
                }
            }
        }
        if(!st.isEmpty()) this.isValid = false;
    }
    
    private void solveSecondAPD(String input) {
        this.isValid = true;
        Stack<Character> st = new Stack();
        if(input.length()%2 == 0){
            for(int i = 0; i < input.length() / 2; i++){
                st.push(input.charAt(i));
            }
            for (int i = input.length() / 2; i < input.length() && this.isValid; i++) {
                if(!st.isEmpty() && st.peek() == input.charAt(i)){
                    st.pop();
                }else{
                    this.isValid = false;
                }
            }
        }else{
            this.isValid = false;
        }
    }

    private void solveThirdAPD(String input) {
        this.isValid = true;
        char c;
        Stack<Character> st = new Stack();
        for(int i = 0;i < input.length(); i++){
            c = input.charAt(i);
            if(c == '(' || c == '['){
                st.push(c);
            }else{
                if(!st.isEmpty() && c == ')' && st.peek() == '('){
                    st.pop();
                }else if(!st.isEmpty() && c == ']' && st.peek() == '['){
                    st.pop();
                }else{
                    if(c != ' ')this.isValid = false;
                }
            }
        }
        if(this.isValid && st.isEmpty()) this.isValid = true;
    }
    
    private void solveMooreMachine(String input){
        this.result = "";
        for(int i = 1; i < input.length(); i++){
            if(input.charAt(i) == '1' && input.charAt(i-1) == '0'){
                this.result += 'a';
            }
        }
    }
}
