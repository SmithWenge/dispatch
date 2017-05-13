package com.dls.function.support.dictionary;

import java.util.List;

public interface IDictionaryManager {
    List<Dictionary> dictionaries(String groupValue);
    void addDataToMemory();
    Dictionary dictionary(int itemKey, String groupValue);
    Dictionary dictionaryChange(String groupValue, String itemValue);
}
