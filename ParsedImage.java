import java.awt.*;
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
    public Color[][] colourData;

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

            fileSize = parseBytes(size);
            f.close();

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

            if (bitDepth == 1) {
                parseMonochrome();
            } else if (bitDepth == 24) {
                parseColoured();
            }


            System.out.println("finished parsing!");
        } catch (Exception e) {
            System.out.println("error parsing file!");
        } 

       
    }

    public void parseColoured() {
        BitSet fullBit = BitSet.valueOf(fullContent);
        //System.out.println(fullContent.length);;

        // 954 bytes full, 900 bytes colours 3 bytes RGB then 1 byte padding for each pixel 15*20 * 3(1 unused?? or just skipped)
        int counter = 0;
        colourData = new Color[height][width];
        Color[] before = new Color[height*width];
        for(int i = pixelDataStart; i < fullContent.length; i+=3) {
            int r = parseBytes(fullContent[i]);
            int g = parseBytes(fullContent[i+1]);
            int b = parseBytes(fullContent[i+2]);

            //ystem.out.println("test");
            Color c = new Color(b, g, r);
            //colourData[counter / height][counter % width] = c;
            before[counter] = c;
            counter++;
        }

        for(int x = 0; x < width; x++) {
            for(int y = 0; y< height; y++) {
                colourData[y][x] = before[x + y*width];
            }
        }

        //colourData = result;

        System.out.println(colourData);
    }

    private void parseMonochrome() {
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


        for(int row = 0; row < height; row++) {
            boolean[] thisRow = new boolean[width];
            result[row] = new boolean[width];
            for(int i = 0; i < width; i++) {
                int index = (int) (Math.ceil((double) (row * 32) / 32) * 32 + i);
                thisRow[i] = before[index];
            }
            result[row] = thisRow;
            System.out.println(row + " : " + Arrays.toString(thisRow));
        }
        rawData = result;
    }

    public int parseTotal(int from, int length) {
        int total = 0;
        for (int i = 0; i < length; i++) {
            total += (int) (Math.pow(16, i)*fullContent[from + i]);
        }
        return total;
    }





    public int parseBytes(byte[] input) {
        BitSet set = BitSet.valueOf(input);
        int total = 0;

        for (int bit = (input.length*8) -1; bit >= 0; bit--) {
            if(set.get(bit)) {
                System.out.print(1);
                total += (1 << bit);

            } else {
                System.out.print(0);
            }
       }

        return total;
    }

    public int parseBytes(byte input) {
        BitSet set = BitSet.valueOf(new byte[] {input});
        int total = 0;

        for (int bit = 7; bit >= 0; bit--) {
            if(set.get(bit)) {
                System.out.print(1);
                total += (1 << bit);

            } else {
                System.out.print(0);
            }
        }

        return total;
    }







    
}