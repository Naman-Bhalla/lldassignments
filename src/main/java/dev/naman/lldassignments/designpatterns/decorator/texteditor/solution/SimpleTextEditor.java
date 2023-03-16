package dev.naman.lldassignments.designpatterns.decorator.texteditor.solution;

class SimpleTextEditor extends TextEditor {
    private final String text;

    public SimpleTextEditor(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}