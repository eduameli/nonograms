import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class ParsedImage {

    private static boolean coloured;
    public static int ignoredColour;

    public static ArrayList<Integer> possibleColours = new ArrayList<>();


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
    private static byte[] pixelContent;


    // Load all relevant data from the .bmp file stored at the specified path.
    // file size, pixel data start location, image dimensions(height & width), bit depth, pixel data(bytes from pixel data start location to EOF)
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

            int fileSize = parseBytes(readFileSize);
            int pixelDataStart = parseBytes(readPixelStart);
            width = parseBytes(readWidth);
            height = parseBytes(readHeight);
            int bitDepth = parseBytes(readBitDepth);
            f.close();

            f = new FileInputStream(path);
            f.skip(pixelDataStart);
            pixelContent = new byte[fileSize - pixelDataStart];
            f.read(pixelContent);
            f.close();


            if(bitDepth == 0) {
                coloured = false;
            } else if(bitDepth == 24) {
                coloured = true;
                ignoredColour =  -1;
            }


        } catch (Exception e) {
            System.out.println("error parsing file!");
        } 

       
    }





    // Returns result of reading byte array as a little endian number
    private static int parseBytes(byte[] input) {
        BitSet set = BitSet.valueOf(input);
        int total = 0;

        for (int bit = (input.length*8) -1; bit >= 0; bit--) {
            if(set.get(bit)) {
                total += (1 << bit);
            }
       }

        return total;
    }

    // Returns result of reading byte as a little endian number
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

    // Returns the stored colour directly from the source byte array, calculated on-demand from each ColouredTile's constructor
    public static int getColour(int x, int y) {
        int r = parseBytes(pixelContent[(x*3)+((height-y-1)*3)*width]);
        int g = parseBytes(pixelContent[(x*3)+((height-y-1)*3)*width + 1]);
        int b = parseBytes(pixelContent[(x*3)+((height-y-1)*3)*width + 2]);
        Color c = new Color(b, g, r);

        if(!possibleColours.contains(c.getRGB())) {
            possibleColours.add(c.getRGB());
        }
        return c.getRGB();

    }

    // Returns the stored boolean directly from the source byte array, calculated on-demand from each MonochromeTile's constructor
    public static boolean getBoolean(int x, int y) {
        int index = (int) (Math.ceil((double) ((height-y-1) * 4) / 4) * 4 + x/8);
        return isBitSet(pixelContent[index], x%8);
    }


    // Read if a specific bit from a byte is 1 or 0
    private static boolean isBitSet(byte in, int pos) {
        return ((in >> (7-pos)) & 1) == 1;
    }

    // Reads the corresponding boolean slice for a MonochromeInfoTile at position x, y
    public static int[] getBooleanSlice(int x, int y) {
        int[] slice = new int[0];

        if(x == 0 && y == 0) {
            return slice;
        }

        if (y == 0) {
            slice = new int[height];
            for(int i = 0; i < height; i++) {
                slice[i] = getBoolean(x-1, y+i) ? 1 : 0;
            }
        } else if (x == 0) {
            slice = new int[width];
            for(int i = 0; i < width; i++) {
                slice[i] = getBoolean(x+i, y-1) ? 1 : 0;
            }
        }
        return slice;
    }

    // Reads the corresponding colour slice for a ColouredInfoTile at position x, y
    public static int[] getColourSlice(int x, int y) {
        int[] slice = new int[0];

        if(x == 0 && y == 0) {
            return slice;
        }

        if (y == 0) {
            slice = new int[height];
            for(int i = 0; i < height; i++) {
                slice[i] = getColour(x-1, y+i);
            }
        } else if (x == 0) {
            slice = new int[width];
            for(int i = 0; i < width; i++) {
                slice[i] = getColour(x+i, y-1);
            }
        }
        return slice;
    }
}