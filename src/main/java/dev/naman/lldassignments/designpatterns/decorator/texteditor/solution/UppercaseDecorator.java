package dev.naman.lldassignments.designpatterns.decorator.texteditor.solution;

class UppercaseDecorator extends TextEditorDecorator {
    public UppercaseDecorator(TextEditor textEditor) {
        super(textEditor);
    }

    @Override
    public String getText() {
        return textEditor.getText().toUpperCase();
    }
}