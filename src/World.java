import java.util.Observable;
import java.util.Random;

public class World extends Observable {

    private int tick;
    private int size;

    private Player player;
    private Thread thread;
    private boolean notOver;
    private long delayed = 500;
    private int enemyCount = 10;

    private Enemy [] enemies;

    public World(int size) {
        this.size = size;
        tick = 0;
        player = new Player(size/2, size/2);
        enemies = new Enemy[enemyCount];
        Random random = new Random();
        for(int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(random.nextInt(size), random.nextInt(size));
        }
    }

    public void start() {
        player.reset();
        player.setPosition(size/2, size/2);
        tick = 0;
        notOver = true;
        thread = new Thread() {
            @Override
            public void run() {
                while(notOver) {
                    tick++;
                    player.move();
                    checkCollisions();
                    setChanged();
                    notifyObservers();
                    waitFor(delayed);
                }
            }
        };
        thread.start();
    }

    private void checkCollisions() {
        for(Enemy e : enemies) {
            if(e.hit(player)) {
                notOver = false;
            }
        }
    }

    private void waitFor(long delayed) {
        try {
            Thread.sleep(delayed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTick() {
        return tick;
    }

    public int getSize() {
        return size;
    }

    public Player getPlayer() {
        return player;
    }

    public void turnPlayerNorth() {
        player.turnNorth();
    }

    public void turnPlayerSouth() {
        player.turnSouth();
    }

    public void turnPlayerWest() {
        player.turnWest();
    }

    public void turnPlayerEast() {
        player.turnEast();
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public boolean isGameOver() {
        return !notOver;
    }
}
