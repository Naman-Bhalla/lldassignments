package dev.naman.lldassignments.designpatterns.decorator.texteditor.solution;

abstract class TextEditorDecorator extends TextEditor {
    protected final TextEditor textEditor;

    public TextEditorDecorator(TextEditor textEditor) {
        this.textEditor = textEditor;
    }
}