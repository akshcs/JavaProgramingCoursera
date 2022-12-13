package org.example.util.impl.title;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class TitleStartFromPhraseFilter implements Filter {
    private String phrase;

    public TitleStartFromPhraseFilter(String phrase){
        this.phrase = phrase;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getInfo().startsWith(phrase));
    }
}
