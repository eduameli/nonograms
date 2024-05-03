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

            System.out.println(f.available());
            //f.skip(pixelDataStart-29);
            f.close();
            f = new FileInputStream(path);
            f.skip(pixelDataStart);

            pixelContent = new byte[fileSize - pixelDataStart];
            f.read(pixelContent);

            //f.read(pixelContent);
            System.out.println("pixels: " + pixelContent.length * 8);
            //System.out.println(pixelContent);

            BitSet bpixels = BitSet.valueOf(pixelContent);
//            System.out.println("PIXELCONTENT BIT CONTENT NOT REVERSED!");
//            for(int i = 0; i < pixelContent.length; i++) {
//                BitSet row = bpixels.get(i * 8, ((i * 8) + 8));
//                for(int x = 7; x >= 0; x--) {
//
//                    boolean bit = row.get(x);
//                    if(bit) {
//                        System.out.print(1);
//                    } else {
//                        System.out.print(0);
//                    }
//
//                }
//                System.out.println();
//            }


            f.close();


            coloured = (bitDepth == 24);




            System.out.println("finished parsing!");
        } catch (Exception e) {
            System.out.println("error parsing file!");
        } 

       
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
        int r = parseBytes(pixelContent[(x*3)+((height-y-1)*3)*width]);
        int g = parseBytes(pixelContent[(x*3)+((height-y-1)*3)*width + 1]);
        int b = parseBytes(pixelContent[(x*3)+((height-y-1)*3)*width + 2]);

        Color c = new Color(b, g, r);
        return c;
    }

    public static boolean getValue(int x, int y) {
        int index = (int) (Math.ceil((double) (y * 4) / 4) * 4 + x/8);
        return isBitSet(pixelContent[index], x%8);

    }


    private static boolean isBitSet(byte in, int pos) {
        return ((in >> (7-pos)) & 1) == 1;
    }









    
}