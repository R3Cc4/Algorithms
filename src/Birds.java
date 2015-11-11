import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Vamsi on 11/9/2015.
 */
public class Birds {
   static int[][] pixels = new int[726408][19];
    static int fileNumber =0;
    public static void LoadData(Path filePath){

        BufferedReader reader = null;
        File readFile = new File(String.valueOf(filePath));
        try {
            reader = new BufferedReader(new FileReader(readFile));
            String text = null;
            int i = 0;
            while((text=reader.readLine())!=null){
                if(i>2){
                    pixels[i-3][fileNumber]=Integer.parseInt(text);
                }
                i++;
            }
            fileNumber++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sortPixels(){
        int j = pixels.length;
        int rowSize = 17;


        for(int i =0;i<j;i++){
            // sort the row and take the 13th column and store in the output.

            int[] tempArray = new int[17];
            for(int m=0;m<17;m++){
                tempArray[m]= pixels[i][m];
            }
            Arrays.sort(tempArray);
            int median = tempArray[13];
            pixels[i][18]= median;


        }

    }
    public static void main(String args[])  {
        int fileNumber=0;
        String FolderPath = "H:\\NJIT\\Fall 2015\\DataStructures & Algos CS610\\Problem sets\\Birds";
        try {
            Files.walk(Paths.get("H:\\NJIT\\Fall 2015\\DataStructures & Algos CS610\\Problem sets\\Birds")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {

                  LoadData(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

         File filePath = new File("H:\\NJIT\\Fall 2015\\DataStructures & Algos CS610\\Problem sets\\Birds\\clean.ppm");
         int j = pixels.length;
        sortPixels();
        System.out.println(" **********Length ==" + j + "*******" + pixels[726407][17]);
        BufferedWriter writer = null;
        try {
            filePath.createNewFile();
             writer = new BufferedWriter(new FileWriter(filePath));
            int k =0;
            String value = new Integer(pixels[722360][3]).toString();
           // writer.write(value+"***** \n");
            writer.write("P3\n" +
                    "684 354\n" +
                    "255\n");
            while(k<j) {

                value = new Integer(pixels[k][18]).toString();
                writer.write(value+"\n");

                k++;
            }


        }catch (Exception e){
e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
