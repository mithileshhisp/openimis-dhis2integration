package org.beehyv.dhis2openimis.adapter.openimis.pojo.claim.claimItem;

public class Category {
    private String text;

    public Category() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Category [text=" + text + "]";
    }
}
