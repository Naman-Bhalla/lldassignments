Implement a decorator pattern for a `TextEditor` class that 
manipulates text. The base class should have a `String getText()` 
method that returns the initial text. Add decorators to 
apply transformations such as uppercase, lowercase, and 
capitalizing the first letter of each word. Write methods 
to apply these transformations in any order and return 
the transformed text.

Following should return `HELLO WORLD`
```
TextEditor textEditor = new UppercaseDecorator(new CapitalizeDecorator(new LowercaseDecorator(new SimpleTextEditor("hELLO wORLD"))));
textEditor.getText();
```