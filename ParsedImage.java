import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ParsedImage {
    private String path;
    private boolean loaded = false;

    private int fileSize;
    private byte[] fullContent;
    private byte[] pixelContent;
    public boolean[][] rawData;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int width;
    private int height;
    private int bitDepth;
    private int pixelDataStart;

    public ParsedImage(String path) {
        this.path = path;

        try {
            InputStream f = new FileInputStream(path);
            f.skip(2);
            byte[] size = new byte[4];
            f.read(size);
            System.out.println(size);

            System.out.println(parseBytes(size));

            //int fileSize = f.read();
            int fileSize = parseBytes(size);
            System.out.println("size!!!: " + fileSize);
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

            // ^^^ parse data from .bmp file!


            rawData = new boolean[height][width];
            BitSet fullBit = BitSet.valueOf(fullContent);


            int counter = 0;
            boolean[][] result = new boolean[height][width];
            boolean[] before = new boolean[(fileSize - pixelDataStart)*8];

            for(int i = pixelDataStart; i < fullContent.length; i++) {
                BitSet row = fullBit.get(i * 8, ((i * 8) + 8));
                for(int x = 7; x >= 0; x--) {

                    boolean bit = row.get(x);
                    before[counter] = bit;
                    counter++;
                }
            }
            System.out.println();
            System.out.println("BEFORE ARRAY: ");
            for(boolean b : before) {
                if (b) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
                //System.out.print(b);
            }


            for(int row = 0; row < height; row++) {
                boolean[] thisRow = new boolean[width];
                // this row needs to hold before[Math.ceil(row*width / 32) * 32, Math.ceil(row*width / 32) * 32 + width]
                result[row] = new boolean[width];
                for(int i = 0; i < width; i++) {
                    int index = (int) (Math.ceil((double) (row * 32) / 32) * 32 + i);
                    thisRow[i] = before[index];
                }
                result[row] = thisRow;
                System.out.println(row + " : " + Arrays.toString(thisRow));
            }
            rawData = result;


            System.out.println("finished parsing!");
        } catch (Exception e) {
            System.out.println("error parsing file!");
        } 

       
    }

    public int parseTotal(int from, int length) {
        int total = 0;
        for (int i = 0; i < length; i++) {
            //System.out.println("pow: " + Math.pow(16, i) + " num: " + fullContent[from + i]);
            total += (int) (Math.pow(16, i)*fullContent[from + i]);
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

    public int parseBytes(byte[] input) {
        BitSet set = BitSet.valueOf(input);
        int total = 0;


        //System.out.println();
        for (int bit = (input.length*8) -1; bit >= 0; bit--) {
        //    //System.out.print(set.get(bit));
            if(set.get(bit)) {
                System.out.print(1);
                total += (1 << bit);

            } else {
                System.out.print(0);
            }
       }
        //System.out.println();
        //System.out.println("ended!");

        return total;
    }





    
}