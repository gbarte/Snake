package world.customgamemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import utils.TileType;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class CustomGameMapLoader {

    private static Json json = new Json();
    private static final int SIZE = 50;

    /**
     * Generate a custom game map using without specifying the maximum size of the map.
     * @param id The id of the map to generate.
     * @param name The name of the map to generate.
     * @return The generated map of 100 by 100 tiles.
     */
    public static CustomGameMapData generateRandomMap(String id, String name) {
        return generateRandomMap(id, name, SIZE);
    }

    /**
     * Generate a custom game map using by specifying the maximum size of the map.
     * @param id The id of the map to generate.
     * @param name The name of the map to generate.
     * @param maxEdge The maximum size of the map's edge, in the amount of tiles on each edge.
     * @return The generated map of given edge size.
     */
    public static CustomGameMapData generateRandomMap(String id, String name, int maxEdge) {
        if (maxEdge > SIZE) {
            maxEdge = SIZE;
        }
        CustomGameMapData mapData = new CustomGameMapData();
        mapData.id = id;
        mapData.name = name;
        mapData.map = new int[2][maxEdge][maxEdge]; //change the dimensions of the layer!!!if needed

        for (int row = 0; row < maxEdge; row++) {
            for (int col = 0; col < maxEdge; col++) {
                if ((col <= 1 || col > mapData.map[0][row].length - 3)
                        || (row <= 1 || row > mapData.map[0].length - 3)) {
                    mapData.map[0][row][col] = TileType.DARKBLUEWALL.getId();
                } else {
                    mapData.map[0][row][col] = TileType.WHITETILE.getId();
                }
            }
        }

        return mapData;
    }

    /**
     * Use this to load in a map.
     * If the map already exists then it'll be loaded from the existing files in the maps folder.
     * Otherwise it'll be created and saved.
     * @param id The id of the map.
     * @param name The name of the map.
     * @return A custom game map's data.
     */
    public static CustomGameMapData loadMap(String id, String name) {
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/" + id + ".map");
        if (file.exists()) {
            CustomGameMapData data = json.fromJson(CustomGameMapData.class, file.readString());
            return data;
        } else {
            CustomGameMapData data = generateRandomMap(id, name);
            saveMap(data.id, data.name, data.map);
            return data;
        }
    }

    private static void saveMap(String id, String name, int[][][] map) {
        CustomGameMapData data = new CustomGameMapData();
        data.id = id;
        data.name = name;
        data.map = map;

        //if the folder doesn't exist, u create it
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/" + id + ".map");
        file.writeString(json.prettyPrint(data), false); //false cz u wanna overwrite ipv append
    }
}
