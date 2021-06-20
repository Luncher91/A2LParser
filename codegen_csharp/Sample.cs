using System;
using System.Diagnostics;
using System.Linq;
using ALenzen.A2l;

namespace A2LParserCsharpSample
{
    class Sample
    {
        static Asap2File parseA2lFile(string jarPath, string a2lPath)
        {
            Process p = new Process();
            p.StartInfo.FileName = "java";
            p.StartInfo.ArgumentList.Add("-jar");
            p.StartInfo.ArgumentList.Add(jarPath);
            p.StartInfo.ArgumentList.Add("-mj");
            p.StartInfo.ArgumentList.Add("-a2l");
            p.StartInfo.ArgumentList.Add(a2lPath);
            p.StartInfo.RedirectStandardOutput = true;
            p.Start();
            var jsonOutput = p.StandardOutput.ReadToEnd();
            p.WaitForExit();

            return Asap2File.FromJson(jsonOutput);
        }

        static void Main(string[] args)
        {
            Asap2File a2l = parseA2lFile(args[0], args[1]);
            foreach (var item in a2l.Project.Modules.First().Characteristics)
            {
                Console.WriteLine(item.Name);
            }
        }
    }
}