package sideproject.java.rpsmember;

public interface RPSOracleQuery {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	
	public static final String TABLE_NAME = "RPS_MEMBER";
	
	public static final String COL_MEMBER_NUMBER = "MEMBER_NUMBER";
	public static final String COL_MEMBER_ID = "MEMBER_ID";
	public static final String COL_MEMBER_PASSWORD = "MEMBER_PASSWORD";
	public static final String COL_MEMBER_NAME = "MEMBER_NAME";
	public static final String COL_EMAIL = "MEMBER_EMAIL";
	public static final String COL_POINT = "MEMBER_POINT";
	
	public static final String SQL_INSERT = "INSERT INTO "+ TABLE_NAME+
											" VALUES(RPS_SEQ.NEXTVAL, ?, ?, ?, ?, 1000)";
	
	public static final String SQL_LOGIN = "SELECT "+COL_MEMBER_PASSWORD+" FROM "+ TABLE_NAME
											+" WHERE "+ COL_MEMBER_ID +" = ?";
	
	public static final String SQL_INFO = "SELECT * FROM "+ TABLE_NAME +" WHERE "+ COL_MEMBER_ID + " = ?";
	
	public static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET " + COL_MEMBER_PASSWORD + " = ?, "
											+ COL_MEMBER_NAME + " = ?, " + COL_EMAIL + " = ? " + "WHERE " + COL_MEMBER_NUMBER + " = ?";
	
	public static final String SQL_DELETE = "DELETE "+ TABLE_NAME + " WHERE " + COL_MEMBER_NUMBER + " = ?";
	
	public static final String SQL_POINT_RANKING = "SELECT * FROM "+ TABLE_NAME + " ORDER BY "+ COL_POINT +" DESC";
	
	public static final String SQL_UPDATE_POINT = "UPDATE " +TABLE_NAME+" SET " + COL_POINT+ " = ?"+" WHERE "+ COL_MEMBER_NUMBER + " = ?";
}
