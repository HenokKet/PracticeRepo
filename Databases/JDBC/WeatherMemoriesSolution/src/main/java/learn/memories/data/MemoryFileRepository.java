package learn.memories.data;

import learn.memories.models.Memory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryFileRepository implements MemoryRepository {
    private final String filePath;
    private final String delimeter = "~";

    public MemoryFileRepository(@Value("${dataFilePath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Memory> findAll() throws DataAccessException{
        List<Memory> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                Memory m = lineToMemory(line);
                if(m!=null){
                    result.add(m);
                }
            }
        } catch (FileNotFoundException e){
            // If the file doesn't exist, no big deal.
            // We'll create it when we add a new memory.
            // No file just means no memories yet.

        } catch (IOException e){
            throw  new DataAccessException("Could not open file path " + filePath, e);
        }
        return result;
    }
    @Override
    public Memory findById(int memoryId) throws DataAccessException{
        List<Memory> all = findAll();
        for(Memory memory: all){
            if(memory.getId() == memoryId){
                return memory;
            }
        }
        return null;
    }

    @Override
    public List<Memory> findShareable(boolean shareable) throws DataAccessException{
        List<Memory> memoryShare = new ArrayList<>();

        for(Memory memory: findAll()){
            if(memory.isShareable() == shareable){
                memoryShare.add(memory);
            }
        }
        return memoryShare;
    }

    @Override
    public Memory add(Memory memory) throws DataAccessException{
        List<Memory> all = findAll();
        int nextId = getNextID(all);
        memory.setId(nextId);
        all.add(memory);
        writeToFile(all);
        return memory;
    }

    @Override
    public boolean update(Memory memory) throws DataAccessException{
        List<Memory> all = findAll();
        for (int i =0; i < all.size(); i++){
            if (all.get(i).getId() == memory.getId()){
                all.set(i, memory);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int memoryId) throws DataAccessException{
        List<Memory> all = findAll();
        for (int i =0; i < all.size(); i++){
            if (all.get(i).getId() == memoryId){
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    //Private Helper Methods
    private void writeToFile(List<Memory> memories) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)){
            for (Memory memory: memories){
                writer.println(memoryToLine(memory));
            }
        } catch (IOException e){
            throw new DataAccessException("Could not write to file", e);
        }
    }

    private Memory lineToMemory(String line){
        String[] fields = line.split(delimeter);
        return new Memory(
            Integer.parseInt(fields[0]),
            fields[1],
            fields[2],
            "true".equals(fields[3])
        );
    }

    private String memoryToLine(Memory memory){
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(memory.getId()).append(delimeter);
        buffer.append(cleanField(memory.getFrom())).append(delimeter);
        buffer.append(cleanField(memory.getContent())).append(delimeter);
        buffer.append(memory.isShareable());
        return buffer.toString();
    }

    private String cleanField(String field){
        return field.replace("/r", "").replace("/n", "");
    }

    private int getNextID(List<Memory> memories){
        int maxID = 0;
        for (Memory memory: memories){
            if(maxID < memory.getId()){
                maxID = memory.getId();
            }
        }
        return maxID+1;
    }
}
