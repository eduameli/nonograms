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
            System.out.println("file: " + fileSize);
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

            //pixelContent = sliceContent(pixelDataStart, fileSize);

            int x_counter = 0;
            int y_counter = 0;


            rawData = new boolean[15][15];
            ByteBuffer bb = ByteBuffer.wrap(fullContent);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            byte yes = bb.get(62);
            BitSet fullBit = BitSet.valueOf(fullContent);
            byte[] little = bb.array();
            byte b1 = (byte) Short.reverseBytes(little[62]);
            byte b2 = fullContent[62];
            for(int i = pixelDataStart; i < fullContent.length; i++) {
                String row_data = new StringBuilder(Integer.toBinaryString(fullContent[i])).reverse().toString();
            }
            int padding_tracker = 0;
            for(int i = pixelDataStart; i < fullContent.length; i++) {

                //String str = String.format("%8s", Integer.toBinaryString(fullContent[i] & 0xFF)).replace(' ', '0');
                //System.out.println("byte: " + str);
                BitSet row = fullBit.get(i*8, i*8 +8 +8 +8 +8);
               // System.out.print("alternative:\t");
                //System.out.println("SIZE: " + row.size());

                    //if(x > 15) {
                    //    continue;                f
                for(int x = 7; x >= 0; x--) {

                    if(x_counter% 32 > 15) {
                        continue;
                    }

                    //if (!row.get(x)) {
                    //    System.out.print("0");
                    //    rawData[x_counter / 15][x_counter % 15] = 0;
                    //} else {
                    //    System.out.print("1");
                    //    rawData[x_counter / 15][x_counter % 15] = 1;
                    //}
                    x_counter++;
                    padding_tracker++;
                    //System.out.print(row.get(x));
                }

                //if(y_counter % 15 == 0) {
                //    i = 62 + (int) Math.ceil((double) x_counter /15)*15;

                //}
                System.out.println();
                //System.out.print("alt: " + fullBit.get(0));
                padding_tracker++;
            }

            System.out.println("ALTERNATIVE PADDING SKIP: ");
            for(int y = 0; y < 15; y++) {
                for(int x = 0; x < 15; x++) {
                    boolean bit = fullBit.get(62*8 + x + y*32);
                    if(bit) {
                        System.out.print(1);
                    } else {
                        System.out.print(0);
                    }
                }
            }
            System.out.println();
            System.out.println("NEXT");

            int counter = 0;
            boolean[][] result = new boolean[15][15];
            boolean[] before = new boolean[(fileSize - pixelDataStart)*8];

            for(int i = pixelDataStart; i < fullContent.length; i++) {
                BitSet row = fullBit.get(i * 8, (i * 8) + 8);
                for(int x = 7; x >= 0; x--) {
                    //if(counter > 14) {
                    //    i = (int) (pixelDataStart + Math.ceil((double) i / 15) * 15);
                    //    counter = 0;
                    //    break;
                    //}

                    boolean bit = row.get(x);
                    before[counter] = bit;
                    //if(bit) {
                    //    System.out.print(1);
                    //} else {
                    //    System.out.print(0);
                    //}
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

            System.out.println();

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
    


    
}