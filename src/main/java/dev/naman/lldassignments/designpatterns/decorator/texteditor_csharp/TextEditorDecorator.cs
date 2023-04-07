using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TextEditor
    {
    public abstract class TextEditorDecorator:textEditor
        {
            public textEditor texteditor;
            public TextEditorDecorator(textEditor t)
                {
                    this.texteditor = t;
                }
        }
    }
