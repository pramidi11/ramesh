package logbook;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ReadCsv {

    public List<Task> readTasks(String logSheet) throws URISyntaxException, IOException {
        File file = new File(ClassLoader.getSystemResource(logSheet).toURI());
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .typedSchemaFor(Task.class)
                .withHeader()
                .withColumnReordering(true);
        MappingIterator<Task> taskMappingIterator = new CsvMapper().readerWithTypedSchemaFor(Task.class).with(schema).readValues(file);
        return taskMappingIterator.readAll();
    }

    public List<Worker> readWorkers(String workerSheet) throws IOException, URISyntaxException {
        File file = new File(ClassLoader.getSystemResource(workerSheet).toURI());
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .typedSchemaFor(Task.class)
                .withHeader()
                .withColumnReordering(true);
        MappingIterator<Worker> taskMappingIterator = new CsvMapper().readerWithTypedSchemaFor(Worker.class).with(schema).readValues(file);
        return taskMappingIterator.readAll();
    }

    public List<Product> readProducts(String piesSheet) throws IOException, URISyntaxException {
        File file = new File(ClassLoader.getSystemResource(piesSheet).toURI());
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .typedSchemaFor(Product.class)
                .withHeader()
                .withColumnReordering(true);
        MappingIterator<Product> taskMappingIterator = new CsvMapper().readerWithTypedSchemaFor(Product.class).with(schema).readValues(file);
        return taskMappingIterator.readAll();
    }
}
