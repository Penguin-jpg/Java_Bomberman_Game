package Entity.Static;

import Graphics.AssetManager;
import Item.Item;
import Texture.Tile;
import Utility.Handler;

import java.awt.*;
import java.util.Random;

public class BreakableBox extends StaticEntity {
    public BreakableBox(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        //設定bounding box
        boundingRect.x = 0;
        boundingRect.y = 0;
        boundingRect.width = Tile.TILE_WIDTH;
        boundingRect.height = Tile.TILE_HEIGHT;
    }

    @Override
    public void tick() {
        checkCollisionWithExplosion();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(AssetManager.breakableBox, (int) position.x, (int) position.y, width, height, null);
        /*graphics.setColor(Color.GREEN);
        graphics.fillRect((int) position.x, (int) position.y, boundingRect.width, boundingRect.height);*/
    }

    @Override
    public void onDestroy() {
        Random random = new Random();
        int drop = random.nextInt(12);

        if (drop >= 6 && drop < 8) {
            handler.getItemManager()
                    .addItem(Item.powerUpItem.createItem((int) position.x, (int) position.y));
        } else if (drop >= 8 && drop < 10) {
            handler.getItemManager()
                    .addItem(Item.speedUpItem.createItem((int) position.x, (int) position.y));
        } else if (drop >= 10 && drop < 11) {
            handler.getItemManager()
                    .addItem(Item.ammoUpItem.createItem((int) position.x, (int) position.y));
        } else if (drop == 12) {
            handler.getItemManager()
                    .addItem(Item.pierceItem.createItem((int) position.x, (int) position.y));
        }
    }

    @Override
    public boolean isBreakable() {
        return true;
    }
}
