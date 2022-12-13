package org.example.util.impl.title;

import org.example.DAO.QuakeEntry;
import org.example.util.intf.Filter;

public class TitleEndAtPhraseFilter implements Filter {
    private String phrase;

    public TitleEndAtPhraseFilter(String phrase){
        this.phrase = phrase;
    }
    @Override
    public boolean satisfies(QuakeEntry quakeEntry) {
        return (quakeEntry.getInfo().endsWith(phrase));
    }
}
