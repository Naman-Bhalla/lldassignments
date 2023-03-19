package dev.naman.lldassignments.designpatterns.decorator.texteditor.problem;

import dev.naman.lldassignments.designpatterns.decorator.texteditor.solution.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextEditorTest {

    @Test
    void testUppercaseDecorator() {
        TextEditor textEditor = new UppercaseDecorator(new SimpleTextEditor("Hello World"));
        assertEquals("HELLO WORLD", textEditor.getText());
    }

    @Test
    void testLowercaseDecorator() {
        TextEditor textEditor = new LowercaseDecorator(new SimpleTextEditor("Hello World"));
        assertEquals("hello world", textEditor.getText());
    }

    @Test
    void testCapitalizeDecorator() {
        TextEditor textEditor = new CapitalizeDecorator(new SimpleTextEditor("hello world"));
        assertEquals("Hello World", textEditor.getText());
    }

    @Test
    void testCombinedDecorators() {
        TextEditor textEditor = new UppercaseDecorator(new CapitalizeDecorator(new LowercaseDecorator(new SimpleTextEditor("hELLO wORLD"))));
        assertEquals("HELLO WORLD", textEditor.getText());
    }
}
