using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TextEditor;

namespace TesttextEditor
    {
    [TestClass]
    public class TextEditorTester
        {
            [TestMethod]
            public void testUppercaseDecorato()
            {
                textEditor textEditor = new UpperCaseDecorator(new SimpletextEditor("Hello World"));
                Assert.AreEqual("HELLO WORLD", textEditor.getText());
            }
            [TestMethod]
            public void testLowercaseDecorator() {
                textEditor textEditor = new LowerCaseDecorator(new SimpletextEditor("Hello World"));
                Assert.AreEqual("hello world", textEditor.getText());
            }

            [TestMethod]
            public void testCapitalizeDecorator() {
                textEditor textEditor = new CapitalizeTextDecorator(new SimpletextEditor("hello world"));
                Assert.AreEqual("Hello World", textEditor.getText());
            }

            [TestMethod]
            public void testCombinedDecorators() {
                textEditor textEditor = new UpperCaseDecorator(new CapitalizeTextDecorator(new LowerCaseDecorator(new SimpletextEditor("hELLO wORLD"))));
                Assert.AreEqual("HELLO WORLD", textEditor.getText());
            }

        }
    }
