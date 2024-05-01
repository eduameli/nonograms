import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
            int y_counter = 0;


            //int row_padding_counter = 0;
            //int rows_done = 0;

            rawData = new int[50][50];
            //ByteBuffer buffer = ByteBuffer.allocate(fileSize).wrap(fullContent).order(ByteOrder.LITTLE_ENDIAN);
            //byte[] arr = buffer.array();
            //System.out.println("byte array!");
            BitSet fullBit = BitSet.valueOf(fullContent);
            for(int i = pixelDataStart; i < fullContent.length; i++) {
                //System.out.println(new StringBuilder(Integer.toBinaryString(fullContent[i])).reverse().toString());
                String row_data = new StringBuilder(Integer.toBinaryString(fullContent[i])).reverse().toString();

                BitSet bite = fullBit.get(i*8, (i+1)*8);
                for(int x = 0; x < bite.size(); x++) {
                    if (bite.get(x)) {
                        System.out.print("1");
                    } else {
                        System.out.print("0");
                    }
                    //System.out.print(bite.get(x));
                }
                //new StringBuilder(Integer.toBinaryString(arr[i])).reverse().toString()
            }
            //System.out.println();

            //BitSet data = BitSet.valueOf(fullContent);
            //for(int i = pixelDataStart; i < data.size(); i++) {
            //    System.out.print(data.get(i));
            //}


            //System.out.println("bit set size: " + data.size());
            //for(boolean b : data) {
            //    System.out.println(b);
            //}
            for(int i = pixelDataStart; i < fullContent.length; i++) {
            //    System.out.println("index " +i + " : " +String.format("%8s", Integer.toBinaryString(fullContent[i] & 0xFF)).replace(' ', '0')+ " : " + fullContent[i]);
                String str = String.format("%8s", Integer.toBinaryString(fullContent[i] & 0xFF)).replace(' ', '0');
            //    System.out.print("bit by byt: ");
                //x_counter = 0;
                //for(int j = 0; j < 8; j++) {
                    
                    //System.out.print(getBit(fullContent[i], j));
                    //int value = Integer.valueOf(String.format("%8s", Integer.toBinaryString(fullContent[i] & 0xFF)).replace(' ', '0').substring(j, j+1));
                    //rawData[x_counter / 15][x_counter % 15] = getBit(fullContent[i], j);
                //    int value = str.charAt(j);
                //    rawData[x_counter / 15][x_counter % 15] = value;
                    //System.out.print(fullContent[i][j]);
                    //for(int g = 1 ; g <= fullContent[i] ; g<<=1) {
                    //    int bit = fullContent[i]&g;
                    //    System.out.print(bit);
                    //} 

                  
                //}
                //y_counter++;
                //int x_counter = 0;
                //for(int j = 0; j < 32; j++) {
                //    System.out.print(getBit(fullContent[i], j));
                //    rawData[x_counter / 15][x_counter % 15] = getBit(fullContent[i], j);
                //    x_counter++;
                //}
                //System.out.println();
                //y_counter++;



            }
 
    
            //System.out.println("raw binary data: \n");
            //for(int[] i : rawData) {
            //    System.out.print(i);
            //}
            System.out.println("finished parsing!");

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