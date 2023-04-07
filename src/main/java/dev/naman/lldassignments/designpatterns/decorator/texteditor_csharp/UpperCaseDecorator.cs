using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TextEditor
    {
    public class UpperCaseDecorator:TextEditorDecorator
        {
            public UpperCaseDecorator(textEditor t):base(t)
            {

            }


            public override string getText()
                {
                    return texteditor.getText().ToUpper();
                }
        }
    }
