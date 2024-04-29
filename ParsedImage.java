import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ParsedImage {
    private String path;
    private boolean loaded = false;

    private int fileSize;
    private byte[] fullContent;
    private byte[] pixelContent;
    public int[][] rawData;

    private int width;
    private int height;
    private int bitDepth;
    private int pixelDataStart;

    public ParsedImage(String path) {
        this.path = path;

        try {
            InputStream f = new FileInputStream(path);
            f.skip(2);
            int fileSize = f.read();
            f.close();

            System.out.println(fileSize);

            f = new FileInputStream(path);
            fullContent = new byte[fileSize];
            f.read(fullContent, 0, fileSize);
            f.close();

            pixelDataStart = parseTotal(10, 4);
            width = parseTotal(18, 4);
            height = parseTotal(22, 4);
            bitDepth = parseTotal(28, 2);

            //rawData = new int[15][15];
            
            //System.out.println("start: " + pixelDataStart);
            pixelContent = sliceContent(pixelDataStart, fileSize);
            //for (byte b : pixelContent) {
            //    System.out.println("byte: " + b);
            //}

            //for (byte b : fullContent) {
            //    System.out.println("byte second try: " + b);
            //}
            int x_counter = 0;
            rawData = new int[15][15];
            //System.out.println("size: " + fullContent.length);
            for(int i = pixelDataStart; i < fullContent.length; i++) {
                System.out.println("index " +i + " : " +Integer.toBinaryString(fullContent[i]));
                //int x_counter = 0;
                for(int j = 0; j < 32; j++) {
                    System.out.print(getBit(fullContent[i], j));
                    rawData[x_counter / 15][x_counter % 15] = getBit(fullContent[i], j);
                    x_counter++;
                }
                System.out.println();
                //y_counter++;

            }

    
            //System.out.println("raw binary data: \n");
            //for(int[] i : rawData) {
            //    System.out.print(i);
            //}


            //System.out.println(getBit((byte) 0x01, 0));
        } catch (Exception e) {
            System.out.println("error parsing file!");
        } 

       
    }

    public int parseTotal(int from, int length) {
        int total = 0;
        for (int i = 0; i < length; i++) {
            //System.out.println("pow: " + Math.pow(16, i) + " num: " + fullContent[from + i]);
            total += Math.pow(16, i)*fullContent[from + i];
        }
        return total;
    }

    public byte[] sliceContent(int from, int to) {
        byte[] arr = new byte[to - from];
        for (int i = 0; i < to - from; i++) {
            arr[i] = fullContent[from + i];
        }
        return arr;
    }

    public int getBit(byte value, int position)
    {
       return (value >> position) & 1;
    }
    

    
}