using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TextEditor
    {
    public class CapitalizeTextDecorator:TextEditorDecorator
        {
            public CapitalizeTextDecorator(textEditor t) : base(t) { }

            public override string getText()
                {
                    TextInfo textInfo = CultureInfo.CurrentCulture.TextInfo;
                    return textInfo.ToTitleCase(texteditor.getText());
                }
            
            
        }
    }
