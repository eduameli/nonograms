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

    private static boolean coloured;
    private static int fileSize;
    private static byte[] fullContent;
    public static boolean[][] rawData;

    private static Color[] possibleColours;

    public static Color[][] colourData;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static boolean isColoured() {
        return coloured;
    }


    private static int width;
    private static int height;
    private static int bitDepth;
    private static int pixelDataStart;
    private static byte[] pixelContent;



    public static void loadBMP(String path) {
        try {
            FileInputStream f = new FileInputStream(path);
            f.skip(2);

            byte[] readFileSize = new byte[4];
            byte[] readPixelStart = new byte[4];
            byte[] readWidth = new byte[4];
            byte[] readHeight = new byte[4];
            byte[] readBitDepth = new byte[2];

            // init with size = fileSize - pixelStart
            f.read(readFileSize);

            f.skip(4);
            f.read(readPixelStart);

            f.skip(4);
            f.read(readWidth);
            f.read(readHeight);
            f.skip(2);
            f.read(readBitDepth);

            fileSize = parseBytes(readFileSize);
            pixelDataStart = parseBytes(readPixelStart);
            width = parseBytes(readWidth);
            height = parseBytes(readHeight);
            bitDepth = parseBytes(readBitDepth);

            f.skip(pixelDataStart - 29);

            pixelContent = new byte[fileSize - pixelDataStart];
            f.read(pixelContent);
            //System.out.println(pixelContent);



            f.close();

            f = new FileInputStream(path);
            fullContent = new byte[fileSize];
            f.read(fullContent, 0, fileSize);
            f.close();

            //pixelDataStart = parseTotal(10, 4);

            //width = parseTotal(18, 4);
            //height = parseTotal(22, 4);

            //bitDepth = parseTotal(28, 2);

            if(bitDepth == 1) {
                coloured = false;
                parseMonochrome();
            } else if (bitDepth == 24) {
                coloured = true;
                parseColoured();
            }



            System.out.println("finished parsing!");
        } catch (Exception e) {
            System.out.println("error parsing file!");
        } 

       
    }

    private static void parseColoured() {
        BitSet fullBit = BitSet.valueOf(fullContent);

        int counter = 0;
        colourData = new Color[height+1][width+1];
        Color[] before = new Color[(height+1)*(width+1)];
        System.out.println("color size: " + before.length);
        System.out.println("fileSize: " + fileSize + ", " + fullContent.length);
        System.out.println("start: " + pixelDataStart);

        System.out.println("counter max: " + (fileSize-pixelDataStart)/3);
        // 54
        for(int i = pixelDataStart; i < fileSize; i+=3) {

            int r = parseBytes(fullContent[i]);
            int g = parseBytes(fullContent[i+1]);
            int b = parseBytes(fullContent[i+2]);

            Color c = new Color(b, g, r);

            //Color c = Color.black;
            before[counter] = c;
            counter++;


        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                colourData[y][x] = before[x + y*width];
            }
        }

        //colourData = result;

        //System.out.println(colourData);
    }

    private static void parseMonochrome() {
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


        for(int row = 0; row < height; row++) {
            boolean[] thisRow = new boolean[width];
            result[row] = new boolean[width];
            for(int i = 0; i < width; i++) {
                int index = (int) (Math.ceil((double) (row * 32) / 32) * 32 + i);
                thisRow[i] = before[index];
            }
            result[row] = thisRow;
            //System.out.println(row + " : " + Arrays.toString(thisRow));
        }
        rawData = result;
    }

    private static int parseTotal(int from, int length) {
        int total = 0;
        for (int i = 0; i < length; i++) {
            total += (int) (Math.pow(16, i)*fullContent[from + i]);
        }
        return total;
    }





    private static int parseBytes(byte[] input) {
        BitSet set = BitSet.valueOf(input);
        int total = 0;

        for (int bit = (input.length*8) -1; bit >= 0; bit--) {
            if(set.get(bit)) {
                //System.out.print(1);
                total += (1 << bit);
            }
            //} else {
                //System.out.print(0);
            //}
       }

        return total;
    }

    private static int parseBytes(byte input) {
        BitSet set = BitSet.valueOf(new byte[] {input});
        int total = 0;

        for (int bit = 7; bit >= 0; bit--) {
            if(set.get(bit)) {
                //System.out.print(1);
                total += (1 << bit);

            } //else {
                //System.out.print(0);
            //}
        }

        return total;
    }

    public static Color getColour(int x, int y) {
        return colourData[height-y][x-1];
    }

    public static boolean getValue(int x, int y) {
        return rawData[height -y][x-1];
    }







    
}