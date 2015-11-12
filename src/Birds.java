/**
 * Created by Vamsi on 11/9/2015.
 */
import java.io.*;
import java.util.Arrays;
public class Birds {
    static final String inputFolderPath = "H:\\NJIT\\Fall 2015\\DataStructures & Algos CS610\\Problem sets\\Birds";
    static final String saveFilePath = inputFolderPath + "\\clean.ppm";
    static int fileSize = 0;
    static int[][] pixels = new int[726408][19];
    static int fileNumber =0;
    static String[] configArray = new String[3];
    //This function Loads the Image Data from a filepath given in as parameter into the Pixels 2-D Array for Processing.
    public static void LoadData(String filePath) {
        BufferedReader reader = null;
        File readFile = new File(inputFolderPath + "\\" + filePath);
        try {
            reader = new BufferedReader(new FileReader(readFile));
            String text = null;
            int i = 0;
            while((text=reader.readLine())!=null){
                if(i>2){
                    pixels[i-3][fileNumber]=Integer.parseInt(text);
                } else {
                    configArray[i] = text;
                }
                i++;
            }
            fileSize = i;
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
    // This function Sorts the pixels in the 2D Array row wise. I.e, each row will be sorted making the 13th column as pivot.
    //hence all the elements to the left of 13th column are less and after it are more. I tried other values but 13th has the
    //most cleanest image!!
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
            int targetRow = ((int) Math.ceil((Double) (rowSize * .75)));
            int median = tempArray[targetRow];
            pixels[i][18] = median; // storing the required value in a new column which is at the end.
            // since we have 17 images the result is stored in 18th column
        }
    }
    public static void main(String args[])  {
        //This code will open the files in the specified path.
        File[] files = new File(inputFolderPath).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        for (File file : files) {
            if (file.isFile() && file.getName().contains("birds")) {
                LoadData(file.getName());
            }
        }
        int j = pixels.length;
        sortPixels();// sort all the rows in the pixel 2D array.
        //once the array is sorted write the resultant image pixel data into the Clear.ppm file where the image will be clean.
        BufferedWriter writer = null;
        File savefile = new File(saveFilePath);
        try {
            savefile.createNewFile();
            writer = new BufferedWriter(new FileWriter(savefile));
            String value = null;
            writer.write("P3\n" +
                    "684 354\n" +
                    "255\n");
            int k = 0;
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