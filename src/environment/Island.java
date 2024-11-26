package environment;

public class Island {
    private final Location[][] side;

    public Island(int height, int wight) {
        side = new Location[height][wight];
        for (int y = 0; y < side.length; y++) {
            for (int x = 0; x < side[y].length; x++) {
                side[y][x] = new Location();
            }
        }
    }

    public Location[][] getSide() {
        return side;
    }

}
