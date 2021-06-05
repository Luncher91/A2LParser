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
        static void collectPathIds(JsonElement root, string currentPath, Dictionary<string, string> map)
        {
            if (root.ValueKind == JsonValueKind.Object)
            {
                foreach (var item in root.EnumerateObject())
                {
                    if (item.Value.ValueKind == JsonValueKind.String && item.Name == "id")
                    {
                        map.Add(item.Value.GetString(), currentPath);
                    }
                    else
                    {
                        collectPathIds(item.Value, currentPath + "/" + item.Name, map);
                    }
                }
            }
        }

        static string ToJsonString(JsonDocument jdoc)
        {
            using (var stream = new MemoryStream())
            {
                Utf8JsonWriter writer = new Utf8JsonWriter(stream, new JsonWriterOptions { Indented = true });
                jdoc.WriteTo(writer);
                writer.Flush();
                return Encoding.UTF8.GetString(stream.ToArray());
            }
        }

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

            Console.WriteLine("Preparing JSON schema...");
            var doc = JsonDocument.Parse(schemaJson);
            var idDict = new Dictionary<string, string>();
            collectPathIds(doc.RootElement, "#", idDict);

            var replacedJson = ToJsonString(doc);
            foreach (var item in idDict)
            {
                replacedJson = replacedJson.Replace("\"$ref\": \"" + item.Key + "\"", "\"$ref\": \"" + item.Value + "\"");
            }

            Console.WriteLine("Parsing schema...");
            var schema = JsonSchema.FromJsonAsync(replacedJson).Result;
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
