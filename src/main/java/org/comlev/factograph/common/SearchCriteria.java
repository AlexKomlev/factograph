package org.comlev.factograph.common;

/**
 * Search criteria.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 17.12.2017
 */
public class SearchCriteria {

    public static final String cnstMatchRu = "он";

    private boolean charsetVerified;

    public boolean isCharsetVerified() {
        return charsetVerified;
    }

    public void setCharsetVerified(boolean charsetVerified) {
        this.charsetVerified = charsetVerified;
    }
}
