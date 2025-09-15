package Enum;

public class Main {
 
	public static void main(String[] args) {
        Document doc = new Document();
 
        doc.transitionTo(DocumentState.REVIEW);     // Valid
        doc.transitionTo(DocumentState.PUBLISHED);  // Invalid
        doc.transitionTo(DocumentState.APPROVED);   // Valid (from REVIEW)
        doc.transitionTo(DocumentState.PUBLISHED);  // Valid
        doc.transitionTo(DocumentState.ARCHIVED);   // Valid
        doc.transitionTo(DocumentState.REVIEW);     // Invalid (archived)
    }
}
