package dev.naman.lldassignments.designpatterns.decorator.texteditor.solution;

class CapitalizeDecorator extends TextEditorDecorator {
    public CapitalizeDecorator(TextEditor textEditor) {
        super(textEditor);
    }

    @Override
    public String getText() {
        String[] words = textEditor.getText().split("\\s+");
        StringBuilder capitalizedText = new StringBuilder();
        for (String word : words) {
            if (capitalizedText.length() > 0) {
                capitalizedText.append(" ");
            }
            capitalizedText.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
        }
        return capitalizedText.toString();
    }
}