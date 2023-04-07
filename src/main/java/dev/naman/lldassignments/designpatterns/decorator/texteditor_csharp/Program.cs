using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TextEditor
    {
    class Program
        {
        static void Main(string[] args)
            {    
                textEditor t = new CapitalizeTextDecorator(new SimpletextEditor("heLLO worLd"));
                Console.WriteLine(t.getText());
                textEditor t1 =new UpperCaseDecorator(new SimpletextEditor("hello world!"));
                Console.WriteLine(t1.getText());
                textEditor t2 = new LowerCaseDecorator(new SimpletextEditor("HEllO WORLD!"));
                Console.WriteLine(t2.getText());
                textEditor t3 = new CapitalizeTextDecorator(new LowerCaseDecorator(new SimpletextEditor("HEllO WORLD!")));
                Console.WriteLine(t3.getText());
                textEditor t4 = new CapitalizeTextDecorator(new UpperCaseDecorator(new SimpletextEditor("HEllO WORLD!")));
                Console.WriteLine(t4.getText());
                Console.ReadLine();
            }
        }
    }
