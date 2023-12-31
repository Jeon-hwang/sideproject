package sideproject.java.rpsmember;

public interface RPSOracleQuery {
	public static final String URL = "jdbc:oracle:thin:@172.16.3.12:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";

	public static final String TABLE_NAME = "RPS_MEMBER";
	public static final String TABLE_BNAME = "RPS_BOARD";
	public static final String TABLE_INAME = "RPS_ITEM";
	public static final String TABLE_INVNAME = "MY_INVENTORY";

	public static final String COL_MEMBER_NUMBER = "MEMBER_NUMBER";
	public static final String COL_MEMBER_ID = "MEMBER_ID";
	public static final String COL_MEMBER_PASSWORD = "MEMBER_PASSWORD";
	public static final String COL_MEMBER_NAME = "MEMBER_NAME";
	public static final String COL_EMAIL = "MEMBER_EMAIL";
	public static final String COL_POINT = "MEMBER_POINT";

	public static final String COL_BOARD_NUMBER = "BOARD_NUMBER";
	public static final String COL_WIN = "BOARD_WIN";
	public static final String COL_LOSE = "BOARD_LOSE";
	public static final String COL_DATE = "BOARD_DATE";

	public static final String COL_ITEM_ID = "ITEM_ID";
	public static final String COL_ITEM_NAME = "ITEM_NAME";

	public static final String COL_ITEM_COUNT = "ITEM_COUNT";
	public static final String COL_ITEM_DATE = "ITEM_DATE";

	public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " VALUES(RPS_SEQ.NEXTVAL, ?, ?, ?, ?, 1000)";

	public static final String SQL_LOGIN = "SELECT " + COL_MEMBER_PASSWORD + " FROM " + TABLE_NAME + " WHERE "
			+ COL_MEMBER_ID + " = ?";

	public static final String SQL_INFO = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_MEMBER_ID + " = ?";

	public static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET " + COL_MEMBER_PASSWORD + " = ?, "
			+ COL_MEMBER_NAME + " = ?, " + COL_EMAIL + " = ? " + "WHERE " + COL_MEMBER_NUMBER + " = ?";

	public static final String SQL_DELETE = "DELETE " + TABLE_NAME + " WHERE " + COL_MEMBER_NUMBER + " = ?";

	public static final String SQL_POINT_RANKING = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_POINT + " DESC";

	public static final String SQL_UPDATE_POINT = "UPDATE " + TABLE_NAME + " SET " + COL_POINT + " = ?" + " WHERE "
			+ COL_MEMBER_NUMBER + " = ?";

	public static final String SQL_BOARD_INSERT = "INSERT INTO " + TABLE_BNAME
			+ " VALUES(RPS_BOARD_SEQ.NEXTVAL, ?,?,?,CURRENT_TIMESTAMP)";

	public static final String SQL_BOARD_FIND = "SELECT * FROM(SELECT * FROM "
			+ TABLE_BNAME + " ORDER BY " + COL_BOARD_NUMBER + " DESC) WHERE " + COL_MEMBER_ID + " = ? AND ROWNUM = 1";

	public static final String SQL_USED_DIAMOND_BOARD = "UPDATE "+TABLE_BNAME+" SET "+COL_WIN +" = ?, "+COL_LOSE+" = ? WHERE "+COL_BOARD_NUMBER+" = ?";
	
	public static final String SQL_BOARD_LEADER_BOARD = "SELECT * FROM (SELECT * FROM " + TABLE_BNAME + " ORDER BY "
			+ COL_DATE + " DESC) WHERE " + COL_MEMBER_ID + " = ? AND ROWNUM <= 10";

	public static final String SQL_WINNING_RANKING = "SELECT " + COL_MEMBER_ID + ", " + COL_WIN + ", " + COL_LOSE
			+ " FROM (SELECT " + COL_MEMBER_ID + ", " + COL_WIN + ", " + COL_LOSE + ", "
			+ "ROW_NUMBER() OVER (PARTITION BY " + COL_MEMBER_ID + " ORDER BY " + COL_DATE + " DESC) AS rn" + " FROM "
			+ TABLE_BNAME + " ORDER BY " + COL_WIN + " DESC)subquery WHERE rn = 1";

	public static final String SQL_ITEM_INFO = "SELECT * FROM " + TABLE_INAME + " WHERE " + COL_ITEM_ID + " = ?";

	public static final String SQL_ITEM_LIST = "SELECT * FROM " + TABLE_INAME + " ORDER BY "+COL_ITEM_ID;
	
	public static final String SQL_ITEM_BUY = "INSERT INTO " + TABLE_INVNAME
			+ " VALUES (INVENTORY_SEQ.NEXTVAL, ?, ?, ?, CURRENT_TIMESTAMP)";

	public static final String SQL_ITEM_COUNT = "SELECT " + COL_ITEM_COUNT + " FROM(SELECT * FROM " + TABLE_INVNAME
			+ " ORDER BY " + COL_ITEM_DATE + " DESC) WHERE " + COL_MEMBER_ID + " = ? AND " + COL_ITEM_NAME
			+ " = ? AND ROWNUM = 1";

	public static final String SQL_MY_INVENTORY = "SELECT " + COL_ITEM_NAME + ", " + COL_ITEM_COUNT + " FROM(SELECT "
			+ COL_MEMBER_ID + ", " + COL_ITEM_NAME + ", " + COL_ITEM_COUNT + ", ROW_NUMBER() OVER (PARTITION BY "
			+ COL_ITEM_NAME + " ORDER BY " + COL_ITEM_DATE + " DESC) AS rn" + " FROM " + TABLE_INVNAME + " WHERE "
			+ COL_MEMBER_ID + " = ? ORDER BY " + COL_ITEM_NAME + ", " + COL_ITEM_DATE + ") WHERE rn = 1";

}
