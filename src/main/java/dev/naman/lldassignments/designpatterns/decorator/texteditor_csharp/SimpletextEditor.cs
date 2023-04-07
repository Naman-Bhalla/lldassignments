using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TextEditor
    {
        public class SimpletextEditor:textEditor
        {
            private string text;
            public SimpletextEditor(string t)
            {
                text = t;
            }


            public override string getText()
                {
                    return this.text;
                }

            public void setText(string text)
                {
                    this.text = text;
                }
        }
    }
