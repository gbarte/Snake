package utils;

import java.util.HashMap;

public enum TileType {

    BLACKTILE(1, "blackTile", true),
    GRAYTILE(2, "grayTile", false),
    DARKBLUEWALL(3, "darkBlueWall", true),
    BLUEWALL(4, "blueWall", true),
    WHITETILE(5, "whiteTile", false);

    public static final int TILE_SIZE = 16;

    private int id;
    private String name;
    private boolean collidable;
    private int damage;

    /**
     * Default constructor for the tile type.
     * @param id The id of the tile.
     * @param name The name of the tile.
     * @param collidable Whether or not the tile is collidable.
     */
    TileType(int id, String name, boolean collidable) {
        this(id, name, collidable, 0);
    }

    /**
     * Constructor that gets called from the default for tile type that has a damage.
     * @param id The id of the tile.
     * @param name The name of the tile.
     * @param collidable Whether or not the tile is collidable.
     * @param damage The amount of damage this tile does, can be negative or positive integer.
     */
    TileType(int id, String name, boolean collidable, int damage) {
        //damage can be point boost or actual damage
        this.id = id;
        this.name = name;
        this.collidable = collidable;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public int getDamage() {
        return damage;
    }

    private static HashMap<Integer, TileType> tileMap;

    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType t : TileType.values()) {
            tileMap.put(t.getId(), t);
        }
    }

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }
}
