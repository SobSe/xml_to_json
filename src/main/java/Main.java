import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "data.xml";
        String outputFileName = "data.json";

        ReaderXML reader = new ReaderXML();
        List<Employee> list = reader.parseXML(inputFileName);

        WriterJSON writer = new WriterJSON();
        String json = writer.listToJSON(list);
        writer.writeJSON(outputFileName, json);
    }
}
