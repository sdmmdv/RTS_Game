package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Map class:
 * Contains the attributes and methods needed to use the different maps.
 */
public class Map {
    public final int PIXEL_SIZE = 32;
    public final int MAP_WIDTH = 26;
    public final int MAP_HEIGHT = 20;
    public int pathLength;
    public int checkpointsLength;
    private int[][] grids;
    private int[][] pathTilesPixel;
    private int[][] checkpoints;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the class.
     */
    public Map(){
        grids = new int [MAP_HEIGHT][MAP_WIDTH];
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters and setters

    /**
     * Gets map's width.
     * @return map's width
     */
    public int getMapWidth() {
        return MAP_WIDTH;
    }

    /**
     * Gets the map's height.
     * @return map's height
     */
    public  int getMapHeight() {
        return MAP_HEIGHT;
    }

    /**
     * Gets the map's check points. Helps the enemies not to go out of their paths.
     * @return map's check points
     */
    public int[][] getCheckpoints() {
        return checkpoints;
    }

    /**
     * Gets the map's pixel size.
     * @return map's pixel size
     */
    public int getPixelSize(){
        return PIXEL_SIZE;
    }

    /**
     * Gets the map's pixel size.
     * @return map's pixel size
     */
    public int getPIXEL_SIZE() {
        return PIXEL_SIZE;
    }

    /**
     * Gets the map's grids.
     * @return map's grids
     */
    public int[][] getGrids() {
        return grids;
    }

    /**
     * Sets the map's grids.
     * @param grids - map's grids
     */
    public void setGrids(int[][] grids) {
        this.grids = grids;
    }

    /**
     * Gets the map's grid at the selected coordinates.
     * @param x - coordinate for width
     * @param y - coordinate for height
     * @return - map's grids at (x,y)
     */
    public int getGridsAt(int x, int y){
        try {
            return this.grids[x][y];
        } catch(Exception e){

        }
        return -1;
    }

    /**
     * Gets map's path tiles pixel.
     * @return map's path tiles pixel
     */
    public int[][] getPathTilesPixel() {
        return pathTilesPixel;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Converts an index to pixels.
     * @param index
     * @return index converted to pixels
     */
    public int convertToPixel(int index){
        if(index == 0)
            return index;
        else
            return index*PIXEL_SIZE + PIXEL_SIZE/2;
    }

    /**
     * Loads the selected level's map.
     * @param levelPath - path of the level's map selected
     * @throws IOException
     */
    public void loadMapFromFile(String levelPath) throws IOException {
        int cnt = 0; int i=0;
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        String line;
        line = br.readLine();
        String token[] = line.split(" ", 2);
        pathLength = Integer.parseInt(token[0]);
        checkpointsLength = Integer.parseInt(token[1]);
        checkpoints = new int[checkpointsLength][2];
        pathTilesPixel = new int [pathLength][2];

        line = br.readLine();
        String token2[] = line.split(",", checkpointsLength);
        for(int k=0; k < checkpointsLength;k++) {
            checkpoints[k][0] = convertToPixel(Integer.parseInt(token2[k].split(" ",2)[0]));
            checkpoints[k][1] = convertToPixel(Integer.parseInt(token2[k].split(" ",2)[1]));
            //System.out.println(Integer.parseInt(token2[k].split(" ",2)[0]) + " " + Integer.parseInt(token2[k].split(" ",2)[1]));
        }

        int rowCount = 0;
        while ((line = br.readLine()) != null) {
            int columnCount = 0;
            for(char gridType : line.toCharArray()){
                 grids[rowCount][columnCount] = Character.getNumericValue(gridType);
                if(Character.getNumericValue(gridType) == 1){
                     pathTilesPixel[i][0] = columnCount*32 + 16;
                     pathTilesPixel[i][1] = rowCount*32 + 16;
                    //System.out.println(columnCount + " and " + rowCount);
                     i++;
                }

                columnCount++;
            }
           rowCount++;
        }
    }

    /**
     * toString.
     * @return string matrix info
     */
    public String toString(){
        String matrixInfo = "";
        for(int i=0; i<MAP_HEIGHT; i++){
            for(int j=0;j<MAP_WIDTH; j++){
                matrixInfo += grids[i][j]+ " ";
            }
            matrixInfo+= "\n";
        }
        return matrixInfo;
    }
}