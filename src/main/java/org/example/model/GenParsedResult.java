package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.xml.TypeToGen;

@Setter
@Getter
public class GenParsedResult {

    private int min;
    private int max;
    private TypeToGen type;

    public GenParsedResult(int min, int max, TypeToGen type) {
        this.min = min;
        this.max = max;
        this.type = type;
    }

}
