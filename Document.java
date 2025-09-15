package Enum;

class Document {
    private DocumentState currentState;
 
    public Document() {
        this.currentState = DocumentState.DRAFT; // Default initial state
    }
 
    public DocumentState getCurrentState() {
        return currentState;
    }
 
    public void transitionTo(DocumentState nextState) {
        if (currentState.getAllowedTransitions().contains(nextState)) {
            System.out.println("Transitioning from " + currentState + " to " + nextState);
            currentState = nextState;
        } else {
            System.out.println("❌ Invalid transition: " + currentState + " → " + nextState);
        }
    }
}