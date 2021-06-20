using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.Json;
using NJsonSchema;
using NJsonSchema.CodeGeneration.CSharp;

namespace A2lParserCodeGenerator
{
    class Asap2CodeGenerator 
    {
        static void Main(string[] args)
        {
            if (args.Length < 1)
            {
                Console.Error.WriteLine("Please specify either the A2lParser JAR or the JSON Schema file.");
                return;
            }

            var fileParameter = args[0];
            var schemaJson = "";
            if (fileParameter.EndsWith(".json"))
            {
                schemaJson = File.ReadAllText(fileParameter);
            }
            else if (fileParameter.EndsWith(".jar"))
            {
                Console.WriteLine("Generate code from A2lParser JAR...");
                Process p = new Process();
                p.StartInfo.FileName = "java";
                p.StartInfo.ArgumentList.Add("-jar");
                p.StartInfo.ArgumentList.Add(fileParameter);
                p.StartInfo.ArgumentList.Add("-jsc");
                p.StartInfo.RedirectStandardOutput = true;

                Console.WriteLine("Generating JSON schema...");
                p.Start();
                schemaJson = p.StandardOutput.ReadToEnd();
                p.WaitForExit();
            }
            else
            {
                Console.Error.WriteLine("Please specify either the A2lParser JAR or the JSON Schema file.");
                return;
            }

            Console.WriteLine("Parsing schema...");
            var schema = JsonSchema.FromJsonAsync(schemaJson).Result;
            schema.Title = schema.Id.Split(":").Last();

            Console.WriteLine("Generating code...");
            var generator = new CSharpGenerator(schema);
            generator.Settings.Namespace = "ALenzen.A2l";
            generator.Settings.GenerateJsonMethods = true;
            var file = generator.GenerateFile();
            File.WriteAllText("A2l.cs", file);

            Console.WriteLine("completed!");
        }
    }
}
