package org.empathy.soulsplit.woodcutting;

import org.soulsplit.api.wrappers.Tile;

public enum TreeData {

	REG(1276, 1512, new Tile[] { new Tile(2789, 3433), new Tile(2779, 3440) }, "normal" ), 
	OAK(1281, 1522, new Tile[] { new Tile(2789, 3433) }, "oak"), 
	WILLOW(1308, 1520, new Tile[] { new Tile(2789, 3433),  new Tile(2784, 3428) }, "willow" ),
	MAPLE(1307, 1518, new Tile[] { new Tile(2751, 3467) }, "maple" ),
	YEW(1309, 1516, new Tile[] { new Tile(2789, 3433), new Tile(2776, 3434), new Tile(2763, 3430), new Tile(2758, 3431) }, "yew" ),
	MAGIC(1306, 1514, new Tile[] { new Tile(2328, 3830) }, "magic" );

	private int treeId;
	private int logId;
	private Tile[] walkTile;
	private String name;

	TreeData(int treeId, int logId, Tile[] walkTo, String name) {
		this.treeId = treeId;
		this.logId = logId;
		this.walkTile = walkTo;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int getTreeId() {
		return treeId;
	}

	public Tile[] getWalkTiles() {
		return walkTile;
	}
	
	public int getLogId() {
		return logId;
	}
}