package ru.job4j.design.srp;

import java.util.List;
import java.util.Objects;

/**
 * class for data containing
 * @author nmardakov
 * @since 21.03.20
 */
public class Column {
    private String headLine;
    private List<String> elements;

    public Column(String headLine, List<String> elements) {
        this.headLine = headLine;
        this.elements = elements;
    }

    public String getHeadLine() {
        return headLine;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Column column = (Column) o;
        return Objects.equals(headLine, column.headLine)
                && Objects.equals(elements, column.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headLine, elements);
    }
}
