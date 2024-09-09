package Service;

import ServiceInteface.IndexManagerI;

import java.io.*;

public class IndexManager implements IndexManagerI {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int currentIndex = 1;

    public IndexManager()
    {
        try {
            loadIndex();
        } catch (FileNotFoundException e)
        {
            System.out.println("Index file not found. Starting with index 0.");
        }
    }

    public void loadIndex() throws FileNotFoundException
    {
        File file = new File("index.txt");

        if (file.exists())
        {
            try
            {
                bufferedReader = new BufferedReader(new FileReader(file));

                String line = bufferedReader.readLine();

                if (line != null)
                    currentIndex = Integer.parseInt(line.trim());

                bufferedReader.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveIndex() throws IOException
    {
        this.bufferedWriter = new BufferedWriter(new FileWriter("index.txt"));
        bufferedWriter.write(String.valueOf(currentIndex));

        bufferedWriter.close();
    }

    public void increment()
    {
        this.currentIndex++;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
