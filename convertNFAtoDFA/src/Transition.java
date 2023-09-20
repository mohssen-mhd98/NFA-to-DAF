
public class Transition {
	
	 public final String startState;
	    public final char input;
	    public final String newState;

	    /**
	     * Constructs a new transition.
	     * @param startState The state to start from.
	     * @param input The input on which the transition should trigger.
	     * @param newState The state to transition to.s
	     */
	    public Transition(String startState, char input, String newState) {
	        this.startState = startState;
	        this.input = input;
	        this.newState = newState;
	       
	    }

}
