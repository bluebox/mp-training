package randomAcessFile;


import java.io.*;
import java.util.*;

public class EmployeeDataWriter {

    public static Map<Integer, Long> convertTxtToBinary(String txtPath, String datPath) throws IOException {
        Map<Integer, Long> indexMap = new TreeMap<>();
        try (
            BufferedReader reader = new BufferedReader(new FileReader(txtPath));
            RandomAccessFile raf = new RandomAccessFile(datPath, "rw")
        ) {
            raf.setLength(0); 

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 4) continue;

                int id = Integer.parseInt(parts[0]);
                double salary = Double.parseDouble(parts[1]);
                String fname = parts[2];
                String lname = parts[3];

                long position = raf.getFilePointer();
                indexMap.put(id, position);

                raf.writeInt(id);
                raf.writeDouble(salary);
                raf.writeInt(fname.length());
                raf.writeBytes(fname);
                raf.writeInt(lname.length());
                raf.writeBytes(lname);
            }
        }
        return indexMap;
    }
}
