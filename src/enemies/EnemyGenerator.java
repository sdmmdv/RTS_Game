package enemies;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Enemy Generator:
 *  Class used to generate the enemies and add it to the game with their default values.
 */
public class EnemyGenerator {

    public int streamLength;
    public int streamWidth;
    private int waveLevel;
    private int[][] checkpoints;
    private Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    int[][] enemyStream;

    /**
     * Reads the String with the info of the path the enemies should follow.
     * @param streamPath - String with path info
     * @throws IOException
     */
    public void loadStreamFromFile(String streamPath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(streamPath));
        String line;
        String streamToken[];
        line = br.readLine();
        streamLength = Integer.parseInt(line.split(" ", 2)[0]);
        streamWidth = Integer.parseInt(line.split(" ", 2)[1]);
        enemyStream = new int[streamLength][streamWidth];
        int i = 0,j;

        while ((line = br.readLine()) != null) {    //file reading
                streamToken = line.split(" ",streamWidth);
                j = 0;
                for (String str : streamToken) {
                    enemyStream[i][j] = Integer.parseInt(str);
                    j++;
                }
                i++;
        }
    }

    public void setFlow (int[][] checkpoints, int waveLevel) {
        this.checkpoints = checkpoints;
        this.waveLevel = waveLevel-1;
    }

    /**
     * Fetch enemies from stream levels into queue.
     */
    public void createEnemyQueue(){

         for(int i=0; i<streamWidth; i++) {

               for(int j = 0; j < enemyStream[waveLevel][i]; j++){

                    Enemy enemy = addEnemy(i);
                    enemyQueue.add(enemy);
               }
         }
    }

    /**
     * Shuffles the enemies on the Enemy Queue.
     */
    public void shuffleEnemyQueue(){
        Collections.shuffle((LinkedList<Enemy>) enemyQueue);
    }

    /**
     * Creates an x enemy.
     * @param x - number/id of the enemy to create.
     * @return - Enemy created.
     */
    public Enemy addEnemy(int x){
        Enemy enemy;
        switch(x) {
            case 0:
                enemy = new Goblin(checkpoints);
                break;
            case 1:
                enemy = new HyperKid(checkpoints);
                break;
            case 2:
                enemy = new NutCracker(checkpoints);
                break;
            case 3:
                enemy = new MadKnight(checkpoints);
                break;
            case 4:
                enemy = new BioStrong(checkpoints);
                break;
            default:
                enemy = null;
        }

        return enemy;
    }

    public int getWaveLevel() {
        return waveLevel;
    }

    public int[][] getCheckpoints() {
        return checkpoints;
    }

    public Queue<Enemy> getEnemyQueue() {
        return enemyQueue;
    }

    public int[][] getEnemyStream() {
        return enemyStream;
    }
}
