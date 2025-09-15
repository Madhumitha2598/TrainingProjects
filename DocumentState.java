package Enum;

import java.util.EnumSet;
import java.util.Set;
 
enum DocumentState {
    DRAFT,
    REVIEW,
    APPROVED,
    PUBLISHED,
    ARCHIVED;
 
    // Define allowed transitions for each state
    public Set<DocumentState> getAllowedTransitions() {
        switch (this) {
            case DRAFT:
                return EnumSet.of(REVIEW);
            case REVIEW:
                return EnumSet.of(APPROVED, DRAFT); // back to draft if rejected
            case APPROVED:
                return EnumSet.of(PUBLISHED);
            case PUBLISHED:
                return EnumSet.of(ARCHIVED);
            case ARCHIVED:
                return EnumSet.noneOf(DocumentState.class); // No transitions
            default:
                return EnumSet.noneOf(DocumentState.class);
        }
    }
}